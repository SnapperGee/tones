package sogott.beep;

import java.util.List;
import java.util.ArrayList;
import javax.sound.sampled.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import static java.util.Collections.unmodifiableList;

final public class Main {
    private final static class Default {
        final static int BPM = 140;
        final static int NOTE_BEAT_VALUE = 4;
        final static Wave WAVE = Wave.SIN;
    }

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
                        "Usage: beep [--%s|-%s %s] [--%s|-%s %s] [--%s|-%s] [--%s|-%s %s] [--%s|-%s] [WAVE>]NOTE.INTEGER..."
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
                                + "\nPlay musical note based beeps.\nExample: beep C4.4 D4.4 E-4.8 D4.8",
                        options);
            } else if (cliArgs.hasOption(CliOption.VERSION.value())) {
                System.out.println("beep 0.0.1");
            } else {
                final int bpm = cliArgs.getParsedOptionValue(CliOption.BPM.value(), Default.BPM);

                final int noteBeatValue = cliArgs.getParsedOptionValue(CliOption.NOTE_BEAT_VALUE.value(),
                        Default.NOTE_BEAT_VALUE);

                final Wave wave = cliArgs.getParsedOptionValue(CliOption.WAVE.value(), Default.WAVE);

                final double beatDuration = 60000.0 / bpm;

                final double wholeNoteDuration = beatDuration * noteBeatValue;

                final List<String> operands = unmodifiableList(cliArgs.getArgList());

                final record ValidAndInvalidOperands(List<Audio> valid, List<String> invalid) {
                }

                final ValidAndInvalidOperands validAndInvalidOperands = operands.stream().reduce(
                        new ValidAndInvalidOperands(new ArrayList<Audio>(), new ArrayList<String>()),
                        (acc, operand) -> {

                            AudioString.parse(operand).ifPresentOrElse(
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
                    System.err.format("Illegal audio argument(s): %s", validAndInvalidOperands.invalid());
                    System.exit(222);
                }

                System.out.println(validAndInvalidOperands.valid());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.format("\n%s", e.getMessage());
        }

        // final float sampleRate = 44100;
        // final int sampleSizeInBits = 16;
        // final int channels = 1;
        // final boolean signed = true;
        // final boolean bigEndian = false;
        // final float frequency = 260;
        // final int duration = 750;

        // final AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
        // channels, signed, bigEndian);

        // // Obtain a line to play the sound
        // SourceDataLine line = AudioSystem.getSourceDataLine(format);
        // line.open(format);
        // line.start();

        // // Generate waveform data (e.g., sine wave)
        // byte[] buffer = Wave.sin(frequency, duration);

        // // Play the waveform
        // line.write(buffer, 0, buffer.length);
        // line.drain();
        // buffer = Wave.triangle(frequency, duration);
        // line.write(buffer, 0, buffer.length);
        // line.drain();
        // line.close();
    }
}
