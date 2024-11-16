package sogott.tones;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static java.util.Collections.unmodifiableList;

import java.nio.file.Path;

/**
 * Command line processing takes place here and an operation dependent on the
 * parsed command line arguments is executed. If invalid command line arguments
 * are passed, an exception stacktrace and message is printed to stderr and
 * the application is exited.
 *
 * @see CliOption
 * @see OutputByteBuffers
 * @see ByteBuffers
 */
final public class Main {

    private final static int BPM = 140;
    private final static int NOTE_BEAT_VALUE = 4;
    private final static Wave WAVE = Wave.SINE;

    /**
     * The entry point of this application where command line processing takes
     * place and an operation dependent on the command line arguments is
     * executed. If invalid command line arguments are passed, an exception is
     * thrown.
     *
     * @param args The passed command line argument strings.
     */
    public static void main(String[] args) {

        try {
            final CommandLine cliArgs = CliOption.parse(args);

            if (cliArgs.hasOption(CliOption.HELP.value())) {
                CliOption.printHelp();
            } else if (cliArgs.hasOption(CliOption.VERSION.value())) {
                System.out.println("beep 0.0.1");
            } else if (cliArgs.hasOption(CliOption.QUIET.value())
                    && !cliArgs.hasOption(CliOption.OUT.value())) {
                System.out.println(
                        "No audio output specified (audible playback disabled and no file output selected). Exiting...");
            } else {
                final int bpm = cliArgs.getParsedOptionValue(CliOption.BPM.value(), BPM);

                final int noteBeatValue = cliArgs.getParsedOptionValue(
                        CliOption.NOTE_BEAT_VALUE.value(),
                        NOTE_BEAT_VALUE);

                final Wave wave = cliArgs.getParsedOptionValue(CliOption.WAVE.value(), WAVE);

                final double beatDuration = 60000.0 / bpm;

                final double wholeNoteDuration = beatDuration * noteBeatValue;

                final List<String> operands = unmodifiableList(cliArgs.getArgList());

                final record ValidAndInvalidOperands(List<Audio> valid, List<String> invalid) {
                }

                final ValidAndInvalidOperands validAndInvalidOperands = operands.stream().reduce(
                        new ValidAndInvalidOperands(new ArrayList<Audio>(),
                                new ArrayList<String>()),
                        (acc, operand) -> {

                            AudioString.parse(operand, wave).ifPresentOrElse(
                                    parsedOperand -> acc.valid().add(parsedOperand),
                                    () -> acc.invalid().add(operand));

                            return acc;
                        },
                        (operands1, operands2) -> {
                            operands1.valid().addAll(operands2.valid());
                            operands1.invalid().addAll(operands2.invalid());
                            return operands1;
                        });

                if (!validAndInvalidOperands.invalid().isEmpty()) {
                    System.err.format("Illegal audio %s: [\"%s\"]",
                            validAndInvalidOperands.invalid().size() == 1 ? "argument"
                                    : "arguments",
                            String.join("\", \"", validAndInvalidOperands.invalid()));
                    System.exit(200);
                }

                final ByteBuffers byteBuffers = new ByteBuffers(validAndInvalidOperands.valid(), wholeNoteDuration);

                if (cliArgs.hasOption(CliOption.OUT.value())) {
                    final Path outputFilePath = cliArgs.getParsedOptionValue(CliOption.OUT.value());
                    final Path pathWrittenTo = OutputByteBuffers.toWavFile(byteBuffers, outputFilePath);
                    System.out.println("Audio file written to: \"%s\"".formatted(pathWrittenTo));
                }

                if (!cliArgs.hasOption(CliOption.QUIET.value())) {
                    final SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(ByteBuffers.AUDIO_FORMAT);
                    OutputByteBuffers.toAudio(byteBuffers, sourceDataLine);
                } else {
                    System.out.println("Silent option passed. Not audibly outputting audio...");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(100);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(311);
        }
    }

    private Main() {
        throw new UnsupportedOperationException(
                Main.class.getSimpleName() + " cannot be instantiated.");
    }
}
