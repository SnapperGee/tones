package sogott.beep;

import java.util.List;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import static java.util.Collections.unmodifiableList;

final public class Main {
        public static void main(String[] args) {
                final Options options = new Options()
                                .addOption(CliOption.BPM.value())
                                .addOption(CliOption.NOTE_BEAT_VALUE.value())
                                .addOption(CliOption.WAVE.value())
                                .addOption(CliOption.VERSION.value())
                                .addOption(CliOption.HELP.value());

                final CommandLineParser cliParser = new DefaultParser(false);

                try {
                        final CommandLine cliArgs = cliParser.parse(options, args);

                        if (cliArgs.hasOption(CliOption.HELP.value())) {
                                final HelpFormatter helpFormatter = new HelpFormatter();

                                helpFormatter.printHelp(
                                                CliOption.CMD_LINE_SYNTAX,
                                                options);
                        } else if (cliArgs.hasOption(CliOption.VERSION.value())) {
                                System.out.println("beep 0.0.1");
                        } else {
                                final int bpm = cliArgs.getParsedOptionValue(CliOption.BPM.value(), Default.BPM);

                                final int noteBeatValue = cliArgs.getParsedOptionValue(
                                                CliOption.NOTE_BEAT_VALUE.value(),
                                                Default.NOTE_BEAT_VALUE);

                                final Wave wave = cliArgs.getParsedOptionValue(CliOption.WAVE.value(), Default.WAVE);

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
                                        System.exit(222);
                                }

                                final byte[][] audioBuffers = validAndInvalidOperands.valid().stream()
                                                .map(audio -> audio.wave() != null
                                                                ? audio.wave()
                                                                                .generate(Frequency.from(audio.pitch()),
                                                                                                (int) Math.round(1.0
                                                                                                                / audio.duration()
                                                                                                                * wholeNoteDuration))
                                                                : GenerateWave.silence(
                                                                                (int) Math.round(1.0 / audio.duration()
                                                                                                * wholeNoteDuration),
                                                                                Default.SAMPLE_RATE))
                                                .toArray(byte[][]::new);

                                final AudioFormat audioFormat = new AudioFormat(Default.SAMPLE_RATE,
                                                Default.SAMPLE_SIZE,
                                                Default.CHANNELS, Default.SIGNED, Default.BIG_ENDIAN);

                                final SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);

                                line.open(audioFormat);
                                line.start();

                                for (final byte[] buffer : audioBuffers) {
                                        final int soundBytes = ((int) (Default.SILENCE_RATIO * buffer.length))
                                                        / audioFormat.getFrameSize()
                                                        * audioFormat.getFrameSize();
                                        line.write(buffer, 0, soundBytes);
                                        final byte[] silenceBuffer = new byte[(buffer.length - soundBytes)
                                                        / audioFormat.getFrameSize()
                                                        * audioFormat.getFrameSize()];
                                        line.write(silenceBuffer, 0, silenceBuffer.length);
                                }

                                line.drain();
                                line.stop();
                                line.close();
                        }
                } catch (ParseException e) {
                        e.printStackTrace();
                        System.err.format("\n%s", e.getMessage());
                } catch (LineUnavailableException e) {
                        e.printStackTrace();
                        System.err.format("\n%s", e.getMessage());
                }
        }
}
