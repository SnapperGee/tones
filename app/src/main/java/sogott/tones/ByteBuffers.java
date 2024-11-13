package sogott.tones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.sound.sampled.AudioFormat;

import static java.util.Arrays.copyOfRange;
import static java.util.Collections.unmodifiableList;

final class ByteBuffers {
    private final static float SAMPLE_RATE = 44100;

    private final static int SAMPLE_SIZE = 16; // in bits
    private final static int CHANNELS = 1;
    private final static boolean SIGNED = true;
    private final static boolean BIG_ENDIAN = false;
    private final static double SILENCE_RATIO = 0.85;

    final static AudioFormat AUDIO_FORMAT = new AudioFormat(SAMPLE_RATE,
            SAMPLE_SIZE, CHANNELS, SIGNED, BIG_ENDIAN);

    private final List<Audio> _audioObjects;
    private final List<byte[]> _byteBuffers;
    private final int _hashCode;

    ByteBuffers(Collection<Audio> audioObjects, double wholeNoteDuration) {
        if (audioObjects == null) {
            throw new IllegalArgumentException("Null audio objects");
        }

        if (wholeNoteDuration <= 0) {
            throw new IllegalArgumentException("Non positive whole not duration: " + wholeNoteDuration);
        }

        this._audioObjects = List.copyOf(audioObjects);
        this._byteBuffers = unmodifiableList(this._audioObjects.stream().reduce(
                new ArrayList<byte[]>(),
                (buffers, audio) -> {
                    // if audio is not silence, fadeout tail of audio to prevent
                    // same notes from blending together
                    if (audio.wave() != null) {
                        final byte[] audioByteBuffer = audio.wave()
                                .generate(Frequency.from(audio.pitch()),
                                        (int) Math.ceil(1.0
                                                / audio.duration()
                                                * wholeNoteDuration));

                        final int soundBytes = ((int) (SILENCE_RATIO * audioByteBuffer.length))
                                / AUDIO_FORMAT.getFrameSize()
                                * AUDIO_FORMAT.getFrameSize();

                        // Create a fade-out effect on the last portion of the retained sound
                        final int fadeOutBytes = audioByteBuffer.length - soundBytes;
                        for (int i = 0; i < fadeOutBytes; i += 2) {
                            final double fadeFactor = 1.0 - ((double) i / fadeOutBytes); // Gradually decreases from 1
                                                                                         // to 0
                            final int sampleIndex = soundBytes + i;
                            if (sampleIndex + 1 < audioByteBuffer.length) {
                                // Apply fade-out to each sample in little-endian order
                                final short originalSample = (short) ((audioByteBuffer[sampleIndex + 1] << 8)
                                        | (audioByteBuffer[sampleIndex] & 0xFF));
                                final short fadedSample = (short) (originalSample * fadeFactor);
                                audioByteBuffer[sampleIndex] = (byte) (fadedSample & 0xFF);
                                audioByteBuffer[sampleIndex + 1] = (byte) ((fadedSample >> 8) & 0xFF);
                            }
                        }

                        // Add the faded audio buffer to the output
                        buffers.add(copyOfRange(audioByteBuffer, 0, soundBytes + fadeOutBytes));
                    } else {
                        final byte[] audioByteBuffer = GenerateWaveByteBuffer.silence(
                                (int) Math.round(1.0 / audio.duration()
                                        * wholeNoteDuration));
                        buffers.add(audioByteBuffer);
                    }

                    return buffers;
                },
                (buffers, moreBuffers) -> {
                    buffers.addAll(moreBuffers);
                    return buffers;
                }));

        this._hashCode = this._byteBuffers.hashCode();
    }

    List<byte[]> asList() {
        return this._byteBuffers;
    }

    Stream<byte[]> stream() {
        return this._byteBuffers.stream();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof ByteBuffers other && this._byteBuffers.equals(other._byteBuffers);
    }

    @Override
    public int hashCode() {
        return this._hashCode;
    }
}
