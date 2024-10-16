package sogott.beep;

final class Note {
    static enum Accidental {
        SHARP(1),
        FLAT(-1);

        final private int _offset;

        private Accidental(int offset) {
            this._offset = offset;
        }

        int offset() {
            return this._offset;
        }
    }

    static int baseSemitoneOffset(char note) {
        switch (note) {
            case 'C':
            case 'c':
                return -9;
            case 'D':
            case 'd':
                return -7;
            case 'E':
            case 'e':
                return -5;
            case 'F':
            case 'f':
                return -4;
            case 'G':
            case 'g':
                return -2;
            case 'A':
            case 'a':
                return 0;
            case 'B':
            case 'b':
                return 2;
            default:
                throw new IllegalArgumentException("Illegal note (A-F expected): '%c'".formatted(note));
        }
    }

    static double frequency(char note, Accidental accidental, int octave) {
        final int baseNoteOffset = baseSemitoneOffset(note);
        final int octaveOffset = (octave - 4) * 12;
        final double noteFrequency = 440
                * Math.pow(2, (baseNoteOffset + (accidental == null ? 0 : accidental.offset()) + octaveOffset) / 12.0);
        return noteFrequency;
    }

    static double frequency(char note) {
        return frequency(note, null, 4);
    }

    static double frequency(char note, Accidental accidental) {
        return frequency(note, accidental, 4);
    }

    static double frequency(char note, int octave) {
        return frequency(note, null, octave);
    }
}
