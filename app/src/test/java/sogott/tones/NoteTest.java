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

final class NoteArgsProvider {

    final static RandomGenerator random = RandomGenerator.getDefault();

    final private static List<Wave> waves = unmodifiableList(asList(Wave.values()));
    final private static List<PitchClass> notes = unmodifiableList(asList(PitchClass.values()));
    final private static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static class Valid {

        final static class WavePitchDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> Stream.concat(
                        notes.stream()
                                .map(note -> arguments(wave,
                                        new Pitch(note, null,
                                                random.nextInt(13)),
                                        1 << random.nextInt(8))),
                        notes.stream().flatMap(note -> accidentals.stream()
                                .map(accidental -> arguments(wave,
                                        new Pitch(note, accidental,
                                                random.nextInt(13)),
                                        1 << random.nextInt(8))))));
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

                    return Stream.concat(
                            notes.stream()
                                    .map(note -> {
                                        final int noteIndex = notes
                                                .indexOf(note);
                                        final int differentNoteIndex = random
                                                .ints(0, notes.size())
                                                .filter(i -> i != noteIndex)
                                                .findFirst()
                                                .orElseThrow();
                                        final PitchClass differentNote = notes
                                                .get(
                                                        differentNoteIndex);
                                        final int duration = 1 << random
                                                .nextInt(8);
                                        final int differentDuration = random
                                                .ints(0, 8)
                                                .map(i -> 1 << i)
                                                .filter(i -> i != duration)
                                                .findFirst()
                                                .orElseThrow();

                                        return arguments(wave, differentWave,
                                                new Pitch(note, null,
                                                        random.nextInt(13)),
                                                new Pitch(differentNote,
                                                        null,
                                                        random.nextInt(13)),
                                                duration,
                                                differentDuration);
                                    }),
                            notes.stream().flatMap(note -> {
                                final int noteIndex = notes.indexOf(note);
                                final int differentNoteIndex = random
                                        .ints(0, notes.size())
                                        .filter(i -> i != noteIndex).findFirst()
                                        .orElseThrow();
                                final PitchClass differentNote = notes
                                        .get(differentNoteIndex);
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
                                                    new Pitch(note, accidental,
                                                            random.nextInt(13)),
                                                    new Pitch(differentNote,
                                                            differentAccidental,
                                                            random.nextInt(13)),
                                                    duration,
                                                    differentDuration);
                                        });
                            }));
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

                    return Stream.concat(
                            notes.stream()
                                    .map(note -> arguments(wave, differentWave,
                                            new Pitch(note, null, random
                                                    .nextInt(13)),
                                            1 << random.nextInt(8))),
                            notes.stream().flatMap(note -> accidentals.stream()
                                    .map(accidental -> arguments(wave,
                                            differentWave,
                                            new Pitch(note, accidental,
                                                    random.nextInt(13)),
                                            1 << random.nextInt(8)))));
                });
            }
        }

        final static class WaveAltPitchesAndDurations implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> Stream.concat(
                        notes.stream()
                                .map(note -> {
                                    final int noteIndex = notes.indexOf(note);
                                    final int differentNoteIndex = random
                                            .ints(0, notes.size())
                                            .filter(i -> i != noteIndex)
                                            .findFirst().orElseThrow();
                                    final PitchClass differentNote = notes
                                            .get(differentNoteIndex);
                                    final int duration = 1 << random.nextInt(8);
                                    final int differentDuration = random.ints(0, 8)
                                            .map(i -> 1 << i)
                                            .filter(i -> i != duration)
                                            .findFirst().orElseThrow();

                                    return arguments(wave,
                                            new Pitch(note, null, random
                                                    .nextInt(13)),
                                            new Pitch(differentNote, null,
                                                    random.nextInt(13)),
                                            duration, differentDuration);
                                }),
                        notes.stream().flatMap(note -> {
                            final int noteIndex = notes.indexOf(note);
                            final int differentNoteIndex = random.ints(0, notes.size())
                                    .filter(i -> i != noteIndex).findFirst()
                                    .orElseThrow();
                            final PitchClass differentNote = notes.get(differentNoteIndex);
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
                                                new Pitch(note, accidental,
                                                        random.nextInt(13)),
                                                new Pitch(differentNote,
                                                        differentAccidental,
                                                        random.nextInt(13)),
                                                duration,
                                                differentDuration);
                                    });
                        })));
            }
        }

        final static class WavePitchDurationAlts implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> Stream.concat(
                        notes.stream()
                                .map(note -> {
                                    final int duration = 1 << random.nextInt(8);
                                    final int differentDuration = random.ints(0, 8)
                                            .map(i -> 1 << i)
                                            .filter(i -> i != duration)
                                            .findFirst().orElseThrow();

                                    return arguments(wave,
                                            new Pitch(note, null, random
                                                    .nextInt(13)),
                                            duration, differentDuration);
                                }),
                        notes.stream().flatMap(note -> {
                            final int duration = 1 << random.nextInt(8);
                            final int differentDuration = random.ints(0, 8).map(i -> 1 << i)
                                    .filter(i -> i != duration).findFirst()
                                    .orElseThrow();

                            return accidentals.stream()
                                    .map(accidental -> arguments(wave,
                                            new Pitch(note, accidental,
                                                    random.nextInt(13)),
                                            duration,
                                            differentDuration));
                        })));
            }
        }

        final static class WavePitchAltsDuration implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return waves.stream().flatMap(wave -> Stream.concat(
                        notes.stream()
                                .map(note -> {
                                    final int noteIndex = notes.indexOf(note);
                                    final int differentNoteIndex = random
                                            .ints(0, notes.size())
                                            .filter(i -> i != noteIndex)
                                            .findFirst().orElseThrow();
                                    final PitchClass differentNote = notes
                                            .get(differentNoteIndex);
                                    return arguments(wave,
                                            new Pitch(note, null, random
                                                    .nextInt(13)),
                                            new Pitch(differentNote, null,
                                                    random.nextInt(13)),
                                            1 << random.nextInt(8));
                                }),
                        notes.stream().flatMap(note -> {
                            final int noteIndex = notes.indexOf(note);
                            final int differentNoteIndex = random.ints(0, notes.size())
                                    .filter(i -> i != noteIndex).findFirst()
                                    .orElseThrow();
                            final PitchClass differentNote = notes.get(differentNoteIndex);

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
                                                new Pitch(note, accidental,
                                                        random.nextInt(13)),
                                                new Pitch(differentNote,
                                                        differentAccidental,
                                                        random.nextInt(13)),
                                                1 << random.nextInt(8));
                                    });
                        })));
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

                    return Stream.concat(
                            notes.stream()
                                    .map(note -> {
                                        final int noteIndex = notes
                                                .indexOf(note);
                                        final int differentNoteIndex = random
                                                .ints(0, notes.size())
                                                .filter(i -> i != noteIndex)
                                                .findFirst()
                                                .orElseThrow();
                                        final PitchClass differentNote = notes
                                                .get(differentNoteIndex);
                                        return arguments(wave, differentWave,
                                                new Pitch(note, null,
                                                        random
                                                                .nextInt(13)),
                                                new Pitch(differentNote,
                                                        null,
                                                        random.nextInt(13)),
                                                1 << random.nextInt(8));
                                    }),
                            notes.stream().flatMap(note -> {
                                final int noteIndex = notes.indexOf(note);
                                final int differentNoteIndex = random
                                        .ints(0, notes.size())
                                        .filter(i -> i != noteIndex).findFirst()
                                        .orElseThrow();
                                final PitchClass differentNote = notes
                                        .get(differentNoteIndex);

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
                                                    new Pitch(note, accidental,
                                                            random.nextInt(13)),
                                                    new Pitch(differentNote,
                                                            differentAccidental,
                                                            random.nextInt(13)),
                                                    1 << random.nextInt(
                                                            8));
                                        });
                            }));
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
                        Stream.concat(
                                notes.stream()
                                        .flatMap(note -> Stream.of(
                                                arguments(wave, new Pitch(
                                                        note,
                                                        null,
                                                        random.nextInt(13)),
                                                        -(1 << random.nextInt(
                                                                8))),
                                                arguments(null, new Pitch(
                                                        note,
                                                        null,
                                                        random.nextInt(13)),
                                                        1 << random.nextInt(
                                                                8)),
                                                arguments(null, new Pitch(
                                                        note,
                                                        null,
                                                        random.nextInt(13)),
                                                        -(1 << random.nextInt(
                                                                8))))),
                                notes.stream().flatMap(note -> accidentals.stream()
                                        .flatMap(accidental -> Stream.of(
                                                arguments(wave, new Pitch(
                                                        note,
                                                        accidental,
                                                        random.nextInt(13)),
                                                        -(1 << random.nextInt(
                                                                8))),
                                                arguments(null, new Pitch(
                                                        note,
                                                        accidental,
                                                        random.nextInt(13)),
                                                        -(1 << random.nextInt(
                                                                8))),
                                                arguments(null, new Pitch(
                                                        note,
                                                        accidental,
                                                        random.nextInt(13)),
                                                        1 << random.nextInt(
                                                                8))))))));
            }
        }
    }
}

final class NoteTest {

    static private final IntStream positiveIntArgs() {
        return NoteArgsProvider.random.ints(5, 1, 2000);
    }

    static private final IntStream nonPositiveIntArgs() {
        return NoteArgsProvider.random.ints(5, -2000, 0);
    }

    ///////////////////////
    // valid constructor //
    ///////////////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) does not throw")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioConstructorPassedValidWavePitchDurationDoesNotThrow(Wave wave, Pitch pitch, int duration) {
        assertDoesNotThrow(() -> new Note(wave, pitch, duration));
    }

    /////////////////////////
    // invalid constructor //
    /////////////////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) throws")
    @ArgumentsSource(NoteArgsProvider.Invalid.WavePitchDuration.class)
    void audioConstructorPassedInvalidWavesPitchesDurationsThrows(Wave wave, Pitch pitch, int duration) {
        assertThrows(IllegalArgumentException.class, () -> new Note(wave, pitch, duration));
    }

    /////////////////////////
    // static silence ctor //
    /////////////////////////

    @ParameterizedTest(name = "Audio.silence({0}) does not throw")
    @MethodSource("positiveIntArgs")
    void silenceDoesNotThrow(int duration) {
        assertDoesNotThrow(() -> Note.silence(duration));
    }

    @ParameterizedTest(name = "Audio.silence({0}) throws")
    @MethodSource("nonPositiveIntArgs")
    void silencePassedNonPositiveThrows(int duration) {
        assertThrows(IllegalArgumentException.class, () -> Note.silence(duration));
    }

    @ParameterizedTest(name = "Audio.silence({0}).wave() returns null")
    @MethodSource("positiveIntArgs")
    void silenceWaveIsNull(int duration) {
        final Note silenceAudio = Note.silence(duration);
        assertNull(silenceAudio.wave());
    }

    @ParameterizedTest(name = "Audio.silence({0}).pitch() returns null")
    @MethodSource("positiveIntArgs")
    void silencePitchIsNull(int duration) {
        final Note silenceAudio = Note.silence(duration);
        assertNull(silenceAudio.pitch());
    }

    @ParameterizedTest(name = "Audio.silence({0}).duration() returns {0}")
    @MethodSource("positiveIntArgs")
    void silenceDurationReturnsDuration(int duration) {
        final Note silenceAudio = Note.silence(duration);
        assertEquals(duration, silenceAudio.duration());
    }

    @ParameterizedTest(name = "Audio.silence({0}).stringValue() returns \"?.{0}\"")
    @MethodSource("positiveIntArgs")
    void silenceStringValueReturnsStringValue(int duration) {
        final Note silenceAudio = Note.silence(duration);
        final String stringValue = "%c%c%d".formatted(NoteString.SILENCE_CHAR,
                NoteString.Delimiter.VOICE_AND_DURATION.charValue(), duration);
        assertEquals(stringValue, silenceAudio.stringValue());
    }

    ////////////////////////
    // properties/getters //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).wave() returns wave")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioWavePropertyReturnsWave(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        assertSame(wave, audio.wave());
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).pitch() returns pitch")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioPitchPropertyReturnsPitch(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        assertSame(pitch, audio.pitch());
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).duration() returns duration")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioDurationPropertyReturnsDuration(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        assertEquals(duration, audio.duration());
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}).stringValue() returns string value")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioStringValuePropertyReturnsStringValue(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        final String stringValue = "%s>%s.%d".formatted(wave.stringValue(), pitch.stringValue(), duration);
        assertEquals(stringValue, audio.stringValue());
    }

    ////////////
    // equals //
    ////////////

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) equals same returns true")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioEqualsSameReturnsTrue(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        assertTrue(audio.equals(audio));
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, {2}) equals equivalent returns true")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioEqualsEquivalentReturnsTrue(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        final Note equalAudio = new Note(wave, pitch, duration);
        assertTrue(audio.equals(equalAudio));
    }

    @ParameterizedTest(name = "new Pitch(<Wave.{0}>, <{2}>, <{4}>) equals new Pitch(<Wave.{1}>, <{3}>, <{5}>) returns false")
    @ArgumentsSource(NoteArgsProvider.Valid.AltWavesPitchesDurations.class)
    void audioEqualsUnequalWavePitchDurationReturnsFalse(Wave aWave, Wave anotherWave, Pitch aPitch,
            Pitch anotherPitch,
            int aDuration, int anotherDuration) {
        final Note anAudio = new Note(aWave, aPitch, aDuration);
        final Note aDifferentAudio = new Note(anotherWave, anotherPitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Pitch(<Wave.{0}>, {2}, {3}) equals new Pitch(<Wave.{1}>, {2}, {3}) returns false")
    @ArgumentsSource(NoteArgsProvider.Valid.WaveAltsPitchDuration.class)
    void audioEqualsUnequalWavesPitchDurationReturnsFalse(Wave aWave, Wave anotherWave, Pitch pitch,
            int duration) {
        final Note anAudio = new Note(aWave, pitch, duration);
        final Note aDifferentAudio = new Note(anotherWave, pitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, <{1}>, <{3}>) equals new Pitch(Wave.{0}, <{2}>, <{4}>) returns false")
    @ArgumentsSource(NoteArgsProvider.Valid.WaveAltPitchesAndDurations.class)
    void audioEqualsWaveWithUnequalPitchesAndDurationsReturnsFalse(Wave wave, Pitch aPitch, Pitch anotherPitch,
            int aDuration, int anotherDuration) {
        final Note anAudio = new Note(wave, aPitch, aDuration);
        final Note aDifferentAudio = new Note(wave, anotherPitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, {1}, <{2}>) equals new Pitch(Wave.{0}, {1}, <{3}>) returns false")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDurationAlts.class)
    void audioEqualsWavePitchUnequalDurationsReturnsFalse(Wave wave, Pitch pitch,
            int aDuration, int anotherDuration) {
        final Note anAudio = new Note(wave, pitch, aDuration);
        final Note aDifferentAudio = new Note(wave, pitch, anotherDuration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Pitch(Wave.{0}, <{1}>, {3}) equals new Pitch(Wave.{0}, <{2}>, {3}) returns false")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchAltsDuration.class)
    void audioEqualsWavePitchUnequalDurationsReturnsFalse(Wave wave, Pitch aPitch,
            Pitch anotherPitch, int duration) {
        final Note anAudio = new Note(wave, aPitch, duration);
        final Note aDifferentAudio = new Note(wave, anotherPitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    @ParameterizedTest(name = "new Pitch(<Wave.{0}>, {2}, {3}) equals new Pitch(<Wave.{1}>, {2}, {3}) returns false")
    @ArgumentsSource(NoteArgsProvider.Valid.AltWavesAndPitchesWithDuration.class)
    void audioAltWavesAndPitchesWithEqualDurationReturnsFalse(Wave wave, Wave anotherWave,
            Pitch aPitch, Pitch anotherPitch, int duration) {
        final Note anAudio = new Note(wave, aPitch, duration);
        final Note aDifferentAudio = new Note(anotherWave, anotherPitch, duration);
        assertFalse(anAudio.equals(aDifferentAudio));
    }

    //////////////
    // hashCode //
    //////////////

    @ParameterizedTest(name = "new Audio(Note.{0}, Accidental.{1}, {2}).hashCode() equals Objects.hash(Wave.{0}, {1}, {2})")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioHashCodeComputedFromWavePitchDuration(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        final int computedHashCode = hash(wave, pitch, duration);
        assertEquals(computedHashCode, audio.hashCode());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).hashCode() is same for same object")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void sameAudioHasEqualHashCode(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        assertTrue(audio.hashCode() == audio.hashCode());
    }

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).hashCode() is same for equal object")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void equalAudioHasEqualHashCode(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        final Note equalAudio = new Note(wave, pitch, duration);
        assertTrue(audio.hashCode() == equalAudio.hashCode());
    }

    //////////////
    // toString //
    //////////////

    @ParameterizedTest(name = "new Audio(Wave.{0}, {1}, {2}).toString() returns pretty String")
    @ArgumentsSource(NoteArgsProvider.Valid.WavePitchDuration.class)
    void audioToStringReturnsPrettyString(Wave wave, Pitch pitch, int duration) {
        final Note audio = new Note(wave, pitch, duration);
        final String toString = "%s {wave=%s, pitch=\"%s\", duration=%d}".formatted(
                Note.class.getSimpleName(),
                wave.name(), pitch.stringValue(), duration);
        assertEquals(toString, audio.toString());
    }
}
