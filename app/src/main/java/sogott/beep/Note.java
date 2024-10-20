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

    double frequency(int octave, Accidental accidental) {
        final int octaveOffset = (octave - 4) * 12;
        final double noteFrequency = 440
                * Math.pow(2,
                        (this._offset + (accidental == null ? 0 : accidental.offset()) + octaveOffset)
                                / 12.0);
        return noteFrequency;
    }

    double frequency(int octave) {
        return frequency(octave, null);
    }

    static Optional<Note> fromChar(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return Arrays.stream(Note.values()).filter(note -> note.charValue() == upperCaseChar).findFirst();
    }

    static enum Accidental {
        SHARP('+', 1),
        FLAT('-', -1);

        final private char _char;
        final private int _offset;

        private Accidental(char charValue, int offset) {
            this._char = charValue;
            this._offset = offset;
        }

        int offset() {
            return this._offset;
        }

        char charValue() {
            return this._char;
        }

        static Optional<Accidental> fromChar(char aChar) {
            return SHARP.charValue() == aChar
                    ? Optional.of(SHARP)
                    : FLAT.charValue() == aChar
                            ? Optional.of(FLAT)
                            : Optional.empty();
        }
    }
}
