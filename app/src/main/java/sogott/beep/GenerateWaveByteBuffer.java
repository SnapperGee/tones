package sogott.beep;

final class GenerateWaveByteBuffer {
    static byte[] silence(int duration, float sampleRate) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: " + duration);
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Non positive sample rate: " + sampleRate);
        }

        final int samples = (int) ((duration / 1000.0) * sampleRate);
        return new byte[samples * 2];
    }

    static byte[] sin(double freq, int duration, float sampleRate, short amplitude) {
        if (freq <= 0) {
            throw new IllegalArgumentException("Non positive frequency: " + freq);
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: " + duration);
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Non positive sample rate: " + sampleRate);
        }

        if (amplitude <= 0) {
            throw new IllegalArgumentException("Non positive amplitude: " + amplitude);
        }

        final double sustainLevel = .9;

        final int samples = (int) ((duration / 1000.0) * sampleRate);
        final byte[] output = new byte[samples * 2]; // 16-bit audio

        // ADSR defined in milliseconds
        final int attackDuration = 50;
        final int decayDuration = 50;
        final int releaseDuration = 50;

        final int attackSamples = (int) ((attackDuration / 1000.0) * sampleRate);
        final int decaySamples = (int) ((decayDuration / 1000.0) * sampleRate);
        final int releaseSamples = (int) ((releaseDuration / 1000.0) * sampleRate);

        final int sustainSamples = samples - (attackSamples + decaySamples + releaseSamples);

        for (int currentSample = 0; currentSample < samples; ++currentSample) {
            final double angle = 2.0 * Math.PI * freq * currentSample / sampleRate;

            double envelope;

            if (currentSample < attackSamples) {
                // Attack phase: gradually increase from 0 to 1
                envelope = (double) currentSample / attackSamples;
            } else if (currentSample < attackSamples + decaySamples) {
                // Decay phase: decrease from 1 to sustain level
                int decaySample = currentSample - attackSamples;
                envelope = 1.0 - (1.0 - sustainLevel) * ((double) decaySample / decaySamples);
            } else if (currentSample < attackSamples + decaySamples + sustainSamples) {
                // Sustain phase: maintain sustain level
                envelope = sustainLevel;
            } else {
                // Release phase: decrease from sustain level to 0
                int releaseSample = currentSample - (attackSamples + decaySamples + sustainSamples);
                envelope = sustainLevel * (1.0 - (double) releaseSample / releaseSamples);
            }

            final short sample = (short) (Math.sin(angle) * amplitude * envelope);

            // Little endian
            output[2 * currentSample] = (byte) (sample & 0xFF);
            output[2 * currentSample + 1] = (byte) ((sample >> 8) & 0xFF);
        }

        return output;
    }

    static byte[] square(double freq, int duration, float sampleRate, short amplitude) {
        if (freq <= 0) {
            throw new IllegalArgumentException("Non positive frequency: " + freq);
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: " + duration);
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Non positive sample rate: " + sampleRate);
        }

        if (amplitude <= 0) {
            throw new IllegalArgumentException("Non positive amplitude: " + amplitude);
        }

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
        if (freq <= 0) {
            throw new IllegalArgumentException("Non positive frequency: " + freq);
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: " + duration);
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Non positive sample rate: " + sampleRate);
        }

        if (amplitude <= 0) {
            throw new IllegalArgumentException("Non positive amplitude: " + amplitude);
        }

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

    static byte[] sawUp(double freq, int duration, float sampleRate, short amplitude) {
        if (freq <= 0) {
            throw new IllegalArgumentException("Non positive frequency: " + freq);
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: " + duration);
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Non positive sample rate: " + sampleRate);
        }

        if (amplitude <= 0) {
            throw new IllegalArgumentException("Non positive amplitude: " + amplitude);
        }

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

    static byte[] sawDown(double freq, int duration, float sampleRate, short amplitude) {
        if (freq <= 0) {
            throw new IllegalArgumentException("Non positive frequency: " + freq);
        }

        if (duration <= 0) {
            throw new IllegalArgumentException("Non positive duration: " + duration);
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Non positive sample rate: " + sampleRate);
        }

        if (amplitude <= 0) {
            throw new IllegalArgumentException("Non positive amplitude: " + amplitude);
        }

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

    private GenerateWaveByteBuffer() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated."
                        .formatted(GenerateWaveByteBuffer.class.getSimpleName()));
    }
}
