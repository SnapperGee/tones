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

    PitchClass(PitchLetter letter) {
        this(letter, Accidental.NATURAL);
    }

    Pitch toPitch(int octave) {
        return new Pitch(this.letter, this.accidental, octave);
    }

    String stringValue() {
        return this.accidental == Accidental.NATURAL
                ? Character.toString(this.letter.charValue())
                : new StringBuilder(2)
                        .append(this.letter.charValue())
                        .append(this.accidental.charValue())
                        .toString();
    }
}
