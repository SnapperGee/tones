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

final class OutputByteBuffers {
    final static void toAudio(ByteBuffers byteBuffers) {
        if (byteBuffers == null) {
            throw new IllegalArgumentException("Null byte buffers");
        }

        try {
            final SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(ByteBuffers.AUDIO_FORMAT);
            sourceDataLine.open(ByteBuffers.AUDIO_FORMAT);
            sourceDataLine.start();

            for (final byte[] buffer : byteBuffers.asList()) {
                sourceDataLine.write(buffer, 0, buffer.length);
            }

            sourceDataLine.drain();
            sourceDataLine.stop();
            sourceDataLine.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(311);
        }
    }

    final static Path toWavFile(ByteBuffers byteBuffers, Path outputFilePath) {
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

    private OutputByteBuffers() {
        throw new UnsupportedOperationException(
                OutputByteBuffers.class.getSimpleName() + " is a static class and cannot be instantiated.");
    }
}
