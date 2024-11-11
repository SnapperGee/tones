package sogott.beep;

import java.util.NoSuchElementException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileAlreadyExistsException;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

import static java.util.Arrays.stream;

/**
 * Enum of command line interface options/flags and static methods related to
 * them. These are built using the
 * <a href="https://commons.apache.org/proper/commons-cli/">Apache Commons
 * CLI</a>. Each enum {@link #value() value} consists of its {@link Option}.
 * This enum is used in {@link Main#main(String[])} entry point method to parse
 * the passed command line arguments.
 *
 * @see Option
 * @see Main
 */
enum CliOption {
    /**
     * Contains the {@link Option} for setting the beats per minute (bpm)/tempo
     * of the audio. It expects a positive integer value (greater than 0) and
     * defaults to {@code 140} if not specified.
     */
    BPM(Option.builder("b")
            .argName("INTEGER")
            .longOpt("bpm")
            .hasArg()
            .desc("Set speed of playback to INTEGER beats per minute (defaults to 140).")
            .converter(Integer::parseInt)
            .build()),

    /**
     * Contains the {@link Option} for setting the beat value of a note. The
     * easiest way to think of this value is the denominator of a time
     * signature. This affects how the duration is applied to each note.
     * A positive integer (greater than 0) is expected.
     */
    NOTE_BEAT_VALUE(Option.builder("n")
            .argName("INTEGER")
            .longOpt("note-beat-value")
            .hasArg()
            .desc("Set the note value of a beat (defaults to 4).")
            .build()),

    /**
     * Contains the {@link Option} for setting the default wave shape. It
     * expects a valid wave String value defined in the {@link Wave} enum
     * values. This is the wave shape of a note if it's omitted. Defaults to a
     * SIN wave if not specified.
     */
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

    /**
     * Contains the {@link Option} for writing audio to a WAV file. It expects a
     * path to write the file to and throws an error if the path points to a
     * pre-existing file or directory.
     */
    OUT(Option.builder("o")
            .argName("PATH")
            .longOpt("out")
            .hasArg()
            .converter(aString -> {
                final Path filePathArg = Path.of(aString);

                final Path outputFilePath = filePathArg.isAbsolute() ? filePathArg.normalize()
                        : Path.of(Path.of("").toString(), filePathArg.toString()).toAbsolutePath().normalize();

                if (Files.exists(outputFilePath)) {
                    throw new FileAlreadyExistsException(
                            "Output path already exists: \"%s\"".formatted(outputFilePath.toString()));
                }
                return outputFilePath;
            })
            .desc("Outputs the generated audio to a 44.1khz/16 bit wav file at PATH.")
            .build()),

    /**
     * Contains the {@link Option} for printing the version of this cli
     * application to stdout.
     */
    VERSION(Option.builder("v")
            .longOpt("version")
            .desc("Prints currently installed version (ignores all other arguments except the help option).")
            .build()),

    /**
     * Contains the {@link Option} for printing a help message containing
     * information about how to use this cli application and it's various
     * options/flags.
     */
    HELP(Option.builder("h")
            .longOpt("help")
            .desc("Ignores all other arguments and prints this help message.")
            .build());

    private final static CommandLineParser DEFAULT_PARSER = new DefaultParser(false);

    private final static String CMD_LINE_SYNTAX = "beep [--%s|-%s %s] [--%s|-%s %s] [--%s|-%s] [--%s|-%s %s] [--%s|-%s] [--%s|-%s %s] [%s>]NOTE.INTEGER..."
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
                    CliOption.HELP.value().getOpt(),
                    CliOption.OUT.value().getLongOpt(),
                    CliOption.OUT.value().getOpt(),
                    CliOption.OUT.value().getArgName(),
                    CliOption.WAVE.value().getArgName())
            + "\nPlay musical note based beeps.\nExample: beep C4.4 D4.4 E-4.8 D4.8";

    private final static Options ALL_OPTIONS = stream(CliOption.values()).reduce(
            new Options(),
            (options, option) -> options.addOption(option.value()),
            (options1, options2) -> options1.addOptions(options2));

    /**
     * The default parser used by this cli application to parse the command line
     * arguments in the {@link Main#main(String[])} entry point method.
     *
     * @param args The command line argument {@code String}s to parse.
     *
     * @return The resulting {@link CommandLine} object from parsing the passed
     *         command line arguments.
     *
     * @throws ParseException If an exception occurs during argument parsing.
     *
     * @see Main#main(String[])
     */
    final static CommandLine parse(String[] args) throws ParseException {
        return DEFAULT_PARSER.parse(ALL_OPTIONS, args);
    }

    /**
     * Prints a help message consisting of usage instructions and information
     * about the options and flags to stdout.
     */
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

    /**
     * Returns the {@link Option} object of this {@link CliOption} enum value.
     *
     * @return The {@link Option} object of this {@link CliOption} enum value.
     */
    Option value() {
        return this._value;
    }
}
