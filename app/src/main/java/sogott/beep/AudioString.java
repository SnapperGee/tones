package sogott.beep;

import sogott.beep.Note.Accidental;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.EnumSet;
import java.util.Optional;

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
 * precedes the duration suffix defines the frequency of the audio. This part
 * can be divided into 3 parts, 2 required parts with an optional part in the
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
 * concisely be written as {@code "F+8.2"}
 *
 * @author Snap
 * @see Wave
 * @see Note
 */
final class AudioString implements Comparable<AudioString>, Constable, CharSequence {

    final private static EnumSet<Note> _notes = EnumSet.allOf(Note.class);
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
     * @param aString {@code String} to check if it's parsable to audio.
     * @return {@code true} if passed {@code String} can be parsed to audio.
     */
    static boolean isParsable(String aString) {

        if (aString == null) {
            return false;
        }

        // String needs to be at least a note character, octave integer, period, and
        // duration integer
        if (aString.length() < 4) {
            return false;
        }

        // If there isn't exactly one period character
        if (aString.codePoints().filter(cp -> cp == '.').limit(2).count() != 1) {
            return false;
        }

        // If there is more than 1 right angle bracket/greater than character
        if (aString.codePoints().filter(cp -> cp == '>').limit(2).count() > 1) {
            return false;
        }

        final String[] splitString = aString.split("[.]");

        // String should have 1 period separating frequency and duration
        if (splitString.length != 2) {
            return false;
        }

        final String waveShapePrefixAndFrequency = splitString[0];
        final String durationSuffix = splitString[1];

        // frequency must be at least 2 chars and wave shape prefix can be omitted
        if (waveShapePrefixAndFrequency.length() < 2) {
            return false;
        }

        // Tail of String should be an integer for duration
        if (!durationSuffix.codePoints().allMatch(Character::isDigit)) {
            return false;
        }

        if (waveShapePrefixAndFrequency.endsWith(">")) {
            return false;
        }

        final String[] splitWaveShapePrefixAndFrequency = waveShapePrefixAndFrequency.split(">");

        // if part of string preceding duration suffix doesn't contain a right angle
        // bracket/greater than char
        if (splitWaveShapePrefixAndFrequency.length == 1) {
            final String frequency = splitWaveShapePrefixAndFrequency[0];

            if (frequency.length() < 2) {
                return false;
            }

            // if leading character isn't a valid note character
            if (!Character.isLetter(frequency.charAt(0)) || _notes.stream()
                    .noneMatch(note -> note.charValue() == Character.toUpperCase(frequency.charAt(0)))) {
                return false;
            }

            // if frequency is only 2 chars and second char isn't an octave
            if (frequency.length() == 2 && !Character.isDigit(frequency.charAt(1))) {
                return false;
            }

            if (!frequency.codePoints().skip(frequency.charAt(1) == '+' || frequency.charAt(1) == '-' ? 2 : 1)
                    .allMatch(Character::isDigit)) {
                return false;
            }
        }

        return true;
    }
}

final class AudioStringValidator {
    final private static EnumSet<Note> _notes = EnumSet.allOf(Note.class);
    final private static EnumSet<Wave> _waves = EnumSet.allOf(Wave.class);

    /**
     * Returns {@code true} if string can be parsed as duration (an integer).
     *
     * @param aString String to check if it can be parsed as a duration (an
     *                integer).
     * @return {@code true} if string can be a parsed as duration (an integer).
     */
    boolean isParsableDuration(String aString) {
        return aString != null && aString.length() != 0 && aString.codePoints().allMatch(Character::isDigit);
    }

    boolean isParsableFrequency(String aString) {

        if (aString == null) {
            return false;
        }

        // if string isn't at least a note char and octave
        if (aString.length() < 2) {
            return false;
        }

        final char upperCaseLeadingChar = Character.toUpperCase(aString.charAt(0));

        // if leading char isn't valid note char
        if (_notes.stream()
                .noneMatch(note -> note.charValue() == upperCaseLeadingChar)) {
            return false;
        }

        // Check if the second character is '+' or '-'
        final int startIndex = aString.charAt(1) == '+' || aString.charAt(1) == '-' ? 2 : 1;

        // if second char is '+' or '-' and there aren't any chars left
        if (startIndex == 2 && aString.length() < 3) {
            return false;
        }

        // if there are only digit chars after leading or second '+' or '-' char
        return aString.codePoints().skip(startIndex).allMatch(Character::isDigit);
    }

    /**
     *
     * @author Snap
     * @param aString {@code String} to check if it's parsable to audio.
     * @return {@code true} if passed {@code String} can be parsed to audio.
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

        // If there isn't exactly one period character
        if (aString.codePoints().filter(cp -> cp == '.').limit(2).count() != 1) {
            return false;
        }

        // If there is more than 1 right angle bracket/greater than character
        if (aString.codePoints().filter(cp -> cp == '>').limit(2).count() > 1) {
            return false;
        }

        final String[] splitString = aString.split("[.]");

        // String should have 1 period separating frequency and duration
        if (splitString.length != 2) {
            return false;
        }

        final String waveShapePrefixAndFrequency = splitString[0];
        final String durationSuffix = splitString[1];

        // frequency must be at least 2 chars and wave shape prefix can be omitted
        if (waveShapePrefixAndFrequency.length() < 2) {
            return false;
        }

        // Tail of String should be an integer for duration
        if (!durationSuffix.codePoints().allMatch(Character::isDigit)) {
            return false;
        }

        if (waveShapePrefixAndFrequency.endsWith(">")) {
            return false;
        }

        final String[] splitWaveShapePrefixAndFrequency = waveShapePrefixAndFrequency.split(">");

        // if part of string preceding duration suffix doesn't contain a right angle
        // bracket/greater than char
        if (splitWaveShapePrefixAndFrequency.length == 1) {
            final String frequency = splitWaveShapePrefixAndFrequency[0];

            if (frequency.length() < 2) {
                return false;
            }

            // if leading character isn't a valid note character
            if (!Character.isLetter(frequency.charAt(0)) || _notes.stream()
                    .noneMatch(note -> note.charValue() == Character.toUpperCase(frequency.charAt(0)))) {
                return false;
            }

            // if frequency is only 2 chars and second char isn't an octave
            if (frequency.length() == 2 && !Character.isDigit(frequency.charAt(1))) {
                return false;
            }

            if (!frequency.codePoints().skip(frequency.charAt(1) == '+' || frequency.charAt(1) == '-' ? 2 : 1)
                    .allMatch(Character::isDigit)) {
                return false;
            }
        }

        return true;
    }
}
