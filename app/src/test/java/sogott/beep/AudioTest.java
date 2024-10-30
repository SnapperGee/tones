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
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class AudioArgProvider {

    final private static RandomGenerator random = RandomGenerator.getDefault();

    final private static Set<Wave> waves = unmodifiableSet(EnumSet.allOf(Wave.class));
    final private static Set<Note> notes = unmodifiableSet(EnumSet.allOf(Note.class));
    final private static Set<Accidental> accidentals = unmodifiableSet(EnumSet.allOf(Accidental.class));

    final static class Valid {

        final static class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return waves.stream().flatMap(wave -> Stream.concat(
                        notes.stream()
                                .map(note -> arguments(wave, new Pitch(note, null, random.nextInt(13)),
                                        1 << random.nextInt(8))),
                        notes.stream().flatMap(note -> accidentals.stream()
                                .map(accidental -> arguments(wave, new Pitch(note, accidental, random.nextInt(13)),
                                        1 << random.nextInt(8))))));
            }
        }

        final static class PitchDuration implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> Stream.concat(
                        Stream.of(arguments(new Pitch(note, null, random.nextInt(13)), 1 << random.nextInt(8))),
                        accidentals.stream()
                                .map(accidental -> arguments(new Pitch(note, accidental, random.nextInt(13)),
                                        1 << random.nextInt(8)))));
            }
        }
    }

    final static class Invalid {

        final static class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return waves.stream().flatMap(wave -> Stream.concat(
                        Stream.of(
                                arguments(null, null, 1 << random.nextInt(8)),
                                arguments(null, null, -(1 << random.nextInt(8)))),
                        Stream.concat(
                                notes.stream()
                                        .flatMap(note -> Stream.of(
                                                arguments(wave, new Pitch(note, null, random.nextInt(13)),
                                                        -(1 << random.nextInt(8))),
                                                arguments(null, new Pitch(note, null, random.nextInt(13)),
                                                        1 << random.nextInt(8)),
                                                arguments(null, new Pitch(note, null, random.nextInt(13)),
                                                        -(1 << random.nextInt(8))))),
                                notes.stream().flatMap(note -> accidentals.stream()
                                        .flatMap(accidental -> Stream.of(
                                                arguments(wave, new Pitch(note, accidental, random.nextInt(13)),
                                                        -(1 << random.nextInt(8))),
                                                arguments(null, new Pitch(note, accidental, random.nextInt(13)),
                                                        -(1 << random.nextInt(8))),
                                                arguments(null, new Pitch(note, accidental, random.nextInt(13)),
                                                        1 << random.nextInt(8))))))));
            }
        }

        final static class PitchDuration implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> Stream.concat(
                        Stream.of(
                                arguments(new Pitch(note, null, random.nextInt(13)), -(1 << random.nextInt(8))),
                                arguments(null, 1 << random.nextInt(8)),
                                arguments(null, -(1 << random.nextInt(8)))),
                        accidentals.stream()
                                .map(accidental -> arguments(new Pitch(note, accidental, random.nextInt(13)),
                                        -(1 << random.nextInt(8))))));
            }
        }
    }
}

final class AudioTest {

    ///////////////////////
    // valid constructor //
    ///////////////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) does not throw")
    @ArgumentsSource(AudioArgProvider.Valid.WavePitchDuration.class)
    void audioConstructorPassedValidWavePitchDurationDoesNotThrow(Wave wave, Pitch pitch, int duration) {
        assertDoesNotThrow(() -> new Audio(wave, pitch, duration));
    }

    @ParameterizedTest(name = "new Pitch({0}, {1}) does not throw")
    @ArgumentsSource(AudioArgProvider.Valid.PitchDuration.class)
    void audioConstructorPassedValidPitchDurationDoesNotThrow(Pitch pitch, int duration) {
        assertDoesNotThrow(() -> new Audio(pitch, duration));
    }

    /////////////////////////
    // invalid constructor //
    /////////////////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) throws")
    @ArgumentsSource(AudioArgProvider.Invalid.WavePitchDuration.class)
    void audioConstructorPassedInvalidWavesPitchesDurationsThrows(Wave wave, Pitch pitch, int duration) {
        assertThrows(IllegalArgumentException.class, () -> new Audio(wave, pitch, duration));
    }

    @ParameterizedTest(name = "new Pitch({0}, {1}) throws")
    @ArgumentsSource(AudioArgProvider.Invalid.PitchDuration.class)
    void audioConstructorPassedValidPitchInvalidDurationThrows(Pitch pitch, int duration) {
        assertThrows(IllegalArgumentException.class, () -> new Audio(pitch, duration));
    }

    ////////////////////////
    // properties/getters //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).wave() returns wave")
    @ArgumentsSource(AudioArgProvider.Valid.WavePitchDuration.class)
    void audioWavePropertyReturnsWave(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertSame(wave, audio.wave());
    }

    @ParameterizedTest(name = "new Pitch({0}, {1}).wave() returns Wave.SIN")
    @ArgumentsSource(AudioArgProvider.Valid.PitchDuration.class)
    void audioWavePropertyReturnsWave(Pitch pitch, int duration) {
        final Audio audio = new Audio(pitch, duration);
        assertSame(Wave.SIN, audio.wave());
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).pitch() returns pitch")
    @ArgumentsSource(AudioArgProvider.Valid.WavePitchDuration.class)
    void audioPitchPropertyReturnsPitch(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertSame(pitch, audio.pitch());
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).duration() returns duration")
    @ArgumentsSource(AudioArgProvider.Valid.WavePitchDuration.class)
    void audioDurationPropertyReturnsDuration(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertEquals(duration, audio.duration());
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).stringValue() returns string value")
    @ArgumentsSource(AudioArgProvider.Valid.WavePitchDuration.class)
    void audioStringValuePropertyReturnsStringValue(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final String stringValue = "%s>%s.%d".formatted(wave.stringValue(), pitch.stringValue(), duration);
        assertEquals(stringValue, audio.stringValue());
    }
}
