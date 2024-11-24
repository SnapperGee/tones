package sogott.tones;

import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.EmptySource;

import static java.util.Arrays.asList;
import static java.util.Objects.hash;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class PitchTestArgsProvider {
    final static List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static RandomGenerator random = RandomGenerator.getDefault();

    private static Stream<Arguments> pitchLetterAccidentalAndOctave(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> arguments(pitchLetter, accidental, random.nextInt(octaveOrigin, octaveBound))));
    }

    private static Stream<Arguments> differingPitchLetterAccidentalsAndOctaves(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final int indexOfPitchLetter = pitchLetters.indexOf(pitchLetter);
                    final int indexOfDifferentPitchLetter = random.ints(0, pitchLetters.size())
                            .filter(i -> i != indexOfPitchLetter).findFirst().orElseThrow();
                    final PitchLetter differentPitchLetter = pitchLetters.get(indexOfDifferentPitchLetter);
                    final Accidental differentAccidental = accidental == Accidental.SHARP ? Accidental.FLAT
                            : Accidental.SHARP;
                    final int randomInt = random.nextInt(octaveOrigin, octaveBound);
                    final int differentRandomInt = random.ints(octaveOrigin, octaveBound)
                            .filter(i -> i != randomInt).findFirst().orElseThrow();
                    return arguments(pitchLetter, differentPitchLetter, accidental, differentAccidental,
                            randomInt, differentRandomInt);
                }));
    }

    private static Stream<Arguments> differingPitchLetterWithAccidentalAndOctave(int octaveOrigin,
            int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final int indexOfPitchLetter = pitchLetters.indexOf(pitchLetter);
                    final int indexOfDifferentPitchLetter = random.ints(0, pitchLetters.size())
                            .filter(i -> i != indexOfPitchLetter).findFirst().orElseThrow();
                    final PitchLetter differentPitchLetter = pitchLetters.get(indexOfDifferentPitchLetter);
                    return arguments(pitchLetter, differentPitchLetter, accidental,
                            random.nextInt(octaveOrigin, octaveBound));
                }));
    }

    private static Stream<Arguments> pitchLetterDifferingAccidentalsAndOctave(int octaveOrigin,
            int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final Accidental differentAccidental = accidental == Accidental.SHARP ? Accidental.FLAT
                            : Accidental.SHARP;
                    return arguments(pitchLetter, accidental, differentAccidental,
                            random.nextInt(octaveOrigin, octaveBound));
                }));
    }

    private static Stream<Arguments> pitchClassAccidentalWithDifferingOctaves(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final int randomInt = random.nextInt(octaveOrigin, octaveBound);
                    final int differentRandomInt = random.ints(octaveOrigin, octaveBound)
                            .filter(i -> i != randomInt).findFirst().orElseThrow();
                    return arguments(pitchLetter, accidental, randomInt, differentRandomInt);
                }));
    }

    private static Stream<Arguments> pitchLetterAndOctave(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream()
                .map(pitchLetter -> arguments(pitchLetter, random.nextInt(octaveOrigin, octaveBound)));
    }

    final static class Valid {
        final static class PitchLetterAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterAccidentalAndOctave(0, 13);
            }
        }

        final static class PitchLetterAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterAndOctave(0, 13);
            }
        }

        final static class AccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Accidental.SHARP, random.nextInt(13)),
                        arguments(Accidental.NATURAL, random.nextInt(13)),
                        arguments(Accidental.FLAT, random.nextInt(13)));
            }
        }

        final static class PitchLetterAccidentalAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(13);
                    final String stringValueNoAccidental = "%c%d".formatted(pitchLetter.charValue(),
                            octave);

                    return Stream.concat(
                            Stream.of(arguments(pitchLetter, Accidental.NATURAL, octave, stringValueNoAccidental)),
                            accidentals.stream()
                                    .filter(accidental -> accidental != Accidental.NATURAL)
                                    .map(accidental -> {
                                        final String stringValueWithAccidental = "%c%c%d".formatted(
                                                pitchLetter.charValue(),
                                                accidental.charValue(),
                                                octave);
                                        return arguments(pitchLetter, accidental, octave, stringValueWithAccidental);
                                    }));
                });
            }
        }

        final static class PitchStringValues implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(13);
                    return accidentals.stream()
                            .map(accidental -> {
                                final String stringValueWithAccidental = accidental != Accidental.NATURAL
                                        ? "%c%c%d".formatted(
                                                pitchLetter.charValue(),
                                                accidental.charValue(),
                                                octave)
                                        : "%c%d".formatted(
                                                pitchLetter.charValue(),
                                                octave);
                                return arguments(stringValueWithAccidental);
                            });
                });
            }
        }

        final static class PitchLClassAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                        .map(accidental -> arguments(new PitchClass(pitchLetter, accidental), random.nextInt(0, 13))));
            }
        }

        final static class DifferingPitchLettersAccidentalsAndOctaves implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return differingPitchLetterAccidentalsAndOctaves(0, 12);
            }
        }

        final static class DifferingPitchLettersWithAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return differingPitchLetterWithAccidentalAndOctave(0, 12);
            }
        }

        final static class PitchLetterWithDifferingAccidentalsAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterDifferingAccidentalsAndOctave(0, 12);
            }
        }

        final static class PitchLetterAccidentalWithDifferingOctaves implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchClassAccidentalWithDifferingOctaves(0, 12);
            }
        }

        final static class PitchLetterAccidentalOctaveFrequency implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(PitchLetter.A, Accidental.FLAT, 0, 25.96),
                        arguments(PitchLetter.A, Accidental.NATURAL, 0, 27.5),
                        arguments(PitchLetter.A, Accidental.SHARP, 0, 29.14),
                        arguments(PitchLetter.A, Accidental.FLAT, 1, 51.91),
                        arguments(PitchLetter.A, Accidental.NATURAL, 1, 55),
                        arguments(PitchLetter.A, Accidental.SHARP, 1, 58.27),
                        arguments(PitchLetter.A, Accidental.FLAT, 2, 103.83),
                        arguments(PitchLetter.A, Accidental.NATURAL, 2, 110),
                        arguments(PitchLetter.A, Accidental.SHARP, 2, 116.54),
                        arguments(PitchLetter.A, Accidental.FLAT, 3, 207.65),
                        arguments(PitchLetter.A, Accidental.NATURAL, 3, 220),
                        arguments(PitchLetter.A, Accidental.SHARP, 3, 233.08),
                        arguments(PitchLetter.A, Accidental.FLAT, 4, 415.3),
                        arguments(PitchLetter.A, Accidental.NATURAL, 4, 440),
                        arguments(PitchLetter.A, Accidental.SHARP, 4, 466.16),
                        arguments(PitchLetter.A, Accidental.FLAT, 5, 830.61),
                        arguments(PitchLetter.A, Accidental.NATURAL, 5, 880),
                        arguments(PitchLetter.A, Accidental.SHARP, 5, 932.33),
                        arguments(PitchLetter.A, Accidental.FLAT, 6, 1661.22),
                        arguments(PitchLetter.A, Accidental.NATURAL, 6, 1760),
                        arguments(PitchLetter.A, Accidental.SHARP, 6, 1864.66),
                        arguments(PitchLetter.A, Accidental.FLAT, 7, 3322.44),
                        arguments(PitchLetter.A, Accidental.NATURAL, 7, 3520),
                        arguments(PitchLetter.A, Accidental.SHARP, 7, 3729.31),
                        arguments(PitchLetter.A, Accidental.FLAT, 8, 6644.88),
                        arguments(PitchLetter.A, Accidental.NATURAL, 8, 7040),
                        arguments(PitchLetter.A, Accidental.SHARP, 8, 7458.62),
                        arguments(PitchLetter.B, Accidental.FLAT, 0, 29.14),
                        arguments(PitchLetter.B, Accidental.NATURAL, 0, 30.87),
                        arguments(PitchLetter.B, Accidental.SHARP, 0, 32.7),
                        arguments(PitchLetter.B, Accidental.FLAT, 1, 58.27),
                        arguments(PitchLetter.B, Accidental.NATURAL, 1, 61.74),
                        arguments(PitchLetter.B, Accidental.SHARP, 1, 65.41),
                        arguments(PitchLetter.B, Accidental.FLAT, 2, 116.54),
                        arguments(PitchLetter.B, Accidental.NATURAL, 2, 123.47),
                        arguments(PitchLetter.B, Accidental.SHARP, 2, 130.81),
                        arguments(PitchLetter.B, Accidental.FLAT, 3, 233.08),
                        arguments(PitchLetter.B, Accidental.NATURAL, 3, 246.95),
                        arguments(PitchLetter.B, Accidental.SHARP, 3, 261.63),
                        arguments(PitchLetter.B, Accidental.FLAT, 4, 466.16),
                        arguments(PitchLetter.B, Accidental.NATURAL, 4, 493.88),
                        arguments(PitchLetter.B, Accidental.SHARP, 4, 523.25),
                        arguments(PitchLetter.B, Accidental.FLAT, 5, 932.33),
                        arguments(PitchLetter.B, Accidental.NATURAL, 5, 987.77),
                        arguments(PitchLetter.B, Accidental.SHARP, 5, 1046.5),
                        arguments(PitchLetter.B, Accidental.FLAT, 6, 1864.66),
                        arguments(PitchLetter.B, Accidental.NATURAL, 6, 1975.53),
                        arguments(PitchLetter.B, Accidental.SHARP, 6, 2093),
                        arguments(PitchLetter.B, Accidental.FLAT, 7, 3729.31),
                        arguments(PitchLetter.B, Accidental.NATURAL, 7, 3951.07),
                        arguments(PitchLetter.B, Accidental.SHARP, 7, 4186.01),
                        arguments(PitchLetter.B, Accidental.FLAT, 8, 7458.62),
                        arguments(PitchLetter.B, Accidental.NATURAL, 8, 7902.13),
                        arguments(PitchLetter.B, Accidental.SHARP, 8, 8372.02),
                        arguments(PitchLetter.C, Accidental.FLAT, 0, 15.43),
                        arguments(PitchLetter.C, Accidental.NATURAL, 0, 16.35),
                        arguments(PitchLetter.C, Accidental.SHARP, 0, 17.32),
                        arguments(PitchLetter.C, Accidental.FLAT, 1, 30.87),
                        arguments(PitchLetter.C, Accidental.NATURAL, 1, 32.7),
                        arguments(PitchLetter.C, Accidental.SHARP, 1, 34.65),
                        arguments(PitchLetter.C, Accidental.FLAT, 2, 61.74),
                        arguments(PitchLetter.C, Accidental.NATURAL, 2, 65.41),
                        arguments(PitchLetter.C, Accidental.SHARP, 2, 69.3),
                        arguments(PitchLetter.C, Accidental.FLAT, 3, 123.47),
                        arguments(PitchLetter.C, Accidental.NATURAL, 3, 130.81),
                        arguments(PitchLetter.C, Accidental.SHARP, 3, 138.59),
                        arguments(PitchLetter.C, Accidental.FLAT, 4, 246.94),
                        arguments(PitchLetter.C, Accidental.NATURAL, 4, 261.63),
                        arguments(PitchLetter.C, Accidental.SHARP, 4, 277.18),
                        arguments(PitchLetter.C, Accidental.FLAT, 5, 493.88),
                        arguments(PitchLetter.C, Accidental.NATURAL, 5, 523.25),
                        arguments(PitchLetter.C, Accidental.SHARP, 5, 554.37),
                        arguments(PitchLetter.C, Accidental.FLAT, 6, 987.77),
                        arguments(PitchLetter.C, Accidental.NATURAL, 6, 1046.5),
                        arguments(PitchLetter.C, Accidental.SHARP, 6, 1108.73),
                        arguments(PitchLetter.C, Accidental.FLAT, 7, 1975.53),
                        arguments(PitchLetter.C, Accidental.NATURAL, 7, 2093),
                        arguments(PitchLetter.C, Accidental.SHARP, 7, 2217.46),
                        arguments(PitchLetter.C, Accidental.FLAT, 8, 3951.07),
                        arguments(PitchLetter.C, Accidental.NATURAL, 8, 4186.01),
                        arguments(PitchLetter.C, Accidental.SHARP, 8, 4434.92),
                        arguments(PitchLetter.D, Accidental.FLAT, 0, 17.32),
                        arguments(PitchLetter.D, Accidental.NATURAL, 0, 18.35),
                        arguments(PitchLetter.D, Accidental.SHARP, 0, 19.45),
                        arguments(PitchLetter.D, Accidental.FLAT, 1, 34.65),
                        arguments(PitchLetter.D, Accidental.NATURAL, 1, 36.71),
                        arguments(PitchLetter.D, Accidental.SHARP, 1, 38.89),
                        arguments(PitchLetter.D, Accidental.FLAT, 2, 69.3),
                        arguments(PitchLetter.D, Accidental.NATURAL, 2, 73.42),
                        arguments(PitchLetter.D, Accidental.SHARP, 2, 77.78),
                        arguments(PitchLetter.D, Accidental.FLAT, 3, 138.59),
                        arguments(PitchLetter.D, Accidental.NATURAL, 3, 146.83),
                        arguments(PitchLetter.D, Accidental.SHARP, 3, 155.56),
                        arguments(PitchLetter.D, Accidental.FLAT, 4, 277.18),
                        arguments(PitchLetter.D, Accidental.NATURAL, 4, 293.66),
                        arguments(PitchLetter.D, Accidental.SHARP, 4, 311.13),
                        arguments(PitchLetter.D, Accidental.FLAT, 5, 554.37),
                        arguments(PitchLetter.D, Accidental.NATURAL, 5, 587.33),
                        arguments(PitchLetter.D, Accidental.SHARP, 5, 622.25),
                        arguments(PitchLetter.D, Accidental.FLAT, 6, 1108.73),
                        arguments(PitchLetter.D, Accidental.NATURAL, 6, 1174.66),
                        arguments(PitchLetter.D, Accidental.SHARP, 6, 1244.51),
                        arguments(PitchLetter.D, Accidental.FLAT, 7, 2217.46),
                        arguments(PitchLetter.D, Accidental.NATURAL, 7, 2349.32),
                        arguments(PitchLetter.D, Accidental.SHARP, 7, 2489.02),
                        arguments(PitchLetter.D, Accidental.FLAT, 8, 4434.92),
                        arguments(PitchLetter.D, Accidental.NATURAL, 8, 4698.63),
                        arguments(PitchLetter.D, Accidental.SHARP, 8, 4978.03),
                        arguments(PitchLetter.E, Accidental.FLAT, 0, 19.45),
                        arguments(PitchLetter.E, Accidental.NATURAL, 0, 20.6),
                        arguments(PitchLetter.E, Accidental.SHARP, 0, 21.83),
                        arguments(PitchLetter.E, Accidental.FLAT, 1, 38.89),
                        arguments(PitchLetter.E, Accidental.NATURAL, 1, 41.2),
                        arguments(PitchLetter.E, Accidental.SHARP, 1, 43.65),
                        arguments(PitchLetter.E, Accidental.FLAT, 2, 77.78),
                        arguments(PitchLetter.E, Accidental.NATURAL, 2, 82.41),
                        arguments(PitchLetter.E, Accidental.SHARP, 2, 87.31),
                        arguments(PitchLetter.E, Accidental.FLAT, 3, 155.56),
                        arguments(PitchLetter.E, Accidental.NATURAL, 3, 164.81),
                        arguments(PitchLetter.E, Accidental.SHARP, 3, 174.61),
                        arguments(PitchLetter.E, Accidental.FLAT, 4, 311.13),
                        arguments(PitchLetter.E, Accidental.NATURAL, 4, 329.63),
                        arguments(PitchLetter.E, Accidental.SHARP, 4, 349.23),
                        arguments(PitchLetter.E, Accidental.FLAT, 5, 622.25),
                        arguments(PitchLetter.E, Accidental.NATURAL, 5, 659.25),
                        arguments(PitchLetter.E, Accidental.SHARP, 5, 698.46),
                        arguments(PitchLetter.E, Accidental.FLAT, 6, 1244.51),
                        arguments(PitchLetter.E, Accidental.NATURAL, 6, 1318.51),
                        arguments(PitchLetter.E, Accidental.SHARP, 6, 1396.91),
                        arguments(PitchLetter.E, Accidental.FLAT, 7, 2489.02),
                        arguments(PitchLetter.E, Accidental.NATURAL, 7, 2637.02),
                        arguments(PitchLetter.E, Accidental.SHARP, 7, 2793.83),
                        arguments(PitchLetter.E, Accidental.FLAT, 8, 4978.03),
                        arguments(PitchLetter.E, Accidental.NATURAL, 8, 5274.04),
                        arguments(PitchLetter.E, Accidental.SHARP, 8, 5587.65),
                        arguments(PitchLetter.F, Accidental.FLAT, 0, 20.6),
                        arguments(PitchLetter.F, Accidental.NATURAL, 0, 21.83),
                        arguments(PitchLetter.F, Accidental.SHARP, 0, 23.12),
                        arguments(PitchLetter.F, Accidental.FLAT, 1, 41.2),
                        arguments(PitchLetter.F, Accidental.NATURAL, 1, 43.65),
                        arguments(PitchLetter.F, Accidental.SHARP, 1, 46.25),
                        arguments(PitchLetter.F, Accidental.FLAT, 2, 82.41),
                        arguments(PitchLetter.F, Accidental.NATURAL, 2, 87.31),
                        arguments(PitchLetter.F, Accidental.SHARP, 2, 92.5),
                        arguments(PitchLetter.F, Accidental.FLAT, 3, 164.81),
                        arguments(PitchLetter.F, Accidental.NATURAL, 3, 174.61),
                        arguments(PitchLetter.F, Accidental.SHARP, 3, 185),
                        arguments(PitchLetter.F, Accidental.FLAT, 4, 329.63),
                        arguments(PitchLetter.F, Accidental.NATURAL, 4, 349.23),
                        arguments(PitchLetter.F, Accidental.SHARP, 4, 369.99),
                        arguments(PitchLetter.F, Accidental.FLAT, 5, 659.25),
                        arguments(PitchLetter.F, Accidental.NATURAL, 5, 698.46),
                        arguments(PitchLetter.F, Accidental.SHARP, 5, 739.99),
                        arguments(PitchLetter.F, Accidental.FLAT, 6, 1318.51),
                        arguments(PitchLetter.F, Accidental.NATURAL, 6, 1396.91),
                        arguments(PitchLetter.F, Accidental.SHARP, 6, 1479.98),
                        arguments(PitchLetter.F, Accidental.FLAT, 7, 2637.02),
                        arguments(PitchLetter.F, Accidental.NATURAL, 7, 2793.83),
                        arguments(PitchLetter.F, Accidental.SHARP, 7, 2959.96),
                        arguments(PitchLetter.F, Accidental.FLAT, 8, 5274.04),
                        arguments(PitchLetter.F, Accidental.NATURAL, 8, 5587.65),
                        arguments(PitchLetter.F, Accidental.SHARP, 8, 5919.91),
                        arguments(PitchLetter.G, Accidental.FLAT, 0, 23.12),
                        arguments(PitchLetter.G, Accidental.NATURAL, 0, 24.5),
                        arguments(PitchLetter.G, Accidental.SHARP, 0, 25.96),
                        arguments(PitchLetter.G, Accidental.FLAT, 1, 46.25),
                        arguments(PitchLetter.G, Accidental.NATURAL, 1, 49),
                        arguments(PitchLetter.G, Accidental.SHARP, 1, 51.91),
                        arguments(PitchLetter.G, Accidental.FLAT, 2, 92.5),
                        arguments(PitchLetter.G, Accidental.NATURAL, 2, 98),
                        arguments(PitchLetter.G, Accidental.SHARP, 2, 103.83),
                        arguments(PitchLetter.G, Accidental.FLAT, 3, 185),
                        arguments(PitchLetter.G, Accidental.NATURAL, 3, 196),
                        arguments(PitchLetter.G, Accidental.SHARP, 3, 207.65),
                        arguments(PitchLetter.G, Accidental.FLAT, 4, 369.99),
                        arguments(PitchLetter.G, Accidental.NATURAL, 4, 392),
                        arguments(PitchLetter.G, Accidental.SHARP, 4, 415.3),
                        arguments(PitchLetter.G, Accidental.FLAT, 5, 739.99),
                        arguments(PitchLetter.G, Accidental.NATURAL, 5, 783.99),
                        arguments(PitchLetter.G, Accidental.SHARP, 5, 830.61),
                        arguments(PitchLetter.G, Accidental.FLAT, 6, 1479.98),
                        arguments(PitchLetter.G, Accidental.NATURAL, 6, 1567.98),
                        arguments(PitchLetter.G, Accidental.SHARP, 6, 1661.22),
                        arguments(PitchLetter.G, Accidental.FLAT, 7, 2959.96),
                        arguments(PitchLetter.G, Accidental.NATURAL, 7, 3135.96),
                        arguments(PitchLetter.G, Accidental.SHARP, 7, 3322.44),
                        arguments(PitchLetter.G, Accidental.FLAT, 8, 5919.91),
                        arguments(PitchLetter.G, Accidental.NATURAL, 8, 6271.93),
                        arguments(PitchLetter.G, Accidental.SHARP, 8, 6644.88));
            }
        }

        final static class PitchLetterOctaveFrequency implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(PitchLetter.A, 0, 27.5),
                        arguments(PitchLetter.A, 1, 55),
                        arguments(PitchLetter.A, 2, 110),
                        arguments(PitchLetter.A, 3, 220),
                        arguments(PitchLetter.A, 4, 440),
                        arguments(PitchLetter.A, 5, 880),
                        arguments(PitchLetter.A, 6, 1760),
                        arguments(PitchLetter.A, 7, 3520),
                        arguments(PitchLetter.A, 8, 7040),
                        arguments(PitchLetter.B, 0, 30.87),
                        arguments(PitchLetter.B, 1, 61.74),
                        arguments(PitchLetter.B, 2, 123.47),
                        arguments(PitchLetter.B, 3, 246.95),
                        arguments(PitchLetter.B, 4, 493.88),
                        arguments(PitchLetter.B, 5, 987.77),
                        arguments(PitchLetter.B, 6, 1975.53),
                        arguments(PitchLetter.B, 7, 3951.07),
                        arguments(PitchLetter.B, 8, 7902.13),
                        arguments(PitchLetter.C, 0, 16.35),
                        arguments(PitchLetter.C, 1, 32.7),
                        arguments(PitchLetter.C, 2, 65.41),
                        arguments(PitchLetter.C, 3, 130.81),
                        arguments(PitchLetter.C, 4, 261.63),
                        arguments(PitchLetter.C, 5, 523.25),
                        arguments(PitchLetter.C, 6, 1046.5),
                        arguments(PitchLetter.C, 7, 2093),
                        arguments(PitchLetter.C, 8, 4186.01),
                        arguments(PitchLetter.D, 0, 18.35),
                        arguments(PitchLetter.D, 1, 36.71),
                        arguments(PitchLetter.D, 2, 73.42),
                        arguments(PitchLetter.D, 3, 146.83),
                        arguments(PitchLetter.D, 4, 293.66),
                        arguments(PitchLetter.D, 5, 587.33),
                        arguments(PitchLetter.D, 6, 1174.66),
                        arguments(PitchLetter.D, 7, 2349.32),
                        arguments(PitchLetter.D, 8, 4698.63),
                        arguments(PitchLetter.E, 0, 20.6),
                        arguments(PitchLetter.E, 1, 41.2),
                        arguments(PitchLetter.E, 2, 82.41),
                        arguments(PitchLetter.E, 3, 164.81),
                        arguments(PitchLetter.E, 4, 329.63),
                        arguments(PitchLetter.E, 5, 659.25),
                        arguments(PitchLetter.E, 6, 1318.51),
                        arguments(PitchLetter.E, 7, 2637.02),
                        arguments(PitchLetter.E, 8, 5274.04),
                        arguments(PitchLetter.F, 0, 21.83),
                        arguments(PitchLetter.F, 1, 43.65),
                        arguments(PitchLetter.F, 2, 87.31),
                        arguments(PitchLetter.F, 3, 174.61),
                        arguments(PitchLetter.F, 4, 349.23),
                        arguments(PitchLetter.F, 5, 698.46),
                        arguments(PitchLetter.F, 6, 1396.91),
                        arguments(PitchLetter.F, 7, 2793.83),
                        arguments(PitchLetter.F, 8, 5587.65),
                        arguments(PitchLetter.G, 0, 24.5),
                        arguments(PitchLetter.G, 1, 49),
                        arguments(PitchLetter.G, 2, 98),
                        arguments(PitchLetter.G, 3, 196),
                        arguments(PitchLetter.G, 4, 392),
                        arguments(PitchLetter.G, 5, 783.99),
                        arguments(PitchLetter.G, 6, 1567.98),
                        arguments(PitchLetter.G, 7, 3135.96),
                        arguments(PitchLetter.G, 8, 6271.93));
            }
        }
    }

    final static class Invalid {
        final static class NoteAccidentalAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterAccidentalAndOctave(-12, 0);
            }
        }

        final static class NoteAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterAndOctave(-12, 0);
            }
        }

        final static class PitchStringValues implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                        .flatMap(accidental -> {
                            final int octave = random.nextInt(13);
                            final String firstString = "%1$c%1$c%2$c%3$d".formatted(pitchLetter.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String secondString = "%1$c%2$c%2$c%3$d".formatted(pitchLetter.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String thirdString = "%3$d%1$c%2$c%3$d".formatted(pitchLetter.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String fourthString = "%1$c%1$c%2$d".formatted(pitchLetter.charValue(),
                                    octave);
                            final String fifthString = "%1$c%2$c%3$d%1$c".formatted(pitchLetter.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String sixthString = "%1$c%2$d%1$c".formatted(pitchLetter.charValue(),
                                    octave);
                            final String seventhString = "%c %c %d".formatted(pitchLetter.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String eighthString = "%c %d".formatted(pitchLetter.charValue(),
                                    octave);
                            final String validStringWithAccidental = "%c%c%d".formatted(pitchLetter.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String validStringNoAccidental = "%c%d".formatted(pitchLetter.charValue(),
                                    octave);
                            return Stream.of(arguments(Character.toString(pitchLetter.charValue())),
                                    arguments(Character.toString(accidental.charValue())),
                                    arguments(Integer.toString(octave)), arguments(firstString),
                                    arguments("\u0020"),
                                    arguments(validStringWithAccidental + validStringWithAccidental),
                                    arguments(secondString), arguments(thirdString),
                                    arguments(fourthString), arguments(fifthString), arguments(sixthString),
                                    arguments(seventhString), arguments(eighthString),
                                    arguments(validStringNoAccidental + validStringNoAccidental));
                        }));
            }
        }
    }
}

final class PitchTest {

    ////////////////////////
    // valid constructors //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch({0}, {1}) does not throw")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLClassAndOctave.class)
    void pitchConstructorWithPitchClassAccidentalAndOctaveDoesNotThrow(
            PitchClass pitchClass,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(pitchClass, octave));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) does not throw")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchConstructorWithNoteNonNullAccidentalAndNonNegativeOctaveDoesNotThrow(PitchLetter pitchLetter,
            Accidental accidental,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(pitchLetter, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, {1}) does not throw")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAndOctave.class)
    void pitchConstructorWithNoteAndNonNegativeOctaveDoesNotThrow(PitchLetter pitchLetter, int octave) {
        assertDoesNotThrow(() -> new Pitch(pitchLetter, octave));
    }

    //////////////////////////
    // invalid constructors //
    //////////////////////////

    @Test
    @DisplayName("new Pitch(PitchLetter, null, 0 <= # <= 12) throws IllegalArgumentException")
    void pitchConstructorWithNullAccidentalThrows() {
        final PitchLetter pitchLetter = PitchTestArgsProvider.pitchLetters
                .get(PitchTestArgsProvider.random.nextInt(PitchTestArgsProvider.pitchLetters.size()));
        assertThrows(IllegalArgumentException.class,
                () -> new Pitch(pitchLetter, null, PitchTestArgsProvider.random.nextInt(13)));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) throws IllegalArgumentException")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.NoteAccidentalAndNegativeOctave.class)
    void pitchConstructorWithNoteAccidentalAndNegativeOctaveThrows(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(pitchLetter, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(null, Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchTestArgsProvider.Valid.AccidentalAndOctave.class)
    void pitchConstructorWithNullNoteAccidentalAndNonNegativeOctaveThrows(Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.NoteAndNegativeOctave.class)
    void pitchConstructorWithNoteAndNegativeOctaveThrows(PitchLetter pitchLetter, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(pitchLetter, octave));
    }

    @Test
    @DisplayName("new Pitch((PitchLetter), null, 0<=#<=12) throws IllegalArgumentException")
    void pitchConstructorWithNullPitchLetterAndValidOctaveThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pitch((PitchLetter) null, PitchTestArgsProvider.random.nextInt(13)));
    }

    ////////////////////////
    // properties/getters //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch({0}, {1}).letter() is PitchLetter.letter()")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLClassAndOctave.class)
    void pitchConstructedWithPitchClassAndOctaveLetterPropertyReturnsLetter(PitchClass pitchClass,
            int octave) {
        final Pitch pitch = new Pitch(pitchClass, octave);
        assertSame(pitchClass.letter(), pitch.letter());
    }

    @ParameterizedTest(name = "new Pitch({0}, {1}).accidental() is PitchLetter.accidental()")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLClassAndOctave.class)
    void pitchConstructedWithPitchClassAndOctaveAccidentalPropertyReturnsAccidental(PitchClass pitchClass,
            int octave) {
        final Pitch pitch = new Pitch(pitchClass, octave);
        assertSame(pitchClass.accidental(), pitch.accidental());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).letter() is PitchLetter.{0}")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchConstructedWithPitchLetterAccidentalAndOctavePropertyReturnsNote(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertSame(pitchLetter, pitch.letter());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).accidental() is Accidental.{1}")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchConstructedWithPitchLetterAccidentalAndOctavePropertyReturnsAccidental(PitchLetter pitchLetter,
            Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertSame(accidental, pitch.accidental());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).octave() is {2}")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchConstructedWithPitchLetterAccidentalAndOctavePropertyReturnsDuration(PitchLetter pitchLetter,
            Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertSame(octave, pitch.octave());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).stringValue() = \"{3}\"")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctaveStringValue.class)
    void pitchConstructedWithPitchLetterAccidentalAndOctaveStringValuePropertyReturnsStringValue(PitchLetter pitchLetter,
            Accidental accidental, int octave, String stringValue) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertEquals(stringValue, pitch.stringValue());
    }

    ////////////
    // equals //
    ////////////

    @ParameterizedTest(name = "new Pitch({0}, {1}).letter() equals {0}.letter()")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLClassAndOctave.class)
    void pitchEqualsPitchClassOctaveSameReturnsTrue(PitchClass pitchClass,
            int octave) {
        final Pitch pitch = new Pitch(pitchClass, octave);
        assertTrue(pitch.equals(pitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) equals same returns true")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchEqualsSameReturnsTrue(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertTrue(pitch.equals(pitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) equals equivalent returns true")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchEqualsEquivalentReturnsTrue(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, octave);
        final Pitch equalPitch = new Pitch(pitchLetter, accidental, octave);
        assertTrue(aPitch.equals(equalPitch));
    }

    @ParameterizedTest(name = "new Pitch(<PitchLetter.{0}>, <Accidental.{2}>, <{4}>) does not equal new Pitch(<Note.{1}>, <Accidental.{3}>, <{5}>)")
    @ArgumentsSource(PitchTestArgsProvider.Valid.DifferingPitchLettersAccidentalsAndOctaves.class)
    void pitchDoesNotEqualPitchWithDifferingNoteAccidentalAndOctave(PitchLetter aPitchLetter,
            PitchLetter anotherPitchLetter, Accidental anAccidental, Accidental anotherAccidental,
            int anOctave, int anotherOctave) {
        final Pitch aPitch = new Pitch(aPitchLetter, anAccidental, anOctave);
        final Pitch notEqualPitch = new Pitch(anotherPitchLetter, anotherAccidental,
                anotherOctave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    @ParameterizedTest(name = "new Pitch(<PitchLetter.{0}>, Accidental.{2}, {3}) does not equal new Pitch(<Note.{1}>, Accidental.{2}, {3})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.DifferingPitchLettersWithAccidentalAndOctave.class)
    void pitchDoesNotEqualPitchWithDifferingNotes(PitchLetter aPitchLetter, PitchLetter anotherPitchLetter,
            Accidental accidental, int octave) {
        final Pitch aPitch = new Pitch(aPitchLetter, accidental, octave);
        final Pitch notEqualPitch = new Pitch(anotherPitchLetter, accidental, octave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, <Accidental.{1}>, {3}) does not equal new Pitch(Note.{0}, <Accidental.{2}>, {3})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterWithDifferingAccidentalsAndOctave.class)
    void pitchDoesNotEqualPitchWithDifferingNotes(PitchLetter pitchLetter, Accidental anAccidental,
            Accidental anotherAccidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, anAccidental, octave);
        final Pitch notEqualPitch = new Pitch(pitchLetter, anotherAccidental, octave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, <{2}>) does not equal new Pitch(Note.{0}, Accidental.{1}, <{3}>)")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalWithDifferingOctaves.class)
    void pitchDoesNotEqualPitchWithDifferingOctaves(PitchLetter pitchLetter, Accidental accidental, int anOctave,
            int anotherOctave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, anOctave);
        final Pitch notEqualPitch = new Pitch(pitchLetter, accidental, anotherOctave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    //////////////
    // hashCode //
    //////////////

    @ParameterizedTest(name = "new Pitch(new PitchClass(PitchLetter.{0}, Accidental.{1}), {2}).hashCode() equals Objects.hash(Note.{0}, Accidental.{1}, {2})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void hashCodesComputedFromPitchLetterAccidentalAndOctave(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        final int computedHashCode = hash(new PitchClass(pitchLetter, accidental), octave);
        assertEquals(computedHashCode, pitch.hashCode());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).hashCode() is same for same object")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void samePitchesHaveSameHashCode(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, octave);
        assertTrue(aPitch.hashCode() == aPitch.hashCode());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).hashCode() is same for equal object")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void equivalentPitchesHaveSameHashCode(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, octave);
        final Pitch anotherEqualPitch = new Pitch(new PitchClass(pitchLetter, accidental), octave);
        assertTrue(aPitch.hashCode() == anotherEqualPitch.hashCode());
    }

    ////////////////
    // isParsable //
    ////////////////

    @ParameterizedTest(name = "Pitch.isParsable(\"{0}\") returns true")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchStringValues.class)
    void isParsableOfValidReturnsTrue(String stringValue) {
        assertTrue(Pitch.isParsable(stringValue));
    }

    @ParameterizedTest(name = "Pitch.isParsable(\"{0}\") returns false")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.PitchStringValues.class)
    @NullAndEmptySource
    void isParsableOfInvalidReturnsFalse(String stringValue) {
        assertFalse(Pitch.isParsable(stringValue));
    }

    ///////////
    // parse //
    ///////////

    @ParameterizedTest(name = "Pitch.parse(\"{3}\") returns new Pitch(Note.{0}, Accidental.{1}, {2})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctaveStringValue.class)
    void staticPitchParseMethodInitializesObject(PitchLetter note,
            Accidental accidental, int octave, String stringValue) {
        final Optional<Pitch> pitchOptional = Pitch.parse(stringValue);
        assertTrue(pitchOptional.isPresent());
        final Pitch pitch = new Pitch(note, accidental, octave);
        assertEquals(pitch, pitchOptional.get());
    }

    @ParameterizedTest(name = "Pitch.parse(\"{0}\") returns empty Optional")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.PitchStringValues.class)
    @EmptySource
    void staticPitchParseMethodReturnsEmptyOptionalIfPassedInvalidString(String invalidStringValue) {
        final Optional<Pitch> pitchOptional = Pitch.parse(invalidStringValue);
        assertTrue(pitchOptional.isEmpty());
    }

    @Test
    @DisplayName("Pitch.parse(null) throws IllegalArgumentException")
    void staticPitchParseMethodThrowsWhenPassedNull() {
        assertThrows(IllegalArgumentException.class, () -> Pitch.parse(null));
    }

    //////////////
    // toString //
    //////////////

    // TODO: Update arguments provider to have toString
    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).toString() returns pretty String")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchLetterAccidentalAndOctave.class)
    void pitchToStringReturnsPrettyString(PitchLetter pitchLetter, Accidental accidental, int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        final String stringValue = accidental == Accidental.NATURAL
                ? "%c%d".formatted(pitchLetter.charValue(), octave)
                : "%c%c%d".formatted(pitchLetter.charValue(), accidental.charValue(), octave);
        final String toString = "%s {note=%s, accidental=%s, octave=%d, stringValue=\"%s\"}".formatted(
                Pitch.class.getSimpleName(),
                pitchLetter.name(), accidental.name(), octave, stringValue);
        assertEquals(toString, pitch.toString());
    }
}
