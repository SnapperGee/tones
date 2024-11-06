package sogott.beep;

import java.util.Optional;

/**
 * This class consists exclusively of static methods and fields for parsing
 * {@code String}s to {@link Audio} objects and validating whether
 * {@code String}s are parsable to {@link Audio} objects or not.
 *
 * <p>
 * A {@code String} can be parsed to either <b><i>audible</i></b> audio or
 * to <b><i>silence</i></b>.
 *
 * <h2>Audible audio</h2>
 * A {@code String} can be parsed to audible audio if it has the information to
 * define the <i>wave shape</i>, <i>pitch/frequency</i>, and <i>duration</i> of
 * the audio. An {@link AudioString} can be divided into 3 segments, each one
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
 * to separate it from the frequency segment of the string which follows it.
 * This is defined via the {@link AudioString.Delineator#WAVE_SHAPE_AND_PITCH}
 * enum value. An example of a wave shape prefix for a triangle wave would be
 * {@code "TRI>"} with the rest of the string coming after the {@code '>'}
 * character. The types of supported wave shapes are defined in the {@link Wave}
 * enum.
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
 * duration segment of the string which comes after. This is defined via the
 * {@link AudioString.Delineator#PITCH_AND_DURATION} enum value. An example of a
 * 440hz wave would be natural {@code 'A'} (no sharp or flat) in the 4th octave.
 * This would be defined as {@code "A4"}. To designate a B&flat; (flat) musical
 * note in the 2nd octave would be {@code "B-2"}, with B&sharp; (sharp) in the
 * 2nd octave being {@code "B+2"}. The characters that correspond to musical
 * notes are defined in the {@link Note} enum.
 *
 * <h3>3.) Duration suffix</h3>
 * The final segment specifies the duration of the audio. This is a positive
 * integer that designates the length of the audio </strong><b>relative to the
 * note beat value and tempo/bpm</b></strong>. An easy way to think of it is
 * that if the duration is integer <i>N</i>, then the length of the note will be
 * 1/<i>N</i>. So, if <i>N</i> were 1, then the duration would be 1/1 which
 * would be a whole note. If <i>N</i> were 4, that'd result in 1/4 so it'd be a
 * quarter note etc.
 *
 * <p>
 * So putting all this together, a {@code String} to explicitly designate a sin
 * wave that is an F&sharp; (sharp) half note in the 8th octave would be
 * {@code "SIN>F+8.2"}. Since an {@link AudioString} defaults to a sin wave if
 * no wave shape prefix is included, this could also be written as
 * {@code "F+8.2"}.
 *
 * <h2>Silence</h2>
 * A {@code String} can be parsed to silence if it start with the leading
 * silence character prefix, defined via the
 * {@link AudioString#SILENCE_CHAR SILENCE_CHAR} const, to designate that it's
 * silence and then a duration for how long the silence should last. The
 * duration, just like for audible audio, is a positive integer that designates
 * the length of the audio </strong><b>relative to the note beat value and
 * tempo/bpm</b></strong>. And just like for audible audio,
 * the {@link AudioString.Delineator#PITCH_AND_DURATION} character separates the
 * duration integer from the {@link AudioString#SILENCE_CHAR SILENCE_CHAR}
 * character prefix.
 *
 * <ol>
 * <li>Silence/rest char prefix
 * <li>Duration suffix
 * </ol>
 *
 * @author Snap
 * @see Audio
 * @see Wave
 * @see Note
 * @see AudioString.Delineator
 */
final class AudioString {
    final static char SILENCE_CHAR = '?';

    static enum Delineator {
        WAVE_SHAPE_AND_PITCH('>'),
        PITCH_AND_DURATION('.');

        private final char _charValue;

        private Delineator(char charValue) {
            this._charValue = charValue;
        }

        char charValue() {
            return this._charValue;
        }
    }

    private final static String SILENCE_PREFIX = new StringBuilder()
            .append(SILENCE_CHAR)
            .append(Delineator.PITCH_AND_DURATION.charValue())
            .toString();

    static Optional<Audio> parse(String aString, Wave defaultWaveShape) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        if (defaultWaveShape == null) {
            throw new IllegalArgumentException("Null default wave.");
        }

        if (isParsableSilence(aString)) {
            return parseSilence(aString);
        }

        if (isParsableTone(aString, false)) {
            return parseTone(aString, defaultWaveShape);
        }

        return Optional.empty();
    }

    static Optional<Audio> parse(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        if (isParsableSilence(aString)) {
            return parseSilence(aString);
        }

        if (isParsableTone(aString, true)) {
            return parsePitch(aString);
        }

        return Optional.empty();
    }

    static boolean isParsableTone(String aString, boolean requireWaveShapePrefix) {
        return aString != null
                && !aString.isBlank()
                && (requireWaveShapePrefix
                        ? isParsableToneWithWaveShapePrefix(aString)
                        : isParsableToneWithoutWaveShapePrefix(aString));
    }

    static boolean isParsableSilence(String aString) {
        if (aString == null) {
            return false;
        }

        // must be at least a leading silence char, angle bracket, period, and
        // duration int
        if (aString.length() < 3) {
            return false;
        }

        if (!aString.startsWith(SILENCE_PREFIX)) {
            return false;
        }

        return Character.isDigit(aString.charAt(2)) && aString.codePoints().skip(3).allMatch(Character::isDigit);
    }

    static private Optional<Audio> parseSilence(String aString) {
        if (!isParsableSilence(aString)) {
            return Optional.empty();
        }

        final String durationString = aString.substring(aString.indexOf(Delineator.PITCH_AND_DURATION.charValue()) + 1);
        final int duration = Integer.parseInt(durationString);
        return Optional.of(Audio.silence(duration));
    }

    static private Optional<Audio> parseTone(String aString, Wave defaultWave) {
        if (!isParsableTone(aString, false)) {
            return Optional.empty();
        }

        final String[] splitString = aString.split("\\.");
        final String waveShapePrefixAndPitch = splitString[0];
        final String durationString = splitString[1];
        final int duration = Integer.parseInt(durationString);
        final String[] splitWaveShapePrefixAndPitch = waveShapePrefixAndPitch.split(">");
        final Wave wave = splitWaveShapePrefixAndPitch.length == 1 ? defaultWave
                : Wave.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow();
        final Pitch pitch = splitWaveShapePrefixAndPitch.length == 1
                ? Pitch.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow()
                : Pitch.parse(splitWaveShapePrefixAndPitch[1]).orElseThrow();

        return Optional.of(new Audio(wave, pitch, duration));
    }

    static private Optional<Audio> parsePitch(String aString) {
        if (!isParsableTone(aString, true)) {
            return Optional.empty();
        }

        final String[] splitString = aString.split("\\.");
        final String waveShapePrefixAndPitch = splitString[0];
        final String durationString = splitString[1];
        final int duration = Integer.parseInt(durationString);
        final String[] splitWaveShapePrefixAndPitch = waveShapePrefixAndPitch.split(">");
        final Wave wave = Wave.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow();
        final Pitch pitch = splitWaveShapePrefixAndPitch.length == 1
                ? Pitch.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow()
                : Pitch.parse(splitWaveShapePrefixAndPitch[1]).orElseThrow();

        return Optional.of(new Audio(wave, pitch, duration));
    }

    private static boolean isParsableToneWithWaveShapePrefix(String aString) {
        // must be at least a leading wav shape, angle bracket, note char,
        // octave int, period, and duration int
        if (aString.length() < 6) {
            return false;
        }

        return Wave.extractPrefix(aString).map(prefix -> {
            final int prefixLength = prefix.length();

            if (aString.charAt(prefixLength) != Delineator.WAVE_SHAPE_AND_PITCH.charValue()) {
                return false;
            }

            final String[] pitchAndDuration = aString.substring(prefixLength + 1).split("\\.", 3);

            return pitchAndDuration.length == 2 && Pitch.isParsable(pitchAndDuration[0])
                    && !pitchAndDuration[1].isBlank() && pitchAndDuration[1].codePoints().allMatch(Character::isDigit);
        }).orElse(false);
    }

    private static boolean isParsableToneWithoutWaveShapePrefix(String aString) {
        // must be at least a note char, octave int, period, and duration int
        if (aString.length() < 4) {
            return false;
        }

        if (!Note.isNoteChar(aString.charAt(0))) {
            return isParsableToneWithWaveShapePrefix(aString);
        }

        final String[] pitchAndDuration = aString.split("\\.", 3);

        return pitchAndDuration.length == 2 && Pitch.isParsable(pitchAndDuration[0]) && !pitchAndDuration[1].isBlank()
                && pitchAndDuration[1].codePoints().allMatch(Character::isDigit);
    }

    private AudioString() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated.".formatted(AudioString.class.getSimpleName()));
    }
}
