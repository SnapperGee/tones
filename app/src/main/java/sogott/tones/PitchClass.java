package sogott.tones;

import java.util.Optional;

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

    Pitch toPitchWithOctave(int octave) {
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

    static Optional<PitchClass> parse(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        if (aString.length() == 1) {
            return PitchLetter.fromChar(aString.charAt(0))
                    .map(pitchLetter -> new PitchClass(pitchLetter, Accidental.NATURAL));
        }

        if (aString.length() == 2) {
            return PitchLetter.fromChar(aString.charAt(0))
                    .map(pitchLetter -> Accidental.fromChar(aString.charAt(1))
                            .map(accidental -> new PitchClass(pitchLetter, accidental)))
                    .orElse(Optional.empty());
        }

        return Optional.empty();
    }
}
