package sogott.tones;

import java.util.Optional;

/**
 * Enum of values to represent musical <strong><em>accidental</em></strong>
 * characters, the plus ({@code '+'}) character for a <i>sharp</i> &sharp; and
 * the minus ({@code '-'}) character for a flat &flat;, and static methods for
 * processing characters in ways related to these accidental enum values.
 *
 * <p>
 * Each accidental value consists of an {@link #offset() offset} {@code int}
 * value used by the static methods of the {@link Frequency} class to convert it
 * into a frequency.
 *
 * <p>
 * Its {@link #charValue() charValue} is also used for parsing {@code String}s
 * to a {@link Pitch} object by the {@link AudioString} and {@link Pitch} class.
 *
 * @see AudioString
 * @see Frequency
 * @see Pitch
 */
enum Accidental {

    /**
     * The value for the <strong><em>sharp</em></strong> &sharp; accidental
     * represented via the plus, {@code '+'}, {@code char} value with an offset
     * of {@code 1}.
     */
    SHARP('+', 1),

    /**
     * The value for the <strong><em>flat</em></strong> &flat; accidental
     * represented via the minus, {@code '-'}, {@code char} value with an offset
     * of {@code -1}.
     */
    FLAT('-', -1);

    final private char _char;
    final private int _offset;

    private Accidental(char charValue, int offset) {
        this._char = charValue;
        this._offset = offset;
    }

    /**
     * The {@code int} value of this {@link Accidental} used by the static
     * methods of the {@link Frequency} class to generate a {@code double}
     * frequency.
     *
     * @return {@code int} value of this {@link Accidental} used by the static
     *         methods of the {@link Frequency} class to generate a {@code double}
     *         frequency.
     */
    int offset() {
        return this._offset;
    }

    /**
     * The {@code char} value of this {@link Accidental} used by the
     * the {@link AudioString} class to parse {@code String}s to
     * {@link Pitch} objects.
     *
     * @return The {@code char} value of this {@link Accidental} used by the
     *         the {@link AudioString} class to parse {@code String}s to
     *         {@link Pitch}.
     *
     * @see AudioString
     * @see Pitch
     */
    char charValue() {
        return this._char;
    }

    /**
     * Returns {@code true} if the passed {@code char} argument matches either
     * of the {@link #SHARP} or {@link #FLAT} {@link #charValue() charValue}s
     * and can therefore be interpreted as such.
     *
     * @param aChar {@code char} to check if it can be interpreted as an
     *              {@link Accidental} value.
     *
     * @return {@code true} if the passed {@code char} argument can be
     *         interpreted as an {@link Accidental} value.
     */
    static boolean isAccidentalChar(char aChar) {
        return Accidental.SHARP.charValue() == aChar || Accidental.FLAT.charValue() == aChar;
    }

    /**
     * Attempts to convert the passed {@code char} value to an
     * {@link Accidental}. If the passed character can't be interpreted as an
     * {@link Accidental}, then an empty Optional is returned, otherwise an
     * Optional containing the resulting {@link Accidental} is returned.
     *
     * @param aChar {@code char} to attempt to convert to an {@link Accidental}.
     *
     * @return an Optional containing the {@link Accidental} if the passed
     *         {@code char} can be converted to one, otherwise an empty Optional.
     */
    static Optional<Accidental> fromChar(char aChar) {
        return SHARP.charValue() == aChar
                ? Optional.of(SHARP)
                : FLAT.charValue() == aChar
                        ? Optional.of(FLAT)
                        : Optional.empty();
    }
}
