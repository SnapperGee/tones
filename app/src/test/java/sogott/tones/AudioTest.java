package sogott.tones;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.random.RandomGenerator;
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
import static org.junit.jupiter.api.Assertions.assertNull;

final class AudioTestArgsProvider {

    final static RandomGenerator random = RandomGenerator.getDefault();

    final private static List<Wave> waves = unmodifiableList(asList(Wave.values()));
    final private static List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    final private static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static class Valid {

        final static class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                        .map(accidental -> arguments(wave,
                                new Pitch(pitchLetter, accidental,
                                        random.nextInt(13)),
                                1 << random.nextInt(8)))));
            }
        }

        final static class AltWavesPitchesDurations implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> {
                    final int waveIndex = waves.indexOf(wave);
                    final int differentWaveIndex = random.ints(0, waves.size())
                            .filter(i -> i != waveIndex).findFirst().orElseThrow();
                    final Wave differentWave = waves.get(differentWaveIndex);

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

        final static class WaveAltsPitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> {
                    final int waveIndex = waves.indexOf(wave);
                    final int differentWaveIndex = random.ints(0, waves.size())
                            .filter(i -> i != waveIndex).findFirst().orElseThrow();
                    final Wave differentWave = waves.get(differentWaveIndex);

                    return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                            .map(accidental -> arguments(wave,
                                    differentWave,
                                    new Pitch(pitchLetter, accidental,
                                            random.nextInt(13)),
                                    1 << random.nextInt(8))));
                });
            }
        }

        final static class WaveAltPitchesAndDurations implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> {
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

        final static class WavePitchDurationAlts implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> {
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

        final static class WavePitchAltsDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> pitchLetters.stream().flatMap(pitchLetter -> {
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

        final static class AltWavesAndPitchesWithDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> {
                    final int waveIndex = waves.indexOf(wave);
                    final int differentWaveIndex = random.ints(0, waves.size())
                            .filter(i -> i != waveIndex).findFirst().orElseThrow();
                    final Wave differentWave = waves.get(differentWaveIndex);

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

    final static class Invalid {

        final static class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> Stream.concat(
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
        return AudioTestArgsProvider.random.ints(5, 1, 2000);
    }

    static private final IntStream nonPositiveIntArgs() {
        return AudioTestArgsProvider.random.ints(5, -2000, 0);
    }

    ///////////////////////
    // valid constructor //
    ///////////////////////

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}) does not throw")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioConstructorPassedValidWavePitchDurationDoesNotThrow(Wave wave, Pitch pitch, int duration) {
        assertDoesNotThrow(() -> new Audio(wave, pitch, duration));
    }

    /////////////////////////
    // invalid constructor //
    /////////////////////////

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}) throws")
    @ArgumentsSource(AudioTestArgsProvider.Invalid.WavePitchDuration.class)
    void audioConstructorPassedInvalidWavesPitchesDurationsThrows(Wave wave, Pitch pitch, int duration) {
        assertThrows(IllegalArgumentException.class, () -> new Audio(wave, pitch, duration));
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

    @ParameterizedTest(name = "Audio.silence({0}).wave() returns null")
    @MethodSource("positiveIntArgs")
    void silenceWaveIsNull(int duration) {
        final Audio silenceAudio = Audio.silence(duration);
        assertNull(silenceAudio.wave());
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

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).wave() returns wave")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioWavePropertyReturnsWave(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertSame(wave, audio.wave());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).pitch() returns Optional containing pitch")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioPitchPropertyReturnsPitch(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.pitch().isPresent(), () -> "new Audio(Wave.%s, %s, %d).pitch() returned non present Optional.".formatted(wave, pitch, duration));
        assertSame(pitch, audio.pitch().get());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).duration() returns duration")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioDurationPropertyReturnsDuration(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertEquals(duration, audio.duration());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).stringValue() returns string value")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioStringValuePropertyReturnsStringValue(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final String stringValue = "%s>%s.%d".formatted(wave.stringValue(), pitch.stringValue(), duration);
        assertEquals(stringValue, audio.stringValue());
    }

    ////////////
    // equals //
    ////////////

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}) equals same returns true")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioEqualsSameReturnsTrue(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.equals(audio));
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}) equals equivalent returns true")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioEqualsEquivalentReturnsTrue(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final Audio equalAudio = new Audio(wave, pitch, duration);
        assertTrue(audio.equals(equalAudio));
    }

    @ParameterizedTest(name = "new Audio(<Wave.{0}>, <{2}>, <{4}>) equals new Pitch(<Wave.{1}>, <{3}>, <{5}>) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.AltWavesPitchesDurations.class)
    void audioEqualsUnequalWavePitchDurationReturnsFalse(Wave aWave, Wave anotherWave, Pitch aPitch,
            Pitch anotherPitch,
            int aDuration, int anotherDuration) {
        final Audio anAudio = new Audio(aWave, aPitch, aDuration);
        final Audio aDifferentAudio = new Audio(anotherWave, anotherPitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(<Wave.{0}>, {2}, {3}) equals new Pitch(<Wave.{1}>, {2}, {3}) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WaveAltsPitchDuration.class)
    void audioEqualsUnequalWavesPitchDurationReturnsFalse(Wave aWave, Wave anotherWave, Pitch pitch,
            int duration) {
        final Audio anAudio = new Audio(aWave, pitch, duration);
        final Audio aDifferentAudio = new Audio(anotherWave, pitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, <{1}>, <{3}>) equals new Pitch(Wave.{0}, <{2}>, <{4}>) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WaveAltPitchesAndDurations.class)
    void audioEqualsWaveWithUnequalPitchesAndDurationsReturnsFalse(Wave wave, Pitch aPitch, Pitch anotherPitch,
            int aDuration, int anotherDuration) {
        final Audio anAudio = new Audio(wave, aPitch, aDuration);
        final Audio aDifferentAudio = new Audio(wave, anotherPitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, <{2}>) equals new Pitch(Wave.{0}, {1}, <{3}>) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDurationAlts.class)
    void audioEqualsWavePitchUnequalDurationsReturnsFalse(Wave wave, Pitch pitch,
            int aDuration, int anotherDuration) {
        final Audio anAudio = new Audio(wave, pitch, aDuration);
        final Audio aDifferentAudio = new Audio(wave, pitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, <{1}>, {3}) equals new Pitch(Wave.{0}, <{2}>, {3}) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchAltsDuration.class)
    void audioEqualsWavePitchUnequalDurationsReturnsFalse(Wave wave, Pitch aPitch,
            Pitch anotherPitch, int duration) {
        final Audio anAudio = new Audio(wave, aPitch, duration);
        final Audio aDifferentAudio = new Audio(wave, anotherPitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Audio(<Wave.{0}>, {2}, {3}) equals new Pitch(<Wave.{1}>, {2}, {3}) returns false")
    @ArgumentsSource(AudioTestArgsProvider.Valid.AltWavesAndPitchesWithDuration.class)
    void audioAltWavesAndPitchesWithEqualDurationReturnsFalse(Wave wave, Wave anotherWave,
            Pitch aPitch, Pitch anotherPitch, int duration) {
        final Audio anAudio = new Audio(wave, aPitch, duration);
        final Audio aDifferentAudio = new Audio(anotherWave, anotherPitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    //////////////
    // hashCode //
    //////////////

    @ParameterizedTest(name = "new Audio(Wave.{0}, Accidental.{1}, {2}).hashCode() equals Objects.hash(Wave.{0}, {1}, {2})")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioHashCodeComputedFromWavePitchDuration(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final int computedHashCode = hash(wave, pitch, duration);
        assertEquals(computedHashCode, audio.hashCode());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).hashCode() is same for same object")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void sameAudioHasEqualHashCode(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        assertTrue(audio.hashCode() == audio.hashCode());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).hashCode() is same for equal object")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void equalAudioHasEqualHashCode(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final Audio equalAudio = new Audio(wave, pitch, duration);
        assertTrue(audio.hashCode() == equalAudio.hashCode());
    }

    //////////////
    // toString //
    //////////////

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).toString() returns pretty String")
    @ArgumentsSource(AudioTestArgsProvider.Valid.WavePitchDuration.class)
    void audioToStringReturnsPrettyString(Wave wave, Pitch pitch, int duration) {
        final Audio audio = new Audio(wave, pitch, duration);
        final String toString = "%s {wave=%s, pitch=\"%s\", duration=%d}".formatted(
                Audio.class.getSimpleName(),
                wave.name(), pitch.stringValue(), duration);
        assertEquals(toString, audio.toString());
    }
}
