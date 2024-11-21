package sogott.tones;

final record PitchClassAndAccidental(PitchLetter pitchLetter, Accidental accidental) {
    PitchClassAndAccidental {
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
