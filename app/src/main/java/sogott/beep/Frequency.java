package sogott.beep;

import java.util.OptionalDouble;

final class Frequency {
    static double from(Note note, Accidental accidental, int octave) {
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

    static double from(Note note, int octave) {
        return from(note, null, octave);
    }

    static double from(Pitch pitch) {
        return from(pitch.note(), pitch.accidental(), pitch.octave());
    }

    static OptionalDouble from(char noteChar, char accidentalChar, int octave) {
        return Note.fromChar(noteChar)
                .flatMap(note -> Accidental.fromChar(accidentalChar)
                        .map(accidental -> OptionalDouble.of(from(note, accidental, octave))))
                .orElse(OptionalDouble.empty());
    }

    static OptionalDouble from(char noteChar, int octave) {
        return Note.fromChar(noteChar)
                .map(note -> OptionalDouble.of(from(note, octave)))
                .orElse(OptionalDouble.empty());
    }

    static OptionalDouble parse(String aString) {
        return Pitch.parse(aString)
                .map(pitch -> OptionalDouble.of(from(pitch)))
                .orElse(OptionalDouble.empty());
    }

    private Frequency() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated.".formatted(Frequency.class.getSimpleName()));
    }
}
