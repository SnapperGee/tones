package sogott.beep;

import static java.util.Objects.hash;
import java.util.Arrays;

final class Audio implements Comparable<Audio> {
    final Wave _wave;
    final Pitch _pitch;
    final private int _duration;
    final private String _string;
    final private int _hashCode;
    final private String _toString;

    Audio(Wave wave, Pitch pitch, int duration) throws IllegalArgumentException {
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

        return this._duration - other._duration;
    }

    static boolean isParsable(String aString) {
        if (aString == null) {
            return false;
        }

        // string must be at least note char, octave int, period, duration int
        if (aString.length() < 4) {
            return false;
        }

        final String[] splitString = aString.split("\\.");

        // string must have at least single period to delineate duration from pitch
        // frequency
        if (splitString.length != 2) {
            return false;
        }

        final String waveShapePrefixAndPitch = splitString[0];
        final String duration = splitString[1];

        // must be at least note char and octave int
        if (waveShapePrefixAndPitch.length() < 2) {
            return false;
        }

        // duration suffix must be an int
        if (!duration.codePoints().allMatch(Character::isDigit)) {
            return false;
        }

        final String[] splitWaveShapePrefixAndPitch = waveShapePrefixAndPitch.split(">");

        // if segment before duration does not contain a right angle bracket/greater
        // than it must be parsable pitch
        if (splitWaveShapePrefixAndPitch.length == 1) {
            return Pitch.isParsable(splitWaveShapePrefixAndPitch[0]);
        }

        if (splitWaveShapePrefixAndPitch.length == 2) {
            final String pitch = splitWaveShapePrefixAndPitch[1];

            if (!Pitch.isParsable(pitch)) {
                return false;
            }

            final String waveShapePrefix = splitWaveShapePrefixAndPitch[0];

            // If wave shape prefix is valid case insensitive wave shape string
            return Arrays.stream(Wave.values()).noneMatch(wave -> wave.stringValueAliases().stream()
                    .anyMatch(waveStringValue -> waveShapePrefix.equalsIgnoreCase(waveStringValue)));
        }

        // wave shape prefix and duration portion contains more than 1 right angle
        // bracket/greater than char
        return false;
    }
}
