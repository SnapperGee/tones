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
 * <h3><i>Wave</i></h3>
 * The {@code wave} property designates the shape of the wave of the synthesized
 * audio. The following shapes include <i>sin</i>, <i>square</i>,
 * <i>triangle</i>, <i>saw up</i>, and <i>saw down</i>. The wave shapes are
 * defined as the values of the {@link Wave} enum.
 * <li>
 * <h3><i>Pitch</i></h3>
 * The {@code pitch} property designates the frequency of the synthesized audio.
 * This property is constructed from a {@link Pitch} object.
 * <li>
 * <h3><i>duration</i></h3>
 * The {@code duration} property designates the duration of the synthesized
 * audio. This is a non negative integer that designates the length of the audio
 * </strong><b>relative to the tempo/bpm and note beat value</b></strong>.
 * </ol>
 *
 * An {@link Audio} object can be represented as a {@code String} that can be
 * divided up into 3 segments, each segment corresponding to each of the 3
 * properties that an {@link Audio} object is composed of.
 *
 * <ol>
 * <li>The first segment of the string corresponds to the {@link Audio#wave()}
 * property that defines what the wave shape of the synthesized audio will be. A
 * bracket/greater than character ({@code '>'}) is then used to delineate it
 * from the next segment of the string.
 * <li>The 2nd segment of the string corresponds to the {@link Audio#pitch()}
 * property that defines the frequency of the synthesized audio. As such, this
 * segment must correspond to the string format of the {@link Pitch} class. It
 * must contain at least a note character followed by an octave integer, and can
 * also contain a plus ({@code '+'}) or minus ({@code '-'}) character after the
 * note character to indicate whether the note is a <i>flat</i> (&flat;) or
 * <i>sharp</i> (&sharp;). A period ({@code '.'}) character is then used to
 * delineate it from the next and final segment of the string.
 * <li>The final part of the string corresponds to the {@link Audio#duration()}
 * property and defines the duration of the synthesized audio.
 * </ol>
 *
 * So putting all this together, a {@code String} to explicitly designate a sin
 * wave that is an F&sharp; (sharp) half note in the 8th octave would be written
 * as {@code "SIN>F+8.2"}.
 *
 * @author Snap
 * @see Wave
 * @see Pitch
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
