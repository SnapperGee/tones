package sogott.beep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class NoteTest {
    @Test
    void noteAOffsetEquals0() {
        final int noteOffsetValue = Note.A.offset();
        final int expectedOffsetValue = 0;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }

    @Test
    void noteBOffsetEquals2() {
        final int noteOffsetValue = Note.B.offset();
        final int expectedOffsetValue = 2;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }

    @Test
    void noteCOffsetEqualsNegative9() {
        final int noteOffsetValue = Note.C.offset();
        final int expectedOffsetValue = -9;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }

    @Test
    void noteDOffsetEqualsNegative7() {
        final int noteOffsetValue = Note.D.offset();
        final int expectedOffsetValue = -7;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }

    @Test
    void noteEOffsetEqualsNegative5() {
        final int noteOffsetValue = Note.E.offset();
        final int expectedOffsetValue = -5;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }

    @Test
    void noteFOffsetEqualsNegative4() {
        final int noteOffsetValue = Note.F.offset();
        final int expectedOffsetValue = -4;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }

    @Test
    void noteGOffsetEqualsNegative2() {
        final int noteOffsetValue = Note.G.offset();
        final int expectedOffsetValue = -2;
        assertEquals(expectedOffsetValue, noteOffsetValue);
    }
}
