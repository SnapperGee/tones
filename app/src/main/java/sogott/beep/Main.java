package sogott.beep;

import javax.sound.sampled.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

final public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        final Options options = new Options()
                .addOption(Cli.BPM.option())
                .addOption(Cli.TIME_SIGNATURE.option())
                .addOption(Cli.VERSION.option())
                .addOption(Cli.HELP.option());

        final CommandLineParser cliParser = new DefaultParser(false);

        try {
            final CommandLine cliArgs = cliParser.parse(options, args);

            if (cliArgs.hasOption(Cli.HELP.option())) {
                final HelpFormatter helpFormatter = new HelpFormatter();

                helpFormatter.printHelp(
                        "beep [--%s|-%s=%s] [--%s|-%s=%s] [--%s|-%s] [--%s|-%s]"
                                .formatted(Cli.BPM.option().getLongOpt(), Cli.BPM.option().getOpt(),
                                        Cli.BPM.option().getArgName(), Cli.TIME_SIGNATURE.option().getLongOpt(),
                                        Cli.TIME_SIGNATURE.option().getOpt(),
                                        Cli.TIME_SIGNATURE.option().getArgName(), Cli.VERSION.option().getLongOpt(),
                                        Cli.VERSION.option().getOpt(), Cli.HELP.option().getLongOpt(),
                                        Cli.HELP.option().getOpt()),
                        options);
            } else if (cliArgs.hasOption(Cli.VERSION.option())) {
                System.out.println("beep 0.0.1");
            } else {
                if (cliArgs.hasOption(Cli.BPM.option())) {
                    System.out.println("BPM arg passed: %d".formatted(cliArgs.getParsedOptionValue(Cli.BPM.option())));
                }

                if (cliArgs.hasOption(Cli.TIME_SIGNATURE.option())) {
                    System.out.println(
                            "TIME_SIGNATURE arg passed: %s"
                                    .formatted(cliArgs.getOptionValue(Cli.TIME_SIGNATURE.option())));
                }
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
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
