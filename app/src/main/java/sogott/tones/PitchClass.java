package sogott.tones;

final record PitchClass(PitchLetter letter, Accidental accidental) {
    PitchClass {
        if (letter == null) {
            throw new IllegalArgumentException("Null " + PitchLetter.class.getSimpleName());
        }

        if (accidental == null) {
            throw new IllegalArgumentException("Null " + Accidental.class.getSimpleName());
        }
    }

    Pitch toPitchWithOctave(int octave) {
        return new Pitch(this.letter, this.accidental, octave);
    }
}
