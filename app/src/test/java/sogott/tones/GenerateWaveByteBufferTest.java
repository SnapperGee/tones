package sogott.tones;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class GenerateWaveByteBufferTest {
    private final RandomGenerator random = RandomGenerator.getDefault();

    @Test
    @DisplayName("GenerateWaveByteBufferTest.silence(0 and # < 0) throws")
    void generateWaveByteBufferSilencePassedNonPositiveThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.silence(0));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.silence(random.nextInt(Integer.MIN_VALUE, 0)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.sin(0 and # < 0, # > 0) throws")
    void generateWaveByteBufferSinPassedAndNonPositiveFrequencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.sine(0, random.nextInt(1500)));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.sine(random.nextInt(Integer.MIN_VALUE, 0), random.nextInt(1500)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.sin(# > 0, 0 and # < 0) throws")
    void generateWaveByteBufferSinPassedAndNonPositiveDurationThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.sine(random.nextInt(1500), 0));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.sine(random.nextInt(1500), random.nextInt(Integer.MIN_VALUE, 0)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.square(0 and # < 0, # > 0) throws")
    void generateWaveByteBufferSquarePassedAndNonPositiveFrequencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.square(0, random.nextInt(1500)));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.square(random.nextInt(Integer.MIN_VALUE, 0), random.nextInt(1500)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.square(# > 0, 0 and # < 0) throws")
    void generateWaveByteBufferSquarePassedAndNonPositiveDurationThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.square(random.nextInt(1500), 0));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.square(random.nextInt(1500), random.nextInt(Integer.MIN_VALUE, 0)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.triangle(0 and # < 0, # > 0) throws")
    void generateWaveByteBufferTrianglePassedAndNonPositiveFrequencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.triangle(0, random.nextInt(1500)));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.triangle(random.nextInt(Integer.MIN_VALUE, 0), random.nextInt(1500)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.triangle(# > 0, 0 and # < 0) throws")
    void generateWaveByteBufferTrianglePassedAndNonPositiveDurationThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.triangle(random.nextInt(1500), 0));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.triangle(random.nextInt(1500), random.nextInt(Integer.MIN_VALUE, 0)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.sawUp(0 and # < 0, # > 0) throws")
    void generateWaveByteBufferSawUpPassedAndNonPositiveFrequencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.sawUp(0, random.nextInt(1500)));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.sawUp(random.nextInt(Integer.MIN_VALUE, 0), random.nextInt(1500)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.sawUp(# > 0, 0 and # < 0) throws")
    void generateWaveByteBufferSawUpPassedAndNonPositiveDurationThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.sawUp(random.nextInt(1500), 0));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.sawUp(random.nextInt(1500), random.nextInt(Integer.MIN_VALUE, 0)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.sawDown(0 and # < 0, # > 0) throws")
    void generateWaveByteBufferSawDownPassedAndNonPositiveFrequencyThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.sawDown(0, random.nextInt(1500)));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.sawDown(random.nextInt(Integer.MIN_VALUE, 0), random.nextInt(1500)));
    }

    @Test
    @DisplayName("GenerateWaveByteBufferTest.sawDown(# > 0, 0 and # < 0) throws")
    void generateWaveByteBufferSawDownPassedAndNonPositiveDurationThrows() {
        assertThrows(IllegalArgumentException.class, () -> GenerateWaveByteBuffer.sawDown(random.nextInt(1500), 0));
        assertThrows(IllegalArgumentException.class,
                () -> GenerateWaveByteBuffer.sawDown(random.nextInt(1500), random.nextInt(Integer.MIN_VALUE, 0)));
    }
}
