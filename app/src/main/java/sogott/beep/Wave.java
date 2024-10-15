package sogott.beep;

class Wave {
    static byte[] sin(double freq, int duration) {
        return sin(freq, duration, 44100);
    }

    static byte[] sin(double freq, int duration, float sampleRate) {
        final int samples = (int) ((duration / 1000.0) * sampleRate);
        final byte[] output = new byte[samples * 2]; // 16-bit audio

        for (int currentSample = 0; currentSample < samples; ++currentSample) {
            final double angle = 2.0 * Math.PI * freq * currentSample / sampleRate;
            final short sample = (short) (Math.sin(angle) * Short.MAX_VALUE);

            // Little endian
            output[2 * currentSample] = (byte) (sample & 0xFF);
            output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        return output;
    }
}
