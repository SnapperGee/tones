package sogott.beep;

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

final class AudioByteBuffers {
    private final static AudioFormat AUDIO_FORMAT = new AudioFormat(Default.SAMPLE_RATE,
            Default.SAMPLE_SIZE,
            Default.CHANNELS, Default.SIGNED, Default.BIG_ENDIAN);

    private final List<Audio> _audioList;
    private final List<byte[]> _audioByteBuffers;
    private final SourceDataLine _line;

    AudioByteBuffers(Collection<Audio> audioCollection, double wholeNoteDuration)
            throws LineUnavailableException {
        if (audioCollection == null) {
            throw new IllegalArgumentException("Null audio collection.");
        }

        this._audioList = List.copyOf(audioCollection);
        this._audioByteBuffers = this._audioList.stream().reduce(
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

                        final int soundBytes = ((int) (Default.SILENCE_RATIO * audioByteBuffer.length))
                                / AUDIO_FORMAT.getFrameSize()
                                * AUDIO_FORMAT.getFrameSize();

                        final byte[] silenceBuffer = new byte[(audioByteBuffer.length - soundBytes)
                                / AUDIO_FORMAT.getFrameSize()
                                * AUDIO_FORMAT.getFrameSize()];

                        buffers.add(copyOfRange(audioByteBuffer, 0, soundBytes));
                        buffers.add(silenceBuffer);
                    } else {
                        final byte[] audioByteBuffer = GenerateWaveByteBuffer.silence(
                                (int) Math.round(1.0 / audio.duration()
                                        * wholeNoteDuration),
                                Default.SAMPLE_RATE);
                        buffers.add(audioByteBuffer);
                    }

                    return buffers;
                },
                (buffers, moreBuffers) -> {
                    buffers.addAll(moreBuffers);
                    return buffers;
                });

        this._line = AudioSystem.getSourceDataLine(AUDIO_FORMAT);
    }

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
            System.err.format("\n%s", e.getMessage());
            System.exit(311);
        }
    }

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
}
