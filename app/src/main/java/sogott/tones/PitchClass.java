package sogott.tones;

import java.util.Map;
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

    static Optional<Map.Entry<PitchClass, String>> parsePrefix(String aString) {
        if (aString.isBlank()) {
            return Optional.empty();
        }

        return PitchLetter.fromChar(aString.charAt(0))
            .flatMap(pitchLetter -> aString.length() > 1 && Accidental.isAccidentalChar(aString.charAt(1))
                // if string starts with pitch letter and accidental
                ? Accidental.fromChar(aString.charAt(1)).map(accidental -> Map.entry(new PitchClass(pitchLetter, accidental), aString.substring(0, 2)))
                // if string starts with only pitch letter
                : Optional.of(Map.entry(new PitchClass(pitchLetter), aString.substring(0, 1))));
    }
}
