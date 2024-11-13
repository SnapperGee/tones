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
        // if audio has a Wave shape and is therefore not silence add a fadeout to it
        if (audio.wave() != null) {
            final byte[] audioByteBuffer = audio.wave()
                    .generate(Frequency.from(audio.pitch()),
                            (int) Math.ceil(1.0
                                    / audio.duration()
                                    * wholeNoteDuration));

            return applyFadeoutTail(audioByteBuffer, SILENCE_RATIO, AUDIO_FORMAT);
        } else {
            return GenerateWaveByteBuffer.silence(
                    (int) Math.round(1.0 / audio.duration()
                            * wholeNoteDuration));
        }
    }

    /**
     * Takes in a byte array buffer of PCM audio and applies a fadeout to its
     * tail.
     *
     * <p>
     * Where to begin the fade out tail is determined by the
     * {@code double fadeoutStartFactor} parameter. This should be a value
     * between exclusive 0 and inclusive 1 and determines the point in the byte
     * buffer array to begin the fadeout. So if this value is {@code 0.8}, then
     * the fadeout will begin at 80% of the audio byte buffer array.
     *
     * <p>
     * This method is designed to process PCM audio that is formatted a specific
     * way. Particularly, it expects the audio to be 16bit and little endian.
     *
     * @param audioBytes         The byte array buffer of PCM audio to apply the
     *                           fadeout tail to.
     *
     * @param fadeoutStartFactor The point in the audio byte buffer array,
     *                           designated as a percentage of it, to begin the
     *                           fadeout tail.
     *
     * @param audioFormat        The {@link AudioFormat} that will be used to
     *                           format the byte buffer array.
     *
     * @return The byte buffer array with its tail updated with the attenuated
     *         fadeout.
     *
     * @throws IllegalArgumentException If any of the passed arguments are
     *                                  {@code null}, the fadeout start factor
     *                                  isn't in the range of 0 <
     *                                  {@code fadeoutStartFactor} <= 1 or the
     *                                  passed {@link AudioFormat} is in
     *                                  big endian.
     */
    private static byte[] applyFadeoutTail(
            byte[] audioBytes,
            double fadeoutStartFactor,
            AudioFormat audioFormat) {

        if (audioBytes == null) {
            throw new IllegalArgumentException("Null byte array.");
        }

        if (fadeoutStartFactor <= 0 || fadeoutStartFactor > 1) {
            throw new IllegalArgumentException(
                    "Fade out factor not between exclusive 0 and inclusive 1: " + fadeoutStartFactor);
        }

        if (audioFormat == null) {
            throw new IllegalArgumentException("Null audio format.");
        }

        if (audioFormat.isBigEndian()) {
            throw new IllegalArgumentException("Audio format is big endian and needs to be little endian.");
        }

        final int numOfBytesToNotAttenuate = ((int) (fadeoutStartFactor * audioBytes.length))
                / audioFormat.getFrameSize() * audioFormat.getFrameSize();

        final int numOfBytesToAttenuate = audioBytes.length - numOfBytesToNotAttenuate;

        for (int i = 0; i < numOfBytesToAttenuate; i += 2) {

            // Gradually decreases from 1 to 0 applying more attenuation
            final double attenuateFactor = 1.0 - ((double) i / numOfBytesToAttenuate);

            // index of byte to attenuate relative to last index of byte to not
            // attenuate
            final int sampleIndex = numOfBytesToNotAttenuate + i;

            if (sampleIndex + 1 < audioBytes.length) {
                // Apply fade-out to each sample in little-endian order
                final short originalSample = (short) ((audioBytes[sampleIndex + 1] << 8)
                        | (audioBytes[sampleIndex] & 0xFF));
                final short attenuatedSample = (short) (originalSample * attenuateFactor);
                audioBytes[sampleIndex] = (byte) (attenuatedSample & 0xFF);
                audioBytes[sampleIndex + 1] = (byte) ((attenuatedSample >> 8) & 0xFF);
            }
        }

        // return array with attenuated bytes appended to end of it
        return copyOfRange(audioBytes, 0, numOfBytesToNotAttenuate + numOfBytesToAttenuate);
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
