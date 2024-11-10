package sogott.beep;

import java.util.Set;
import java.util.EnumSet;
import java.util.Optional;
import static java.util.Collections.unmodifiableSet;

/**
 * Enum of values to represent musical <strong><em>note</em></strong>s,
 * characters A-G, and static methods for processing characters in ways related
 * to these musical note enum values. Each note value consists of an
 * {@link #offset() offset} {@code int} value used by the static methods of the
 * {@link Frequency} class to convert it into a frequency.
 *
 * @see AudioString
 * @see Frequency
 * @see Pitch
 */
enum Note {
    A(0), B(2), C(-9), D(-7), E(-5), F(-4), G(-2);

    final private static Set<Note> _notes = unmodifiableSet(EnumSet.allOf(Note.class));

    final private char _char;
    final private int _offset;

    private Note(int offSet) {
        this._char = this.name().charAt(0);
        this._offset = offSet;
    }

    /**
     * The {@code char} value of the note.
     *
     * @return The {@code char} value of the note.
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
     * Attempts to convert a {@code char} to a {@link Note} value. Returns an
     * optional containing the {@link Note} value of the char if it can be
     * converted, otherwise an empty optional is returned.
     *
     * @param aChar {@code char} to attempt to convert to a {@link Note} value.
     *
     * @return An optional containing the {@link Note} value of the char if it
     *         can be converted, otherwise an empty optional
     */
    static Optional<Note> fromChar(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return _notes.stream().filter(note -> note.charValue() == upperCaseChar).findFirst();
    }

    /**
     * Returns {@code true} if the passed {@code char} argument can be
     * interpreted as a {@link Note} value.
     *
     * @param aChar {@code char} to check if it can be interpreted as a
     *              {@link Note} value.
     *
     * @return {@code true} if the passed {@code char} argument can be
     *         interpreted as a {@link Note} value.
     */
    static boolean isNoteChar(char aChar) {
        final char upperCaseChar = Character.toUpperCase(aChar);
        return _notes.stream().anyMatch(note -> note.charValue() == upperCaseChar);
    }
}
