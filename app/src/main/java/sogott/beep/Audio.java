package sogott.beep;

import static java.util.Objects.hash;

/**
 * The {@link Audio} class contains the properties necessary needed to
 * synthesize audio.
 *
 * <p>
 * The following properties are:
 *
 * <ol>
 * <li>
 * <h3><i>{@link Audio#wave() Wave}</i></h3>
 * The {@link Audio#wave() wave} property designates the shape of the wave of
 * the synthesized audio. The following shapes include <i>sin</i>,
 * <i>square</i>, <i>triangle</i>, <i>saw up</i>, and <i>saw down</i>. The wave
 * shapes are defined as the values of the {@link Wave} enum.
 * <li>
 * <h3><i>{@link Audio#pitch() Pitch}</i></h3>
 * The {@link Audio#pitch() pitch} property designates the frequency of the
 * synthesized audio. This property is constructed from a {@link Pitch} object.
 * <li>
 * <h3><i>{@link Audio#duration() Duration}</i></h3>
 * The {@link Audio#duration() duration} property designates the duration of the
 * synthesized audio. This is a non negative integer that designates the length
 * of the audio </strong><b>relative to the tempo/bpm and note beat
 * value</b></strong>.
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

    Audio(Wave wave, Pitch pitch, int duration) {
        if (wave == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Audio.class.getSimpleName(), Wave.class.getSimpleName()));
        }

        if (pitch == null) {
            throw new IllegalArgumentException(
                    "Null %s %s.".formatted(Audio.class.getSimpleName(), Pitch.class.getSimpleName()));
        }

        if (duration < 0) {
            throw new IllegalArgumentException("Negative duration: %d".formatted(duration));
        }

        this._wave = wave;
        this._pitch = pitch;
        this._duration = duration;
        this._string = "%s>%s.%d".formatted(this._wave.stringValue(), this._pitch.stringValue(), this._duration);
        this._hashCode = hash(this._wave, this._pitch, this._duration);
        this._toString = "%s {wave=%s, pitch=\"%s\", duration=%d}".formatted(this._wave.name(),
                this._pitch.stringValue(), this._duration);
    }

    Audio(Pitch pitch, int duration) {
        this(Wave.SIN, pitch, duration);
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
