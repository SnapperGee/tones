package sogott.beep;

import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static java.util.Collections.unmodifiableCollection;

final class AudioCollection {
    private final static AudioFormat audioFormat = new AudioFormat(Default.SAMPLE_RATE,
            Default.SAMPLE_SIZE,
            Default.CHANNELS, Default.SIGNED, Default.BIG_ENDIAN);

    final private Collection<Audio> _audioCollection;

    AudioCollection(Collection<Audio> audioCollection) {
        if (audioCollection == null) {
            throw new IllegalArgumentException("Null audio collection.");
        }

        this._audioCollection = unmodifiableCollection(audioCollection);
    }

    private byte[][] generateAudioByteBuffers(double wholeNoteDuration) {
        return this._audioCollection.stream()
                .map(audio -> audio.wave() != null
                        ? audio.wave()
                                .generate(Frequency.from(audio.pitch()),
                                        (int) Math.round(1.0
                                                / audio.duration()
                                                * wholeNoteDuration))
                        : GenerateAudioByteBuffer.silence(
                                (int) Math.round(1.0 / audio.duration()
                                        * wholeNoteDuration),
                                Default.SAMPLE_RATE))
                .toArray(byte[][]::new);
    }

    void outputToAudio(double wholeNoteDuration) {
        try {
            final SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);

            line.open(audioFormat);
            line.start();

            for (final byte[] buffer : generateAudioByteBuffers(wholeNoteDuration)) {
                final int soundBytes = ((int) (Default.SILENCE_RATIO * buffer.length))
                        / audioFormat.getFrameSize()
                        * audioFormat.getFrameSize();
                line.write(buffer, 0, soundBytes);
                final byte[] silenceBuffer = new byte[(buffer.length - soundBytes)
                        / audioFormat.getFrameSize()
                        * audioFormat.getFrameSize()];
                line.write(silenceBuffer, 0, silenceBuffer.length);
            }

            line.drain();
            line.stop();
            line.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.format("\n%s", e.getMessage());
            System.exit(333);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AudioCollection other && this._audioCollection.equals(other._audioCollection);
    }

    @Override
    public int hashCode() {
        return this._audioCollection.hashCode();
    }
}
