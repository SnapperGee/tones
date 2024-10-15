package sogott.beep;

import java.util.function.Function;
import java.util.function.BiFunction;

public enum Wave {
    SIN((freq, i) -> (sampleRate) -> 2.0 * Math.PI * freq * i / sampleRate);

    final private BiFunction<Double, Integer, Function<Float, Double>> _waveTypeFunc;

    private Wave(BiFunction<Double, Integer, Function<Float, Double>> waveTypeFunc) {
        this._waveTypeFunc = waveTypeFunc;
    }

    final public byte[] generate(double freq, int duration, float sampleRate) {
        final int samples = (int) ((duration / 1000.0) * sampleRate);
        final byte[] output = new byte[samples * 2]; // 16-bit audio

        for (int i = 0; i < samples; ++i) {
            final double angle = this._waveTypeFunc.apply(freq, i).apply(sampleRate);
            final short sample = (short) (Math.sin(angle) * Short.MAX_VALUE);

            // Little endian
            output[2 * i] = (byte) (sample & 0xFF);
            output[2 * i + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        return output;
    }
}
