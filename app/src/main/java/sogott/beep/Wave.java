package sogott.beep;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Collections;
import java.util.Optional;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiFunction;

import static java.util.Collections.unmodifiableSet;

enum Wave {
    SIN((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.sin(freq, duration, sampleRate, amplitude)),
    SQUARE((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.square(freq, duration, sampleRate, amplitude),
            "SQR"),
    TRIANGLE((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.triangle(freq, duration, sampleRate, amplitude),
            "TRI",
            Set.of("TRIANGLE")),
    SAW_UP((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.sawUp(freq, duration, sampleRate, amplitude),
            "SUP",
            Set.of("SAWUP")),
    SAW_DOWN((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.sawDown(freq, duration, sampleRate, amplitude),
            "SDN",
            Set.of("SAWDOWN"));

    final private static Set<Wave> waves = unmodifiableSet(EnumSet.allOf(Wave.class));

    final private static float DEFAULT_SAMPLE_RATE = 44100;

    final private BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> _generatorFunc;

    final private String _stringValue;

    final private Set<String> _stringValueAliases;

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc) {
        this._generatorFunc = generatorFunc;
        this._stringValue = this.name();
        this._stringValueAliases = Collections.singleton(this._stringValue);
    }

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc, String stringValue) {
        this._generatorFunc = generatorFunc;
        this._stringValue = stringValue;
        this._stringValueAliases = Set.of(this._stringValue, this.name());
    }

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc, String stringValue,
            Set<String> stringValueAliases) {
        this._generatorFunc = generatorFunc;
        this._stringValue = stringValue;
        final Set<String> stringSet = new HashSet<String>(Arrays.asList(this._stringValue, this.name()));
        stringSet.addAll(stringValueAliases);
        this._stringValueAliases = Collections.unmodifiableSet(stringSet);
    }

    String stringValue() {
        return this._stringValue;
    }

    Set<String> stringValueAliases() {
        return this._stringValueAliases;
    }

    boolean prefixes(String aString, boolean ignoreCase) {
        return aString != null && !aString.isEmpty() && this._stringValueAliases.stream()
                .anyMatch(stringValueAlias -> aString.length() >= stringValueAlias.length()
                        && aString.regionMatches(ignoreCase, 0, stringValueAlias, 0, stringValueAlias.length()));
    }

    boolean prefixes(String aString) {
        return prefixes(aString, true);
    }

    static Optional<String> extractPrefix(String aString, boolean ignoreCase) {
        return waves.stream().flatMap(wave -> wave.stringValueAliases().stream())
                .filter(waveStringAlias -> aString.length() >= waveStringAlias.length()
                        && aString.regionMatches(ignoreCase, 0, waveStringAlias, 0, waveStringAlias.length()))
                .findFirst();
    }

    static Optional<String> extractPrefix(String aString) {
        return extractPrefix(aString, true);
    }

    static Optional<Wave> parse(String aString) {
        return aString != null && !aString.isBlank()
                ? waves.stream().filter(wave -> wave._stringValueAliases.stream()
                        .anyMatch(waveStringValue -> aString.equalsIgnoreCase(waveStringValue))).findFirst()
                : Optional.empty();
    }

    byte[] generate(double freq, int duration) {
        return this._generatorFunc.apply(freq, duration).apply(DEFAULT_SAMPLE_RATE, Short.MAX_VALUE);
    }

    byte[] generate(double freq, int duration, short amplitude) {
        return this._generatorFunc.apply(freq, duration).apply(DEFAULT_SAMPLE_RATE, amplitude);
    }

    byte[] generate(double freq, int duration, float sampleRate, short amplitude) {
        return this._generatorFunc.apply(freq, duration).apply(sampleRate, amplitude);
    }
}
