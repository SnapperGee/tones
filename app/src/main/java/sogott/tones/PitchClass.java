package sogott.tones;

final record PitchClass(PitchLetter pitchLetter, Accidental accidental) {
    PitchClass {
        if (pitchLetter == null) {
            throw new IllegalArgumentException("Null " + PitchLetter.class.getSimpleName());
        }

        if (accidental == null) {
            throw new IllegalArgumentException("Null " + Accidental.class.getSimpleName());
        }
    }

    Pitch toPitchWithOctave(int octave) {
        return new Pitch(this.pitchLetter, this.accidental, octave);
    }
}
