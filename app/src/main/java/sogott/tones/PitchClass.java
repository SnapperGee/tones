package sogott.tones;

import java.util.Optional;

import static java.util.Objects.hash;

final class PitchClass {
    private final PitchLetter _letter;
    private final Accidental _accidental;
    private final int _hashCode;
    private final String _stringValue;
    private final String _toString;

    PitchClass(PitchLetter letter, Accidental accidental) {
        if (letter == null) {
            throw new IllegalArgumentException("Null " + PitchLetter.class.getSimpleName());
        }

        if (accidental == null) {
            throw new IllegalArgumentException("Null " + Accidental.class.getSimpleName());
        }

        this._letter = letter;
        this._accidental = accidental;
        this._hashCode = hash(this._letter, this._accidental);
        this._stringValue = this._accidental == Accidental.NATURAL
                ? Character.toString(this._letter.charValue())
                : new StringBuilder(2)
                        .append(this._letter.charValue())
                        .append(this._accidental.charValue())
                        .toString();
        this._toString = "%s{letter=%s, accidental=%s}".formatted(PitchClass.class.getSimpleName(), this._letter.name(),
                this._accidental.name());
    }

    PitchClass(PitchLetter letter) {
        this(letter, Accidental.NATURAL);
    }

    Pitch toPitch(int octave) {
        return new Pitch(this._letter, this._accidental, octave);
    }

    String stringValue() {
        return this._stringValue;
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PitchClass other
                && this._letter == other._letter
                && this._accidental == other._accidental;
    }

    @Override
    public int hashCode() {
        return this._hashCode;
    }

    @Override
    public String toString() {
        return this._toString;
    }
}
