package sogott.beep;

import org.apache.commons.cli.Option;

enum CliOption {
    BPM(Option.builder("b")
            .argName("INTEGER")
            .longOpt("bpm")
            .hasArg()
            .desc("Set speed of playback to INTEGER beats per minute (defaults to 140).")
            .converter(Integer::parseInt)
            .build()),
    TIME_SIGNATURE(Option.builder("t")
            .argName("INTEGER/INTEGER")
            .longOpt("time-signature")
            .hasArg()
            .desc("Set time signature to INTEGER/INTEGER (defaults to 4/4).")
            .build()),
    WAVE(Option.builder("w")
            .argName("WAVE_SHAPE")
            .longOpt("wave-shape")
            .hasArg()
            .desc("Set default wave shape to WAVE_SHAPE (defaults to SIN).")
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
