package sogott.tones;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class PitchLetterTestArgsProvider {
    final static class EnumValuesWithOffset implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(PitchLetter.A, 0),
                    arguments(PitchLetter.B, 2),
                    arguments(PitchLetter.C, -9),
                    arguments(PitchLetter.D, -7),
                    arguments(PitchLetter.E, -5),
                    arguments(PitchLetter.F, -4),
                    arguments(PitchLetter.G, -2));
        }
    }

    final static class EnumValuesWithUpperCaseChar implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(PitchLetter.A, 'A'),
                    arguments(PitchLetter.B, 'B'),
                    arguments(PitchLetter.C, 'C'),
                    arguments(PitchLetter.D, 'D'),
                    arguments(PitchLetter.E, 'E'),
                    arguments(PitchLetter.F, 'F'),
                    arguments(PitchLetter.G, 'G'));
        }
    }

    final static class EnumValuesWithLowerCaseChar implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(PitchLetter.A, 'a'),
                    arguments(PitchLetter.B, 'b'),
                    arguments(PitchLetter.C, 'c'),
                    arguments(PitchLetter.D, 'd'),
                    arguments(PitchLetter.E, 'e'),
                    arguments(PitchLetter.F, 'f'),
                    arguments(PitchLetter.G, 'g'));
        }
    }
}

final class PitchLetterTest {
    static private char[] nonPitchLetterChars() {
        return IntStream.concat(
                IntStream.concat(
                        IntStream.rangeClosed(32, 64),
                        IntStream.rangeClosed(72, 96)),
                IntStream.rangeClosed(104, 126))
                .mapToObj(i -> (char) i)
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString()
                .toCharArray();
    }

    @ParameterizedTest(name = "PitchLetter.{0}.offSet() is {1}")
    @ArgumentsSource(PitchLetterTestArgsProvider.EnumValuesWithOffset.class)
    void pitchClassOffsetValueIsValid(PitchLetter pitchClass, int offset) {
        final int pitchClassOffset = pitchClass.offset();
        assertSame(offset, pitchClassOffset);
    }

    @ParameterizedTest(name = "PitchLetter.{0}.charValue() is ''{1}''")
    @ArgumentsSource(PitchLetterTestArgsProvider.EnumValuesWithUpperCaseChar.class)
    void pitchClassCharValueIsValid(PitchLetter pitchClass, char expectedCarValue) {
        final char charValue = pitchClass.charValue();
        assertSame(expectedCarValue, charValue);
    }

    @ParameterizedTest(name = "PitchLetter.fromChar(''{1}'') is PitchClass.{0}")
    @ArgumentsSource(PitchLetterTestArgsProvider.EnumValuesWithUpperCaseChar.class)
    void pitchClassFromUpperCaseChar(PitchLetter pitchClass, char upperCaseChar) {
        final Optional<PitchLetter> pitchClassOptional = PitchLetter.fromChar(upperCaseChar);
        assertTrue(pitchClassOptional.isPresent());
        assertSame(pitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchLetter.fromChar(''{1}'') is PitchClass.{0}")
    @ArgumentsSource(PitchLetterTestArgsProvider.EnumValuesWithLowerCaseChar.class)
    void pitchClassFromLowerCaseChar(PitchLetter pitchClass, char lowerCaseChar) {
        final Optional<PitchLetter> pitchClassOptional = PitchLetter.fromChar(lowerCaseChar);
        assertTrue(pitchClassOptional.isPresent());
        assertSame(pitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchLetter.isPitchLetter(''{0}'') returns true")
    @ValueSource(chars = { 'A', 'B', 'C', 'D', 'E', 'F' })
    void pitchClassIsPitchLetterReturnsTrueForValidUpperCaseChar(char validUpperCasePitchClassLetterChar) {
        final boolean isCharResultShouldBeTrue = PitchLetter.isPitchLetter(validUpperCasePitchClassLetterChar);
        assertTrue(isCharResultShouldBeTrue);
    }

    @ParameterizedTest(name = "PitchLetter.isPitchLetter(''{0}'') returns true")
    @ValueSource(chars = { 'a', 'b', 'c', 'd', 'e', 'f' })
    void pitchClassIsPitchLetterReturnsTrueForValidLowerCaseChar(char validLowerCasePitchClassLetterChar) {
        final boolean isCharResultShouldBeTrue = PitchLetter.isPitchLetter(validLowerCasePitchClassLetterChar);
        assertTrue(isCharResultShouldBeTrue);
    }

    @ParameterizedTest(name = "PitchLetter.isPitchLetter(''{0}'') returns false")
    @MethodSource("nonPitchLetterChars")
    void pitchClassIsPitchLetterReturnsFalseForInvalidNoteChar(char nonPitchLetterChar) {
        final boolean isCharResultShouldBeFalse = PitchLetter.isPitchLetter(nonPitchLetterChar);
        assertFalse(isCharResultShouldBeFalse);
    }

    @ParameterizedTest(name = "PitchLetter.fromChar(''{0}'') returns empty Optional")
    @MethodSource("nonPitchLetterChars")
    void pitchClassFromCharReturnsEmptyOptionalForInvalidNoteChar(char nonPitchLetterChar) {
        final Optional<PitchLetter> fromCharResultShouldBeEmpty = PitchLetter.fromChar(nonPitchLetterChar);
        final boolean isEmptyShouldBeTrue = fromCharResultShouldBeEmpty.isEmpty();
        assertTrue(isEmptyShouldBeTrue);
    }
}
