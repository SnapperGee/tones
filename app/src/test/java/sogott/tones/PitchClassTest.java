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

final class PitchClassTestArgsProvider {
    final static class EnumValuesWithOffset implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(PitchClass.A, 0),
                    arguments(PitchClass.B, 2),
                    arguments(PitchClass.C, -9),
                    arguments(PitchClass.D, -7),
                    arguments(PitchClass.E, -5),
                    arguments(PitchClass.F, -4),
                    arguments(PitchClass.G, -2));
        }
    }

    final static class EnumValuesWithUpperCaseChar implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(PitchClass.A, 'A'),
                    arguments(PitchClass.B, 'B'),
                    arguments(PitchClass.C, 'C'),
                    arguments(PitchClass.D, 'D'),
                    arguments(PitchClass.E, 'E'),
                    arguments(PitchClass.F, 'F'),
                    arguments(PitchClass.G, 'G'));
        }
    }

    final static class EnumValuesWithLowerCaseChar implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(PitchClass.A, 'a'),
                    arguments(PitchClass.B, 'b'),
                    arguments(PitchClass.C, 'c'),
                    arguments(PitchClass.D, 'd'),
                    arguments(PitchClass.E, 'e'),
                    arguments(PitchClass.F, 'f'),
                    arguments(PitchClass.G, 'g'));
        }
    }
}

final class PitchClassTest {
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

    @ParameterizedTest(name = "PitchClass.{0}.offSet() is {1}")
    @ArgumentsSource(PitchClassTestArgsProvider.EnumValuesWithOffset.class)
    void noteOffsetValueIsValid(PitchClass note, int offset) {
        final int noteOffset = note.offset();
        assertSame(offset, noteOffset);
    }

    @ParameterizedTest(name = "PitchClass.{0}.charValue() is ''{1}''")
    @ArgumentsSource(PitchClassTestArgsProvider.EnumValuesWithUpperCaseChar.class)
    void noteCharValueIsValid(PitchClass note, char expectedCarValue) {
        final char charValue = note.charValue();
        assertSame(expectedCarValue, charValue);
    }

    @ParameterizedTest(name = "PitchClass.fromChar(''{1}'') is Note.{0}")
    @ArgumentsSource(PitchClassTestArgsProvider.EnumValuesWithUpperCaseChar.class)
    void noteFromUpperCaseChar(PitchClass note, char upperCaseChar) {
        final Optional<PitchClass> noteOptional = PitchClass.fromChar(upperCaseChar);
        assertTrue(noteOptional.isPresent());
        assertSame(note, noteOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.fromChar(''{1}'') is Note.{0}")
    @ArgumentsSource(PitchClassTestArgsProvider.EnumValuesWithLowerCaseChar.class)
    void noteFromLowerCaseChar(PitchClass note, char lowerCaseChar) {
        final Optional<PitchClass> noteOptional = PitchClass.fromChar(lowerCaseChar);
        assertTrue(noteOptional.isPresent());
        assertSame(note, noteOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.isNoteChar(''{0}'') returns true")
    @ValueSource(chars = { 'A', 'B', 'C', 'D', 'E', 'F' })
    void noteIsNoteCharReturnsTrueForValidUpperCaseChar(char validUpperCaseNoteChar) {
        final boolean isCharResultShouldBeTrue = PitchClass.isPitchLetter(validUpperCaseNoteChar);
        assertTrue(isCharResultShouldBeTrue);
    }

    @ParameterizedTest(name = "PitchClass.isNoteChar(''{0}'') returns true")
    @ValueSource(chars = { 'a', 'b', 'c', 'd', 'e', 'f' })
    void noteIsNoteCharReturnsTrueForValidLowerCaseChar(char validLowerCaseNoteChar) {
        final boolean isCharResultShouldBeTrue = PitchClass.isPitchLetter(validLowerCaseNoteChar);
        assertTrue(isCharResultShouldBeTrue);
    }

    @ParameterizedTest(name = "PitchClass.isNoteChar(''{0}'') returns false")
    @MethodSource("nonPitchLetterChars")
    void noteIsNoteCharReturnsFalseForInvalidNoteChar(char invalidNoteChar) {
        final boolean isCharResultShouldBeFalse = PitchClass.isPitchLetter(invalidNoteChar);
        assertFalse(isCharResultShouldBeFalse);
    }

    @ParameterizedTest(name = "PitchClass.fromChar(''{0}'') returns empty Optional")
    @MethodSource("nonPitchLetterChars")
    void noteFromCharReturnsEmptyOptionalForInvalidNoteChar(char invalidNoteChar) {
        final Optional<PitchClass> fromCharResultShouldBeEmpty = PitchClass.fromChar(invalidNoteChar);
        final boolean isEmptyShouldBeTrue = fromCharResultShouldBeEmpty.isEmpty();
        assertTrue(isEmptyShouldBeTrue);
    }
}
