package sogott.tones;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.sound.sampled.AudioFormat;

import static java.util.Arrays.copyOfRange;

/**
 * This class converts a {@code Collection} of {@link Audio} objects to a
 * {@code List} of their PCM byte array buffers appropriate for playback and
 * writing to files.
 *
 * <p>
 * Note that while the {@code List} of byte arrays is unmodifiable, the byte
 * arrays themselves can be modified.
 *
 * @see Audio
 * @see OutputByteBuffers
 */
final class ByteBuffers {
    private final static float SAMPLE_RATE = 44100;

    private final static int SAMPLE_SIZE = 16; // in bits
    private final static int CHANNELS = 1;
    private final static boolean SIGNED = true;
    private final static boolean BIG_ENDIAN = false;
    private final static double SILENCE_RATIO = 0.85;

    /**
     * {@link AudioFormat} used by this class when converting the {@link Audio}
     * objects to their PCM byte array buffers and internally by the
     * {@link OutputByteBuffers} static methods when outputting the byte array
     * buffers.
     */
    final static AudioFormat AUDIO_FORMAT = new AudioFormat(SAMPLE_RATE,
            SAMPLE_SIZE, CHANNELS, SIGNED, BIG_ENDIAN);

    private final List<Audio> _audioObjects;
    private final List<byte[]> _byteBuffers;

    /**
     * Constructs a {@link ByteBuffers} object instance from the passed
     * {@link Audio} Collection using the whole note duration {@code double} to
     * calculate the duration of the PCM byte array buffers the {@link Audio}
     * objects are converted to. If the {@link Audio} Collection is {@code null}
     * or the whole note duration is not positive (0 or less) then an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param audioObjects      Collection of {@link Audio} objects to convert
     *                          to a List of their PCM byte array buffers.
     *
     * @param wholeNoteDuration A positive {@code double} (greater than 0) used
     *                          to calculate the duration of the PCM byte array
     *                          buffers the {@link Audio} objects are converted to.
     *
     * @throws IllegalArgumentException If the {@link Audio} Collection is
     *                                  {@code null} or the whole note duration
     *                                  is not positive (0 or less).
     */
    ByteBuffers(Collection<Audio> audioObjects, double wholeNoteDuration) {
        if (audioObjects == null) {
            throw new IllegalArgumentException("Null audio objects collection.");
        }

        if (wholeNoteDuration <= 0) {
            throw new IllegalArgumentException("Non positive whole not duration: " + wholeNoteDuration);
        }

        this._audioObjects = List.copyOf(audioObjects);
        this._byteBuffers = this._audioObjects.stream()
                .map(audio -> audioObjectToByteArrayBuffer(audio, wholeNoteDuration)).toList();
    }

    /**
     * Returns an unmodifiable List of the PCM byte array buffers from the
     * converted {@link Audio} objects.
     *
     * <p>
     * Note that while the {@code List} of byte arrays is unmodifiable, the byte
     * arrays themselves can be modified.
     *
     * @return An unmodifiable List of the PCM byte array buffers from the
     *         converted {@link Audio} objects.
     */
    List<byte[]> asList() {
        return this._byteBuffers;
    }

    /**
     * Returns a {@code Stream} of the PCM byte array buffers from the converted
     * {@link Audio} objects.
     *
     * @return A {@code Stream} of the PCM byte array buffers from the converted
     *         {@link Audio} objects.
     */
    Stream<byte[]> stream() {
        return this._byteBuffers.stream();
    }

    private static byte[] audioObjectToByteArrayBuffer(Audio audio, double wholeNoteDuration) {
        if (audio.wave() != null) {
            final byte[] audioByteBuffer = audio.wave()
                    .generate(Frequency.from(audio.pitch()),
                            (int) Math.ceil(1.0
                                    / audio.duration()
                                    * wholeNoteDuration));

            // Add the faded audio buffer to the output
            return appendFadeout(audioByteBuffer, SILENCE_RATIO, AUDIO_FORMAT);
        } else {
            return GenerateWaveByteBuffer.silence(
                    (int) Math.round(1.0 / audio.duration()
                            * wholeNoteDuration));
        }
    }

    private static byte[] appendFadeout(byte[] audioBytes, double silenceRatio, AudioFormat audioFormat) {
        final int soundBytes = ((int) (silenceRatio * audioBytes.length))
                / audioFormat.getFrameSize()
                * audioFormat.getFrameSize();

        // Create a fade-out effect on the last portion of the retained sound
        final int fadeOutBytes = audioBytes.length - soundBytes;
        for (int i = 0; i < fadeOutBytes; i += 2) {
            // Gradually decreases from 1 to 0
            final double fadeFactor = 1.0 - ((double) i / fadeOutBytes);
            final int sampleIndex = soundBytes + i;
            if (sampleIndex + 1 < audioBytes.length) {
                // Apply fade-out to each sample in little-endian order
                final short originalSample = (short) ((audioBytes[sampleIndex + 1] << 8)
                        | (audioBytes[sampleIndex] & 0xFF));
                final short fadedSample = (short) (originalSample * fadeFactor);
                audioBytes[sampleIndex] = (byte) (fadedSample & 0xFF);
                audioBytes[sampleIndex + 1] = (byte) ((fadedSample >> 8) & 0xFF);
            }
        }

        // Add the faded audio buffer to the output
        return copyOfRange(audioBytes, 0, soundBytes + fadeOutBytes);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ByteBuffers other && this._byteBuffers.equals(other._byteBuffers);
    }

    @Override
    public int hashCode() {
        return this._byteBuffers.hashCode();
    }
}
