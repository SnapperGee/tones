package sogott.beep;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;

enum Wave {
    SIN((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.sin(freq, duration, sampleRate, amplitude)),
    SQUARE((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.square(freq, duration, sampleRate, amplitude),
            "SQR"),
    TRIANGLE((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.triangle(freq, duration, sampleRate, amplitude),
            "TRI"),
    SAW_UP((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.sawUp(freq, duration, sampleRate, amplitude),
            "SUP",
            Set.of("SAWUP")),
    SAW_DOWN((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.sawDown(freq, duration, sampleRate, amplitude),
            "SDN",
            Set.of("SAWDOWN"));

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
        this._stringValue = Objects.requireNonNull(stringValue, "Null Wave string value.");
        this._stringValueAliases = Set.of(this._stringValue, this.name());
    }

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc, String stringValue,
            Set<String> stringValueAliases) {
        this._generatorFunc = generatorFunc;
        this._stringValue = Objects.requireNonNull(stringValue, "Null Wave string value.");
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

    static Optional<Wave> parse(String aString) {
        return aString != null && !aString.isBlank()
                ? Arrays.stream(Wave.values()).filter(wave -> wave._stringValueAliases.stream()
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
