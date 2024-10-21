package sogott.beep;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

final class Pitch {
    final private Note _note;
    final private Accidental _accidental;
    final private int _octave;
    final private String _stringValue;
    final private int _hashCode;
    final private String _toString;

    Pitch(Note note, Accidental accidental, int octave) {
        if (note == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Pitch.class.getSimpleName(), Note.class.getSimpleName()));
        }

        if (octave < 0) {
            throw new IllegalArgumentException("Negative octave: %d".formatted(octave));
        }

        this._note = note;
        this._accidental = accidental;
        this._octave = octave;
        this._stringValue = this._accidental == null
                ? "%c%d".formatted(this._note.charValue(), this._octave)
                : "%c%c%d".formatted(this._note.charValue(), this._accidental.charValue(), this._octave);
        this._hashCode = Objects.hash(this._note, this._accidental, this._octave);
        this._toString = "%s{note=%s, accidental=%s, octave=%d, stringValue=\"%s\"}".formatted(this._note.name(),
                this._accidental.name(),
                this._octave, this._stringValue);
    }

    Note note() {
        return this._note;
    }

    Accidental accidental() {
        return this._accidental;
    }

    int octave() {
        return this._octave;
    }

    String stringValue() {
        return this._stringValue;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pitch other
                && this._note == other._note
                && this._accidental == other._accidental
                && this._octave == other._octave;
    }

    @Override
    public int hashCode() {
        return this._hashCode;
    }

    @Override
    public String toString() {
        return this._toString;
    }

    double frequency() {
        return frequencyFrom(this._note, this._accidental, this._octave);
    }

    static Pitch create(Note note, Accidental accidental, int octave) {
        return new Pitch(note, accidental, octave);
    }

    static Pitch create(Note note, int octave) {
        return new Pitch(note, null, octave);
    }

    static double frequencyFrom(Note note, Accidental accidental, int octave) {
        if (note == null) {
            throw new IllegalArgumentException(
                    "Null %s.".formatted(Note.class.getSimpleName()));
        }

        if (octave < 0) {
            throw new IllegalArgumentException("Negative octave: %d".formatted(octave));
        }

        final int octaveOffset = (octave - 4) * 12;
        final double noteFrequency = 440
                * Math.pow(2,
                        (note.offset() + (accidental == null ? 0 : accidental.offset()) + octaveOffset)
                                / 12.0);
        return noteFrequency;
    }

    static OptionalDouble frequencyFrom(char note, char accidental, int octave) {
        final Optional<Note> _note = Note.fromChar(note);

        if (_note.isEmpty()) {
            OptionalDouble.empty();
        }

        final Optional<Accidental> _accidental = Accidental.fromChar(accidental);

        if (_accidental.isEmpty()) {
            OptionalDouble.empty();
        }

        return OptionalDouble.of(frequencyFrom(_note.get(), _accidental.get(), octave));
    }

    static double frequencyFrom(Note note, int octave) {
        return frequencyFrom(note, null, octave);
    }

    static OptionalDouble frequencyFrom(char note, int octave) {
        final Optional<Note> _note = Note.fromChar(note);

        if (_note.isEmpty()) {
            OptionalDouble.empty();
        }

        return OptionalDouble.of(frequencyFrom(_note.get(), null, octave));
    }

    static OptionalDouble frequencyFrom(String aString) {

        // A pitch string must be at least a note and octave (int)
        if (aString.length() < 2) {
            return OptionalDouble.empty();
        }

        final Optional<Note> noteOptional = Note.fromChar(aString.charAt(0));

        // first char must be note
        if (noteOptional.isEmpty()) {
            return OptionalDouble.empty();
        }

        final Note note = noteOptional.get();

        // Check if the second character is '+' or '-'
        final int startIndex = aString.charAt(1) == Accidental.SHARP.charValue()
                || aString.charAt(1) == Accidental.FLAT.charValue() ? 2 : 1;

        // if second char is '+' or '-' and there aren't any chars left (an octave is
        // required)
        if (startIndex == 2 && aString.length() < 3) {
            return OptionalDouble.empty();
        }

        // if an octave (int) isn't following leading note char or accidental if present
        if (!aString.codePoints().skip(startIndex).allMatch(Character::isDigit)) {
            return OptionalDouble.empty();
        }

        final int octave = Integer.parseInt(aString.substring(startIndex));

        // if there is an accidental
        if (startIndex == 2) {
            final Accidental accidental = Accidental.fromChar(aString.charAt(1))
                    .orElseThrow(() -> new RuntimeException("Accidental value could not be converted from char."));
            return OptionalDouble.of(frequencyFrom(note, accidental, octave));
        } // if there is no accidental

        return OptionalDouble.of(frequencyFrom(note, null, octave));
    }

    static boolean isParsable(String aString) {
        // if string isn't at least a note char and octave
        if (aString.length() < 2) {
            return false;
        }

        // if leading char isn't valid note char
        if (Note.isNoteChar(aString.charAt(0))) {
            return false;
        }

        // Check if the second character is '+' or '-'
        final int startIndex = aString.charAt(1) == Accidental.SHARP.charValue()
                || aString.charAt(1) == Accidental.FLAT.charValue() ? 2 : 1;

        // if second char is '+' or '-' and there aren't any chars left
        if (startIndex == 2 && aString.length() < 3) {
            return false;
        }

        // if there are only digit chars after leading or second '+' or '-' char
        return aString.codePoints().skip(startIndex).allMatch(Character::isDigit);
    }
}
