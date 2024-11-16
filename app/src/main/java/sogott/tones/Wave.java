package sogott.tones;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

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
enum Wave {
    /**
     * A <i>SINE</i> wave.
     */
    SINE(GenerateWaveByteBuffer::sine, "SIN"),

    /**
     * A <i>SQUARE</i> wave.
     */
    SQUARE(GenerateWaveByteBuffer::square, "SQR"),

    /**
     * A <i>TRIANGLE</i> wave.
     */
    TRIANGLE(GenerateWaveByteBuffer::triangle, "TRI"),

    /**
     * A <i>SAW UP</i> wave.
     */
    SAW_UP(GenerateWaveByteBuffer::sawUp, "SUP", Set.of("SAWUP")),

    /**
     * A <i>SAW DOWN</i> wave.
     */
    SAW_DOWN(GenerateWaveByteBuffer::sawDown, "SDN", Set.of("SAWDOWN"));

    final private static List<Wave> _waves = unmodifiableList(asList(Wave.values()));

    final private BiFunction<Double, Integer, byte[]> _generatorFunc;

    final private String _stringValue;

    final private Set<String> _stringValueAliases;

    private Wave(BiFunction<Double, Integer, byte[]> generatorFunc, String stringValue) {
        this._generatorFunc = generatorFunc;
        this._stringValue = stringValue;
        this._stringValueAliases = Set.of(this._stringValue, this.name());
    }

    private Wave(BiFunction<Double, Integer, byte[]> generatorFunc, String stringValue,
            Set<String> stringValueAliases) {
        this._generatorFunc = generatorFunc;
        this._stringValue = stringValue;
        final Set<String> stringSet = new HashSet<String>(Arrays.asList(this._stringValue, this.name()));
        stringSet.addAll(stringValueAliases);
        this._stringValueAliases = unmodifiableSet(stringSet);
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
     * Returns {@code true} if the passed {@code String} argument is prefixed
     * with this enum value's {@link #stringValue() stringValue} or any of the
     * strings in this enum value's
     * {@link #stringValueAliases() stringValueAliases}. A {@code boolean} value
     * can optionally be passed to specify whether case is ignored or not. By
     * default it's case insensitive.
     *
     * @param aString    {@code String} to check if it's prefixed with any of
     *                   this enum value's string values.
     *
     * @param ignoreCase Optional {@code boolean} to specify whether case is
     *                   ignored or not. Defaults to {@code true} making case
     *                   ignored.
     *
     * @return {@code true} if the passed {@code String} argument is prefixed
     *         with this enum value's {@link #stringValue() stringValue} or any
     *         of the strings in this enum value's
     *         {@link #stringValueAliases() stringValueAliases}.
     */
    boolean prefixes(String aString, boolean ignoreCase) {
        return aString != null && !aString.isEmpty() && this._stringValueAliases.stream()
                .anyMatch(stringValueAlias -> aString.length() >= stringValueAlias.length()
                        && aString.regionMatches(ignoreCase, 0, stringValueAlias, 0, stringValueAlias.length()));
    }

    /**
     * Returns {@code true} if the passed {@code String} argument is prefixed
     * with this enum value's {@link #stringValue() stringValue} or any of the
     * strings in this enum value's
     * {@link #stringValueAliases() stringValueAliases}. Case is ignored when
     * checking for the prefix.
     *
     * @param aString {@code String} to case insensitively check if it's
     *                prefixed with any of this enum value's string values.
     *
     * @return {@code true} if the passed {@code String} argument is case
     *         insensitively prefixed with this enum value's
     *         {@link #stringValue() stringValue} or any of the strings in this
     *         enum value's {@link #stringValueAliases() stringValueAliases}.
     */
    boolean prefixes(String aString) {
        return prefixes(aString, true);
    }

    /**
     * Returns an optional containing the enum value string the passed string
     * argument is prefixed with if it is prefixed with one, otherwise returns
     * an empty optional. A {@code boolean} argument can also optionally be
     * passed to specify whether to ignore the case when matching the prefix.
     * Defaults to {@code true} to perform a case insensitive match.
     *
     * @param aString    {@code String} to extract enum string value prefix from
     *                   if present.
     *
     * @param ignoreCase {@code boolean} specifying whether to ignore case or not
     *                   when matching the prefix.
     *
     * @return An optional containing the extracted prefix enum string value if
     *         present or an empty optional if not.
     */
    static Optional<String> extractPrefix(String aString, boolean ignoreCase) {
        return _waves.stream()
                .flatMap(wave -> wave.stringValueAliases().stream())
                .filter(waveStringAlias -> aString.length() >= waveStringAlias.length()
                        && aString.regionMatches(ignoreCase, 0, waveStringAlias, 0, waveStringAlias.length()))
                .max(Comparator.comparingInt(String::length));
    }

    /**
     * Returns an optional containing the enum value string the passed string
     * argument is prefixed with if it is prefixed with one, otherwise returns
     * an empty optional. Performs a case insensitive match when checking the
     * prefix string value.
     *
     * @param aString {@code String} to extract enum string value prefix from
     *                if present.
     *
     * @return An optional containing the extracted prefix enum string value if
     *         present or an empty optional if not.
     */
    static Optional<String> extractPrefix(String aString) {
        return extractPrefix(aString, true);
    }

    /**
     * Returns an optional containing the {@link Wave} value the passed string
     * argument can be parsed to. If it can't be parsed to a {@link Wave}, then
     * an empty optional is returned.
     *
     * @param aString    {@code String} to case insensitively parse to a
     *                   {@link Wave}.
     *
     * @param ignoreCase {@code boolean} specifying whether case is ignored or
     *                   not when parsing the passed {@code String} argument.
     *
     * @return An optional containing the {@link Wave} value the passed string
     *         argument can be parsed to if it can be, otherwise an empty optional.
     *
     * @throws IllegalArgumentException If passed {@code String} argument is
     *                                  {@code null}.
     */
    static Optional<Wave> parse(String aString, boolean ignoreCase) {
        if (aString == null) {
            throw new IllegalArgumentException("Null string argument.");
        }

        if (aString.isBlank()) {
            return Optional.empty();
        }

        if (ignoreCase) {
            return _waves.stream().filter(wave -> wave._stringValueAliases.stream()
                    .anyMatch(waveStringValue -> aString.equalsIgnoreCase(waveStringValue))).findFirst();
        }

        return _waves.stream().filter(wave -> wave._stringValueAliases.stream()
                .anyMatch(waveStringValue -> aString.equals(waveStringValue))).findFirst();
    }

    /**
     * Returns an optional containing the {@link Wave} value the passed string
     * argument can be case insensitively parsed to. If it can't be parsed to a
     * {@link Wave}, then an empty optional is returned.
     *
     * @param aString {@code String} to case insensitively parse to a {@link Wave}.
     *
     * @return An optional containing the {@link Wave} value the passed string
     *         argument can be case insensitively parsed to if it can be,
     *         otherwise an empty optional.
     */
    static Optional<Wave> parse(String aString) {
        return parse(aString, true);
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
