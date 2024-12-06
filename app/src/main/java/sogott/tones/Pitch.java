package sogott.tones;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.hash;

/**
 * This class defines an object that can be used to represent the frequency of
 * audio expressed as a musical note. It consists of 3 primary properties:
 *
 * <h2>1.) {@code Pitch Letter}</h2>
 * The {@link #letter() letter} property consists of a character value
 * corresponding to one of the musical notes (A-G). These note chars are defined
 * in the {@link PitchLetter} enum.
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
 * <hr>
 * In addition to the 3 primary properties above, it also contains the following
 * property as well:
 *
 * <h3>4.) String value</h3>
 * The above 3 primary properties can be combined into a single {@code String}
 * that represents a {@link Pitch} object. It consists of a leading note char,
 * followed by an accidental char or nothing if it doesn't have one, followed by
 * an octave integer. A string value of each {@link Pitch} object is stored as a
 * {@link #stringValue() stringValue} property. On top of that, the
 * {@link Pitch} class contains the static {@link #parse(String) parse(String)}
 * method capable of parsing a string into a {@link Pitch} object.
 *
 * <h3>4.) Pitch class</h3>
 * The {@link #pitchClass() pitchClass} is simply an object that encapsulate
 * this object's {@link PitchLetter} and {@link Accidental}.
 *
 * <p>
 * An example of a 440hz wave would be natural {@code 'A'} (no sharp or flat) in
 * the 4th octave. This would be defined as {@code "A4"}. To designate a B&flat;
 * (flat) musical note in the 2nd octave would be {@code "B-2"}, with B&sharp;
 * (sharp) in the 2nd octave e being {@code "B+2"}. The characters that
 * correspond to musical notes and their accidentals are defined in the
 * {@link PitchLetter} and {@link Accidental} enum respectively. A {@link Pitch}
 * object
 * of the note B&sharp; (sharp) in the 2nd octave would be <code>Pitch {
 * note=Note.B, accidental=Accidental.SHARP, octave=2}</code> with a string
 * value of {@code "B+2"}.
 *
 * @see PitchLetter
 * @see Accidental
 * @see PitchClass
 * @see Audio
 * @see AudioString
 */
final class Pitch {

    final private PitchClass _pitchClass;
    final private int _octave;
    final private double _frequency;
    final private String _stringValue;
    final private int _hashCode;
    final private String _toString;

    /**
     * Construct a {@link Pitch} object instance with the provided
     * {@link PitchClass} and {@code octave}.
     *
     * @param pitchClass The {@link PitchClass} of the instantiated {@link Pitch}
     *                   object.
     *
     * @param octave     A non negative (0 or greater) {@code int} octave of the
     *                   instantiated {@link Pitch}.
     *
     * @throws IllegalArgumentException If the passed {@link PitchClass} is
     *                                  {@code null} or the octave is negative
     *                                  (less than 0).
     */
    Pitch(PitchClass pitchClass, int octave) {
        if (pitchClass == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Pitch.class.getSimpleName(), PitchClass.class.getSimpleName()));
        }

        if (octave < 0) {
            throw new IllegalArgumentException("Negative octave: " + octave);
        }

        this._pitchClass = pitchClass;
        this._octave = octave;

        final int octaveOffset = (this._octave - 4) * 12;
        this._frequency = 440 * Math.pow(
            2,
            (this._pitchClass.letter().offset() + (this._pitchClass.accidental().offset()) + octaveOffset) / 12.0
        );
        this._stringValue = this._pitchClass.stringValue() + this._octave;
        this._hashCode = hash(this._pitchClass, this._octave);
        this._toString = "%s {note=%s, accidental=%s, octave=%d, stringValue=\"%s\"}".formatted(
                Pitch.class.getSimpleName(),
                this._pitchClass.letter().name(),
                this._pitchClass.accidental().name(),
                this._octave,
                this._stringValue);
    }

    /**
     * Constructs a {@link Pitch} object instance with the following
     * {@link PitchLetter}, {@link Accidental}, and octave properties.
     *
     * @param pitchLetter The {@link PitchLetter} of the constructed {@link Pitch}
     *                    object.
     *
     * @param accidental  The {@link Accidental} (sharp &sharp; or flat &flat;)
     *                    of the constructed {@link Pitch} object or {@code null} if
     *                    it's natural &natural; (neither sharp &sharp; nor flat
     *                    &flat;).
     *
     * @param octave      A non negative {@code int} specifying what octave the note
     *                    of the constructed {@link Pitch} object is in.
     *
     * @throws IllegalArgumentException If the passed {@link PitchLetter} is
     *                                  {@code null} or the octave is negative
     *                                  (less than 0).
     *
     * @see PitchLetter
     * @see Accidental
     */
    Pitch(PitchLetter pitchLetter, Accidental accidental, int octave) {
        this(new PitchClass(pitchLetter, accidental), octave);
    }

    /**
     * Constructs a {@link Pitch} object instance with the following note and
     * octave properties and its {@link #accidental() accidental} property set
     * to {@link Accidental#NATURAL NATURAL} designating that its note is
     * natural &natural; (neither sharp &sharp; nor flat &flat;).
     *
     * @param pitchLetter The {@link PitchLetter} of the constructed {@link Pitch}
     *                    object.
     *
     * @param octave      A non negative {@code int} specifying what octave the note
     *                    of the constructed {@link Pitch} object is in.
     *
     * @throws IllegalArgumentException If the passed {@link PitchLetter} is
     *                                  {@code null} or the octave is negative
     *                                  (less than 0).
     *
     * @see PitchLetter
     */
    Pitch(PitchLetter pitchLetter, int octave) {
        this(pitchLetter, Accidental.NATURAL, octave);
    }

    /**
     * The {@link PitchClass} value of this {@link Pitch} object.
     *
     * @return The {@link PitchClass} value of this {@link Pitch} object.
     *
     * @see PitchClass
     */
    PitchClass pitchClass() {
        return this._pitchClass;
    }

    /**
     * The {@link PitchLetter} value of this {@link Pitch} object. That is, one
     * of the {@code char} values A-G defined in the {@link PitchLetter} enum.
     *
     * @return The {@link PitchLetter} value of this {@link Pitch} object.
     *
     * @see PitchLetter
     */
    PitchLetter letter() {
        return this._pitchClass.letter();
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
        return this._pitchClass.accidental();
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
     * The {@code double} frequency of this {@link Pitch} derived from its
     * {@link #letter() letter}, {@link #accidental() accidental}, and
     * {@link #octave() octave}.
     *
     * @return The {@code double} frequency of this {@link Pitch} derived from its
     *         {@link #letter() letter}, {@link #accidental() accidental}, and
     *         {@link #octave() octave}.
     */
    double frequency() {
        return this._frequency;
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
                        && this._pitchClass.equals(other._pitchClass)
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
     * @throws IllegalArgumentException if passed {@code String} argument is {@code null}.
     */
    static Optional<Pitch> parse(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        return PitchClass.parsePrefix(aString)
            .filter(pitchClassAndStringEntry -> aString.length() > pitchClassAndStringEntry.getValue().length()
                && aString.codePoints().skip(pitchClassAndStringEntry.getValue().length()).allMatch(Character::isDigit))
            .map(pitchClassAndStringEntry ->
                new Pitch(
                    pitchClassAndStringEntry.getKey(),
                    Integer.parseInt(aString, pitchClassAndStringEntry.getValue().length(), aString.length(), 10)
                )
            );
    }

    /**
     * If the passed {@code String} argument starts with a leading prefix that
     * can be parsed into a {@link Pitch}, returns an {@link Optional}
     * containing a {@link Map.Entry} that contains the resulting {@link Pitch}
     * object of parsing it as the entry key and the raw {@code String} prefix
     * that was parsed as the entry value.
     *
     * @param aString
     *
     * @return An {@link Optional} containing a {@link Map.Entry} that contains
     * the resulting {@link Pitch} object of parsing the leading prefix of the
     * passed {@code String} argument as the entry key and the raw {@code String}
     * prefix that was parsed as the entry value.
     *
     * @throws IllegalArgumentException If the passed {@code String} argument is
     * {@code null}.
     */
    static Optional<Map.Entry<Pitch, String>> parsePrefix(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null String");
        }

        return PitchClass.parsePrefix(aString)
            .filter(pitchClassAndStringEntry -> aString.length() > pitchClassAndStringEntry.getValue().length())
            .flatMap(pitchClassAndStringEntry ->
                {
                    final int pitchClassStringLength = pitchClassAndStringEntry.getValue().length();

                    final int firstNonDigitCharIndex = pitchClassAndStringEntry.getValue().length()
                        + (int) aString.codePoints()
                            .skip(pitchClassAndStringEntry.getValue().length())
                            .takeWhile(Character::isDigit)
                            .count();

                    if (firstNonDigitCharIndex == pitchClassStringLength)
                    {
                        return Optional.empty();
                    }

                    final int octave = Integer.parseInt(aString, pitchClassStringLength, firstNonDigitCharIndex, 10);

                    return Optional.of(Map.entry(
                        new Pitch(
                            pitchClassAndStringEntry.getKey(),
                            octave
                        ),
                        aString.substring(0, firstNonDigitCharIndex)
                    ));
                }
            );
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
        if (!PitchLetter.isPitchLetter(aString.charAt(0))) {
            return false;
        }

        // Check if the second character is accidental ('+', '=', or '-')
        final int startIndex = Accidental.isAccidentalChar(aString.charAt(1)) ? 2 : 1;

        // if second char is accidental ('+', '=', or '-') there must be an
        // octave int after it
        if (startIndex == 2 && aString.length() < 3) {
            return false;
        }

        // only an octave int after leading note or accidental '+', '=', or '-' char
        return aString.codePoints().skip(startIndex).allMatch(Character::isDigit);
    }
}
