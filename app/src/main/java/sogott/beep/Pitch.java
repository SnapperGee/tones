package sogott.beep;

import sogott.beep.Note.Accidental;
import java.util.Optional;
import java.util.OptionalDouble;

final class Pitch {
    static double frequency(Note note, Accidental accidental, int octave) {
        final int octaveOffset = (octave - 4) * 12;
        final double noteFrequency = 440
                * Math.pow(2,
                        (note.offset() + (accidental == null ? 0 : accidental.offset()) + octaveOffset)
                                / 12.0);
        return noteFrequency;
    }

    static double frequency(Note note, int octave) {
        return frequency(note, null, octave);
    }

    static OptionalDouble frequency(String aString) {

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
        final int startIndex = aString.charAt(1) == Note.Accidental.SHARP.charValue()
                || aString.charAt(1) == Note.Accidental.FLAT.charValue() ? 2 : 1;

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
            final Optional<Accidental> accidentalOptional = Note.Accidental.fromChar(aString.charAt(1));
            if (accidentalOptional.isEmpty()) {
                return OptionalDouble.empty();
            }
            final Accidental accidental = accidentalOptional.get();
            final double frequency = frequency(note, accidental, octave);
            return OptionalDouble.of(frequency);
        } // if there is no accidental

        final double frequency = frequency(note, null, octave);

        return OptionalDouble.of(frequency);
    }

    private Pitch() {
        throw new UnsupportedOperationException("This is a static class and cannot be instantiated.");
    }
}
