package sogott.tones;

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

final class NoteStringsArgProvider {
        private final static List<Wave> waves = unmodifiableList(asList(Wave.values()));
        private final static List<PitchClass> pitchClasses = unmodifiableList(asList(PitchClass.values()));
        private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

        final static RandomGenerator random = RandomGenerator.getDefault();

        static final class Valid {
                final static class WaveShapePrefixedAudioStringValueAndAudio implements ArgumentsProvider {
                        @Override
                        public Stream<Arguments> provideArguments(ExtensionContext context) {
                                return pitchClasses.stream().flatMap(pitchClass -> {
                                        final int octave = random.nextInt(0, 13);
                                        final int duration = random.nextInt(1, 200000);
                                        return waves.stream()
                                                        .flatMap(wave -> wave.stringValueAliases().stream()
                                                                        .flatMap(waveStringAlias -> Stream.concat(
                                                                                        Stream.of(arguments(
                                                                                                        "%s%c%c%d%c%d".formatted(
                                                                                                                        waveStringAlias,
                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                        .charValue(),
                                                                                                                        pitchClass.charValue(),
                                                                                                                        octave,
                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                        .charValue(),
                                                                                                                        duration),
                                                                                                        new Note(wave, new Pitch(
                                                                                                                        pitchClass,
                                                                                                                        null,
                                                                                                                        octave),
                                                                                                                        duration)),
                                                                                                        arguments("%s%c%c%d%c%d"
                                                                                                                        .formatted(waveStringAlias
                                                                                                                                        .toLowerCase(),
                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                        .charValue(),
                                                                                                                                        Character.toLowerCase(
                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                        octave,
                                                                                                                                        Character.toLowerCase(
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue()),
                                                                                                                                        duration),
                                                                                                                        new Note(wave, new Pitch(
                                                                                                                                        pitchClass,
                                                                                                                                        null,
                                                                                                                                        octave),
                                                                                                                                        duration))),
                                                                                        accidentals.stream()
                                                                                                        .flatMap(accidental -> Stream
                                                                                                                        .of(arguments(
                                                                                                                                        "%s%c%c%c%d%c%d".formatted(
                                                                                                                                                        waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                        accidental.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration),
                                                                                                                                        new Note(wave, new Pitch(
                                                                                                                                                        pitchClass,
                                                                                                                                                        accidental,
                                                                                                                                                        octave),
                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%s%c%c%c%d%c%d".formatted(
                                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                        .charValue(),
                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                        octave,
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue(),
                                                                                                                                                                        duration),
                                                                                                                                                        new Note(wave, new Pitch(
                                                                                                                                                                        pitchClass,
                                                                                                                                                                        accidental,
                                                                                                                                                                        octave),
                                                                                                                                                                        duration)))))));
                                });
                        }
                }

                final static class AudioStringValueWithoutWaveShapePrefixAndAudio implements ArgumentsProvider {
                        @Override
                        public Stream<Arguments> provideArguments(ExtensionContext context) {
                                return pitchClasses.stream().flatMap(pitchClasses -> {
                                        final int octave = random.nextInt(0, 13);
                                        final int duration = random.nextInt(1, 200000);
                                        return waves.stream()
                                                        .flatMap(wave -> Stream.concat(
                                                                        Stream.of(arguments(
                                                                                        "%c%d%c%d".formatted(
                                                                                                        pitchClasses
                                                                                                                        .charValue(),
                                                                                                        octave,
                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                        .charValue(),
                                                                                                        duration),
                                                                                        wave,
                                                                                        new Note(wave, new Pitch(
                                                                                                        pitchClasses,
                                                                                                        null, octave),
                                                                                                        duration)),
                                                                                        arguments(
                                                                                                        "%c%d%c%d".formatted(
                                                                                                                        Character.toLowerCase(
                                                                                                                                        pitchClasses.charValue()),
                                                                                                                        octave,
                                                                                                                        Character.toLowerCase(
                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                        .charValue()),
                                                                                                                        duration),
                                                                                                        wave,
                                                                                                        new Note(wave, new Pitch(
                                                                                                                        pitchClasses,
                                                                                                                        null,
                                                                                                                        octave),
                                                                                                                        duration))),
                                                                        accidentals.stream()
                                                                                        .flatMap(accidental -> Stream
                                                                                                        .of(arguments(
                                                                                                                        "%c%c%d%c%d".formatted(
                                                                                                                                        pitchClasses.charValue(),
                                                                                                                                        accidental.charValue(),
                                                                                                                                        octave,
                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                        .charValue(),
                                                                                                                                        duration),
                                                                                                                        wave,
                                                                                                                        new Note(wave, new Pitch(
                                                                                                                                        pitchClasses,
                                                                                                                                        accidental,
                                                                                                                                        octave),
                                                                                                                                        duration)),
                                                                                                                        arguments(
                                                                                                                                        "%c%c%d%c%d".formatted(
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        pitchClasses.charValue()),
                                                                                                                                                        accidental.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration),
                                                                                                                                        wave,
                                                                                                                                        new Note(wave, new Pitch(
                                                                                                                                                        pitchClasses,
                                                                                                                                                        accidental,
                                                                                                                                                        octave),
                                                                                                                                                        duration))))));
                                });
                        }
                }

                final static class AudioStringValueWithoutWaveShapePrefix implements ArgumentsProvider {
                        @Override
                        public Stream<Arguments> provideArguments(ExtensionContext context) {
                                return pitchClasses.stream().flatMap(pitchClass -> {
                                        final int octave = random.nextInt(0, 13);
                                        final int duration = random.nextInt(1, 200000);
                                        return Stream.concat(
                                                        Stream.of(arguments(
                                                                        "%c%d%c%d".formatted(pitchClass.charValue(),
                                                                                        octave,
                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                        .charValue(),
                                                                                        duration)),
                                                                        arguments(
                                                                                        "%c%d%c%d".formatted(
                                                                                                        Character.toLowerCase(
                                                                                                                        pitchClass.charValue()),
                                                                                                        octave,
                                                                                                        Character.toLowerCase(
                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                        .charValue()),
                                                                                                        duration))),
                                                        accidentals.stream()
                                                                        .flatMap(accidental -> Stream.of(arguments(
                                                                                        "%c%c%d%c%d".formatted(
                                                                                                        pitchClass.charValue(),
                                                                                                        accidental.charValue(),
                                                                                                        octave,
                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                        .charValue(),
                                                                                                        duration)),
                                                                                        arguments(
                                                                                                        "%c%c%d%c%d".formatted(
                                                                                                                        Character.toLowerCase(
                                                                                                                                        pitchClass.charValue()),
                                                                                                                        accidental.charValue(),
                                                                                                                        octave,
                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                        .charValue(),
                                                                                                                        duration)))));
                                });
                        }
                }
        }

        static final class Invalid {
                final static class AudioStringValue implements ArgumentsProvider {
                        @Override
                        public Stream<Arguments> provideArguments(ExtensionContext context) {
                                return pitchClasses.stream().flatMap(note -> {
                                        final int octave = random.nextInt(0, 13);
                                        final int duration = random.nextInt(1, 200000);
                                        return waves.stream()
                                                        .flatMap(wave -> wave.stringValueAliases().stream()
                                                                        .flatMap(waveStringAlias -> Stream.concat(
                                                                                        Stream.of(
                                                                                                        // duplicate
                                                                                                        arguments(
                                                                                                                        "%1$s%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        note.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        note.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%3$c%4$d%5$c%6$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        note.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$d%5$c%5$c%6$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        note.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration)),
                                                                                                        // missing
                                                                                                        arguments(
                                                                                                                        "%1$c%2$c%3$d%4$c%5$d"
                                                                                                                                        .formatted(
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        note.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$d%4$c%5$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        note.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$d%4$c%5$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        note.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$c%5$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        note.charValue()),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$d%5$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        note.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        duration)),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$d%5$c"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        note.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue())))),
                                                                                        accidentals.stream()
                                                                                                        .flatMap(accidental -> Stream
                                                                                                                        .of(
                                                                                                                                        // duplicates
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%1$s%2$c%3$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        note.charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%2$c%3$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        note.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%3$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        note.charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        note.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$d%6$c%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        note.charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        // missing
                                                                                                                                        arguments(
                                                                                                                                                        "%1$c%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        note.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        note.charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        note.charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$d%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        note.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        duration)),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$d%6$c"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        note.charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue())))))));
                                });
                        }
                }

                final static class NoteStringValueAndWave implements ArgumentsProvider {
                        @Override
                        public Stream<Arguments> provideArguments(ExtensionContext context) {
                                return pitchClasses.stream().flatMap(pitchClass -> {
                                        final int octave = random.nextInt(0, 13);
                                        final int duration = random.nextInt(1, 200000);
                                        return waves.stream()
                                                        .flatMap(wave -> wave.stringValueAliases().stream()
                                                                        .flatMap(waveStringAlias -> Stream.concat(
                                                                                        Stream.of(
                                                                                                        // duplicate
                                                                                                        arguments(
                                                                                                                        "%1$s%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%3$c%4$d%5$c%6$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$d%5$c%5$c%6$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        // missing
                                                                                                        arguments(
                                                                                                                        "%1$c%2$c%3$d%4$c%5$d"
                                                                                                                                        .formatted(
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$d%4$c%5$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$d%4$c%5$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                        .charValue(),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$c%5$d"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue()),
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$d%5$d"
                                                                                                                                        .formatted(waveStringAlias,
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                        octave,
                                                                                                                                                        duration),
                                                                                                                        wave),
                                                                                                        arguments(
                                                                                                                        "%1$s%2$c%3$c%4$d%5$c"
                                                                                                                                        .formatted(
                                                                                                                                                        waveStringAlias.toLowerCase(),
                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                        .charValue(),
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        pitchClass.charValue()),
                                                                                                                                                        octave,
                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                        .charValue())),
                                                                                                                        wave)),
                                                                                        accidentals.stream()
                                                                                                        .flatMap(accidental -> Stream
                                                                                                                        .of(
                                                                                                                                        // duplicates
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%1$s%2$c%3$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        pitchClass
                                                                                                                                                                                                                        .charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%2$c%3$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%3$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        pitchClass
                                                                                                                                                                                                                        .charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%4$c%5$d%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$d%6$c%6$c%7$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        pitchClass
                                                                                                                                                                                                                        .charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        // missing
                                                                                                                                        arguments(
                                                                                                                                                        "%1$c%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        pitchClass
                                                                                                                                                                                                                        .charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$c%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        pitchClass
                                                                                                                                                                                                                        .charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$d%6$d"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias,
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        pitchClass.charValue(),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        duration),
                                                                                                                                                        wave),
                                                                                                                                        arguments(
                                                                                                                                                        "%1$s%2$c%3$c%4$c%5$d%6$c"
                                                                                                                                                                        .formatted(
                                                                                                                                                                                        waveStringAlias
                                                                                                                                                                                                        .toLowerCase(),
                                                                                                                                                                                        NoteString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                                                                                                                        .charValue(),
                                                                                                                                                                                        Character.toLowerCase(
                                                                                                                                                                                                        pitchClass
                                                                                                                                                                                                                        .charValue()),
                                                                                                                                                                                        accidental.charValue(),
                                                                                                                                                                                        octave,
                                                                                                                                                                                        NoteString.Delimiter.VOICE_AND_DURATION
                                                                                                                                                                                                        .charValue()),
                                                                                                                                                        wave))))));
                                });
                        }
                }
        }
}

final class NoteStringTest {
        @Test
        @DisplayName("NoteString.SILENCE_CHAR is '?'")
        void audioStringSilenceNoteCharConstIsQuestionMark() {
                assertSame('?', NoteString.SILENCE_CHAR);
        }

        @Test
        @DisplayName("NoteString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue() is '>'")
        void audioStringDelimiterWaveShapeAndPitchCharConstIsRightAngleBracket() {
                assertSame('>', NoteString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue());
        }

        @Test
        @DisplayName("NoteString.Delimiter.VOICE_AND_DURATION.charValue() is '.'")
        void audioStringDelimiterVoiceAndDurationCharConstIsPeriod() {
                assertSame('.', NoteString.Delimiter.VOICE_AND_DURATION.charValue());
        }

        @ParameterizedTest(name = "NoteString.parse(null, {0}) throws")
        @EnumSource(Wave.class)
        @NullSource
        void audioStringParseTwoNullsThrows(Wave wave) {
                assertThrows(IllegalArgumentException.class, () -> NoteString.parse(null, wave));
        }

        @Test
        @DisplayName("NoteString.parse(null) throws")
        void audioStringParseSingleNullThrows() {
                assertThrows(IllegalArgumentException.class, () -> NoteString.parse(null));
        }

        @ParameterizedTest(name = "NoteString.parse(\"{0}\") creates optional of {1}")
        @ArgumentsSource(NoteStringsArgProvider.Valid.WaveShapePrefixedAudioStringValueAndAudio.class)
        void audioStringParseReturnsAudioObjectForValidAudioStringWithPrefix(String audioString,
                        Note audio) {
                final Optional<Note> parsedAudio = NoteString.parse(audioString);
                assertTrue(parsedAudio.isPresent(), () -> "AudioString.parse(\"%s\") returned non present optional."
                                .formatted(audioString));
                assertEquals(audio, parsedAudio.get());
        }

        @ParameterizedTest(name = "NoteString.parse(\"{0}\", Wave.{1}) creates optional of {2}")
        @ArgumentsSource(NoteStringsArgProvider.Valid.AudioStringValueWithoutWaveShapePrefixAndAudio.class)
        void audioStringParseReturnsAudioObjectForValidAudioStringWithoutPrefixWithDefaultWave(String audioString,
                        Wave wave, Note audio) {
                final Optional<Note> parsedAudio = NoteString.parse(audioString, wave);
                assertTrue(parsedAudio.isPresent(), () -> "AudioString.parse(\"%s\") returned non present optional."
                                .formatted(audioString));
                assertEquals(audio, parsedAudio.get());
        }

        @ParameterizedTest(name = "NoteString.parse(\"{0}\") returns empty optional")
        @ArgumentsSource(NoteStringsArgProvider.Invalid.AudioStringValue.class)
        @EmptySource
        void audioStringParseReturnsEmptyOptionalForInvalidAudioStringArgument(String audioString) {
                final Optional<Note> parsedAudio = NoteString.parse(audioString);
                assertTrue(parsedAudio.isEmpty(), () -> "AudioString.parse(\"%s\") returned non empty optional."
                                .formatted(audioString));
        }

        @ParameterizedTest(name = "NoteString.parse(\"{0}\", {1}) returns empty optional")
        @ArgumentsSource(NoteStringsArgProvider.Invalid.NoteStringValueAndWave.class)
        void audioStringParseReturnsEmptyOptionalForInvalidAudioStringAndWaveArgument(String audioString, Wave wave) {
                final Optional<Note> parsedAudio = NoteString.parse(audioString, wave);
                assertTrue(parsedAudio.isEmpty(), () -> "AudioString.parse(\"%s\") returned non empty optional."
                                .formatted(audioString));
        }
}
