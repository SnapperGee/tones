package sogott.beep;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import static java.util.Collections.unmodifiableList;

final public class Main {
    public static void main(String[] args) {

        try {
            final CommandLine cliArgs = CliOption.parse(args);

            if (cliArgs.hasOption(CliOption.HELP.value())) {
                CliOption.printHelp();
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

                final AudioCollection audioCollection = new AudioCollection(validAndInvalidOperands.valid());

                audioCollection.outputToAudio(wholeNoteDuration);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.format("\n%s", e.getMessage());
        }
    }
}
