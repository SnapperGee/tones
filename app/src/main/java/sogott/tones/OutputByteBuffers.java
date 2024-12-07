package sogott.tones;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static java.util.Collections.enumeration;

/**
 * Class consisting exclusively of static methods for outputting the byte
 * buffers of {@link ByteBuffers} objects to a WAV file or audio output.
 *
 * @see ByteBuffers
 */
final class OutputByteBuffers {

    /**
     * Outputs the byte buffers as audible output. It uses the default
     * {@link SourceDataLine} output device/mixer which should be whatever the
     * output device the operating system is set to use.
     *
     * @param byteBuffers    The byte buffers to output as audio.
     *
     * @param sourceDataLine The {@link SourceDataLine} to use to write and drain
     *                       the byte buffer arrays to.
     *
     * @throws LineUnavailableException If there's an exception opening the passed
     *                                  {@link SourceDataLine}.
     *
     * @throws IllegalArgumentException If either of the passed arguments are
     *                                  {@code null}.
     */
    static final void toAudio(
            ByteBuffers byteBuffers,
            SourceDataLine sourceDataLine) throws LineUnavailableException {
        if (byteBuffers == null) {
            throw new IllegalArgumentException("Null byte buffers.");
        }

        if (sourceDataLine == null) {
            throw new IllegalArgumentException("Null source data line.");
        }

        sourceDataLine.open(sourceDataLine.getFormat());
        sourceDataLine.start();

        for (final byte[] buffer : byteBuffers.asList()) {
            sourceDataLine.write(buffer, 0, buffer.length);
        }

        sourceDataLine.drain();
        sourceDataLine.stop();
        sourceDataLine.close();
    }

    /**
     * Writes the passed byte buffers to a WAV file at the provided {@link Path}.
     * If an {@link IOException} occurs, then the stacktrace and exception message
     * is printed to stderr and the application is exited with code {@code 322}.
     * It either argument is null, then a {@link IllegalArgumentException} is
     * thrown.
     *
     * @param byteBuffers    The byte buffers to write to the WAV file.
     *
     * @param outputFilePath The path to write the WAV file to.
     *
     * @return The {@link Path} the WAV file was written to.
     *
     * @throws IllegalArgumentException If either of the passed arguments are
     *                                  {@code null}.
     */
    static final Path toWavFile(ByteBuffers byteBuffers, Path outputFilePath)
        throws IOException
    {
        if (byteBuffers == null) {
            throw new IllegalArgumentException("Null byte buffers");
        }

        if (outputFilePath == null) {
            throw new IllegalArgumentException("Null output file path");
        }

        final List<ByteArrayInputStream> inputStreams = byteBuffers.stream().map(ByteArrayInputStream::new)
                .toList();

        final Enumeration<ByteArrayInputStream> inputStreamsEnumeration = enumeration(inputStreams);
        final SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreamsEnumeration);

        final AudioInputStream audioInputStream = new AudioInputStream(sequenceInputStream, ByteBuffers.AUDIO_FORMAT,
                AudioSystem.NOT_SPECIFIED);

        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFilePath.toFile());

        return outputFilePath;
    }

    private OutputByteBuffers() {
        throw new UnsupportedOperationException(
                OutputByteBuffers.class.getSimpleName() + " is a static class and cannot be instantiated.");
    }
}
