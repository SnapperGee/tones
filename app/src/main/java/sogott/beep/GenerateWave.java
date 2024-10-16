package sogott.beep;

final class GenerateWave {
    static byte[] sin(double freq, int duration) {
        return sin(freq, duration, 44100, Short.MAX_VALUE);
    }

    static byte[] square(double freq, int duration) {
        return square(freq, duration, 44100, Short.MAX_VALUE);
    }

    static byte[] triangle(double freq, int duration) {
        return triangle(freq, duration, 44100, Short.MAX_VALUE);
    }

    static byte[] sin(double freq, int duration, float sampleRate, short amplitude) {
        final int samples = (int) ((duration / 1000.0) * sampleRate);
        final byte[] output = new byte[samples * 2]; // 16-bit audio

        for (int currentSample = 0; currentSample < samples; ++currentSample) {
            final double angle = 2.0 * Math.PI * freq * currentSample / sampleRate;
            final short sample = (short) (Math.sin(angle) * amplitude);

            // Little endian
            output[2 * currentSample] = (byte) (sample & 0xFF);
            output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        return output;
    }

    static byte[] square(double freq, int duration, float sampleRate, short amplitude) {
        final int samples = (int) ((duration / 1000.0) * sampleRate);
        final byte[] output = new byte[samples * 2]; // 16-bit audio

        for (int currentSample = 0; currentSample < samples; ++currentSample) {
            final double angle = 2.0 * Math.PI * freq * currentSample / sampleRate;
            final short sample = (short) ((Math.sin(angle) >= 0 ? 1 : -1) * amplitude);

            // Little endian
            output[2 * currentSample] = (byte) (sample & 0xFF);
            output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        return output;
    }

    static byte[] triangle(double freq, int duration, float sampleRate, short amplitude) {
        final int samples = (int) ((duration / 1000.0) * sampleRate);
        final double period = sampleRate / freq;
        final byte[] output = new byte[samples * 2]; // 16-bit audio

        for (int currentSample = 0; currentSample < samples; ++currentSample) {
            final double position = currentSample % period;
            final double value = 2 * Math.abs(2 * (position / period) - 1) - 1;
            final short sample = (short) (value * amplitude);

            // Little endian
            output[2 * currentSample] = (byte) (sample & 0xFF);
            output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        return output;
    }

    static class Saw {

        static byte[] up(double freq, int duration) {
            return up(freq, duration, 44100, Short.MAX_VALUE);
        }

        static byte[] down(double freq, int duration) {
            return down(freq, duration, 44100, Short.MAX_VALUE);
        }

        static byte[] up(double freq, int duration, float sampleRate, short amplitude) {
            final int samples = (int) ((duration / 1000.0) * sampleRate);
            final double period = sampleRate / freq;
            final byte[] output = new byte[samples * 2]; // 16-bit audio

            for (int currentSample = 0; currentSample < samples; ++currentSample) {
                final double position = currentSample % period;
                final double value = (2 * (position / period)) - 1;
                final short sample = (short) (value * amplitude);

                // Little endian
                output[2 * currentSample] = (byte) (sample & 0xFF);
                output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
            }

            return output;
        }

        static byte[] down(double freq, int duration, float sampleRate, short amplitude) {
            final int samples = (int) ((duration / 1000.0) * sampleRate);
            final double period = sampleRate / freq;
            final byte[] output = new byte[samples * 2]; // 16-bit audio

            for (int currentSample = 0; currentSample < samples; ++currentSample) {
                final double position = currentSample % period;
                final double value = 1 - (2 * (position / period));
                final short sample = (short) (value * amplitude);

                // Little endian
                output[2 * currentSample] = (byte) (sample & 0xFF);
                output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
            }

            return output;
        }
    }
}
