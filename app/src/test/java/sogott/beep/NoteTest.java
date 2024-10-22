package sogott.beep;

import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class NoteTest {
    static private char[] nonNoteChars() {
        return IntStream.concat(
                IntStream.concat(
                        IntStream.rangeClosed(32, 64),
                        IntStream.rangeClosed(72, 96)),
                IntStream.rangeClosed(104, 126))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString()
                .toCharArray();
    }

    @Test
    void noteACharValueEqualsA() {
        final char noteCharValue = Note.A.charValue();
        final char expectedNoteCharValue = 'A';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteBCharValueEqualsB() {
        final char noteCharValue = Note.B.charValue();
        final char expectedNoteCharValue = 'B';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteCCharValueEqualsC() {
        final char noteCharValue = Note.C.charValue();
        final char expectedNoteCharValue = 'C';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteDCharValueEqualsD() {
        final char noteCharValue = Note.D.charValue();
        final char expectedNoteCharValue = 'D';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteECharValueEqualsE() {
        final char noteCharValue = Note.E.charValue();
        final char expectedNoteCharValue = 'E';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteFCharValueEqualsF() {
        final char noteCharValue = Note.F.charValue();
        final char expectedNoteCharValue = 'F';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteGCharValueEqualsG() {
        final char noteCharValue = Note.G.charValue();
        final char expectedNoteCharValue = 'G';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteAOffsetEquals0() {
        final int noteOffsetValue = Note.A.offset();
        final int expectedNoteOffsetValue = 0;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
    }

    @Test
    void noteBOffsetEquals2() {
        final int noteOffsetValue = Note.B.offset();
        final int expectedNoteOffsetValue = 2;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
    }

    @Test
    void noteCOffsetEqualsNegative9() {
        final int noteOffsetValue = Note.C.offset();
        final int expectedNoteOffsetValue = -9;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
    }

    @Test
    void noteDOffsetEqualsNegative7() {
        final int noteOffsetValue = Note.D.offset();
        final int expectedNoteOffsetValue = -7;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
    }

    @Test
    void noteEOffsetEqualsNegative5() {
        final int noteOffsetValue = Note.E.offset();
        final int expectedNoteOffsetValue = -5;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
    }

    @Test
    void noteFOffsetEqualsNegative4() {
        final int noteOffsetValue = Note.F.offset();
        final int expectedNoteOffsetValue = -4;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
    }

    @Test
    void noteGOffsetEqualsNegative2() {
        final int noteOffsetValue = Note.G.offset();
        final int expectedNoteOffsetValue = -2;
        assertEquals(expectedNoteOffsetValue, noteOffsetValue);
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

    @Test
    void noteFromCharUpperCaseAReturnsA() {
        final Optional<Note> optionalNote = Note.fromChar('A');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.A;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharUpperCaseBReturnsB() {
        final Optional<Note> optionalNote = Note.fromChar('B');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.B;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharUpperCaseCReturnsC() {
        final Optional<Note> optionalNote = Note.fromChar('C');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.C;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharUpperCaseDReturnsD() {
        final Optional<Note> optionalNote = Note.fromChar('D');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.D;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharUpperCaseEReturnsE() {
        final Optional<Note> optionalNote = Note.fromChar('E');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.E;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharUpperCaseFReturnsF() {
        final Optional<Note> optionalNote = Note.fromChar('F');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.F;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharUpperCaseGReturnsG() {
        final Optional<Note> optionalNote = Note.fromChar('G');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.G;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseAReturnsA() {
        final Optional<Note> optionalNote = Note.fromChar('a');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.A;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseBReturnsB() {
        final Optional<Note> optionalNote = Note.fromChar('b');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.B;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseCReturnsC() {
        final Optional<Note> optionalNote = Note.fromChar('c');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.C;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseDReturnsD() {
        final Optional<Note> optionalNote = Note.fromChar('d');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.D;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseEReturnsE() {
        final Optional<Note> optionalNote = Note.fromChar('e');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.E;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseFReturnsF() {
        final Optional<Note> optionalNote = Note.fromChar('f');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.F;

        assertEquals(expectedNoteValue, noteValue);
    }

    @Test
    void noteFromCharLowerCaseGReturnsG() {
        final Optional<Note> optionalNote = Note.fromChar('g');
        final boolean optionalNoteIsPresent = optionalNote.isPresent();

        assertTrue(optionalNoteIsPresent);

        final Note noteValue = optionalNote.get();
        final Note expectedNoteValue = Note.G;

        assertEquals(expectedNoteValue, noteValue);
    }
}
