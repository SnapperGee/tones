package sogott.tones;

final record PitchClassAndAccidental(PitchClass pitchClass, Accidental accidental) {
    PitchClassAndAccidental {
        if (pitchClass == null) {
            throw new IllegalArgumentException("Null " + PitchClass.class.getSimpleName());
        }

        if (accidental == null) {
            throw new IllegalArgumentException("Null " + Accidental.class.getSimpleName());
        }
    }

    Pitch toPitchWithOctave(int octave) {
        return new Pitch(this.pitchClass, this.accidental, octave);
    }
}
