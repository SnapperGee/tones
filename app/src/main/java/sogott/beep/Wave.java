package sogott.beep;

import java.util.function.BiFunction;

enum Wave {
    SIN((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.sin(freq, duration, sampleRate, amplitude)),
    SQUARE((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.square(freq, duration, sampleRate, amplitude)),
    TRIANGLE((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.triangle(freq, duration, sampleRate, amplitude)),
    SAW_UP((freq, duration) -> (sampleRate, amplitude) -> GenerateWave.Saw.up(freq, duration, sampleRate, amplitude)),
    SAW_DOWN((freq,
            duration) -> (sampleRate, amplitude) -> GenerateWave.Saw.down(freq, duration, sampleRate, amplitude));

    final private static float DEFAULT_SAMPLE_RATE = 44100;

    final private BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> _generatorFunc;

    private Wave(BiFunction<Double, Integer, BiFunction<Float, Short, byte[]>> generatorFunc) {
        this._generatorFunc = generatorFunc;
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
