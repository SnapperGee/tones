package sogott.beep;

import java.util.Optional;
import java.util.OptionalDouble;

final class Frequency {
    static double of(Note note, Accidental accidental, int octave) {
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

    static OptionalDouble of(char noteChar, char accidentalChar, int octave) {
        return Note.fromChar(noteChar)
                .flatMap(note -> Accidental.fromChar(accidentalChar)
                        .map(accidental -> OptionalDouble.of(of(note, accidental, octave))))
                .orElse(OptionalDouble.empty());
    }

    static OptionalDouble of(char note, int octave) {
        final Optional<Note> _note = Note.fromChar(note);

        if (_note.isEmpty()) {
            OptionalDouble.empty();
        }

        return OptionalDouble.of(of(_note.get(), null, octave));
    }

    static OptionalDouble of(String aString) {

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

        // Check if the second character is an accidental ('+' or '-')
        final int startIndex = Accidental.isAccidentalChar(aString.charAt(1)) ? 2 : 1;

        // if second char is accidental ('+' or '-') there must be an octave int after
        // it
        if (startIndex == 2 && aString.length() < 3) {
            return OptionalDouble.empty();
        }

        // if an octave (int) isn't following leading note char or accidental
        if (!aString.codePoints().skip(startIndex).allMatch(Character::isDigit)) {
            return OptionalDouble.empty();
        }

        final int octave = Integer.parseInt(aString.substring(startIndex));

        // if there is an accidental
        if (startIndex == 2) {
            final Accidental accidental = Accidental.fromChar(aString.charAt(1))
                    .orElseThrow(() -> new RuntimeException("Accidental value could not be converted from char."));
            return OptionalDouble.of(of(note, accidental, octave));
        } // if there is no accidental

        return OptionalDouble.of(of(note, null, octave));
    }

    private Frequency() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated.".formatted(Frequency.class.getName()));
    }
}
