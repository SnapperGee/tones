package sogott.beep;

import java.util.NoSuchElementException;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import static java.util.Arrays.stream;

enum CliOption {
    BPM(Option.builder("b")
            .argName("INTEGER")
            .longOpt("bpm")
            .hasArg()
            .desc("Set speed of playback to INTEGER beats per minute (defaults to 140).")
            .converter(Integer::parseInt)
            .build()),
    NOTE_BEAT_VALUE(Option.builder("n")
            .argName("INTEGER")
            .longOpt("note-beat-value")
            .hasArg()
            .desc("Set the note value of a beat (defaults to 4).")
            .build()),
    WAVE(Option.builder("w")
            .argName("WAVE")
            .longOpt("wave")
            .hasArg()
            .converter(aString -> stream(Wave.values())
                    .filter(wave -> wave.stringValueAliases().stream()
                            .anyMatch(waveStringValue -> aString
                                    .equalsIgnoreCase(waveStringValue)))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException(
                            "Invalid WAVE command line option argument.")))
            .desc("Set default wave shape to WAVE (defaults to SIN).")
            .build()),
    VERSION(Option.builder("v")
            .longOpt("version")
            .desc("Prints currently installed version (ignores all other arguments except the help option).")
            .build()),
    HELP(Option.builder("h")
            .longOpt("help")
            .desc("Ignores all other arguments and prints this help message.")
            .build());

    private final static CommandLineParser DEFAULT_PARSER = new DefaultParser(false);

    private final static String CMD_LINE_SYNTAX = "Usage: beep [--%s|-%s %s] [--%s|-%s %s] [--%s|-%s] [--%s|-%s %s] [--%s|-%s] [WAVE>]NOTE.INTEGER..."
            .formatted(CliOption.BPM.value().getLongOpt(),
                    CliOption.BPM.value().getOpt(),
                    CliOption.BPM.value().getArgName(),
                    CliOption.NOTE_BEAT_VALUE.value().getLongOpt(),
                    CliOption.NOTE_BEAT_VALUE.value().getOpt(),
                    CliOption.NOTE_BEAT_VALUE.value().getArgName(),
                    CliOption.VERSION.value().getLongOpt(),
                    CliOption.VERSION.value().getOpt(),
                    CliOption.WAVE.value().getLongOpt(),
                    CliOption.WAVE.value().getOpt(),
                    CliOption.WAVE.value().getArgName(),
                    CliOption.HELP.value().getLongOpt(),
                    CliOption.HELP.value().getOpt())
            + "\nPlay musical note based beeps.\nExample: beep C4.4 D4.4 E-4.8 D4.8";

    private final static Options ALL_OPTIONS = stream(CliOption.values()).reduce(
            new Options(),
            (options, option) -> options.addOption(option.value()),
            (options1, options2) -> options1.addOptions(options2));

    final static CommandLine parse(String[] args) throws ParseException {
        return DEFAULT_PARSER.parse(ALL_OPTIONS, args);
    }

    final static void printHelp() {
        new HelpFormatter().printHelp(
                CliOption.CMD_LINE_SYNTAX,
                ALL_OPTIONS);
        ;
    }

    final private Option _value;

    private CliOption(Option value) {
        this._value = value;
    }

    Option value() {
        return this._value;
    }
}
