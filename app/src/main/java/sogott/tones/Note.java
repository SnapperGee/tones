package sogott.tones;

import static java.util.Objects.hash;

/**
 * This class contains the properties necessary needed to generate byte arrays
 * that are suitable for being used as buffers to synthesize audio. The
 * following properties these objects contain are:
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
 * synthesized audio. This is a positive integer that designates the length of
 * the audio <strong><b>relative to the tempo/bpm and note beat
 * value</b></strong>. This information alone isn't enough to extrapolate the
 * actual span of time the audio will play for. The tempo/bpm of the playback
 * and the value each beat has must also be known to be able to use this value
 * to create the span of time the audio will play.
 *
 * <p>
 * If a {@link Note} object has a duration <i>N</i>, then its span of time to
 * play for is <sup>1</sup>&frasl;<sub><i>N</i></sub> of a whole note. So if it
 * has a duration value of {@code 4}, then the audio has a time of
 * <sup>1</sup>&frasl;<sub>4</sub> of a whole note which is a quarter note.
 * </ol>
 *
 * A {@link Note} object can be represented as a {@code String} that can be
 * divided up into 3 segments, each segment corresponding to each of the 3
 * properties that a {@link Note} object is composed of. Refer to the
 * {@link NoteString} class for more info.
 *
 * @author Snap
 * @see Wave
 * @see Pitch
 * @see NoteString
 */
final class Note {
    final private Wave _wave;
    final private Pitch _pitch;
    final private int _duration;
    final private String _string;
    final private int _hashCode;
    final private String _toString;

    private Note(int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Negative duration: %d".formatted(duration));
        }

        this._wave = null;
        this._pitch = null;
        this._duration = duration;
        this._string = "%c.%d".formatted(NoteString.SILENCE_CHAR, this._duration);
        this._hashCode = hash(this._wave, this._pitch, this._duration);
        this._toString = "%s {wave=null, pitch=null, duration=%d}".formatted(
                Note.class.getSimpleName(), this._duration);
    }

    /**
     * Constructs a {@link Note} object instance with the following wave,
     * pitch, and duration properties.
     *
     * @param wave     The {@link Wave} of the constructed {@link Note} object.
     *
     * @param pitch    The {@link Pitch} of the constructed {@link Note} object.
     *
     * @param duration The positive duration {@code int} of the constructed
     *                 {@link Note} object.
     *
     * @throws IllegalArgumentException if either of the wave or pitch arguments
     *                                  are {@code null} or the duration is not
     *                                  positive (less than or equal to 0).
     *
     * @see Wave
     * @see Pitch
     */
    Note(Wave wave, Pitch pitch, int duration) {
        if (wave == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Note.class.getSimpleName(), Wave.class.getSimpleName()));
        }

        if (pitch == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Note.class.getSimpleName(), Pitch.class.getSimpleName()));
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: %d".formatted(duration));
        }

        this._wave = wave;
        this._pitch = pitch;
        this._duration = duration;
        this._string = "%s>%s.%d".formatted(this._wave.stringValue(), this._pitch.stringValue(), this._duration);
        this._hashCode = hash(this._wave, this._pitch, this._duration);
        this._toString = "%s {wave=%s, pitch=\"%s\", duration=%d}".formatted(Note.class.getSimpleName(),
                this._wave.name(),
                this._pitch.stringValue(), this._duration);
    }

    /**
     * Constructs a {@link Note} object instance with {@code null} wave and
     * pitch properties and the duration property set to the passed {@code int}
     * argument.
     *
     * @param duration The positive duration {@code int} of the constructed
     *                 {@link Note} object.
     *
     * @return A {@link Note} object instance with {@code null} wave and pitch
     *         properties and the passed duration property.
     */
    static Note silence(int duration) {
        return new Note(duration);
    }

    /**
     * The {@link Wave} shape value of this {@link Note} object is it's audible
     * or {@code null} if it's silence. The possible wave shape values are
     * defined in the {@link Wave} enum. The possible wave shapes are:
     *
     * <ul>
     * <li><i>SIN</i>
     * <li><i>SQUARE</i>
     * <li><i>TRIANGLE</i>
     * <li><i>SAW UP</i>
     * <li><i>SAW DOWN</i>
     * </ul>
     *
     * @return The {@link Wave} value of this {@link Note} object if it's
     *         audible or {@code null} if it's silence.
     *
     * @see Wave
     */
    Wave wave() {
        return this._wave;
    }

    /**
     * The {@link Pitch} frequency value of this {@link Note} object is it's
     * audible or {@code null} if it's silence.
     *
     * @return The {@link Pitch} frequency value of this {@link Note} object if
     *         it's audible or {@code null} if it's silence.
     */
    Pitch pitch() {
        return this._pitch;
    }

    /**
     * The positive {@code int} value of this {@link Note} object's duration.
     *
     * <p>
     * This value is <strong><b>relative to the tempo/bpm and note beat
     * value</b></strong>. This value alone isn't enough to extrapolate the
     * actual span of time the audio will play for. The tempo/bpm of the
     * playback and the value each beat has must also be known to be able to use
     * this value to create the span of time the audio will play.
     *
     * <p>
     * If a {@link Note} object has a duration of <i>N</i>, then its span of
     * time to play for is 1/<i>N</i> of a whole note. So if it has a duration
     * value of {@code 4}, then the audio has a time of 1/4 of a whole note or a
     * quarter note.
     *
     * @return The positive {@code int} value of this {@link Note} object's
     *         duration.
     */
    int duration() {
        return this._duration;
    }

    /**
     * A {@code String} representation of this {@link Note} object. Note that
     * this is different from the string returned by the {@link #toString()}
     * method which is more suited for debugging purposes.
     *
     * @return a {@code String} representation of this {@link Note} object.
     */
    String stringValue() {
        return this._string;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj ||
                obj instanceof Note other
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
}
