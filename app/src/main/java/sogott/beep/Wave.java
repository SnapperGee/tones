package sogott.beep;

import java.util.Objects;
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
            "SUP"),
    SAW_DOWN((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.sawDown(freq, duration, sampleRate, amplitude),
            "SDN");

    final private static float DEFAULT_SAMPLE_RATE = 44100;

    final private BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> _generatorFunc;

    final private String _stringValue;

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc) {
        this._generatorFunc = generatorFunc;
        this._stringValue = this.name();
    }

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc, String stringValue) {
        this._generatorFunc = generatorFunc;
        this._stringValue = Objects.requireNonNull(stringValue, "Null Wave string value.");
    }

    String stringValue() {
        return this._stringValue;
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
