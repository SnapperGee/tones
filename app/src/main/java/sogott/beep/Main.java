package sogott.beep;

import javax.sound.sampled.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

final public class Main {
    private final static class Default {
        final static int BPM = 140;
        final static int TIME_SIGNATURE_DENOMINATOR = 4;
        final static String WAVE = Wave.SIN.name();
    }

    public static void main(String[] args) throws LineUnavailableException {
        final Options options = new Options()
                .addOption(CliOption.BPM.value())
                .addOption(CliOption.TIME_SIGNATURE.value())
                .addOption(CliOption.WAVE.value())
                .addOption(CliOption.VERSION.value())
                .addOption(CliOption.HELP.value());

        final CommandLineParser cliParser = new DefaultParser(false);

        try {
            final CommandLine cliArgs = cliParser.parse(options, args);

            if (cliArgs.hasOption(CliOption.HELP.value())) {
                final HelpFormatter helpFormatter = new HelpFormatter();

                helpFormatter.printHelp(
                        "beep [--%s|-%s %s] [--%s|-%s %s] [--%s|-%s] [--%s|-%s] [WAVE>](NOTE.INTEGER|FLOAT)..."
                                .formatted(CliOption.BPM.value().getLongOpt(), CliOption.BPM.value().getOpt(),
                                        CliOption.BPM.value().getArgName(),
                                        CliOption.TIME_SIGNATURE.value().getLongOpt(),
                                        CliOption.TIME_SIGNATURE.value().getOpt(),
                                        CliOption.TIME_SIGNATURE.value().getArgName(),
                                        CliOption.VERSION.value().getLongOpt(),
                                        CliOption.VERSION.value().getOpt(), CliOption.HELP.value().getLongOpt(),
                                        CliOption.HELP.value().getOpt()),
                        options);
            } else if (cliArgs.hasOption(CliOption.VERSION.value())) {
                System.out.println("beep 0.0.1");
            } else {
                final int bpm = cliArgs.getParsedOptionValue(CliOption.BPM.value(), Default.BPM);

                final int timeSignatureDenominator = cliArgs.getParsedOptionValue(CliOption.TIME_SIGNATURE.value(),
                        Default.TIME_SIGNATURE_DENOMINATOR);

                final String wave = cliArgs.getOptionValue(CliOption.WAVE.value(), Default.WAVE);

                final double beatDuration = 60000.0 / bpm;

                final double wholeNoteDuration = beatDuration * timeSignatureDenominator;
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
