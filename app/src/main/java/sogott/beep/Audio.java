package sogott.beep;

import static java.util.Objects.hash;

/**
 * This class contains the properties necessary needed to synthesize audible
 * audio or silence.
 *
 * <p>
 * The following properties are:
 *
 * <ol>
 * <li>
 * <h2><i>{@link #wave() Wave}</i></h2>
 * The {@link #wave() wave} property designates the shape of the wave of the
 * synthesized audio. This property will be one of the values defined in the
 * {@link Wave} enum if it's audible audio or {@code null} if it's silence. The
 * following wave shapes are:
 * <ul>
 * <li><i>sin</i>
 * <li><i>triangle</i>
 * <li><i>square</i>
 * <li><i>sawtooth up</i>
 * <li><i>sawtooth down</i>
 * </ul>
 * <li>
 * <h2><i>{@link #pitch() Pitch}</i></h2>
 * The {@link #pitch() pitch} property designates the frequency of the
 * synthesized audio. This property is constructed from a {@link Pitch} object.
 * <li>
 * <h2><i>{@link #duration() Duration}</i></h2>
 * The {@link #duration() duration} property designates the duration of the
 * synthesized audio. This is a non negative integer that designates the length
 * of the audio <strong><b>relative to the tempo/bpm and note beat
 * value</b></strong>. That is, if an {@link Audio} object has a duration of
 * <i>N</i>, it has the duration of 1/<i>N</i>. So if it has a duration value of
 * {@code 4}, then the audio has a time of 1/4 or a quarter note. This
 * information on its own isn't enough to extrapolate the actual duration of the
 * note. The tempo/bpm of the audio playing and the value each beat has must
 * also be known to be able to use this value to create the span of time the
 * audio will play.
 * </ol>
 *
 * An {@link Audio} object can be represented as a {@code String} that can be
 * divided up into 3 segments, each segment corresponding to each of the 3
 * properties that an {@link Audio} object is composed of. Refer to the
 * {@link AudioString} class for more info.
 *
 * @author Snap
 * @see Wave
 * @see Pitch
 * @see AudioString
 */
final class Audio implements Comparable<Audio> {
    final Wave _wave;
    final Pitch _pitch;
    final private int _duration;
    final private String _string;
    final private int _hashCode;
    final private String _toString;

    private Audio(int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Negative duration: %d".formatted(duration));
        }

        this._wave = null;
        this._pitch = null;
        this._duration = duration;
        this._string = "%c.%d".formatted(AudioString.SILENCE_CHAR, this._duration);
        this._hashCode = hash(this._wave, this._pitch, this._duration);
        this._toString = "%s {wave=null, pitch=null, duration=%d}".formatted(
                Audio.class.getSimpleName(), this._duration);
    }

    /**
     * Constructs an {@link Audio} object instance with the following wave,
     * pitch, and duration properties.
     *
     * @param wave     The {@link Wave} of the constructed {@link Audio} object.
     *
     * @param pitch    The {@link Pitch} of the constructed {@link Audio} object.
     *
     * @param duration The duration {@code int} of the constructed {@link Audio}
     *                 object.
     *
     * @throws IllegalArgumentException if either of the wave or pitch arguments
     *                                  are {@code null} or the duration is not
     *                                  positive (less than or equal to 0).
     *
     * @see Wave
     * @see Pitch
     */
    Audio(Wave wave, Pitch pitch, int duration) {
        if (wave == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Audio.class.getSimpleName(), Wave.class.getSimpleName()));
        }

        if (pitch == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Audio.class.getSimpleName(), Pitch.class.getSimpleName()));
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: %d".formatted(duration));
        }

        this._wave = wave;
        this._pitch = pitch;
        this._duration = duration;
        this._string = "%s>%s.%d".formatted(this._wave.stringValue(), this._pitch.stringValue(), this._duration);
        this._hashCode = hash(this._wave, this._pitch, this._duration);
        this._toString = "%s {wave=%s, pitch=\"%s\", duration=%d}".formatted(Audio.class.getSimpleName(),
                this._wave.name(),
                this._pitch.stringValue(), this._duration);
    }

    Audio(Pitch pitch, int duration) {
        this(Wave.SIN, pitch, duration);
    }

    static Audio silence(int duration) {
        return new Audio(duration);
    }

    Wave wave() {
        return this._wave;
    }

    Pitch pitch() {
        return this._pitch;
    }

    int duration() {
        return this._duration;
    }

    String stringValue() {
        return this._string;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj ||
                obj instanceof Audio other
                        && this._wave == other._wave
                        && this._pitch.equals(other._pitch)
                        && this._duration == other._duration;
    }

    @Override
    public int hashCode() {
        return this._hashCode;
    }

    @Override
    public String toString() {
        return this._toString;
    }

    @Override
    public int compareTo(Audio other) {
        int compareResults = this._wave.compareTo(other._wave);

        if (compareResults != 0) {
            return compareResults;
        }

        compareResults = this._pitch.compareTo(other._pitch);

        if (compareResults != 0) {
            return compareResults;
        }

        return Integer.compare(this._duration, other._duration);
    }
}
