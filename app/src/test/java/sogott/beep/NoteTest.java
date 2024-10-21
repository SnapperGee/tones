package sogott.beep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class NoteTest {
    @Test
    void noteAOCharValueEqualsA() {
        final char noteCharValue = Note.A.charValue();
        final char expectedNoteCharValue = 'A';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteBOCharValueEqualsB() {
        final char noteCharValue = Note.B.charValue();
        final char expectedNoteCharValue = 'B';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteCOCharValueEqualsC() {
        final char noteCharValue = Note.C.charValue();
        final char expectedNoteCharValue = 'C';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteDOCharValueEqualsD() {
        final char noteCharValue = Note.D.charValue();
        final char expectedNoteCharValue = 'D';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteEOCharValueEqualsE() {
        final char noteCharValue = Note.E.charValue();
        final char expectedNoteCharValue = 'E';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteFOCharValueEqualsF() {
        final char noteCharValue = Note.F.charValue();
        final char expectedNoteCharValue = 'F';
        assertEquals(expectedNoteCharValue, noteCharValue);
    }

    @Test
    void noteGOCharValueEqualsG() {
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
}
