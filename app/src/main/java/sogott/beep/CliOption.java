package sogott.beep;

import java.util.Arrays;
import java.util.NoSuchElementException;
import org.apache.commons.cli.Option;

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
                        .desc("Set time signature to INTEGER (defaults to 4).")
                        .build()),
        WAVE(Option.builder("w")
                        .argName("WAVE")
                        .longOpt("wave")
                        .hasArg()
                        .converter(aString -> Arrays.stream(Wave.values())
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

        final private Option _value;

        private CliOption(Option value) {
                this._value = value;
        }

        Option value() {
                return this._value;
        }
}
