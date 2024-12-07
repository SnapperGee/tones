package sogott.tones;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.random.RandomGenerator;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.DisplayName;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.io.FileMatchers.anExistingFile;;

final class OutputByteBuffersTest {
    private final RandomGenerator random = RandomGenerator.getDefault();

    /////////////
    // toAudio //
    /////////////

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffers, null) throws")
    void outputByteBuffersToAudioPassedNullSourceDataLineThrows() {
        final ByteBuffers byteBuffers = new ByteBuffers(emptyList(), random.nextInt(1, 1000));
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toAudio(byteBuffers, null));
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(null, SourceDataLine) throws")
    void outputByteBuffersToAudioPassedNullByteBuffersThrows() throws LineUnavailableException {
        final SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(ByteBuffers.AUDIO_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toAudio(null, sourceDataLine));
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffers, SourceDataLine) opens, starts, writes to, drains, stops, and closes SourceDataLine")
    void outputByteBuffersToAudioCallsOpenStartDrainStopClose() throws LineUnavailableException {
        final ByteBuffers byteBuffers = mock();
        final SourceDataLine sourceDataLine = mock();

        final List<byte[]> byteArrayList = List.of(new byte[] { 1, 2, 3 }, new byte[] { 4, 5, 6 });

        when(byteBuffers.asList()).thenReturn(byteArrayList);

        OutputByteBuffers.toAudio(byteBuffers, sourceDataLine);

        verify(sourceDataLine).open(sourceDataLine.getFormat());
        verify(sourceDataLine).start();
        verify(byteBuffers).asList();
        verify(sourceDataLine).write(eq(byteArrayList.get(0)), eq(0), anyInt());
        verify(sourceDataLine).write(eq(byteArrayList.get(1)), eq(0), anyInt());
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }

    ///////////////
    // toWavFile //
    ///////////////

    @Test
    @DisplayName("OutputByteBuffers.toWaveFile(ByteBuffers, null) throws")
    void outputByteBuffersToWaveFilePassedNullPathThrows() {
        final ByteBuffers byteBuffers = new ByteBuffers(emptyList(), random.nextInt(1, 1000));
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toWavFile(byteBuffers, null));
    }

    @Test
    @DisplayName("OutputByteBuffers.toWaveFile(null, Path) throws")
    void outputByteBuffersToWavFilePassedNullByteBuffers(@TempDir Path tempDir) {
        final Path filePath = tempDir.resolve("TEST_FILE.wav");
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toWavFile(null, filePath));
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffers, Path with \".wav\" extension) creates file at Path")
    void outputByteBuffersToWavFilePassedPathWithWavExtensionCreatesFileAtPath(@TempDir Path tempDir) throws IOException {
        final ByteBuffers byteBuffers = new ByteBuffers(emptyList(), random.nextInt(1, 1000));
        final Path filePath = tempDir.resolve("TEST_FILE.wav");
        OutputByteBuffers.toWavFile(byteBuffers, filePath);
        assertTrue(filePath.toFile().exists(), () -> "File \"%s\" not created".formatted(filePath.toString()));
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffers, Path with out \".wav\" extension) creates file at Path with \".wav\" extension")
    void outputByteBuffersToWavFilePassedPathWithoutWavExtensionCreatesFileAtPathWithWavExtension(
            @TempDir Path tempDir) throws IOException {
        final ByteBuffers byteBuffers = new ByteBuffers(emptyList(), random.nextInt(1, 1000));
        final Path filePathArg = tempDir.resolve("TEST_FILE");
        final Path expectedFilePath = Path.of(filePathArg.toString() + ".wav");
        OutputByteBuffers.toWavFile(byteBuffers, expectedFilePath);
        assertThat("File \"%s\" not created".formatted(expectedFilePath.toString()), expectedFilePath.toFile(), is(anExistingFile()));
    }
}
