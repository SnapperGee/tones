package sogott.tones;

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
            .desc("Set speed of audio to INTEGER beats per minute (defaults to 140).")
            .type(Integer.class)
            .converter(arg -> {
                if (arg.isBlank() || arg.codePoints().anyMatch(cp -> !Character.isDigit(cp))) {
                    throw new IllegalArgumentException(
                            "BPM requires positive (greater than 0) integer but got: \"%s\""
                                    .formatted(arg));
                }

                return Integer.parseInt(arg);
            })
            .build()),

    /**
     * Contains the {@link Option} for setting the beat value of a note. The
     * easiest way to think of this value is the bottom value of a time
     * signature. So if there's a time signature of
     * <sup>3</sup>&frasl;<sub>4</sub>, then {@code 4} is the beat value of a
     * note. This affects how the duration is applied to each note. A positive
     * integer (greater than 0) is expected.
     */
    NOTE_BEAT_VALUE(Option.builder("n")
            .argName("INTEGER")
            .longOpt("note-beat-value")
            .hasArg()
            .desc("Set the note value of a beat (defaults to 4)."
                    + " The easiest way to think of this value is the bottom value of a time signature."
                    + " So if there's a time signature of 3/4, then `4` is the beat value of a note.")
            .type(Integer.class)
            .converter(intString -> {
                if (intString.isBlank() || intString.codePoints().anyMatch(cp -> !Character.isDigit(cp))) {
                    throw new IllegalArgumentException(
                            "Note beat value requires positive (greater than 0) integer but got: \"%s\""
                                    .formatted(intString));
                }

                return Integer.parseInt(intString);
            })
            .build()),

    /**
     * Contains the {@link Option} for setting the default wave shape. It
     * expects a valid wave {@code String} value defined in the {@link WaveShape}
     * enum values. This is the wave shape of a note if it's omitted. Defaults
     * to a SINE wave if not specified.
     *
     * @see WaveShape
     */
    WAVE(Option.builder("w")
            .argName("WAVE")
            .longOpt("wave")
            .hasArg()
            .type(WaveShape.class)
            .converter(waveShapeString -> WaveShape.parse(waveShapeString).orElseThrow(() -> new IllegalArgumentException(
                    "Invalid WAVE command line option argument. Wave shape String expected: \"%s\"".formatted(waveShapeString))))
            .desc("Set default wave shape to WAVE (defaults to SIN).")
            .build()),

    /**
     * Contains the {@link Option} for setting the musical scale of audio
     * strings formatted with an index int for the pitch. It expects a valid
     * scale {@code String} value defined as 1 of the {@link ScalePitchClasses}
     * enum values.
     *
     * @see ScalePitchClasses
     */
    SCALE(Option.builder("s")
            .argName("SCALE")
            .longOpt("scale")
            .hasArg()
            .type(ScalePitchClasses.class)
            .converter(scaleString ->
                stream(ScalePitchClasses.values())
                    .filter(scaleName -> scaleString.equalsIgnoreCase(scaleName.name()))
                    .findFirst()
                    .orElseThrow(() ->
                        new IllegalArgumentException(
                        "Invalid SCALE command line option argument. Scale name String expected: \"%s\"".formatted(scaleString))))
            .desc("Set scale to SCALE (defaults to MINOR).")
            .build()),

    /**
     * Contains the {@link Option} for setting the root pitch of the  scale used
     * for audio strings formatted with an index int for the pitch. It expects a
     * valid {@link Pitch} {@code String} value.
     *
     * @see Pitch
     */
    ROOT(Option.builder("r")
        .argName("PITCH")
        .longOpt("root")
        .hasArg()
        .type(Pitch.class)
        .converter(pitchString -> Pitch.parse(pitchString).orElseThrow(() -> new IllegalArgumentException(
                "Invalid ROOT command line option argument. Pitch String expected: \"%s\"".formatted(pitchString))))
        .desc("Set scale root pitch to PITCH (defaults to A4).")
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
            .type(Path.class)
            .converter(pathString -> {
                final Path filePathArg = Path.of(pathString);

                final Path outputFilePath = filePathArg.isAbsolute() ? filePathArg.normalize()
                        : Path.of(Path.of("").toString(), filePathArg.toString())
                                .toAbsolutePath().normalize();

                if (Files.exists(outputFilePath)) {
                    throw new FileAlreadyExistsException(
                            "Output path already exists: \"%s\""
                                    .formatted(outputFilePath.toString()));
                }
                return outputFilePath.getFileName().toString().contains(".") ? outputFilePath
                        : Path.of(outputFilePath + ".wav");
            })
            .desc("Outputs the generated audio to a 44.1khz/16 bit wav file at PATH.")
            .build()),

    /**
     * Contains the {@link Option} for preventing output from being audibly played.
     */
    QUIET(Option.builder("q")
            .longOpt("quiet")
            .desc("Prevents output from being audibly played.")
            .numberOfArgs(0)
            .build()),

    /**
     * Contains the {@link Option} for printing the version of this cli
     * application to stdout.
     */
    VERSION(Option.builder("v")
            .longOpt("version")
            .desc("Prints currently installed version (ignores all other arguments except the help option).")
            .numberOfArgs(0)
            .build()),

    /**
     * Contains the {@link Option} for printing a help message containing
     * information about how to use this cli application and it's various
     * options/flags.
     */
    HELP(Option.builder("h")
            .longOpt("help")
            .desc("Ignores all other arguments and prints this help message.")
            .numberOfArgs(0)
            .build());

    private static final CommandLineParser DEFAULT_PARSER = new DefaultParser(false);

    private static final String CMD_LINE_SYNTAX = "tones %s %s %s %s %s %s %s %s %s [%s>]PITCH.INTEGER..."
            .formatted(CliOption.BPM.usageString(),
                    CliOption.NOTE_BEAT_VALUE.usageString(),
                    CliOption.VERSION.usageString(),
                    CliOption.WAVE.usageString(),
                    CliOption.HELP.usageString(),
                    CliOption.OUT.usageString(),
                    CliOption.QUIET.usageString(),
                    CliOption.SCALE.usageString(),
                    CliOption.ROOT.usageString(),
                    CliOption.WAVE.value().getArgName())
            + "\nPlay musical note based tones.\nExample: beep C4.4 D4.4 E-4.8 D4.8";

    private static final Options ALL_OPTIONS = stream(CliOption.values()).reduce(
            new Options(),
            (options, option) -> options.addOption(option.value()),
            (someOptions, moreOptions) -> someOptions.addOptions(moreOptions));

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
    static final CommandLine parse(String[] args) throws ParseException {
        return DEFAULT_PARSER.parse(ALL_OPTIONS, args);
    }

    /**
     * Prints a help message consisting of usage instructions and information
     * about the options and flags to stdout.
     */
    static final void printHelp() {
        new HelpFormatter().printHelp(
                CliOption.CMD_LINE_SYNTAX,
                ALL_OPTIONS);
    }

    final private Option _value;
    final private String _usageString;

    private CliOption(Option value) {
        this._value = value;
        this._usageString = this._value.hasArgName()
            ? new StringBuilder("[--")
                .append(this._value.getLongOpt())
                .append("|-")
                .append(this._value.getOpt())
                .append('\u0020')
                .append(this._value.getArgName())
                .append(']')
                .toString()
            : new StringBuilder("[--")
                .append(this._value.getLongOpt())
                .append("|-")
                .append(this._value.getOpt())
                .append(']')
                .toString();
    }

    /**
     * Returns the {@link Option} object of this {@link CliOption} enum value.
     *
     * @return The {@link Option} object of this {@link CliOption} enum value.
     */
    Option value() {
        return this._value;
    }

    /**
     * Returns the {@code String} showing this {@link Option}'s usage suitable
     * for use in a usage/example help string.
     *
     * @return {@code String} showing this {@link Option}'s usage suitable
     *         for use in a usage/example help string.
     */
    String usageString() {
        return this._usageString;
    }
}
