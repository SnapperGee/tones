package sogott.beep;

import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.EnumSet;
import java.util.Optional;

import sogott.beep.Note.Accidental;

/**
 * A {@code String} can be parsed to audio if it has at least enough
 * information to know the frequency and duration of the audio. These parts of
 * the string are referred to as the <i>frequency</i> and <i>duration</i>
 * respectively. The duration also being referred to as the <i>duration
 * suffix</i> as it's always the last part of the string. An optional leading
 * prefix can specify the wave shape of the audio. This part is referred to as
 * the <i>wave</i> or <i>wave shape prefix</i> as it's always the leading part
 * of the string if it's present.
 *
 * <p>
 * So an {@link AudioString} can be divided into 3 parts, 1 optional prefix and
 * 2 required parts:
 *
 * <ol>
 * <li>Wave shape prefix (optional)
 * <li>* <b>Frequency</b> (required)
 * <li>* <b>Duration</b> suffix (required)
 * </ol>
 *
 * <h3>1.) Wave shape prefix</h3>
 * The leading portion <i>can</i> specify what kind of wave shape the audio
 * consists of. The types of wave shapes can be designated via:
 *
 * <ul>
 * <li>{@code "SIN"}
 * <li>{@code "TRIANGLE"}
 * <li>{@code "SAWUP"}
 * <li>{@code "SAWDOWN"}
 * </ul>
 *
 * The right angle bracket/greater than character ({@code '>'}) is then used
 * to separate it from the frequency section of the string which follows it.
 * An example of a wave shape prefix for a triangle wave would be
 * {@code "TRIANGLE>"} with the rest of the string coming after the
 * {@code '>'} character (not shown in example). The types of supported wave
 * shapes are defined in the {@link Wave} enum.
 *
 * <p>
 * If the leading wave shape prefix is omitted it defaults to a sin wave.
 *
 * <h3>2.) Frequency</h3>
 * The part that follows the wave shape prefix (if it's present) and
 * precedes the duration defines the frequency of the audio. This part can
 * be divided into 3 parts, 2 required parts with an optional part in the
 * middle:
 *
 * <ol>
 * <li>A leading alpha character corresponding to a musical note specifying the
 * pitch.
 * <li>An optional plus ({@code '+'}) or minus ({@code '-'}) character
 * specifying if the note is a sharp or flat.
 * <li>And an integer specifying the octave of the note.
 * </ol>
 *
 * The period character ({@code '.'}) is then used to separate it from the
 * duration section of the string which comes after. An example of a 440hz wave
 * would be natural A (no sharp or flat) in the 4th octave. This would be
 * defined as {@code "A4"}. To designate a B&flat; (flat) musical note in the
 * 2nd octave would be {@code "B-2"}, with B&sharp; (sharp) being
 * {@code "B+2"}. The characters that correspond to musical notes are defined
 * in the {@link Note} enum.
 *
 * <h3>3.) Duration</h3>
 * The final part specifies the duration of the audio. This is an integer that
 * designates the length of the audio </strong><b>relative to the
 * tempo/bpm</b></strong>. An easy way to think of it is that if the duration
 * is integer <i>n</i>, then the length of the note will be 1/<i>n</i>. So, if
 * <i>n</i> were 1, then the duration would be 1/1 which would be a whole note.
 * If <i>n</i> were 4, that'd result in 1/4 so it'd be a quarter note etc.
 *
 * <p>
 * So putting all this together, a {@code String} to explicitly designate a sin
 * wave that is an F&sharp; (sharp) half note in the 8th octave would be
 * {@code "SIN>F+8.2"}. Because a sin wave is the default wave shape, the
 * leading {@code "SIN>"} prefix could have been omitted and it could more
 * concisely written as {@code "F+8.2"}
 *
 * @author Snap
 * @see Wave
 * @see Note
 */
final class AudioString implements Comparable<AudioString>, Constable, CharSequence {

    final private static EnumSet<Wave> _waves = EnumSet.allOf(Wave.class);

    final private String _string;
    final private Wave _wave;
    final private double _frequency;
    final private int _duration;
    final private String _toString;

    AudioString(String string) {
        this._string = string;

        final String[] splitString = this._string.split(">", 2);

        this._wave = splitString.length == 2
                ? _waves.stream()
                        .filter(wave -> {
                            final String waveString = wave.name().replace("_", "");
                            return splitString[0].equalsIgnoreCase(waveString);
                        })
                        .findFirst()
                        .orElse(Wave.SIN)
                : Wave.SIN;

        final String[] frequencyAndDuration = splitString.length == 2
                ? splitString[1].split("[.]")
                : splitString[0].split("[.]");

        this._frequency = Character.isAlphabetic(frequencyAndDuration[0].charAt(0))
                ? frequencyAndDuration[0].length() == 3
                        ? Note.Parser.frequency(frequencyAndDuration[0].charAt(0),
                                frequencyAndDuration[0].charAt(1) == '+' ? Accidental.SHARP : Accidental.FLAT,
                                Character.getNumericValue(frequencyAndDuration[0].charAt(2)))
                        : Note.Parser.frequency(frequencyAndDuration[0].charAt(0),
                                Character.getNumericValue(frequencyAndDuration[0].charAt(1)))
                : Double.parseDouble(frequencyAndDuration[0]);

        this._duration = Integer.parseInt(frequencyAndDuration[1]);

        this._toString = "%s {string=%s, wave=%s, frequency=%f, duration=%d}".formatted(
                AudioString.class.getSimpleName(),
                this._string, this._wave.name(), this._frequency, this._duration);
    }

    String string() {
        return this._string;
    }

    Wave wave() {
        return this._wave;
    }

    double frequency() {
        return this._frequency;
    }

    int duration() {
        return this._duration;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AudioString operand && this._string.equals(operand._string);
    }

    @Override
    public int hashCode() {
        return this._string.hashCode();
    }

    @Override
    public int compareTo(AudioString operand) {
        return this._string.compareTo(operand._string);
    }

    @Override
    public Optional<? extends ConstantDesc> describeConstable() {
        return this._string.describeConstable();
    }

    @Override
    public int length() {
        return this._string.length();
    }

    @Override
    public char charAt(int index) {
        return this._string.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this._string.subSequence(end, end);
    }

    @Override
    public String toString() {
        return this._toString;
    }

    /**
     *
     * @author Snap
     * @param aString {@code String} to check if it's a valid operand.
     * @return {@code true} if passed {@code String} argument is a valid operand.
     * @see Wave
     */
    static boolean isValid(String aString) {

        if (aString == null) {
            return false;
        }

        // String needs to be at least a note character, octave integer, period, and
        // duration integer
        if (aString.length() < 4) {
            return false;
        }

        final String[] splitString = aString.split("[.]");

        // String should have 1 period separating frequency and duration
        if (splitString.length != 2) {
            return false;
        }

        // Tail of String should be an integer for duration
        if (!splitString[1].codePoints().allMatch(Character::isDigit)) {
            return false;
        }

        // Part preceding duration tail should be frequency.

        return true;
    }
}
