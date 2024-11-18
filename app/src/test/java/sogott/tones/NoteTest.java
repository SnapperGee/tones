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

final class NoteArgProvider {
    final static class EnumValuesWithOffset implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(Note.A, 0),
                    arguments(Note.B, 2),
                    arguments(Note.C, -9),
                    arguments(Note.D, -7),
                    arguments(Note.E, -5),
                    arguments(Note.F, -4),
                    arguments(Note.G, -2));
        }
    }

    final static class EnumValuesWithUpperCaseChar implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(Note.A, 'A'),
                    arguments(Note.B, 'B'),
                    arguments(Note.C, 'C'),
                    arguments(Note.D, 'D'),
                    arguments(Note.E, 'E'),
                    arguments(Note.F, 'F'),
                    arguments(Note.G, 'G'));
        }
    }

    final static class EnumValuesWithLowerCaseChar implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(Note.A, 'a'),
                    arguments(Note.B, 'b'),
                    arguments(Note.C, 'c'),
                    arguments(Note.D, 'd'),
                    arguments(Note.E, 'e'),
                    arguments(Note.F, 'f'),
                    arguments(Note.G, 'g'));
        }
    }
}

final class NoteTest {
    static private char[] nonNoteChars() {
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

    @ParameterizedTest(name = "Note.{0}.offSet() is {1}")
    @ArgumentsSource(NoteArgProvider.EnumValuesWithOffset.class)
    void noteOffsetValueIsValid(Note note, int offset) {
        final int noteOffset = note.offset();
        assertSame(offset, noteOffset);
    }

    @ParameterizedTest(name = "Note.{0}.charValue() is ''{1}''")
    @ArgumentsSource(NoteArgProvider.EnumValuesWithUpperCaseChar.class)
    void noteCharValueIsValid(Note note, char expectedCarValue) {
        final char charValue = note.charValue();
        assertSame(expectedCarValue, charValue);
    }

    @ParameterizedTest(name = "Note.fromChar(''{1}'') is Note.{0}")
    @ArgumentsSource(NoteArgProvider.EnumValuesWithUpperCaseChar.class)
    void noteFromUpperCaseChar(Note note, char upperCaseChar) {
        final Optional<Note> noteOptional = Note.fromChar(upperCaseChar);
        assertTrue(noteOptional.isPresent());
        assertSame(note, noteOptional.get());
    }

    @ParameterizedTest(name = "Note.fromChar(''{1}'') is Note.{0}")
    @ArgumentsSource(NoteArgProvider.EnumValuesWithLowerCaseChar.class)
    void noteFromLowerCaseChar(Note note, char lowerCaseChar) {
        final Optional<Note> noteOptional = Note.fromChar(lowerCaseChar);
        assertTrue(noteOptional.isPresent());
        assertSame(note, noteOptional.get());
    }

    @ParameterizedTest(name = "Note.isNoteChar(''{0}'') returns true")
    @ValueSource(chars = { 'A', 'B', 'C', 'D', 'E', 'F' })
    void noteIsNoteCharReturnsTrueForValidUpperCaseChar(char validUpperCaseNoteChar) {
        final boolean isCharResultShouldBeTrue = Note.isNoteChar(validUpperCaseNoteChar);
        assertTrue(isCharResultShouldBeTrue);
    }

    @ParameterizedTest(name = "Note.isNoteChar(''{0}'') returns true")
    @ValueSource(chars = { 'a', 'b', 'c', 'd', 'e', 'f' })
    void noteIsNoteCharReturnsTrueForValidLowerCaseChar(char validLowerCaseNoteChar) {
        final boolean isCharResultShouldBeTrue = Note.isNoteChar(validLowerCaseNoteChar);
        assertTrue(isCharResultShouldBeTrue);
    }

    @ParameterizedTest(name = "Note.isNoteChar(''{0}'') returns false")
    @MethodSource("nonNoteChars")
    void noteIsNoteCharReturnsFalseForInvalidNoteChar(char invalidNoteChar) {
        final boolean isCharResultShouldBeFalse = Note.isNoteChar(invalidNoteChar);
        assertFalse(isCharResultShouldBeFalse);
    }

    @ParameterizedTest(name = "Note.fromChar(''{0}'') returns empty Optional")
    @MethodSource("nonNoteChars")
    void noteFromCharReturnsEmptyOptionalForInvalidNoteChar(char invalidNoteChar) {
        final Optional<Note> fromCharResultShouldBeEmpty = Note.fromChar(invalidNoteChar);
        final boolean isEmptyShouldBeTrue = fromCharResultShouldBeEmpty.isEmpty();
        assertTrue(isEmptyShouldBeTrue);
    }
}
