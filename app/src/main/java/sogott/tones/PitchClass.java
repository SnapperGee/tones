package sogott.tones;

import java.util.Map;
import java.util.Optional;

/**
     * A container for a {@link PitchLetter} and {@link Accidental} object.
     *
     * @param letter The {@link PitchLetter} of this {@link PitchClass}.
     * @param accidental The {@link Accidental} of this {@link PitchClass}.
     *
     * @see PitchLetter
     * @see Accidental
     */
final record PitchClass(PitchLetter letter, Accidental accidental) {

    /**
     * A container for a {@link PitchLetter} and {@link Accidental} object.
     *
     * @param letter The {@link PitchLetter} of this {@link PitchClass}.
     * @param accidental The {@link Accidental} of this {@link PitchClass}.
     *
     * @throws IllegalArgumentException If the {@link PitchLetter} or
     *         {@link Accidental} is {@code null}.
     *
     * @see PitchLetter
     * @see Accidental
     */
    PitchClass {
        if (letter == null) {
            throw new IllegalArgumentException("Null " + PitchLetter.class.getSimpleName());
        }

        if (accidental == null) {
            throw new IllegalArgumentException("Null " + Accidental.class.getSimpleName());
        }
    }

    /**
     * Constructs a {@link PitchClass} with the provided {@link PitchLetter}
     * {@code letter} and {@link Accidental#NATURAL} {@code accidental}.
     *
     * @param letter The {@link PitchLetter} of the {@link PitchClass} object to create.
     *
     * @see PitchLetter
     */
    PitchClass(PitchLetter letter) {
        this(letter, Accidental.NATURAL);
    }

    /**
     * Returns the {@code String} representation of this {@link PitchClass}
     * object.
     *
     * @return The {@code String} representation of this {@link PitchClass}
     *         object.
     */
    String stringValue() {
        return this.accidental == Accidental.NATURAL
                ? Character.toString(this.letter.charValue())
                : new StringBuilder(2)
                        .append(this.letter.charValue())
                        .append(this.accidental.charValue())
                        .toString();
    }

    /**
     * If the passed {@code String} argument starts with a leading prefix that
     * can be parsed into a {@link PitchClass}, returns an {@link Optional}
     * containing a {@link Map.Entry} that contains the resulting
     * {@link PitchClass} object of parsing it as the entry key and the raw
     * {@code String} prefix that was parsed as the entry value.
     *
     * @param aString {@code String} to attempt to parse the leading prefix from
     *                to a {@link PitchClass} object and to extract the parsed
     *                leading prefix segment.
     *
     * @return An {@link Optional} containing a {@link Map.Entry} that contains
     * the resulting {@link PitchClass} object of parsing the leading prefix of
     * the passed {@code String} argument as the entry key and the raw
     * {@code String} prefix that was parsed as the entry value.
     *
     * @throws IllegalArgumentException If the passed {@code String} argument is
     * {@code null}.
     */
    static Optional<Map.Entry<PitchClass, String>> parsePrefix(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null String");
        }

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
