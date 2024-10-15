package sogott.beep;

import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        final float sampleRate = 44100;
        final int sampleSizeInBits = 16;
        final int channels = 1;
        final boolean signed = true;
        final boolean bigEndian = false;
        final float frequency = 260;
        final int duration = 750;

        final AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);

        // Obtain a line to play the sound
        SourceDataLine line = AudioSystem.getSourceDataLine(format);
        line.open(format);
        line.start();

        // Generate waveform data (e.g., sine wave)
        byte[] buffer = Wave.sin(frequency, duration);

        // Play the waveform
        line.write(buffer, 0, buffer.length);
        line.drain();
        buffer = Wave.triangle(frequency, duration);
        line.write(buffer, 0, buffer.length);
        line.drain();
        line.close();
    }
}
