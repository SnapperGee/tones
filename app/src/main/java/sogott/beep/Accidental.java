package sogott.beep;

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
 * to a {@link Pitch} object.
 *
 * @see AudioString
 * @see Frequency
 * @see Pitch
 */
enum Accidental {
    SHARP('+', 1),
    FLAT('-', -1);

    final private char _char;
    final private int _offset;

    private Accidental(char charValue, int offset) {
        this._char = charValue;
        this._offset = offset;
    }

    int offset() {
        return this._offset;
    }

    char charValue() {
        return this._char;
    }

    static boolean isAccidentalChar(char aChar) {
        return Accidental.SHARP.charValue() == aChar || Accidental.FLAT.charValue() == aChar;
    }

    static Optional<Accidental> fromChar(char aChar) {
        return SHARP.charValue() == aChar
                ? Optional.of(SHARP)
                : FLAT.charValue() == aChar
                        ? Optional.of(FLAT)
                        : Optional.empty();
    }
}
