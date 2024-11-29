package sogott.tones;

import java.util.Optional;

/**
 * Class consisting exclusively of static methods and fields for parsing
 * {@code String}s to {@link Audio} objects.
 *
 * <p>
 * A {@code String} can be parsed to either <b><i>audible</i></b> audio or
 * to <b><i>silence</i></b> and can be divided up into segments that define the
 * different properties of the audio.
 *
 * <h2>Audio String Segments</h2>
 * The segments of an audio string can be grouped together into 2 parts. The
 * first leading part is referred to as the <strong><em>voice</em></strong>.
 * This contains the information about the tone of the audio if it's not
 * silence or information designating that the audio is silence (and therefore
 * has no tone). The 2nd part of the audio string that follows the "voice"
 * segment(s) is the <strong><em>duration suffix segment</em></strong>.
 *
 * <p>
 * Both audible audio and silence need to contain a duration suffix segment to
 * define the duration of the audio. However they differ in the voice segment(s)
 * that precede the duration suffix segment. The character used to designate the
 * start of the duration suffix segment and delineate it from the voice
 * segment(s) is defined via the {@link Delimiter#VOICE_AND_DURATION} enum
 * value.
 *
 * <h3>Duration suffix segment</h3>
 * The duration is specified via a suffix consisting of a leading period
 * character ({@code '.'}) to designate the beginning of the segment and
 * separate it from the previous segments that precede it and is followed by a
 * positive integer that defines the duration amount. The duration amount is
 * <strong><em>relative to the <u>note beat value</u> and
 * <u>bpm/tempo</u></em></strong>. Without these 2 additional bits of
 * information (the note beat value and bpm/tempo) the duration amount alone
 * isn't enough information to extrapolate the actual span of time the audio or
 * silence should be played.
 *
 * <p>
 * An easy way to think of it is that if the duration is integer <i>N</i>, then
 * the length of the note will be <sup>1</sup>&frasl;<sub><i>N</i></sub>. So,
 * if <i>N</i> were 1, then the duration would be
 * <sup>1</sup>&frasl;<sub>1</sub> which would be a whole note. If <i>N</i> were
 * 4, that'd result in <sup>1</sup>&frasl;<sub>4</sub> so it'd be a quarter note
 * etc.
 *
 * <h3>Audible audio</h3>
 * A {@code String} can be parsed to audible audio if its voice consists of the
 * segments to define the <i>wave shape</i> and <i>pitch/frequency</i> of the
 * audio. As such, an audible audio string consists of the 3 ordered
 * segments:
 *
 * <ol>
 * <li>Wave Shape prefix
 * <li>Pitch Frequency
 * <li>Duration suffix
 * </ol>
 *
 * Segments 1 and 2 compose the voice part of an audible audio string.
 *
 * <h4>1.) Wave Shape prefix segment</h4>
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
 * This is defined via the {@link Delimiter#WAVE_SHAPE_AND_PITCH} enum value. An
 * example of a wave shape prefix for a triangle wave would be {@code "TRI>"}
 * with the rest of the string coming after the {@code '>'} character. The types
 * of supported wave shapes are defined in the {@link WaveShape} enum.
 *
 * <p>
 * The leading wave shape prefix and its delimiter can optionally be omitted
 * <strong><em>if a default wave shape value to use in its absence is
 * provided</em></strong>.
 *
 * <h4>2.) Pitch Frequency segment</h4>
 * The segment that follows the wave shape prefix (if it's present) and
 * precedes the duration suffix segment defines the pitch/frequency of the
 * audio. This segment needs to be parsable to a {@link Pitch} object and can be
 * divided into 3 parts:
 *
 * <ol>
 * <li>A leading alpha character corresponding to a musical note specifying the
 * pitch.
 * <li>A plus ({@code '+'}) or minus ({@code '-'}) character specifying if the
 * note is a sharp &sharp; or flat &flat; or no character if it's a natural
 * &natural;.
 * <li>And an integer specifying the octave of the note.
 * </ol>
 *
 * An example of a 440hz wave would be natural {@code 'A'} (no sharp or flat) in
 * the 4th octave. This would be defined as {@code "A4"}. To designate a B&flat;
 * (flat) musical note in the 2nd octave would be {@code "B-2"}, with B&sharp;
 * (sharp) in the 2nd octave being {@code "B+2"}. The characters that correspond
 * to musical notes and their accidentals are defined in the {@link PitchLetter}
 * and {@link Accidental} enum respectively.
 *
 * <p>
 * So putting all this together, a {@code String} to designate a sin wave that
 * is an F&sharp; (sharp) half note in the 8th octave would be
 * {@code "SIN>F+8.2"}.
 *
 * <h3>Silence</h3>
 * A {@code String} can be parsed to silence if it starts with the leading
 * silence character (a question mark {@code '?'}) prefix, defined via the
 * {@link #SILENCE_CHAR} const, followed by the duration suffix segment. As
 * such, an audio string for silence consists of the following 2 ordered
 * segments:
 *
 * <ol>
 * <li>Silence char prefix
 * <li>Duration suffix
 * </ol>
 *
 * An example of a quarter note of silence would be written as {@code "?.4"}.
 *
 * @author Snap
 * @see Audio
 * @see AudioString.Delimiter
 * @see Pitch
 * @see WaveShape
 * @see PitchLetter
 */
final class AudioString {
    /**
     * The character used as a leading prefix to designate that an audio string
     * should be parsed as silence (as opposed to a tone).
     */
    static final char SILENCE_CHAR = '?';

    /**
     * Enum of char values used to separate the segments of an audio string.
     *
     * @see AudioString
     */
    static enum Delimiter {
        /**
         * The character used to separate the wave shape segment from the pitch
         * segment of an audio string that's parsed to a tone (not silence).
         */
        WAVE_SHAPE_AND_PITCH('>'),

        /**
         * The character used to separate the leading voice segment(s) of an
         * audio string from its duration suffix segment.
         */
        VOICE_AND_DURATION('.');

        private final char _charValue;

        private Delimiter(char charValue) {
            this._charValue = charValue;
        }

        /**
         * The character value of the delimiter.
         *
         * @return the character value of the delimiter.
         */
        char charValue() {
            return this._charValue;
        }
    }

    /**
     * <em>Case insensitively</em> parses the passed {@code String} argument to
     * an {@link Audio} object, using the passed {@link WaveShape} argument as
     * the default wave shape if the passed {@code String} is audible audio with
     * a tone (and not silence) and has no wave shape prefix segment. If the
     * passed {@code String} can't be parsed to audio then an empty
     * {@link Optional} is returned, otherwise the returned {@link Optional}
     * contains the resulting audio object from parsing the {@code string}.
     *
     * @param aString          {@code String} to attempt to parse to an
     *                         {@link Audio} object.
     *
     * @param defaultWaveShape {@link WaveShape} to use as a default value if the
     *                         passed {@code String} argument is audible audio
     *                         with a tone (not silence) with no wave shape prefix.
     *
     * @return An {@link Optional} containing the {@link Audio} object parsed
     *         from the passed string or an empty optional if it can't be parsed.
     *
     * @throws IllegalArgumentException If either of the provided arguments are
     *                                  {@code null}.
     */
    static Optional<Audio> parse(String aString, WaveShape defaultWaveShape) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        if (defaultWaveShape == null) {
            throw new IllegalArgumentException("Null default wave.");
        }

        return parseSilence(aString).or(() -> parseTone(aString, defaultWaveShape));
    }

    /**
     * Parses the passed {@code String} argument case insensitively to an
     * {@link Audio} object. If the passed string can't be parsed to audio then
     * an empty optional is returned, otherwise the returned optional contains
     * the resulting audio object from parsing the string.
     *
     * @param aString {@code String} to parse to an {@link Audio} object.
     *
     * @return An {@code Optional} containing the {@link Audio} object parsed from
     *         the passed string or an empty optional if it can't be parsed.
     *
     * @throws IllegalArgumentException If the provided argument is
     *                                  {@code null}.
     */
    static Optional<Audio> parse(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string.");
        }

        return parseSilence(aString).or(() -> parsePitch(aString));
    }

    private static boolean isParsableTone(String aString, boolean requireWaveShapePrefix) {
        return aString != null
                && !aString.isBlank()
                && (requireWaveShapePrefix
                        ? isParsableWaveShapePrefixedTone(aString)
                        : isParsableToneWithoutWaveShapePrefix(aString));
    }

    private static Optional<Audio> parseSilence(String aString) {
        // must be at least a leading silence char, period, and duration int
        if (aString.length() < 3) {
            return Optional.empty();
        }

        // must start with silence char
        if (aString.charAt(0) != SILENCE_CHAR) {
            return Optional.empty();
        }

        final int delimiterIndex = aString.indexOf(Delimiter.VOICE_AND_DURATION.charValue());

        // must contain period char
        if (delimiterIndex == -1) {
            return Optional.empty();
        }

        // must only contain int after delimiter
        if (aString.codePoints().limit(delimiterIndex - 1).anyMatch(cp -> !Character.isDigit(cp))) {
            return Optional.empty();
        }

        final int duration = Integer.parseInt(aString, delimiterIndex, aString.length(), 10);

        return Optional.of(Audio.silence(duration));
    }

    private static Optional<Audio> parseTone(String aString, WaveShape defaultWave) {
        if (!isParsableTone(aString, false)) {
            return Optional.empty();
        }

        final int voiceAndDurationDelimiterIndex = aString.indexOf(Delimiter.VOICE_AND_DURATION.charValue());

        if (voiceAndDurationDelimiterIndex == -1) {
            return Optional.empty();
        }

        final int duration = Integer.parseInt(aString, voiceAndDurationDelimiterIndex + 1, aString.length(), 10);

        final String waveShapePrefixAndPitch = aString.substring(0, voiceAndDurationDelimiterIndex);
        final String[] splitWaveShapePrefixAndPitch = waveShapePrefixAndPitch
                .split(Character.toString(Delimiter.WAVE_SHAPE_AND_PITCH.charValue()));
        final WaveShape waveShape = splitWaveShapePrefixAndPitch.length == 1 ? defaultWave
                : WaveShape.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow();
        final Pitch pitch = splitWaveShapePrefixAndPitch.length == 1
                ? Pitch.parse(splitWaveShapePrefixAndPitch[0]).orElseThrow()
                : Pitch.parse(splitWaveShapePrefixAndPitch[1]).orElseThrow();

        return Optional.of(new Audio(waveShape, pitch, duration));
    }

    private static Optional<Audio> parsePitch(String aString) {
        if (!isParsableTone(aString, true)) {
            return Optional.empty();
        }

        final int voiceAndDurationDelimiterIndex = aString.indexOf(Delimiter.VOICE_AND_DURATION.charValue());

        if (voiceAndDurationDelimiterIndex == -1) {
            return Optional.empty();
        }

        final int duration = Integer.parseInt(aString, voiceAndDurationDelimiterIndex + 1, aString.length(), 10);

        final int waveShapeAndPitchDelimiterIndex = aString.indexOf(Delimiter.WAVE_SHAPE_AND_PITCH.charValue());

        // isolate prefix of String up to wave shape and pitch delimiter
        final String waveShapeString = aString.substring(0, waveShapeAndPitchDelimiterIndex);
        final WaveShape wave = WaveShape.parse(waveShapeString).orElseThrow();

        // isolate section of String between wave shape and pitch delimiter and pitch
        // and duration delimiter
        final String pitchString = aString.substring(waveShapeAndPitchDelimiterIndex + 1,
                voiceAndDurationDelimiterIndex);
        final Pitch pitch = Pitch.parse(pitchString).orElseThrow();

        return Optional.of(new Audio(wave, pitch, duration));
    }

    private static boolean isParsableWaveShapePrefixedTone(String aString) {
        // must be at least a leading wav shape, angle bracket, note char,
        // octave int, period, and duration int
        if (aString.length() < 6) {
            return false;
        }

        return WaveShape.extractPrefix(aString).map(prefix -> {
            final int prefixLength = prefix.length();

            if (aString.charAt(prefixLength) != Delimiter.WAVE_SHAPE_AND_PITCH.charValue()) {
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

        if (!PitchLetter.isPitchLetter(aString.charAt(0))) {
            return isParsableWaveShapePrefixedTone(aString);
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
