package sogott.beep;

import org.apache.commons.cli.Option;

enum Cli {
    BPM(Option.builder("b")
            .argName("INTEGER")
            .longOpt("bpm")
            .hasArg(true)
            .desc("Set speed of playback to INTEGER beats per minute (defaults to 140).")
            .converter(Integer::parseInt)
            .build()),
    TIME_SIGNATURE(Option.builder("t")
            .argName("INTEGER/INTEGER")
            .longOpt("time-signature")
            .hasArg(true)
            .desc("Set time signature to INTEGER/INTEGER (defaults to 4/4).")
            .build()),
    VERSION(Option.builder("v")
            .longOpt("version")
            .desc("Prints currently installed version (ignores all other arguments except the help option).")
            .build()),
    HELP(Option.builder("h")
            .longOpt("help")
            .desc("Ignores all other arguments and prints this help message.")
            .build());

    final private Option _option;

    private Cli(Option option) {
        this._option = option;
    }

    Option option() {
        return this._option;
    }
}
