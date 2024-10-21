package sogott.beep;

import java.util.Arrays;
import java.util.Optional;

enum Note {
    A(0), B(2), C(-9), D(-7), E(-5), F(-4), G(-2);

    final private char _char;
    final private int _offset;

    private Note(int offSet) {
        this._char = this.name().charAt(0);
        this._offset = offSet;
    }

    char charValue() {
        return this._char;
    }

    int offset() {
        return this._offset;
    }

    static Optional<Note> fromChar(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return Arrays.stream(Note.values()).filter(note -> note.charValue() == upperCaseChar).findFirst();
    }

    static boolean isNoteChar(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return Arrays.stream(Note.values()).anyMatch(note -> note.charValue() == upperCaseChar);
    }
}
