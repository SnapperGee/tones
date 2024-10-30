package sogott.beep;

import static java.util.Collections.unmodifiableSet;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

/**
 * A {@code String} can be parsed to audio if it has the information to define
 * the <i>wave shape</i>, <i>pitch/frequency</i>, and <i>duration</i> of the
 * audio. An {@link AudioString} can be divided into 3 segments, each one
 * defining each of these 3 parts:
 *
 * <ol>
 * <li>Wave shape prefix
 * <li>Pitch Frequency
 * <li>Duration suffix
 * </ol>
 *
 * <h3>1.) Wave shape prefix</h3>
 * The leading segment specifies what kind of wave shape the audio consists of.
 * The types of wave shapes can be designated via:
 *
 * <ul>
 * <li>{@code "SIN"} for a <i>sin</i> wave shape.
 * <li>{@code "TRI"} for a <i>triangle</i> wave shape.
 * <li>{@code "SQR"} for a <i>square</i> wave shape.
 * <li>{@code "SUP"} for a <i>sawtooth up</i> wave shape.
 * <li>{@code "SDN"} for a <i>sawtooth down</i> wave shape.
 * </ul>
 *
 * The right angle bracket/greater than character ({@code '>'}) is then used
 * to separate it from the frequency segment of the string which follows it. An
 * example of a wave shape prefix for a triangle wave would be {@code "TRI>"}
 * with the rest of the string coming after the {@code '>'} character. The types
 * of supported wave shapes are defined in the {@link Wave} enum.
 *
 * <p>
 * If the leading wave shape prefix is omitted it defaults to a sin wave.
 *
 * <h3>2.) Pitch Frequency</h3>
 * The segment that follows the wave shape prefix (if it's present) and
 * precedes the duration suffix segment defines the pitch/frequency of the
 * audio. This segment can be divided into 3 parts::
 *
 * <ol>
 * <li>A leading alpha character corresponding to a musical note specifying the
 * pitch.
 * <li>A plus ({@code '+'}) or minus ({@code '-'}) character specifying if the
 * note is a sharp or flat or none if it's a natural.
 * <li>And an integer specifying the octave of the note.
 * </ol>
 *
 * The period character ({@code '.'}) is then used to separate it from the
 * duration segment of the string which comes after. An example of a 440hz wave
 * would be natural {@code 'A'} (no sharp or flat) in the 4th octave. This would
 * be defined as {@code "A4"}. To designate a B&flat; (flat) musical note in the
 * 2nd octave would be {@code "B-2"}, with B&sharp; (sharp) in the 2nd octave
 * being {@code "B+2"}. The characters that correspond to musical notes are
 * defined in the {@link Note} enum.
 *
 * <h3>3.) Duration suffix</h3>
 * The final segment specifies the duration of the audio. This is an integer
 * that designates the length of the audio </strong><b>relative to the
 * tempo/bpm</b></strong>. An easy way to think of it is that if the duration
 * is integer <i>N</i>, then the length of the note will be 1/<i>N</i>. So, if
 * <i>N</i> were 1, then the duration would be 1/1 which would be a whole note.
 * If <i>N</i> were 4, that'd result in 1/4 so it'd be a quarter note etc.
 *
 * <p>
 * So putting all this together, a {@code String} to explicitly designate a sin
 * wave that is an F&sharp; (sharp) half note in the 8th octave would be
 * {@code "SIN>F+8.2"}. Since an {@link AudioString} defaults to a sin wave if
 * no wave shape prefix is included, this could also be written as
 * {@code "F+8.2"}.
 *
 * @author Snap
 * @see Audio
 * @see Wave
 * @see Note
 */
final class AudioString {
    // static Optional<Audio> parse(String aString) {
    // if (!isParsable(aString)) {
    // return Optional.empty();
    // }

    // final String[] splitString = aString.split("\\.");
    // final String waveShapePrefixAndPitch = splitString[0];
    // final String durationString = splitString[1];
    // final int duration = Integer.parseInt(durationString);
    // final String[] splitWaveShapePrefixAndPitch =
    // waveShapePrefixAndPitch.split(">");
    // final Wave wave = splitWaveShapePrefixAndPitch.length == 1 ? Wave.SIN
    // : Wave.parse(splitWaveShapePrefixAndPitch[1]).orElseThrow();
    // final Pitch pitch = splitWaveShapePrefixAndPitch.length == 1
    // ? Pitch.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow()
    // : Pitch.parse(splitWaveShapePrefixAndPitch[1]).orElseThrow();

    // return Optional.of(new Audio(wave, pitch, duration));
    // }

    static boolean isParsable(String aString, boolean requireWaveShape) {
        return aString != null
                && !aString.isBlank()
                && (requireWaveShape
                        ? isParsableWithWaveShape(aString)
                        : isParsableWithoutWaveShape(aString));
    }

    static boolean isParsable(String aString) {
        return isParsable(aString, false);
    }

    private static boolean isParsableWithWaveShape(String aString) {
        // must be at least a leading wav shape, angle bracket, note char,
        // octave int, period, and duration int
        if (aString.length() < 6) {
            return false;
        }

        return Wave.extractPrefix(aString).map(prefix -> {
            final int prefixLength = prefix.length();

            if (aString.charAt(prefixLength) != '>') {
                return false;
            }

            final String[] pitchAndDuration = aString.substring(prefixLength + 1).split("\\.", 3);

            return pitchAndDuration.length == 2 && Pitch.isParsable(pitchAndDuration[0])
                    && !pitchAndDuration[1].isBlank() && pitchAndDuration[1].codePoints().allMatch(Character::isDigit);
        }).orElse(false);
    }

    private static boolean isParsableWithoutWaveShape(String aString) {
        // must be at least a note char, octave int, period, and duration int
        if (aString.length() < 4) {
            return false;
        }

        if (!Note.isNoteChar(aString.charAt(0))) {
            return isParsableWithWaveShape(aString);
        }

        final String[] pitchAndDuration = aString.split("\\.", 3);

        return pitchAndDuration.length == 2 && Pitch.isParsable(pitchAndDuration[0]) && !pitchAndDuration[1].isBlank()
                && pitchAndDuration[1].codePoints().allMatch(Character::isDigit);
    }

    // static boolean isParsable(String aString) {
    // if (aString == null) {
    // return false;
    // }

    // // string must be at least note char, octave int, period, duration int
    // if (aString.length() < 4) {
    // return false;
    // }

    // final String[] splitString = aString.split("\\.");

    // // string must have at least single period to delineate duration from pitch
    // // frequency
    // if (splitString.length != 2) {
    // return false;
    // }

    // final String waveShapePrefixAndPitch = splitString[0];
    // final String duration = splitString[1];

    // // must be at least note char and octave int
    // if (waveShapePrefixAndPitch.length() < 2) {
    // return false;
    // }

    // // duration suffix must be an int
    // if (!duration.codePoints().allMatch(Character::isDigit)) {
    // return false;
    // }

    // final String[] splitWaveShapePrefixAndPitch =
    // waveShapePrefixAndPitch.split(">");

    // // if segment before duration does not contain a right angle bracket/greater
    // // than it must be parsable pitch
    // if (splitWaveShapePrefixAndPitch.length == 1) {
    // return Pitch.isParsable(splitWaveShapePrefixAndPitch[0]);
    // }

    // if (splitWaveShapePrefixAndPitch.length == 2) {
    // final String pitch = splitWaveShapePrefixAndPitch[1];

    // if (!Pitch.isParsable(pitch)) {
    // return false;
    // }

    // final String waveShapePrefix = splitWaveShapePrefixAndPitch[0];

    // // If wave shape prefix is valid case insensitive wave shape string
    // return Arrays.stream(Wave.values()).noneMatch(wave ->
    // wave.stringValueAliases().stream()
    // .anyMatch(waveStringValue ->
    // waveShapePrefix.equalsIgnoreCase(waveStringValue)));
    // }

    // // wave shape prefix and duration portion contains more than 1 right angle
    // // bracket/greater than char
    // return false;
    // }

    private AudioString() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated.".formatted(AudioString.class.getName()));
    }
}
