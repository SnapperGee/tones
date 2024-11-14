package sogott.tones;

import java.util.Iterator;
import java.util.List;
import java.util.random.RandomGenerator;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

final class OutputByteBuffersTest {
    private final RandomGenerator random = RandomGenerator.getDefault();

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffer, null) throws")
    void outputByteBuffersToAudioPassedNullSourceDataLineThrows() {
        final ByteBuffers byteBuffers = new ByteBuffers(emptyList(), random.nextInt(1, 1000));
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toAudio(byteBuffers, null));
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(null, SourceDataLine) throws")
    void outputByteBuffersToAudioPassedNullByteBuffersThrows() {
        final SourceDataLine sourceDataLine = mock();
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toAudio(null, sourceDataLine));
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffers, SourceDataLine) calls asList() on ByteBuffers")
    void outputByteBuffersToAudioCallsByteBuffersAsList() throws LineUnavailableException {
        final ByteBuffers byteBuffers = mock();
        final SourceDataLine sourceDataLine = mock();
        OutputByteBuffers.toAudio(byteBuffers, sourceDataLine);
        verify(byteBuffers).asList();
    }

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffers, SourceDataLine) calls asList() on ByteBuffers")
    void outputByteBuffersToAudioCallsOpenStartDrainStopClose() throws LineUnavailableException {
        final ByteBuffers byteBuffers = mock();
        final SourceDataLine sourceDataLine = mock();

        final List<byte[]> byteArrayList = List.of(new byte[] { 1, 2, 3 }, new byte[] { 4, 5, 6 });

        when(byteBuffers.asList()).thenReturn(byteArrayList);

        OutputByteBuffers.toAudio(byteBuffers, sourceDataLine);

        verify(sourceDataLine).open(sourceDataLine.getFormat());
        verify(sourceDataLine).start();
        verify(sourceDataLine).write(eq(byteArrayList.get(0)), eq(0), anyInt());
        verify(sourceDataLine).write(eq(byteArrayList.get(1)), eq(0), anyInt());
        verify(sourceDataLine).drain();
        verify(sourceDataLine).stop();
        verify(sourceDataLine).close();
    }
}
