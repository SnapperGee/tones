package sogott.tones;

import java.util.stream.Stream;
import java.util.List;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PitchClassTestArgsProvider {
    private static final List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private static final List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    static final RandomGenerator random = RandomGenerator.getDefault();

    static PitchLetter randomPitchLetter() {
        return pitchLetters.get(random.nextInt(pitchLetters.size()));
    }

    static Accidental randomAccidental() {
        return accidentals.get(random.nextInt(accidentals.size()));
    }

    static final class Valid {
        static final class PitchLetterAndAccidental implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter, accidental)));
            }
        }

        static final class PitchLetterAndAccidentalWithPitchClassStringPrefix implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                            .flatMap(accidental ->
                                {
                                    final String pitchClassString = new StringBuilder(2)
                                        .append(pitchLetter.charValue())
                                        .append(accidental.charValue())
                                        .toString();

                                    return Stream.concat(
                                        Stream.of(
                                            arguments(
                                                pitchLetter,
                                                accidental,
                                                pitchClassString)
                                            ),
                                        Util.randomStrings(7, 6, 1).map(aString -> arguments(pitchClassString + aString))
                                    );
                                }
                            )
                        );
            }
        }

        static final class PitchLetter implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter)));
            }
        }

        static final class PitchLetterAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter,
                                        new PitchClass(pitchLetter, Accidental.NATURAL))));
            }
        }
    }
}

final class PitchClassTest {
    @ParameterizedTest(name = "new PitchClass(PitchLetter.{0}, Accidental.{1}) does not throw")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetterAndAccidental.class)
    void pitchConstructorPassedValidPitchLetterAndAccidentalDoesNotThrow(PitchLetter pitchLetter,
            Accidental accidental) {
        assertDoesNotThrow(() -> new PitchClass(pitchLetter, accidental));
    }

    @ParameterizedTest(name = "new PitchClass(PitchLetter.{0}) does not throw")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetter.class)
    void pitchConstructorPassedValidPitchLetterDoesNotThrow(PitchLetter pitchLetter) {
        assertDoesNotThrow(() -> new PitchClass(pitchLetter));
    }

    @Test
    @DisplayName("new PitchClass(null, Accidental) throws IllegalArgumentException")
    void pitchConstructorPassedNullPitchLetterAndNonNullAccidentalThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new PitchClass(null, PitchClassTestArgsProvider.randomAccidental()));
    }

    @Test
    @DisplayName("new PitchClass(PitchLetter, null) throws IllegalArgumentException")
    void pitchConstructorPassedPitchLetterAndNullAccidentalThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new PitchClass(PitchClassTestArgsProvider.randomPitchLetter(), null));
    }

    @Test
    @DisplayName("new PitchClass(null) throws IllegalArgumentException")
    void pitchConstructorPassedNullAThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new PitchClass(null));
    }

    @ParameterizedTest(name = "new PitchClass(PitchLetter.{0}) returns {1}")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetterAndObject.class)
    void pitchConstructorPassedValidPitchLetterReturnsPitchClassWithPitchLetterAndNatural(PitchLetter pitchLetter,
            PitchClass expectedPitchClass) {
        assertEquals(expectedPitchClass, new PitchClass(pitchLetter));
    }
}
