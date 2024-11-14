package sogott.tones;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class OutputByteBuffersTest {
    private final static RandomGenerator random = RandomGenerator.getDefault();

    @Test
    @DisplayName("OutputByteBuffers.toAudio(ByteBuffer, null) throws")
    void frequencyFromNoteAccidentalOctave() {
        final ByteBuffers byteBuffers = new ByteBuffers(emptyList(), random.nextInt(1, 1000));
        assertThrows(IllegalArgumentException.class, () -> OutputByteBuffers.toAudio(byteBuffers, null));
    }
}
