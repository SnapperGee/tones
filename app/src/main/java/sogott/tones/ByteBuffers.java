package sogott.tones;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.sound.sampled.AudioFormat;

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
    private static final float SAMPLE_RATE = 44100;

    private static final int SAMPLE_SIZE = 16; // in bits
    private static final int CHANNELS = 1;
    private static final boolean SIGNED = true;
    private static final boolean BIG_ENDIAN = false;
    private static final double SILENCE_RATIO = 0.85;

    /**
     * {@link AudioFormat} used by this class when converting the {@link Audio}
     * objects to their PCM byte array buffers and internally by the
     * {@link OutputByteBuffers} static methods when outputting the byte array
     * buffers.
     */
    static final AudioFormat AUDIO_FORMAT = new AudioFormat(SAMPLE_RATE,
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
            throw new IllegalArgumentException("Non positive whole note duration: " + wholeNoteDuration);
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

    /**
     * Converts the passed {@link Audio} object to a byte array buffer using
     * the {@code double wholeNoteDuration} to convert the duration of the
     * {@link Audio} object to the actual span of time the byte buffer should
     * be.
     *
     * @param audio             The {@link Audio} object to convert to a byte array
     *                          buffer.
     *
     * @param wholeNoteDuration {@code double} used to convert the duration of the
     *                          {@link Audio} object to the actual span of time the
     *                          byte buffer should be.
     *
     * @return The byte array buffer resulting from converting the {@link Audio}
     *         object.
     *
     * @throws IllegalArgumentException If the passed {@link Audio} object is
     *                                  {@code null} or the
     *                                  {@code wholeNoteDuration} isn't positive
     *                                  (0 or less).
     *
     * @see Audio
     */
    private static byte[] audioObjectToByteArrayBuffer(Audio audio, double wholeNoteDuration) {
        if (audio == null) {
            throw new IllegalArgumentException("Null byte audio.");
        }

        if (wholeNoteDuration <= 0) {
            throw new IllegalArgumentException("Non positive whole note duration: " + wholeNoteDuration);
        }

        return audio.waveShape().map(waveShape -> {
            final byte[] audioByteBuffer = waveShape
                    .generate(audio.pitch().orElseThrow().frequency(),
                            (int) Math.ceil(1.0
                                    / audio.duration()
                                    * wholeNoteDuration));

            return applyFadeoutTail(audioByteBuffer, SILENCE_RATIO, AUDIO_FORMAT);
        }).orElse(GenerateWaveByteBuffer.silence(
                (int) Math.ceil(1.0 / audio.duration()
                        * wholeNoteDuration)));
    }

    /**
     * Takes in a byte array buffer of PCM audio and applies a fadeout to its
     * tail. <strong><em>This method modifies the passed byte array in
     * place.</em></strong>
     *
     * <p>
     * Where to begin the fade out tail is determined by the
     * {@code double fadeoutStartFactor} parameter. This should be a value
     * between exclusive 0 and inclusive 1 and determines the point in the byte
     * buffer array to begin the fadeout. So if this value is {@code 0.8}, then
     * the fadeout will begin at 80% of the audio byte buffer array.
     *
     * <p>
     * This method is designed to process PCM audio that is formatted to be 16bit.
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
     *                                  {@code null} or the fadeout start factor
     *                                  isn't in the range of 0 <
     *                                  {@code fadeoutStartFactor} <= 1.
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

        final int numOfBytesToNotAttenuate = ((int) (fadeoutStartFactor * audioBytes.length))
                / audioFormat.getFrameSize() * audioFormat.getFrameSize();

        final int numOfBytesToAttenuate = audioBytes.length - numOfBytesToNotAttenuate;

        final int leastSignificantByteIndexOffSet = audioFormat.isBigEndian() ? 1 : 0;
        final int mostSignificantByteIndexOffSet = audioFormat.isBigEndian() ? 0 : 1;

        for (int i = 0; i < numOfBytesToAttenuate; i += 2) {

            // gradually decreases from 1 to 0 applying more attenuation
            final double attenuateFactor = 1.0 - ((double) i / numOfBytesToAttenuate);

            // index of byte to attenuate relative to last index of byte to not
            // attenuate
            final int sampleIndex = numOfBytesToNotAttenuate + i;

            if (sampleIndex + 1 < audioBytes.length) {
                final byte leastSignificantByte = audioBytes[sampleIndex + leastSignificantByteIndexOffSet];
                final byte mostSignificantByte = audioBytes[sampleIndex + mostSignificantByteIndexOffSet];

                // convert least significant byte from value between -128 to 127
                // to value between 0 and 255
                final int leastSignificantByteAsUInt = leastSignificantByte & 0xFF;

                // shift most significant bits to least significant bits
                // position so they can then be recombined with original
                // least significant bits.
                final int mostSignificantByteShiftedToLeastSignificant = mostSignificantByte << 8;

                // combine intermediate byte values into single value
                final short originalSample = (short) (mostSignificantByteShiftedToLeastSignificant
                        | leastSignificantByteAsUInt);

                // apply attenuation to sample
                final short attenuatedSample = (short) (originalSample * attenuateFactor);

                // extract least significant byte of attenuated sample and put
                // it back into array
                audioBytes[sampleIndex + leastSignificantByteIndexOffSet] = (byte) (attenuatedSample & 0xFF);

                // extract most significant byte of attenuated sample and put
                // it back into array
                audioBytes[sampleIndex + mostSignificantByteIndexOffSet] = (byte) ((attenuatedSample >> 8) & 0xFF);
            }
        }

        // return byte array with attenuated fadeout tail bytes applied to end of it
        return audioBytes;
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
