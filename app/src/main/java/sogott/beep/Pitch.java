package sogott.beep;

import static java.util.Objects.hash;
import java.util.Optional;

/**
 * This class defines an object that can be used to represent the frequency of
 * audio expressed as a musical note. It consists of 3 primary properties:
 *
 * <h2>1.) {@code Note}</h2>
 * The {@link #note() note} property consists of a character value corresponding
 * to one of the musical notes (A-G). These note chars are defined in the
 * {@link Note} enum.
 *
 * <h2>2.) {@code Accidental}</h2>
 * The {@link #accidental() accidental} property consists of a character value
 * if the note is a <i>sharp</i> &sharp; or <i>flat</i> &flat; or {@code null}
 * if it's a natural &natural; (neither a sharp nor a flat). These accidental
 * chars are defined in the {@link Accidental} enum.
 *
 * <h2>3.) {@code Octave}</h2>
 * The {@link #octave() octave} property consists of a non negative integer
 * designating what octave the note of a {@link Pitch} object is in.
 *
 * <h2>4.) String value</h2>
 * The above 3 properties can be combined into a single {@code String} that
 * represents a {@link Pitch} object. It consists of a leading note char,
 * followed by an accidental char or nothing if it doesn't have one, followed by
 * an octave integer. A string value of each {@link Pitch} object is stored as a
 * {@link #stringValue() stringValue} property. On top of that, the
 * {@link Pitch} class contains the static {@link #parse(String) parse(String)}
 * method capable of parsing a string into a {@link Pitch} object.
 *
 * <p>
 * An example of a 440hz wave would be natural {@code 'A'} (no sharp or flat) in
 * the 4th octave. This would be defined as {@code "A4"}. To designate a B&flat;
 * (flat) musical note in the 2nd octave would be {@code "B-2"}, with B&sharp;
 * (sharp) in the 2nd octave e being {@code "B+2"}. The characters that
 * correspond to musical notes and their accidentals are defined in the
 * {@link Note} and {@link Accidental} enum respectively. A {@link Pitch} object
 * of the note B&sharp; (sharp) in the 2nd octave would be <code>Pitch {
 * note=Note.B, accidental=Accidental.SHARP, octave=2}</code> with a string
 * value of {@code "B+2"}.
 *
 * @see Note
 * @see Accidental
 * @see Audio
 * @see AudioString
 */
final class Pitch implements Comparable<Pitch> {
    final private Note _note;
    final private Accidental _accidental;
    final private int _octave;
    final private String _stringValue;
    final private int _hashCode;
    final private String _toString;

    /**
     * Constructs a {@link Pitch} object instance with the following note,
     * accidental, and octave properties.
     *
     * @param note       The {@link Note} of the constructed {@link Pitch} object.
     *
     * @param accidental The {@link Accidental} (sharp &sharp; or flat &flat;)
     *                   of the constructed {@link Pitch} object or {@code null} if
     *                   it's natural &natural; (neither sharp &sharp; nor flat
     *                   &flat;).
     *
     * @param octave     A non negative {@code int} specifying what octave the note
     *                   of the constructed {@link Pitch} object is in.
     *
     * @throws IllegalArgumentException if the passed note is {@code null} or
     *                                  the octave is negative (less than 0).
     *
     * @see Note
     * @see Accidental
     */
    Pitch(Note note, Accidental accidental, int octave) {
        if (note == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Pitch.class.getSimpleName(), Note.class.getSimpleName()));
        }

        if (octave < 0) {
            throw new IllegalArgumentException("Negative octave: %d".formatted(octave));
        }

        this._note = note;
        this._accidental = accidental;
        this._octave = octave;
        this._stringValue = this._accidental == null
                ? "%c%d".formatted(this._note.charValue(), this._octave)
                : "%c%c%d".formatted(this._note.charValue(), this._accidental.charValue(), this._octave);
        this._hashCode = hash(this._note, this._accidental, this._octave);
        this._toString = "%s {note=%s, accidental=%s, octave=%d, stringValue=\"%s\"}".formatted(
                Pitch.class.getSimpleName(),
                this._note.name(),
                this._accidental == null ? "null" : this._accidental.name(),
                this._octave,
                this._stringValue);
    }

    /**
     * Constructs a {@link Pitch} object instance with the following note and
     * octave properties and its {@link #accidental() accidental} property set
     * to {@code null} designating that its note is natural &natural; (neither
     * sharp &sharp; nor flat &flat;).
     *
     * @param note   The {@link Note} of the constructed {@link Pitch} object.
     *
     * @param octave A non negative {@code int} specifying what octave the note
     *               of the constructed {@link Pitch} object is in.
     *
     * @throws IllegalArgumentException if the octave is negative (less than 0).
     *
     * @see Note
     */
    Pitch(Note note, int octave) {
        this(note, null, octave);
    }

    /**
     * The {@link Note} value of this {@link Pitch} object. That is, one of the
     * char values A-G defined in the {@link Note} enum.
     *
     * @return The {@link Note} value of this {@link Pitch} object.
     *
     * @see Note
     */
    Note note() {
        return this._note;
    }

    /**
     * The {@link Accidental} value of this {@link Pitch} object. That is, one
     * of the char values {@code '+'} or {@code ''-} if the note of this Pitch
     * is a sharp &sharp; or flat &flat; respectively or {@code null} if it's
     * a natural &natural; (neither sharp &sharp; nor flat &flat;).
     *
     * @return The {@link Accidental} value of this {@link Pitch} object.
     *
     * @see Accidental
     */
    Accidental accidental() {
        return this._accidental;
    }

    /**
     * A non negative integer specifying what octave this Pitch's note is in.
     *
     * @return A non negative integer specifying what octave this Pitch's note is
     *         in.
     */
    int octave() {
        return this._octave;
    }

    /**
     * A {@code String} representation of this {@link Pitch} object. Note that
     * this is different from the string returned by the {@link #toString()}
     * method which is more suited for debugging purposes.
     *
     * @return a {@code String} representation of this {@link Pitch} object.
     */
    String stringValue() {
        return this._stringValue;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj ||
                obj instanceof Pitch other
                        && this._note == other._note
                        && this._accidental == other._accidental
                        && this._octave == other._octave;
    }

    @Override
    public int hashCode() {
        return this._hashCode;
    }

    @Override
    public String toString() {
        return this._toString;
    }

    /**
     * Parses a {@code String} to a Pitch object. If the passed string can't be
     * parsed, then an empty optional is returned, otherwise the returned
     * optional contains the Pitch object resulting from parsing the string.
     *
     * @param aString {@code String} to parse to a Pitch object.
     *
     * @return An optional containing the Pitch object resulting from parsing
     *         the string or an empty optional if it can't be parsed.
     *
     * @throws IllegalArgumentException if passed argument is {@code null}.
     */
    static Optional<Pitch> parse(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        // string must be at least a note char and octave int
        if (aString.length() < 2) {
            return Optional.empty();
        }

        final int startIndex = Accidental.isAccidentalChar(aString.charAt(1))
                ? 2
                : 1;

        final String octaveString = aString.substring(startIndex);

        if (octaveString.length() == 0 || !octaveString.codePoints().allMatch(Character::isDigit)) {
            return Optional.empty();
        }

        final int octave = Integer.parseInt(octaveString);

        return Note.fromChar(aString.charAt(0)).map(note -> {
            return startIndex == 2
                    ? Accidental.fromChar(aString.charAt(startIndex - 1))
                            .map(accidental -> Optional.of(new Pitch(note, accidental, octave)))
                            .orElse(Optional.empty())
                    : Optional.of(new Pitch(note, octave));
        }).orElse(Optional.empty());
    }

    /**
     * Returns {@code true} if the passed {@code String} argument can be parsed
     * to a {@link Pitch} object.
     *
     * @param aString {@code String} to check if it can be parsed to a {@link Pitch}
     *                object.
     *
     * @return {@code true} if the passed {@code String} argument can be parsed
     *         to a {@link Pitch} object.
     */
    static boolean isParsable(String aString) {
        if (aString == null) {
            return false;
        }

        // string must be at least a note char and octave int
        if (aString.length() < 2) {
            return false;
        }

        // leading char must be valid note
        if (!Note.isNoteChar(aString.charAt(0))) {
            return false;
        }

        // Check if the second character is accidental ('+' or '-')
        final int startIndex = Accidental.isAccidentalChar(aString.charAt(1)) ? 2 : 1;

        // if second char is accidental ('+' or '-') there must be an octave int after
        // it
        if (startIndex == 2 && aString.length() < 3) {
            return false;
        }

        // only an octave int after leading note or second accidental '+' or '-' char
        return aString.codePoints().skip(startIndex).allMatch(Character::isDigit);
    }

    @Override
    public int compareTo(Pitch other) {
        int compareResult = Integer.compare(this._octave, other._octave);

        if (compareResult != 0) {
            return compareResult;
        }

        compareResult = this._note.compareTo(other._note);

        if (compareResult != 0) {
            return compareResult;
        }

        return this._accidental.compareTo(other._accidental);
    }
}
