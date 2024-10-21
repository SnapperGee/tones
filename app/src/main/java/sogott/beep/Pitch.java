package sogott.beep;

import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.OptionalDouble;

final record Pitch(Note note, Accidental accidental, int octave) {
    Pitch {
        requireNonNull(note, "Null %s %s.".formatted(Pitch.class.getSimpleName(), Note.class.getSimpleName()));
    }

    double frequency() {
        return frequencyFrom(note, accidental, octave);
    }

    static Pitch create(Note note, Accidental accidental, int octave) {
        return new Pitch(note, accidental, octave);
    }

    static Pitch create(Note note, int octave) {
        return new Pitch(note, null, octave);
    }

    static double frequencyFrom(Note note, Accidental accidental, int octave) {
        final int octaveOffset = (octave - 4) * 12;
        final double noteFrequency = 440
                * Math.pow(2,
                        (note.offset() + (accidental == null ? 0 : accidental.offset()) + octaveOffset)
                                / 12.0);
        return noteFrequency;
    }

    static double frequencyFrom(Note note, int octave) {
        return frequencyFrom(note, null, octave);
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
            final Accidental accidental = Accidental.fromChar(aString.charAt(1)).orElseThrow();
            return OptionalDouble.of(frequencyFrom(note, accidental, octave));
        } // if there is no accidental

        return OptionalDouble.of(frequencyFrom(note, null, octave));
    }
}
