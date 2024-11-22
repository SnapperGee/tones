package sogott.tones;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Enum of values to represent musical
 * <strong><em>pitch letter</em></strong>es/<strong><em>pitch
 * letter</em></strong>s, characters A-G, and static methods for processing
 * characters in ways related to these pitch letter enum values.
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
enum PitchLetter {
    /**
     * The enum value for the <strong><em>A</em></strong> pitch letter/letter
     * with an offset value of {@code 0}.
     */
    A(0),

    /**
     * The enum value for the <strong><em>B</em></strong> pitch letter/letter
     * with an offset value of {@code 2}.
     */
    B(2),

    /**
     * The enum value for the <strong><em>C</em></strong> pitch letter/letter
     * with an offset value of {@code -9}.
     */
    C(-9),

    /**
     * The enum value for the <strong><em>D</em></strong> pitch letter/letter
     * with an offset value of {@code -7}.
     */
    D(-7),

    /**
     * The enum value for the <strong><em>E</em></strong> pitch letter/letter
     * with an offset value of {@code -5}.
     */
    E(-5),

    /**
     * The enum value for the <strong><em>F</em></strong> pitch letter/letter
     * with an offset value of {@code -4}.
     */
    F(-4),

    /**
     * The enum value for the <strong><em>G</em></strong> pitch letter/letter
     * with an offset value of {@code -2}.
     */
    G(-2);

    final private static List<PitchLetter> _pitchLetters = unmodifiableList(asList(PitchLetter.values()));

    final private char _char;
    final private int _offset;

    private PitchLetter(int offSet) {
        this._char = this.name().charAt(0);
        this._offset = offSet;
    }

    /**
     * The {@code char} value of the pitch letter/letter.
     *
     * @return The {@code char} value of the pitch letter/letter.
     */
    char charValue() {
        return this._char;
    }

    /**
     * The {@code int} value used by the static methods of the {@link Frequency}
     * class to convert this enum value into a frequency.
     *
     * @return The {@code int} value used by the static methods of the
     *         {@link Frequency} class to convert this enum value into a frequency.
     */
    int offset() {
        return this._offset;
    }

    /**
     * Attempts to case insensitively convert a {@code char} to its
     * {@link PitchLetter} value. Returns an {@link Optional} containing the
     * {@link PitchLetter} of the {@code char} if it can be converted, otherwise
     * an empty {@link Optional} is returned.
     *
     * @param aChar {@code char} to attempt to convert to a {@link PitchLetter}.
     *
     * @return An {@link Optional} containing the {@link PitchLetter} value of
     *         the {@code char} if it can be converted, otherwise an empty
     *         {@link Optional}.
     */
    static Optional<PitchLetter> fromChar(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return _pitchLetters.stream().filter(pitchLetter -> pitchLetter.charValue() == upperCaseChar).findFirst();
    }

    /**
     * Returns {@code true} if the passed {@code char} argument can case
     * insensitively be interpreted as a {@link PitchLetter} value.
     *
     * @param aChar {@code char} to check if it can be interpreted as a
     *              {@link PitchLetter}.
     *
     * @return {@code true} if the passed {@code char} argument can be case
     *         insensitively interpreted as a {@link PitchLetter}.
     */
    static boolean isPitchLetter(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return _pitchLetters.stream().anyMatch(pitchLetter -> pitchLetter.charValue() == upperCaseChar);
    }
}
