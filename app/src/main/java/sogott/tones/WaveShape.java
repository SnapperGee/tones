package sogott.tones;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Comparator.comparingInt;

/**
 * Enum of values used to represent the different wave shapes audio can be and
 * static methods for processing strings in ways related to these wave shape
 * enum values. The following wave shapes are:
 *
 * <ol>
 * <li><i>SINE</i> &acd;
 * <li><i>SQUARE</i> &#9101;
 * <li><i>TRIANGLE</i> &wedge;
 * <li><i>SAW UP</i> &#9727;
 * <li><i>SAW DOWN</i> &#9722;
 * </ol>
 *
 * Each value can be represented via a canonical {@code String} value stored as
 * a {@link #stringValue() stringValue} property. In addition to that, there's
 * also a {@code Set} of {@code string} aliases that can also be used to
 * represent the wave shape value in addition to the canonical one. This
 * {@code String} Set is stored in the {@link #stringValueAliases()
 * stringValueAliases} property. The canonical {@code String} value and enum
 * value {@link #name() name} are always included in this {@code String Set}.
 *
 * @see GenerateWaveByteBuffer
 */
enum WaveShape {
    /**
     * A <i>SINE</i> wave.
     */
    SINE(GenerateWaveByteBuffer::sine, "SIN", Set.of("SINE", "SIN")),

    /**
     * A <i>SQUARE</i> wave.
     */
    SQUARE(GenerateWaveByteBuffer::square, "SQR", Set.of("SQUARE", "SQR")),

    /**
     * A <i>TRIANGLE</i> wave.
     */
    TRIANGLE(GenerateWaveByteBuffer::triangle, "TRI", Set.of("TRIANGLE", "TRI")),

    /**
     * A <i>SAW UP</i> wave.
     */
    SAW_UP(GenerateWaveByteBuffer::sawUp, "SUP", Set.of("SAW_UP", "SAWUP", "SUP")),

    /**
     * A <i>SAW DOWN</i> wave.
     */
    SAW_DOWN(GenerateWaveByteBuffer::sawDown, "SDN", Set.of("SAW_DOWN", "SAWDOWN", "SDN"));

    private static final List<WaveShape> WAVE_SHAPES = unmodifiableList(asList(WaveShape.values()));

    private final BiFunction<Double, Integer, byte[]> _generatorFunc;

    private final String _stringValue;

    private final Set<String> _stringValueAliases;

    private WaveShape(BiFunction<Double, Integer, byte[]> generatorFunc, String stringValue,
            Set<String> stringValueAliases) {
        this._generatorFunc = generatorFunc;
        this._stringValue = stringValue;
        this._stringValueAliases = stringValueAliases;
    }

    /**
     * The canonical {@code String} representation of the wave.
     *
     * @return The canonical {@code String} representation of the wave.
     */
    String stringValue() {
        return this._stringValue;
    }

    /**
     * A string Set of aliases that can also be used to represent the wave in
     * addition to the canonical {@link #stringValue() stringValue}. This string
     * Set always contains at least the canonical
     * {@link #stringValue() stringValue} and enum value {@link #name() name}.
     *
     * @return A string Set of aliases that can also be used to represent the wave
     *         in addition to the canonical {@link #stringValue() stringValue}.
     */
    Set<String> stringValueAliases() {
        return this._stringValueAliases;
    }

    /**
     * Case insensitively checks if the passed {@code String} starts with a
     * {@link WaveShape#stringValueAliases() stringValueAlias} and returns an
     * {@link Optional} containing the matched {@code String} if it starts with
     * one.
     *
     * @param aString {@code String} to extract
     *                {@link WaveShape#stringValueAliases() stringValueAlias} prefix
     *                from if it starts with one.
     *
     * @return An {@link Optional} containing the extracted prefix
     *         {@link WaveShape#stringValueAliases() stringValueAlias} if
     *         present or an empty optional if not.
     */
    static Optional<String> extractPrefixString(String aString) {
        return WAVE_SHAPES.stream()
                .flatMap(wave -> wave.stringValueAliases().stream())
                .filter(waveStringAlias -> aString.length() >= waveStringAlias.length()
                        && aString.regionMatches(true, 0, waveStringAlias, 0, waveStringAlias.length()))
                .max(comparingInt(String::length));
    }

    static Optional<Map.Entry<WaveShape, String>> parsePrefix(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null String argument.");
        }

        return WAVE_SHAPES.stream()
            .flatMap(wave -> wave.stringValueAliases().stream()
                    .filter(waveStringAlias -> aString.length() >= waveStringAlias.length()
                            && aString.regionMatches(true, 0, waveStringAlias, 0, waveStringAlias.length()))
                    .map(alias -> Map.entry(wave, alias)))
            .max(comparingInt(entry -> entry.getValue().length()));
    }

    /**
     * Returns an {@link Optional} containing the {@link WaveShape} value the
     * passed {@code String} can be case insensitively parsed to. If it can't be
     * parsed to a {@link WaveShape}, then an empty {@link Optional} is returned.
     *
     * @param aString    {@code String} to case insensitively parse to a
     *                   {@link WaveShape}.
     *
     * @return An optional containing the {@link WaveShape} value the passed string
     *         argument can be parsed to if it can be, otherwise an empty optional.
     *
     * @throws IllegalArgumentException If passed {@code String} argument is
     *                                  {@code null}.
     */
    static Optional<WaveShape> parse(String aString) {
        if (aString == null) {
            throw new IllegalArgumentException("Null String argument.");
        }

        if (aString.isBlank()) {
            return Optional.empty();
        }

        return WAVE_SHAPES.stream().filter(wave -> wave._stringValueAliases.stream()
                .anyMatch(waveStringValue -> aString.equalsIgnoreCase(waveStringValue))).findFirst();
    }

    /**
     * Generates an array of {@code byte}s that can be used as an audio buffer
     * to generate audio.
     *
     * @param frequency The frequency of the generated audio {@code byte} buffer.
     *
     * @param duration  The duration of the time span the generated audio
     *                  {@code byte} buffer will playback for.
     *
     * @return An array of {@code byte}s that can be used as an audio buffer
     *         to generate audio.
     */
    byte[] generate(double frequency, int duration) {
        return this._generatorFunc.apply(frequency, duration);
    }
}
