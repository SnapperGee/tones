package sogott.beep;

import java.util.stream.Stream;
import java.util.random.RandomGenerator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.api.extension.ExtensionContext;

import static java.util.Collections.unmodifiableList;
import static java.util.Arrays.asList;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class AudioStringArgProvider {
    private final static List<Wave> waves = unmodifiableList(asList(Wave.values()));
    private final static List<Note> notes = unmodifiableList(asList(Note.values()));
    private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static RandomGenerator random = RandomGenerator.getDefault();

    static final class Valid {
        final static class WaveShapePrefixedAudioStringValueAndAudio implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waves.stream()
                            .flatMap(wave -> wave.stringValueAliases().stream()
                                    .flatMap(waveStringAlias -> Stream.concat(
                                            Stream.of(arguments(
                                                    "%s%c%c%d%c%d".formatted(waveStringAlias,
                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH.charValue(),
                                                            note.charValue(),
                                                            octave,
                                                            AudioString.Delineator.PITCH_AND_DURATION.charValue(),
                                                            duration),
                                                    new Audio(wave, new Pitch(note, null, octave), duration)),
                                                    arguments("%s%c%c%d%c%d".formatted(waveStringAlias.toLowerCase(),
                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH.charValue(),
                                                            Character.toLowerCase(note.charValue()),
                                                            octave,
                                                            Character.toLowerCase(
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue()),
                                                            duration),
                                                            new Audio(wave, new Pitch(note, null, octave), duration))),
                                            accidentals.stream()
                                                    .flatMap(accidental -> Stream.of(arguments(
                                                            "%s%c%c%c%d%c%d".formatted(waveStringAlias,
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    note.charValue(),
                                                                    accidental.charValue(),
                                                                    octave,
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue(),
                                                                    duration),
                                                            new Audio(wave, new Pitch(note, accidental, octave),
                                                                    duration)),
                                                            arguments(
                                                                    "%s%c%c%c%d%c%d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration),
                                                                    new Audio(wave, new Pitch(note, accidental, octave),
                                                                            duration)))))));
                });
            }
        }

        final static class AudioStringValueWithoutWaveShapePrefixAndAudio implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waves.stream()
                            .flatMap(wave -> Stream.concat(
                                    Stream.of(arguments(
                                            "%c%d%c%d".formatted(note.charValue(),
                                                    octave,
                                                    AudioString.Delineator.PITCH_AND_DURATION.charValue(),
                                                    duration),
                                            wave,
                                            new Audio(wave, new Pitch(note, null, octave), duration)),
                                            arguments(
                                                    "%c%d%c%d".formatted(
                                                            Character.toLowerCase(note.charValue()),
                                                            octave,
                                                            Character.toLowerCase(
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue()),
                                                            duration),
                                                    wave,
                                                    new Audio(wave, new Pitch(note, null, octave), duration))),
                                    accidentals.stream()
                                            .flatMap(accidental -> Stream.of(arguments(
                                                    "%c%c%d%c%d".formatted(
                                                            note.charValue(),
                                                            accidental.charValue(),
                                                            octave,
                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                    .charValue(),
                                                            duration),
                                                    wave,
                                                    new Audio(wave, new Pitch(note, accidental, octave),
                                                            duration)),
                                                    arguments(
                                                            "%c%c%d%c%d".formatted(
                                                                    Character.toLowerCase(note.charValue()),
                                                                    accidental.charValue(),
                                                                    octave,
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue(),
                                                                    duration),
                                                            wave,
                                                            new Audio(wave, new Pitch(note, accidental, octave),
                                                                    duration))))));
                });
            }
        }

        final static class AudioStringValueWithoutWaveShapePrefix implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return Stream.concat(
                            Stream.of(arguments(
                                    "%c%d%c%d".formatted(note.charValue(),
                                            octave,
                                            AudioString.Delineator.PITCH_AND_DURATION.charValue(),
                                            duration)),
                                    arguments(
                                            "%c%d%c%d".formatted(
                                                    Character.toLowerCase(note.charValue()),
                                                    octave,
                                                    Character.toLowerCase(
                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                    .charValue()),
                                                    duration))),
                            accidentals.stream()
                                    .flatMap(accidental -> Stream.of(arguments(
                                            "%c%c%d%c%d".formatted(
                                                    note.charValue(),
                                                    accidental.charValue(),
                                                    octave,
                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                            .charValue(),
                                                    duration)),
                                            arguments(
                                                    "%c%c%d%c%d".formatted(
                                                            Character.toLowerCase(note.charValue()),
                                                            accidental.charValue(),
                                                            octave,
                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                    .charValue(),
                                                            duration)))));
                });
            }
        }
    }

    static final class Invalid {
        final static class AudioStringValue implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waves.stream()
                            .flatMap(wave -> wave.stringValueAliases().stream()
                                    .flatMap(waveStringAlias -> Stream.concat(
                                            Stream.of(
                                                    // duplicate
                                                    arguments(
                                                            "%1$s%1$s%2$c%3$c%4$d%5$c%6$d".formatted(waveStringAlias,
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    note.charValue(),
                                                                    octave,
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue(),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%2$c%3$c%4$d%5$c%6$d".formatted(
                                                                    waveStringAlias.toLowerCase(),
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    Character.toLowerCase(note.charValue()),
                                                                    octave,
                                                                    Character.toLowerCase(
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue()),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%3$c%4$d%5$c%6$d".formatted(waveStringAlias,
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    note.charValue(),
                                                                    octave,
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue(),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$c%5$c%6$d".formatted(
                                                                    waveStringAlias.toLowerCase(),
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    Character.toLowerCase(note.charValue()),
                                                                    octave,
                                                                    Character.toLowerCase(
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue()),
                                                                    duration)),
                                                    // missing
                                                    arguments(
                                                            "%1$c%2$c%3$d%4$c%5$d".formatted(
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    note.charValue(),
                                                                    octave,
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue(),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$d%4$c%5$d".formatted(
                                                                    waveStringAlias.toLowerCase(),
                                                                    Character.toLowerCase(note.charValue()),
                                                                    octave,
                                                                    Character.toLowerCase(
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue()),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$d%4$c%5$d".formatted(waveStringAlias,
                                                                    note.charValue(),
                                                                    octave,
                                                                    AudioString.Delineator.PITCH_AND_DURATION
                                                                            .charValue(),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$c%5$d".formatted(
                                                                    waveStringAlias.toLowerCase(),
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    Character.toLowerCase(note.charValue()),
                                                                    Character.toLowerCase(
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue()),
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$d".formatted(waveStringAlias,
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    note.charValue(),
                                                                    octave,
                                                                    duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$c".formatted(
                                                                    waveStringAlias.toLowerCase(),
                                                                    AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    Character.toLowerCase(note.charValue()),
                                                                    octave,
                                                                    Character.toLowerCase(
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue())))),

                                            accidentals.stream()
                                                    .flatMap(accidental -> Stream.of(
                                                            // duplicates
                                                            arguments(
                                                                    "%1$s%1$s%2$c%3$c%4$c%5$d%6$c%7$d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%2$c%3$c%4$c%5$d%6$c%7$d".formatted(
                                                                            waveStringAlias,
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%3$c%4$c%5$d%6$c%7$d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$c%4$c%5$d%6$c%7$d".formatted(
                                                                            waveStringAlias,
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$c%5$d%6$c%6$c%7$d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            // missing
                                                            arguments(
                                                                    "%1$c%2$c%3$c%4$d%5$c%6$d".formatted(
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$d%5$c%6$d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$d%5$c%6$d".formatted(
                                                                            waveStringAlias,
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$c%5$c%6$d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$c%5$d%6$d".formatted(
                                                                            waveStringAlias,
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            duration)),
                                                            arguments(
                                                                    "%1$s%2$c%3$c%4$c%5$d%6$c".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delineator.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(note.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delineator.PITCH_AND_DURATION
                                                                                    .charValue())))))));
                });
            }
        }
    }
}

final class AudioStringTest {
    @Test
    @DisplayName("AudioString.SILENCE_CHAR is '?'")
    void audioStringSilenceNoteCharConstIsQuestionMark() {
        assertSame('?', AudioString.SILENCE_CHAR);
    }

    @Test
    @DisplayName("AudioString.Delineator.WAVE_SHAPE_AND_PITCH.charValue() is '>'")
    void audioStringDelineatorWaveShapeAndPitchCharConstIsRightAngleBracket() {
        assertSame('>', AudioString.Delineator.WAVE_SHAPE_AND_PITCH.charValue());
    }

    @Test
    @DisplayName("AudioString.Delineator.PITCH_AND_DURATION.charValue() is '.'")
    void audioStringDelineatorPitchAndDurationCharConstIsPeriod() {
        assertSame('.', AudioString.Delineator.PITCH_AND_DURATION.charValue());
    }

    @ParameterizedTest(name = "AudioString.parse(null, {0}) throws")
    @EnumSource(Wave.class)
    @NullSource
    void audioStringParseTwoNullsThrows(Wave wave) {
        assertThrows(IllegalArgumentException.class, () -> AudioString.parse(null, wave));
    }

    @Test
    @DisplayName("AudioString.parse(null) throws")
    void audioStringParseSingleNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> AudioString.parse(null));
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\") creates optional of {1}")
    @ArgumentsSource(AudioStringArgProvider.Valid.WaveShapePrefixedAudioStringValueAndAudio.class)
    void audioStringParseReturnsAudioObjectForValidAudioStringWithPrefix(String audioString,
            Audio audio) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString);
        assertTrue(parsedAudio.isPresent());
        assertEquals(audio, parsedAudio.get());
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\", Wave.{1}) creates optional of {2}")
    @ArgumentsSource(AudioStringArgProvider.Valid.AudioStringValueWithoutWaveShapePrefixAndAudio.class)
    void audioStringParseReturnsAudioObjectForValidAudioStringWithoutPrefixWithDefaultWave(String audioString,
            Wave wave, Audio audio) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString, wave);
        assertTrue(parsedAudio.isPresent());
        assertEquals(audio, parsedAudio.get());
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\") returns empty optional")
    @ArgumentsSource(AudioStringArgProvider.Invalid.AudioStringValue.class)
    @EmptySource
    void audioStringParseReturnsEmptyOptionalForInvalidAudioStringArgument(String audioString) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString);
        assertTrue(parsedAudio.isEmpty());
    }
}
