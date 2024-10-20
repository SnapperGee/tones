package sogott.beep;

enum Note {
    A(0), B(2), C(-9), D(-7), E(-5), F(-4), G(-2);

    final private char _noteChar;
    final private int _offset;

    private Note(int offSet) {
        this._noteChar = this.name().charAt(0);
        this._offset = offSet;
    }

    char charValue() {
        return this._noteChar;
    }

    int offset() {
        return this._offset;
    }

    double natural() {
        return Parser.frequency(this._noteChar);
    }

    double natural(int octave) {
        return Parser.frequency(this._noteChar, octave);
    }

    double sharp() {
        return Parser.frequency(this._noteChar, Accidental.SHARP);
    }

    double sharp(int octave) {
        return Parser.frequency(this._noteChar, Accidental.SHARP, octave);
    }

    double flat() {
        return Parser.frequency(this._noteChar, Accidental.FLAT);
    }

    double flat(int octave) {
        return Parser.frequency(this._noteChar, Accidental.FLAT, octave);
    }

    static enum Accidental {
        SHARP(1),
        FLAT(-1);

        final private int _semitoneOffset;

        private Accidental(int semitoneOffset) {
            this._semitoneOffset = semitoneOffset;
        }

        int semitoneOffset() {
            return this._semitoneOffset;
        }
    }

    final static class Parser {

        static int noteOffset(char note) throws IllegalArgumentException {
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
            final int baseNoteOffset = noteOffset(note);
            final int octaveOffset = (octave - 4) * 12;
            final double noteFrequency = 440
                    * Math.pow(2,
                            (baseNoteOffset + (accidental == null ? 0 : accidental.semitoneOffset()) + octaveOffset)
                                    / 12.0);
            return noteFrequency;
        }

        static double frequency(String note, int octave) throws IllegalArgumentException {
            if (note == null) {
                throw new IllegalArgumentException("Null note String.");
            }

            if (note.length() == 1) {
                final int baseNoteOffset = noteOffset(note.charAt(0));
                final int octaveOffset = (octave - 4) * 12;
                final double noteFrequency = 440
                        * Math.pow(2, (baseNoteOffset + octaveOffset) / 12.0);
                return noteFrequency;
            }

            if (note.length() == 2) {
                final int baseNoteOffset = noteOffset(note.charAt(0));

                final char accidentalChar = note.charAt(1);
                final Accidental accidental = switch (accidentalChar) {
                    case '+' -> Accidental.SHARP;
                    case '-' -> Accidental.FLAT;
                    default -> throw new IllegalArgumentException(
                            "Illegal note String accidental char ('+' or '-' expected, but got '%c'): \"%s\""
                                    .formatted(accidentalChar, note));
                };

                final int octaveOffset = (octave - 4) * 12;
                final double noteFrequency = 440
                        * Math.pow(2,
                                (baseNoteOffset + (accidental == null ? 0 : accidental.semitoneOffset()) + octaveOffset)
                                        / 12.0);
                return noteFrequency;
            }

            throw new IllegalArgumentException(
                    "Illegal note String length (1-2 expected, but got %d): \"%s\"".formatted(note.length(), note));
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

        static double frequency(String note) {
            return frequency(note);
        }
    }
}
