package sogott.tones;

import java.util.stream.Stream;
import java.util.random.RandomGenerator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;

import static java.util.Collections.unmodifiableList;
import static java.util.Arrays.asList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class AudioStringTestArgsProvider {
    private static final List<WaveShape> waveShapes = unmodifiableList(asList(WaveShape.values()));
    private static final List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private static final List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    static final RandomGenerator random = RandomGenerator.getDefault();

    static final class Valid {
        static final class WaveShapePrefixedAudioPitchStringValueAndAudio implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waveShapes.stream()
                            .flatMap(wave -> wave.stringValueAliases().stream()
                                    .flatMap(waveStringAlias -> accidentals.stream()
                                            .flatMap(accidental -> Stream
                                                    .of(arguments(
                                                            "%s%c%c%c%d%c%d".formatted(
                                                                    waveStringAlias,
                                                                    AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                            .charValue(),
                                                                    pitchLetter.charValue(),
                                                                    accidental.charValue(),
                                                                    octave,
                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                            .charValue(),
                                                                    duration),
                                                            new Audio(wave, new Pitch(
                                                                    pitchLetter,
                                                                    accidental,
                                                                    octave),
                                                                    duration)),
                                                            arguments(
                                                                    "%s%c%c%c%d%c%d".formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    pitchLetter.charValue()),
                                                                            accidental.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration),
                                                                    new Audio(wave, new Pitch(
                                                                            pitchLetter,
                                                                            accidental,
                                                                            octave),
                                                                            duration))))));
                });
            }
        }

        static final class AudioPitchStringValueWithoutWaveShapePrefixAndAudio implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waveShapes.stream()
                            .flatMap(wave -> accidentals.stream()
                                    .flatMap(accidental -> Stream
                                            .of(arguments(
                                                    "%c%c%d%c%d".formatted(
                                                            pitchLetter.charValue(),
                                                            accidental.charValue(),
                                                            octave,
                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                    .charValue(),
                                                            duration),
                                                    wave,
                                                    new Audio(wave, new Pitch(
                                                            pitchLetter,
                                                            accidental,
                                                            octave),
                                                            duration)),
                                                    arguments(
                                                            "%c%c%d%c%d".formatted(
                                                                    Character.toLowerCase(
                                                                            pitchLetter.charValue()),
                                                                    accidental.charValue(),
                                                                    octave,
                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                            .charValue(),
                                                                    duration),
                                                            wave,
                                                            new Audio(wave, new Pitch(
                                                                    pitchLetter,
                                                                    accidental,
                                                                    octave),
                                                                    duration)))));
                });
            }
        }

        static final class AudioPitchStringValueWithoutWaveShapePrefix implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return Stream.concat(
                            Stream.of(arguments(
                                    "%c%d%c%d".formatted(pitchLetter.charValue(),
                                            octave,
                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                    .charValue(),
                                            duration)),
                                    arguments(
                                            "%c%d%c%d".formatted(
                                                    Character.toLowerCase(
                                                            pitchLetter.charValue()),
                                                    octave,
                                                    Character.toLowerCase(
                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                    .charValue()),
                                                    duration))),
                            accidentals.stream()
                                    .flatMap(accidental -> Stream.of(arguments(
                                            "%c%c%d%c%d".formatted(
                                                    pitchLetter.charValue(),
                                                    accidental.charValue(),
                                                    octave,
                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                            .charValue(),
                                                    duration)),
                                            arguments(
                                                    "%c%c%d%c%d".formatted(
                                                            Character.toLowerCase(
                                                                    pitchLetter.charValue()),
                                                            accidental.charValue(),
                                                            octave,
                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                    .charValue(),
                                                            duration)))));
                });
            }
        }
    }

    static final class Invalid {
        static final class AudioPitchStringValue implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(note -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waveShapes.stream()
                            .flatMap(wave -> wave.stringValueAliases().stream()
                                    .flatMap(waveStringAlias -> Stream.concat(
                                            Stream.of(
                                                    // duplicate
                                                    arguments(
                                                            "%1$s%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                    .formatted(waveStringAlias,
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%2$c%3$c%4$d%5$c%6$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    note.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%3$c%4$d%5$c%6$d"
                                                                    .formatted(waveStringAlias,
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$c%5$c%6$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    note.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration)),
                                                    // missing
                                                    arguments(
                                                            "%1$c%2$c%3$d%4$c%5$d"
                                                                    .formatted(
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
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
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$d%4$c%5$d"
                                                                    .formatted(waveStringAlias,
                                                                            note.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$c%5$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    note.charValue()),
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$d"
                                                                    .formatted(waveStringAlias,
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            note.charValue(),
                                                                            octave,
                                                                            duration)),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$c"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    note.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
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
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    note.charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%2$c%3$c%4$c%5$d%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            note.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%3$c%4$c%5$d%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    note.charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%4$c%5$d%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            note.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$d%6$c%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    note.charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    // missing
                                                                    arguments(
                                                                            "%1$c%2$c%3$c%4$d%5$c%6$d"
                                                                                    .formatted(
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            note.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
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
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$c%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    note.charValue()),
                                                                                            accidental.charValue(),
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration)),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$d%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
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
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    note.charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue())))))));
                });
            }
        }

        static final class AudioPitchStringAndWave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 200000);
                    return waveShapes.stream()
                            .flatMap(wave -> wave.stringValueAliases().stream()
                                    .flatMap(waveStringAlias -> Stream.concat(
                                            Stream.of(
                                                    arguments(
                                                            "",
                                                            wave),
                                                    // duplicate
                                                    arguments(
                                                            "%1$s%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                    .formatted(waveStringAlias,
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            pitchLetter.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%2$c%3$c%4$d%5$c%6$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    pitchLetter.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$c%3$c%4$d%5$c%6$d"
                                                                    .formatted(waveStringAlias,
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            pitchLetter.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$c%5$c%6$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    pitchLetter.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration),
                                                            wave),
                                                    // missing
                                                    arguments(
                                                            "%1$c%2$c%3$d%4$c%5$d"
                                                                    .formatted(
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            pitchLetter.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$d%4$c%5$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            Character.toLowerCase(
                                                                                    pitchLetter.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$d%4$c%5$d"
                                                                    .formatted(waveStringAlias,
                                                                            pitchLetter.charValue(),
                                                                            octave,
                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                    .charValue(),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$c%5$d"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    pitchLetter.charValue()),
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
                                                                                            .charValue()),
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$d"
                                                                    .formatted(waveStringAlias,
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            pitchLetter.charValue(),
                                                                            octave,
                                                                            duration),
                                                            wave),
                                                    arguments(
                                                            "%1$s%2$c%3$c%4$d%5$c"
                                                                    .formatted(
                                                                            waveStringAlias.toLowerCase(),
                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                    .charValue(),
                                                                            Character.toLowerCase(
                                                                                    pitchLetter.charValue()),
                                                                            octave,
                                                                            Character.toLowerCase(
                                                                                    AudioString.Delimiter.VOICE_AND_DURATION
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
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    pitchLetter
                                                                                                            .charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%2$c%3$c%4$c%5$d%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            pitchLetter.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%3$c%4$c%5$d%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    pitchLetter
                                                                                                            .charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%4$c%5$d%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            pitchLetter.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$d%6$c%6$c%7$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    pitchLetter
                                                                                                            .charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    // missing
                                                                    arguments(
                                                                            "%1$c%2$c%3$c%4$d%5$c%6$d"
                                                                                    .formatted(
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            pitchLetter.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            Character.toLowerCase(
                                                                                                    pitchLetter
                                                                                                            .charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$d%5$c%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$c%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    pitchLetter
                                                                                                            .charValue()),
                                                                                            accidental.charValue(),
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue(),
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$d%6$d"
                                                                                    .formatted(
                                                                                            waveStringAlias,
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            pitchLetter.charValue(),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            duration),
                                                                            wave),
                                                                    arguments(
                                                                            "%1$s%2$c%3$c%4$c%5$d%6$c"
                                                                                    .formatted(
                                                                                            waveStringAlias
                                                                                                    .toLowerCase(),
                                                                                            AudioString.Delimiter.WAVE_SHAPE_AND_PITCH
                                                                                                    .charValue(),
                                                                                            Character.toLowerCase(
                                                                                                    pitchLetter
                                                                                                            .charValue()),
                                                                                            accidental.charValue(),
                                                                                            octave,
                                                                                            AudioString.Delimiter.VOICE_AND_DURATION
                                                                                                    .charValue()),
                                                                            wave))))));
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
    @DisplayName("AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue() is '>'")
    void audioStringDelimiterWaveShapeAndPitchCharConstIsRightAngleBracket() {
        assertSame('>', AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue());
    }

    @Test
    @DisplayName("AudioString.Delimiter.VOICE_AND_DURATION.charValue() is '.'")
    void audioStringDelimiterVoiceAndDurationCharConstIsPeriod() {
        assertSame('.', AudioString.Delimiter.VOICE_AND_DURATION.charValue());
    }

    @ParameterizedTest(name = "AudioString.parse(null, {0}) throws")
    @EnumSource(WaveShape.class)
    @NullSource
    void audioStringParseTwoNullsThrows(WaveShape wave) {
        assertThrows(IllegalArgumentException.class, () -> AudioString.parse(null, wave));
    }

    @Test
    @DisplayName("AudioString.parse(null) throws")
    void audioStringParseSingleNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> AudioString.parse(null));
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\") creates optional of {1}")
    @ArgumentsSource(AudioStringTestArgsProvider.Valid.WaveShapePrefixedAudioPitchStringValueAndAudio.class)
    void audioStringParseReturnsAudioObjectForValidAudioStringWithPrefix(String audioString,
            Audio audio) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString);
        assertTrue(parsedAudio.isPresent(), () -> "AudioString.parse(\"%s\") returned non present optional."
                .formatted(audioString));
        assertEquals(audio, parsedAudio.get());
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\", Wave.{1}) creates optional of {2}")
    @ArgumentsSource(AudioStringTestArgsProvider.Valid.AudioPitchStringValueWithoutWaveShapePrefixAndAudio.class)
    void audioStringParseReturnsAudioObjectForValidAudioStringWithoutPrefixWithDefaultWave(String audioString,
            WaveShape wave, Audio audio) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString, wave);
        assertTrue(parsedAudio.isPresent(), () -> "AudioString.parse(\"%s\") returned non present optional."
                .formatted(audioString));
        assertEquals(audio, parsedAudio.get());
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\") returns empty optional")
    @ArgumentsSource(AudioStringTestArgsProvider.Invalid.AudioPitchStringValue.class)
    @EmptySource
    void audioStringParseReturnsEmptyOptionalForInvalidAudioStringArgument(String audioString) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString);
        assertTrue(parsedAudio.isEmpty(), () -> "AudioString.parse(\"%s\") returned non empty optional."
                .formatted(audioString));
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\", {1}) returns empty optional")
    @ArgumentsSource(AudioStringTestArgsProvider.Invalid.AudioPitchStringAndWave.class)
    void audioStringParseReturnsEmptyOptionalForInvalidAudioStringAndWaveArgument(String audioString, WaveShape wave) {
        final Optional<Audio> parsedAudio = AudioString.parse(audioString, wave);
        assertTrue(parsedAudio.isEmpty(), () -> "AudioString.parse(\"%s\") returned non empty optional."
                .formatted(audioString));
    }
}
