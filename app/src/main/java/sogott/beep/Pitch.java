package sogott.beep;

import sogott.beep.Note.Accidental;

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
        return frequency(null, octave);
    }
}
