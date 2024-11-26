package sogott.tones;

import java.util.random.RandomGenerator;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ByteBuffersTestArgsProvider {
    final static RandomGenerator random = RandomGenerator.getDefault();
    private final static List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));
    private final static List<WaveShape> waveShapes = unmodifiableList(asList(WaveShape.values()));

    final static List<Audio> audioObjects = pitchLetters.stream().flatMap(pitchLetter -> {
        return accidentals.stream().flatMap(accidental -> {
            final Pitch accidentalPitch = new Pitch(pitchLetter, accidental, random.nextInt(0, 13));
            return waveShapes.stream()
                    .map(wave -> new Audio(wave, accidentalPitch, random.nextInt(1, 1000)));
        });
    }).toList();
}

final class ByteBuffersTest {

    @Test
    @DisplayName("new ByteBuffers(Collection<Audio>, # > 0) does not throw")
    void byteBuffersConstructorPassedValidArgumentsDoesNotThrow() {
        assertDoesNotThrow(() -> new ByteBuffers(ByteBuffersTestArgsProvider.audioObjects,
                ByteBuffersTestArgsProvider.random.nextDouble(1, 200)));
    }

    @Test
    @DisplayName("new ByteBuffers(null, # > 0) throws")
    void byteBuffersConstructorPassedNullAudioCollectionThrows() {
        assertThrows(IllegalArgumentException.class, () -> new ByteBuffers(null,
                ByteBuffersTestArgsProvider.random.nextDouble(1, 201)));
    }

    @Test
    @DisplayName("new ByteBuffers(Collection<Audio>, # <= 0) throws")
    void byteBuffersConstructorPassedNegativeWholeNoteDurationThrows() {
        assertThrows(IllegalArgumentException.class, () -> new ByteBuffers(null,
                ByteBuffersTestArgsProvider.random.nextDouble(-200, 0)));
    }

    @Test
    @DisplayName("ByteBuffers.AUDIO_FORMAT matches new AudioFormat(44100, 16, 1, true, false)")
    void byteBuffersAudioFormatConstIsValid() {
        final AudioFormat expectedAudioFormat = new AudioFormat(44100, 16, 1, true, false);
        assertTrue(expectedAudioFormat.matches(ByteBuffers.AUDIO_FORMAT),
                () -> "ByteBuffers.AUDIO_FORMAT matches new AudioFormat(44100, 16, 1, true, false)");
    }
}
