package sogott.tones;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.ParameterizedTest;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PitchClassTestArgsProvider {
    private final static List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static RandomGenerator random = RandomGenerator.getDefault();

    static PitchLetter randomPitchLetter() {
        return pitchLetters.get(random.nextInt(pitchLetters.size()));
    }

    static Accidental randomAccidental() {
        return accidentals.get(random.nextInt(accidentals.size()));
    }

    final static class Valid {
        final static class PitchLetterAndAccidental implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter, accidental)));
            }
        }

        final static class PitchLetter implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter)));
            }
        }

        final static class PitchLetterAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter,
                                        new PitchClass(pitchLetter, Accidental.NATURAL))));
            }
        }

        final static class QualifiedUpperCasePitchClassStringAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(
                                        new StringBuilder(2).append(pitchLetter.charValue())
                                                .append(accidental.charValue()).toString(),
                                        new PitchClass(pitchLetter, accidental))));
            }
        }

        final static class SoloUpperCasePitchLetterStringAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .map(pitchLetter -> arguments(
                                Character.toString(Character.toLowerCase(pitchLetter.charValue())),
                                new PitchClass(pitchLetter, Accidental.NATURAL)));
            }
        }

        final static class QualifiedLowerCasePitchClassStringAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(
                                        new StringBuilder(2).append(Character.toLowerCase(pitchLetter.charValue()))
                                                .append(accidental.charValue()).toString(),
                                        new PitchClass(pitchLetter, accidental))));
            }
        }

        final static class SoloLowerCasePitchLetterStringAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().map(pitchLetter -> arguments(Character.toString(pitchLetter.charValue()),
                        new PitchClass(pitchLetter, Accidental.NATURAL)));
            }
        }
    }

    final static class Invalid {
        final static class SoloPitchLetterString implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return IntStream.rangeClosed(0, 127)
                        .filter(codePoint -> codePoint != 'A'
                                && codePoint != 'B'
                                && codePoint != 'C'
                                && codePoint != 'D'
                                && codePoint != 'E'
                                && codePoint != 'F'
                                && codePoint != 'G'
                                && codePoint != 'a'
                                && codePoint != 'b'
                                && codePoint != 'c'
                                && codePoint != 'd'
                                && codePoint != 'e'
                                && codePoint != 'f'
                                && codePoint != 'g')
                        .mapToObj(codePoint -> arguments(Character.toString((char) codePoint)));
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

    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns Optional<{1}>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.QualifiedUpperCasePitchClassStringAndObject.class)
    void pitchClassParseStringOfQualifiedUpperCaseValidLetterReturnsPresentOptional(
            String upperCasePitchLetterString,
            PitchClass expectedPitchClass) {
        final Optional<PitchClass> pitchClassOptional = PitchClass.parse(upperCasePitchLetterString);
        assertTrue(pitchClassOptional.isPresent(),
                () -> "PitchClass.parse(\"%s\") returns non present Optional".formatted(upperCasePitchLetterString));
        assertEquals(expectedPitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns Optional<{1}>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.SoloUpperCasePitchLetterStringAndObject.class)
    void pitchClassParseStringOfSoloValidUpperCaseLetterReturnsPresentOptional(
            String soloUpperCasePitchLetterString,
            PitchClass expectedPitchClass) {
        final Optional<PitchClass> pitchClassOptional = PitchClass.parse(soloUpperCasePitchLetterString);
        assertTrue(pitchClassOptional.isPresent(),
                () -> "PitchClass.parse(\"%s\") returns non present Optional"
                        .formatted(soloUpperCasePitchLetterString));
        assertEquals(expectedPitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns Optional<{1}>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.QualifiedLowerCasePitchClassStringAndObject.class)
    void pitchClassParseStringOfQualifiedLowerCaseValidLetterReturnsPresentOptional(
            String lowerCasePitchLetterString,
            PitchClass expectedPitchClass) {
        final Optional<PitchClass> pitchClassOptional = PitchClass.parse(lowerCasePitchLetterString);
        assertTrue(pitchClassOptional.isPresent(),
                () -> "PitchClass.parse(\"%s\") returns non present Optional".formatted(lowerCasePitchLetterString));
        assertEquals(expectedPitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns Optional<{1}>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.SoloLowerCasePitchLetterStringAndObject.class)
    void pitchClassParseStringOfSoloValidLowerCaseLetterReturnsPresentOptional(
            String lowerCaseSoloPitchLetterString,
            PitchClass expectedPitchClass) {
        final Optional<PitchClass> pitchClassOptional = PitchClass.parse(lowerCaseSoloPitchLetterString);
        assertTrue(pitchClassOptional.isPresent(),
                () -> "PitchClass.parse(\"%s\") returns non present Optional"
                        .formatted(lowerCaseSoloPitchLetterString));
        assertEquals(expectedPitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns empty Optional")
    @ArgumentsSource(PitchClassTestArgsProvider.Invalid.SoloPitchLetterString.class)
    @EmptySource
    void pitchClassParseStringOfSoloInvalidLetterReturnsEmptyOptional(
            String invalidSoloPitchLetterString) {
        assertTrue(PitchClass.parse(invalidSoloPitchLetterString).isEmpty(),
                () -> "PitchClass.parse(\"%s\") returned non empty Optional".formatted(invalidSoloPitchLetterString));
    }
}
