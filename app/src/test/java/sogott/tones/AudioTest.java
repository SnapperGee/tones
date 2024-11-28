package sogott.tones;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import static java.util.Objects.hash;
import static java.util.Collections.unmodifiableList;
import static java.util.Arrays.asList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class AudioTestArgsProvider {

    static final RandomGenerator random = RandomGenerator.getDefault();

    private static final List<WaveShape> waveShapes = unmodifiableList(asList(WaveShape.values()));
    private static final List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private static final List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    static PitchLetter randomPitchLetter() {
        return pitchLetters.get(random.nextInt(pitchLetters.size()));
    }

    static Accidental randomAccidental() {
        return accidentals.get(random.nextInt(accidentals.size()));
    }

    static final class Valid {

        static final class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream()
                        .flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(wave,
                                        new Pitch(pitchLetter, accidental,
                                                random.nextInt(13)),
                                        1 << random.nextInt(8)))));
            }
        }

        static final class AltWavesPitchesDurations implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> {
                    final int waveIndex = waveShapes.indexOf(wave);
                    final int differentWaveIndex = random.ints(0, waveShapes.size())
                            .filter(i -> i != waveIndex).findFirst().orElseThrow();
                    final WaveShape differentWave = waveShapes.get(differentWaveIndex);

                    return pitchLetters.stream().flatMap(pitchLetter -> {
                        final int pitchLetterIndex = pitchLetters.indexOf(pitchLetter);
                        final int differentPitchLetterIndex = random
                                .ints(0, pitchLetters.size())
                                .filter(i -> i != pitchLetterIndex).findFirst()
                                .orElseThrow();
                        final PitchLetter differentPitchLetter = pitchLetters
                                .get(differentPitchLetterIndex);
                        final int duration = 1 << random.nextInt(8);
                        final int differentDuration = random.ints(0, 8)
                                .map(i -> 1 << i)
                                .filter(i -> i != duration).findFirst()
                                .orElseThrow();

                        return accidentals.stream()
                                .map(accidental -> {
                                    final int accidentalIndex = accidentals
                                            .indexOf(accidental);
                                    final int differentAccidentalIndex = random
                                            .ints(0, accidentals
                                                    .size())
                                            .filter(i -> i != accidentalIndex)
                                            .findFirst()
                                            .orElseThrow();
                                    final Accidental differentAccidental = accidentals
                                            .get(differentAccidentalIndex);

                                    return arguments(wave,
                                            differentWave,
                                            new Pitch(pitchLetter, accidental,
                                                    random.nextInt(13)),
                                            new Pitch(differentPitchLetter,
                                                    differentAccidental,
                                                    random.nextInt(13)),
                                            duration,
                                            differentDuration);
                                });
                    });
                });
            }
        }

        static final class WaveAltsPitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> {
                    final int waveIndex = waveShapes.indexOf(wave);
                    final int differentWaveIndex = random.ints(0, waveShapes.size())
                            .filter(i -> i != waveIndex).findFirst().orElseThrow();
                    final WaveShape differentWave = waveShapes.get(differentWaveIndex);

                    return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                            .map(accidental -> arguments(wave,
                                    differentWave,
                                    new Pitch(pitchLetter, accidental,
                                            random.nextInt(13)),
                                    1 << random.nextInt(8))));
                });
            }
        }

        static final class WaveAltPitchesAndDurations implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> {
                    final int pitchLetterIndex = pitchLetters.indexOf(pitchLetter);
                    final int differentPitchLetterIndex = random.ints(0, pitchLetters.size())
                            .filter(i -> i != pitchLetterIndex).findFirst()
                            .orElseThrow();
                    final PitchLetter differentPitchLetter = pitchLetters.get(differentPitchLetterIndex);
                    final int duration = 1 << random.nextInt(8);
                    final int differentDuration = random.ints(0, 8).map(i -> 1 << i)
                            .filter(i -> i != duration).findFirst()
                            .orElseThrow();

                    return accidentals.stream()
                            .map(accidental -> {
                                final int accidentalIndex = accidentals
                                        .indexOf(accidental);
                                final int differentAccidentalIndex = random
                                        .ints(0, accidentals
                                                .size())
                                        .filter(i -> i != accidentalIndex)
                                        .findFirst()
                                        .orElseThrow();
                                final Accidental differentAccidental = accidentals
                                        .get(differentAccidentalIndex);

                                return arguments(wave,
                                        new Pitch(pitchLetter, accidental,
                                                random.nextInt(13)),
                                        new Pitch(differentPitchLetter,
                                                differentAccidental,
                                                random.nextInt(13)),
                                        duration,
                                        differentDuration);
                            });
                }));
            }
        }

        static final class WavePitchDurationAlts implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> {
                    final int duration = 1 << random.nextInt(8);
                    final int differentDuration = random.ints(0, 8).map(i -> 1 << i)
                            .filter(i -> i != duration).findFirst()
                            .orElseThrow();

                    return accidentals.stream()
                            .map(accidental -> arguments(wave,
                                    new Pitch(pitchLetter, accidental,
                                            random.nextInt(13)),
                                    duration,
                                    differentDuration));
                }));
            }
        }

        static final class WavePitchAltsDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> {
                    final int pitchLetterIndex = pitchLetters.indexOf(pitchLetter);
                    final int differentPitchLetterIndex = random.ints(0, pitchLetters.size())
                            .filter(i -> i != pitchLetterIndex).findFirst()
                            .orElseThrow();
                    final PitchLetter differentPitchLetter = pitchLetters.get(differentPitchLetterIndex);

                    return accidentals.stream()
                            .map(accidental -> {
                                final int accidentalIndex = accidentals
                                        .indexOf(accidental);
                                final int differentAccidentalIndex = random
                                        .ints(0, accidentals
                                                .size())
                                        .filter(i -> i != accidentalIndex)
                                        .findFirst()
                                        .orElseThrow();
                                final Accidental differentAccidental = accidentals
                                        .get(differentAccidentalIndex);

                                return arguments(wave,
                                        new Pitch(pitchLetter, accidental,
                                                random.nextInt(13)),
                                        new Pitch(differentPitchLetter,
                                                differentAccidental,
                                                random.nextInt(13)),
                                        1 << random.nextInt(8));
                            });
                }));
            }
        }

        static final class AltWavesAndPitchesWithDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> {
                    final int waveIndex = waveShapes.indexOf(wave);
                    final int differentWaveIndex = random.ints(0, waveShapes.size())
                            .filter(i -> i != waveIndex).findFirst().orElseThrow();
                    final WaveShape differentWave = waveShapes.get(differentWaveIndex);

                    return pitchLetters.stream().flatMap(pitchLetter -> {
                        final int pitchLetterIndex = pitchLetters.indexOf(pitchLetter);
                        final int differentPitchLetterIndex = random
                                .ints(0, pitchLetters.size())
                                .filter(i -> i != pitchLetterIndex).findFirst()
                                .orElseThrow();
                        final PitchLetter differentPitchLetter = pitchLetters
                                .get(differentPitchLetterIndex);

                        return accidentals.stream()
                                .map(accidental -> {
                                    final int accidentalIndex = accidentals
                                            .indexOf(accidental);
                                    final int differentAccidentalIndex = random
                                            .ints(0, accidentals
                                                    .size())
                                            .filter(i -> i != accidentalIndex)
                                            .findFirst()
                                            .orElseThrow();
                                    final Accidental differentAccidental = accidentals
                                            .get(differentAccidentalIndex);

                                    return arguments(wave,
                                            differentWave,
                                            new Pitch(pitchLetter, accidental,
                                                    random.nextInt(13)),
                                            new Pitch(differentPitchLetter,
                                                    differentAccidental,
                                                    random.nextInt(13)),
                                            1 << random.nextInt(
                                                    8));
                                });
                    });
                });
            }
        }
    }

    static final class Invalid {

        static final class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waveShapes.stream().flatMap(wave -> Stream.concat(
                        Stream.of(
                                arguments(null, null, 1 << random.nextInt(8)),
                                arguments(null, null, -(1 << random.nextInt(8)))),
                        pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                                .flatMap(accidental -> Stream.of(
                                        arguments(wave, new Pitch(
                                                pitchLetter,
                                                accidental,
                                                random.nextInt(13)),
                                                -(1 << random.nextInt(
                                                        8))),
                                        arguments(null, new Pitch(
                                                pitchLetter,
                                                accidental,
                                                random.nextInt(13)),
                                                -(1 << random.nextInt(
                                                        8))),
                                        arguments(null, new Pitch(
                                                pitchLetter,
                                                accidental,
                                                random.nextInt(13)),
                                                1 << random.nextInt(
                                                        8)))))));
            }
        }
    }
}

final class AudioTest {

    static private final IntStream positiveIntArgs() {
        return IntStream.rangeClosed(1, 20);
    }

    static private final IntStream nonPositiveIntArgs() {
        return IntStream.rangeClosed(-12, 0);
    }

    ///////////////////////
    // valid constructor //
    ///////////////////////

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}) does not throw")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioConstructorPassedValidWavePitchDurationDoesNotThrow(WaveShape wave, Pitch pitch, int duration) {
        assertDoesNotThrow(() -> new Audio(wave, pitch, duration));
    }

    /////////////////////////
    // invalid constructor //
    /////////////////////////

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}) throws")
    @ArgumentsSource(AudioTestArgsProvider.Invalid.WavePitchDuration.class)
    void audioConstructorPassedInvalidWavesPitchesDurationsThrows(WaveShape wave, Pitch pitch, int duration) {
        assertThrows(IllegalArgumentException.class, () -> new Audio(wave, pitch, duration));
    }

    @Test
    @DisplayName("new Audio(null, Pitch, 1 < # < 1000) throws IllegalArgumentException")
    void audioConstructorPassedNullWaveShapeThrows() {
        final Pitch pitch = new Pitch(AudioTestArgsProvider.randomPitchLetter(),
                AudioTestArgsProvider.randomAccidental(), AudioTestArgsProvider.random.nextInt(13));
        assertThrows(
                IllegalArgumentException.class,
                () -> new Audio(null, pitch, AudioTestArgsProvider.random.nextInt(1, 1000)),
                () -> "new Audio(null, Pitch, 1 < # < 1000) did not throw IllegalArgumentException");
    }

    /////////////////////////
    // static silence ctor //
    /////////////////////////

    @ParameterizedTest(name = "Audio.silence({0}) does not throw")
    @MethodSource("positiveIntArgs")
    void silenceDoesNotThrow(int duration) {
        assertDoesNotThrow(() -> Audio.silence(duration));
    }

    @ParameterizedTest(name = "Audio.silence({0}) throws")
    @MethodSource("nonPositiveIntArgs")
    void silencePassedNonPositiveThrows(int duration) {
        assertThrows(IllegalArgumentException.class, () -> Audio.silence(duration));
    }

    @ParameterizedTest(name = "Audio.silence({0}).waveShape().isEmpty() returns true")
    @MethodSource("positiveIntArgs")
    void silenceWaveIsNone(int duration) {
        final Audio silenceAudio = Audio.silence(duration);
        assertTrue(silenceAudio.waveShape().isEmpty(), () -> "Audio.silence(%d).waveShape().isEmpty() returned false".formatted(duration));
    }

    @ParameterizedTest(name = "Audio.silence({0}).pitch() returns empty Optional")
    @MethodSource("positiveIntArgs")
    void silencePitchIsNull(int duration) {
        final Audio silenceAudio = Audio.silence(duration);
        assertTrue(silenceAudio.pitch().isEmpty());
    }

    @ParameterizedTest(name = "Audio.silence({0}).duration() returns {0}")
    @MethodSource("positiveIntArgs")
    void silenceDurationReturnsDuration(int duration) {
        final Audio silenceAudio = Audio.silence(duration);
        assertEquals(duration, silenceAudio.duration());
    }

    @ParameterizedTest(name = "Audio.silence({0}).stringValue() returns \"?.{0}\"")
    @MethodSource("positiveIntArgs")
    void silenceStringValueReturnsStringValue(int duration) {
        final Audio silenceAudio = Audio.silence(duration);
        final String stringValue = "%c%c%d".formatted(AudioString.SILENCE_CHAR,
                AudioString.Delimiter.VOICE_AND_DURATION.charValue(), duration);
        assertEquals(stringValue, silenceAudio.stringValue());
    }

    ////////////////////////
    // properties/getters //
    ////////////////////////

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).waveShape() returns wave")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioWavePropertyReturnsWave(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.waveShape().isPresent(), () -> "new Audio(WaveShape.%s, %s, %d).waveShape().isPresent() returned false");
        assertSame(wave, audio.waveShape().get());
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).pitch() returns Optional containing pitch")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioPitchPropertyReturnsPitch(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.pitch().isPresent(), () -> "new Audio(WaveShape.%s, %s, %d).pitch() returned non present Optional."
                .formatted(wave, pitch, duration));
        assertSame(pitch, audio.pitch().get());
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).duration() returns duration")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioDurationPropertyReturnsDuration(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertEquals(duration, audio.duration());
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).stringValue() returns string value")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioStringValuePropertyReturnsStringValue(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final String stringValue = "%s>%s.%d".formatted(wave.stringValue(), pitch.stringValue(), duration);
        assertEquals(stringValue, audio.stringValue());
    }

    ////////////
    // equals //
    ////////////

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}) equals same returns true")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioEqualsSameReturnsTrue(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.equals(audio));
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}) equals equivalent returns true")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioEqualsEquivalentReturnsTrue(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final Audio equalAudio = new Audio(wave, pitch, duration);
        assertTrue(audio.equals(equalAudio));
    }

    @ParameterizedTest(name = "new Audio(<Wave.{0}>, <{2}>, <{4}>) equals new Pitch(<Wave.{1}>, <{3}>, <{5}>) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.AltWavesPitchesDurations.class)
    void audioEqualsUnequalWavePitchDurationReturnsFalse(WaveShape aWave, WaveShape anotherWave, Pitch aPitch,
            Pitch anotherPitch,
            int aDuration, int anotherDuration) {
        final Audio anAudio = new Audio(aWave, aPitch, aDuration);
        final Audio aDifferentAudio = new Audio(anotherWave, anotherPitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(<Wave.{0}>, {2}, {3}) equals new Pitch(<Wave.{1}>, {2}, {3}) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WaveAltsPitchDuration.class)
    void audioEqualsUnequalWavesPitchDurationReturnsFalse(WaveShape aWave, WaveShape anotherWave, Pitch pitch,
            int duration) {
        final Audio anAudio = new Audio(aWave, pitch, duration);
        final Audio aDifferentAudio = new Audio(anotherWave, pitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, <{1}>, <{3}>) equals new Pitch(Wave.{0}, <{2}>, <{4}>) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WaveAltPitchesAndDurations.class)
    void audioEqualsWaveWithUnequalPitchesAndDurationsReturnsFalse(WaveShape wave, Pitch aPitch, Pitch anotherPitch,
            int aDuration, int anotherDuration) {
        final Audio anAudio = new Audio(wave, aPitch, aDuration);
        final Audio aDifferentAudio = new Audio(wave, anotherPitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, <{2}>) equals new Pitch(Wave.{0}, {1}, <{3}>) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDurationAlts.class)
    void audioEqualsWavePitchUnequalDurationsReturnsFalse(WaveShape wave, Pitch pitch,
            int aDuration, int anotherDuration) {
        final Audio anAudio = new Audio(wave, pitch, aDuration);
        final Audio aDifferentAudio = new Audio(wave, pitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, <{1}>, {3}) equals new Pitch(Wave.{0}, <{2}>, {3}) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchAltsDuration.class)
    void audioEqualsWavePitchUnequalDurationsReturnsFalse(WaveShape wave, Pitch aPitch,
            Pitch anotherPitch, int duration) {
        final Audio anAudio = new Audio(wave, aPitch, duration);
        final Audio aDifferentAudio = new Audio(wave, anotherPitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(<Wave.{0}>, {2}, {3}) equals new Pitch(<Wave.{1}>, {2}, {3}) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.AltWavesAndPitchesWithDuration.class)
    void audioAltWavesAndPitchesWithEqualDurationReturnsFalse(WaveShape wave, WaveShape anotherWave,
            Pitch aPitch, Pitch anotherPitch, int duration) {
        final Audio anAudio = new Audio(wave, aPitch, duration);
        final Audio aDifferentAudio = new Audio(anotherWave, anotherPitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    //////////////
    // hashCode //
    //////////////

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, Accidental.{1}, {2}).hashCode() equals Objects.hash(Wave.{0}, {1}, {2})")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioHashCodeComputedFromWavePitchDuration(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final int computedHashCode = hash(wave, pitch, duration);
        assertEquals(computedHashCode, audio.hashCode());
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).hashCode() is same for same object")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void sameAudioHasEqualHashCode(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.hashCode() == audio.hashCode());
    }

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).hashCode() is same for equal object")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void equalAudioHasEqualHashCode(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final Audio equalAudio = new Audio(wave, pitch, duration);
        assertTrue(audio.hashCode() == equalAudio.hashCode());
    }

    //////////////
    // toString //
    //////////////

    @ParameterizedTest(name = "new Audio(WaveShape.{0}, {1}, {2}).toString() returns pretty String")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioToStringReturnsPrettyString(WaveShape wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final String toString = "%s {wave=%s, pitch=\"%s\", duration=%d}".formatted(
                Audio.class.getSimpleName(),
                wave.name(), pitch.stringValue(), duration);
        assertEquals(toString, audio.toString());
    }
}
