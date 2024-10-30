package sogott.beep;

import java.util.Set;
import java.util.EnumSet;
import java.util.stream.Stream;
import java.util.random.RandomGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static java.util.Collections.unmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

final class AudioArgProvider {

    final private static RandomGenerator random = RandomGenerator.getDefault();

    final private static Set<Wave> waves = unmodifiableSet(EnumSet.allOf(Wave.class));
    final private static Set<Note> notes = unmodifiableSet(EnumSet.allOf(Note.class));
    final private static Set<Accidental> accidentals = unmodifiableSet(EnumSet.allOf(Accidental.class));

    final static class WavePitchDuration implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return waves.stream().flatMap(wave -> {
                return Stream.concat(
                        notes.stream()
                                .map(note -> arguments(wave, new Pitch(note, null, random.nextInt(13)),
                                        random.nextInt(1, 65))),
                        notes.stream().flatMap(note -> accidentals.stream()
                                .map(accidental -> arguments(wave, new Pitch(note, accidental, random.nextInt(13)),
                                        random.nextInt(1, 65)))));
            });
        }
    }
}

///////////////////////
// valid constructor //
///////////////////////

final class AudioTest {
    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) does not throw")
    @ArgumentsSource(AudioArgProvider.WavePitchDuration.class)
    void waveStringValueIsValid(Wave wave, Pitch pitch, int duration) {
        assertDoesNotThrow(() -> new Audio(wave, pitch, duration));
    }
}
