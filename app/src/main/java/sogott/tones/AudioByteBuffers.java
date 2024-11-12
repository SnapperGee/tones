package sogott.tones;

import java.util.Collection;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.file.Path;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static java.util.Arrays.copyOfRange;
import static java.util.Collections.enumeration;

/**
 * This class converts a collection of {@link Audio} objects into audio byte
 * buffers that can be written to a file and/or outputted as audible audio. This
 * class initializes an internal {@link SourceDataLine} suitable for
 * {@link SourceDataLine#drain() drain}ing to playback the audio byte buffers.
 *
 * @see Audio
 */
final class AudioByteBuffers {

    private final static float SAMPLE_RATE = 44100;

    private final static int SAMPLE_SIZE = 16; // in bits
    private final static int CHANNELS = 1;
    private final static boolean SIGNED = true;
    private final static boolean BIG_ENDIAN = false;
    private final static double SILENCE_RATIO = 0.85;

    private final static AudioFormat AUDIO_FORMAT = new AudioFormat(SAMPLE_RATE,
            SAMPLE_SIZE, CHANNELS, SIGNED, BIG_ENDIAN);

    private final List<Audio> _audioList;
    private final List<byte[]> _audioByteBuffers;
    private final SourceDataLine _line;

    /**
     * Constructs an {@link AudioByteBuffers} instance from the passed
     * collection of {@link Audio} objects using the passed {@code double}
     * `wholeNoteDuration` argument to calculate the duration of each audio byte
     * buffer.
     *
     * @param audioCollection   {@link Audio} objects to convert to {@code byte}
     *                          buffers.
     *
     * @param wholeNoteDuration A {@code double} value used to calculate the
     *                          duration of the audio {@code byte} buffers.
     *
     * @throws LineUnavailableException If an error occurs when obtaining the
     *                                  {@link SourceDataLine}.
     *
     * @see Audio
     */
    AudioByteBuffers(Collection<Audio> audioCollection, double wholeNoteDuration)
            throws LineUnavailableException {
        if (audioCollection == null) {
            throw new IllegalArgumentException("Null audio collection.");
        }

        this._audioList = List.copyOf(audioCollection);
        this._audioByteBuffers = insertFadeOutToBuffers(this._audioList, wholeNoteDuration);
        this._line = AudioSystem.getSourceDataLine(AUDIO_FORMAT);
    }

    /**
     * Constructs an {@link AudioByteBuffers} instance from the passed
     * collection of {@link Audio} objects using the passed {@code double}
     * `wholeNoteDuration` argument to calculate the duration of each audio byte
     * buffer. This instantiation method differs from the
     * {@link #AudioByteBuffers(Collection, double) constructor} in that it
     * internally handles the {@link LineUnavailableException} that can be
     * thrown so it doesn't have to be handled externally. It does this by
     * printing the stacktrace and exception message to stderr and exiting if
     * the exception gets thrown.
     *
     * @param audioCollection   {@link Audio} objects to convert to {@code byte}
     *                          buffers.
     *
     * @param wholeNoteDuration A {@code double} value used to calculate the
     *                          duration of the audio {@code byte} buffers.
     *
     * @return An {@link AudioByteBuffers} object constructed from the passed
     *         {@link Audio} objects collection and {@code double}
     *         `wholeNoteDuration` arguments.
     */
    static AudioByteBuffers create(Collection<Audio> audioCollection, double wholeNoteDuration) {
        try {
            return new AudioByteBuffers(audioCollection, wholeNoteDuration);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(300);
            return null;
        }
    }

    /**
     * Outputs this {@link AudioByteBuffers} object byte buffers as audible
     * output. It uses the default {@link SourceDataLine} output device/mixer
     * which should be whatever the output device the operating system is set to
     * use.
     */
    void outputToAudio() {
        try {
            this._line.open(AUDIO_FORMAT);
            this._line.start();

            for (final byte[] buffer : this._audioByteBuffers) {
                this._line.write(buffer, 0, buffer.length);
            }

            this._line.drain();
            this._line.stop();
            this._line.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(311);
        }
    }

    /**
     * Writes this {@link AudioByteBuffers} object byte buffers to a WAV file
     * to the provided path. If an {@link IOException} occurs, then the
     * stacktrace and exception message is printed to stderr and the application
     * is exited.
     *
     * @param outputFilePath The {@link Path} to write the WAV file to.
     *
     * @return The path the WAV file was written to.
     */
    Path outputToFile(Path outputFilePath) {
        final List<ByteArrayInputStream> inputStreams = this._audioByteBuffers.stream().map(ByteArrayInputStream::new)
                .toList();

        final Enumeration<ByteArrayInputStream> inputStreamsEnumeration = enumeration(inputStreams);
        final SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreamsEnumeration);

        final AudioInputStream audioInputStream = new AudioInputStream(sequenceInputStream, AUDIO_FORMAT,
                AudioSystem.NOT_SPECIFIED);

        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFilePath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(322);
            return null;
        }

        return outputFilePath;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AudioByteBuffers other && this._audioList.equals(other._audioList);
    }

    @Override
    public int hashCode() {
        return this._audioList.hashCode();
    }

    private static List<byte[]> insertFadeOutToBuffers(List<Audio> audioList, double wholeNoteDuration) {
        return audioList.stream().reduce(
                new ArrayList<byte[]>(),
                (buffers, audio) -> {
                    // if audio is not silence, trim tail of audio to prevent
                    // same notes from blending together
                    if (audio.wave() != null) {
                        final byte[] audioByteBuffer = audio.wave()
                                .generate(Frequency.from(audio.pitch()),
                                        (int) Math.round(1.0
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
                });
    }
}
