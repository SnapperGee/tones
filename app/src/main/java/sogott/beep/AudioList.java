package sogott.beep;

import java.util.Collection;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

final class AudioList {
    private final static AudioFormat audioFormat = new AudioFormat(Default.SAMPLE_RATE,
            Default.SAMPLE_SIZE,
            Default.CHANNELS, Default.SIGNED, Default.BIG_ENDIAN);

    private final List<Audio> _audioList;
    private final double _wholeNoteDuration;
    private final List<byte[]> _audioByteBuffers;
    private final SourceDataLine _line;

    AudioList(Collection<Audio> audioCollection, double wholeNoteDuration) throws LineUnavailableException {
        if (audioCollection == null) {
            throw new IllegalArgumentException("Null audio collection.");
        }

        this._audioList = List.copyOf(audioCollection);
        this._wholeNoteDuration = wholeNoteDuration;
        this._audioByteBuffers = this._audioList.stream().map(audio -> audio.wave() != null
                ? audio.wave()
                        .generate(Frequency.from(audio.pitch()),
                                (int) Math.round(1.0
                                        / audio.duration()
                                        * wholeNoteDuration))
                : GenerateAudioByteBuffer.silence(
                        (int) Math.round(1.0 / audio.duration()
                                * wholeNoteDuration),
                        Default.SAMPLE_RATE))
                .toList();
        this._line = AudioSystem.getSourceDataLine(audioFormat);
    }

    static AudioList create(Collection<Audio> audioCollection, double wholeNoteDuration) {
        try {
            return new AudioList(audioCollection, wholeNoteDuration);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println('\n' + e.getMessage());
            System.exit(333);
            return null;
        }
    }

    void outputToAudio(double wholeNoteDuration) {
        try {
            this._line.open(audioFormat);
            this._line.start();

            for (final byte[] buffer : this._audioByteBuffers) {
                final int soundBytes = ((int) (Default.SILENCE_RATIO * buffer.length))
                        / audioFormat.getFrameSize()
                        * audioFormat.getFrameSize();
                this._line.write(buffer, 0, soundBytes);
                final byte[] silenceBuffer = new byte[(buffer.length - soundBytes)
                        / audioFormat.getFrameSize()
                        * audioFormat.getFrameSize()];
                this._line.write(silenceBuffer, 0, silenceBuffer.length);
            }

            this._line.drain();
            this._line.stop();
            this._line.close();

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.format("\n%s", e.getMessage());
            System.exit(333);
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
