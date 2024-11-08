package sogott.beep;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.copyOfRange;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

final class AudioList {
    private final static AudioFormat audioFormat = new AudioFormat(Default.SAMPLE_RATE,
            Default.SAMPLE_SIZE,
            Default.CHANNELS, Default.SIGNED, Default.BIG_ENDIAN);

    private final List<Audio> _audioList;
    private final List<byte[]> _audioByteBuffers;
    private final SourceDataLine _line;

    AudioList(Collection<Audio> audioCollection, double wholeNoteDuration) throws LineUnavailableException {
        if (audioCollection == null) {
            throw new IllegalArgumentException("Null audio collection.");
        }

        this._audioList = List.copyOf(audioCollection);
        this._audioByteBuffers = this._audioList.stream().reduce(
                new ArrayList<byte[]>(),
                (buffers, audio) -> {
                    if (audio.wave() != null) {
                        final byte[] byteBufferToAdd = audio.wave()
                                .generate(Frequency.from(audio.pitch()),
                                        (int) Math.round(1.0
                                                / audio.duration()
                                                * wholeNoteDuration));

                        final int soundBytes = ((int) (Default.SILENCE_RATIO * byteBufferToAdd.length))
                                / audioFormat.getFrameSize()
                                * audioFormat.getFrameSize();

                        final byte[] silenceBuffer = new byte[(byteBufferToAdd.length - soundBytes)
                                / audioFormat.getFrameSize()
                                * audioFormat.getFrameSize()];

                        buffers.add(copyOfRange(byteBufferToAdd, 0, soundBytes));
                        buffers.add(silenceBuffer);
                    } else {
                        final byte[] byteBufferToAdd = GenerateAudioByteBuffer.silence(
                                (int) Math.round(1.0 / audio.duration()
                                        * wholeNoteDuration),
                                Default.SAMPLE_RATE);
                        buffers.add(byteBufferToAdd);
                    }

                    return buffers;
                },
                (buffers, moreBuffers) -> {
                    buffers.addAll(moreBuffers);
                    return buffers;
                });

        this._line = AudioSystem.getSourceDataLine(audioFormat);
    }

    static AudioList create(Collection<Audio> audioCollection, double wholeNoteDuration) {
        try {
            return new AudioList(audioCollection, wholeNoteDuration);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(300);
            return null;
        }
    }

    void outputToAudio() {
        try {
            this._line.open(audioFormat);
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

    void outputToFile(double wholeNoteDuration) {
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AudioList other && this._audioList.equals(other._audioList);
    }

    @Override
    public int hashCode() {
        return this._audioList.hashCode();
    }
}
