package sogott.tones;

import java.util.stream.IntStream;
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
    private static final List<ScalePitchClasses> scalePitchClasses = unmodifiableList(asList(ScalePitchClasses.values()));

    static final RandomGenerator random = RandomGenerator.getDefault();

    static final class Valid {
        static final class WaveShapePrefixedAudioPitchStringValueAndAudio implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 256);
                    return waveShapes.stream()
                        .flatMap(wave -> wave.stringValueAliases().stream()
                            .flatMap(waveStringAlias -> accidentals.stream()
                                .flatMap(accidental -> Stream.of(
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(accidental.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        new Audio(
                                            wave,
                                            new Pitch(
                                                pitchLetter,
                                                accidental,
                                                octave
                                            ),
                                            duration
                                        )
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias.toLowerCase())
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(Character.toLowerCase(pitchLetter.charValue()))
                                            .append(accidental.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        new Audio(
                                            wave,
                                            new Pitch(
                                                pitchLetter,
                                                accidental,
                                                octave
                                            ),
                                            duration
                                        )
                                    )
                                ))
                            )
                        );
                });
            }
        }

        static final class WaveShapePrefixedAudioScaleStringValueAndAudio implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return scalePitchClasses.stream().flatMap(scalePitchLetterMaps ->
                    scalePitchLetterMaps.pitchLetterAccidentalMap().entrySet().stream()
                        .flatMap(pitchLetterAccidentalMap ->
                            pitchLetterAccidentalMap.getValue().entrySet().stream()
                                .flatMap(accidentalPitchClassesMap ->
                                    IntStream.rangeClosed(0, 3).mapToObj(octave ->
                                    {
                                        final int minScalePitchIndex = (accidentalPitchClassesMap.getValue().size() - 1) * -octave;
                                        final int maxScalePitchIndexLimit = (accidentalPitchClassesMap.getValue().size() - 1) * 3;
                                        return IntStream.rangeClosed(minScalePitchIndex, maxScalePitchIndexLimit)
                                            .mapToObj(scalePitchIndex ->
                                            {
                                                final Scale scale = new Scale(accidentalPitchClassesMap.getValue(), octave);
                                                final int computedPitchIndex = Math.floorMod(scalePitchIndex, accidentalPitchClassesMap.getValue().size());
                                                final int computedOctave = octave + Math.floorDiv(scalePitchIndex, accidentalPitchClassesMap.getValue().size());
                                                final PitchClass computedPitchClass = accidentalPitchClassesMap.getValue().get(computedPitchIndex);
                                                final Pitch computedPitch = new Pitch(computedPitchClass, computedOctave);
                                                final int duration = random.nextInt(1, 256);

                                                return waveShapes.stream().flatMap(waveShape ->
                                                {
                                                    final Audio expectedAudioObject = new Audio(waveShape, computedPitch, duration);

                                                    return waveShape.stringValueAliases().stream().map(waveShapeStringValueAlias ->
                                                    {
                                                        final String audioScaleString =
                                                            new StringBuilder()
                                                                .append(waveShapeStringValueAlias)
                                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                                .append(scalePitchIndex)
                                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                                .append(duration)
                                                                .toString();

                                                        return arguments(audioScaleString, waveShape, scale, expectedAudioObject);
                                                    });
                                                });
                                            })
                                            .flatMap(s -> s);
                                    })
                                    .flatMap(s -> s)
                                )
                    )
                );
            }
        }

        static final class AudioPitchStringValueWithoutWaveShapePrefixAndAudio implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 256);
                    return waveShapes.stream()
                        .flatMap(wave -> accidentals.stream()
                            .flatMap(accidental -> Stream.of(
                                arguments(
                                    new StringBuilder()
                                        .append(pitchLetter.charValue())
                                        .append(accidental.charValue())
                                        .append(octave)
                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                        .append(duration)
                                        .toString(),
                                    wave,
                                    new Audio(
                                        wave,
                                        new Pitch(
                                            pitchLetter,
                                            accidental,
                                            octave
                                        ),
                                        duration
                                    )
                                ),
                                arguments(
                                    new StringBuilder()
                                        .append(Character.toLowerCase(pitchLetter.charValue()))
                                        .append(accidental.charValue())
                                        .append(octave)
                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                        .append(duration)
                                        .toString(),
                                    wave,
                                    new Audio(
                                        wave,
                                        new Pitch(
                                            pitchLetter,
                                            accidental,
                                            octave
                                        ),
                                        duration
                                    )
                                )
                            ))
                        );
                });
            }
        }

        static final class AudioPitchStringValueWithoutWaveShapePrefix implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 256);
                    return Stream.concat(
                            Stream.of(
                                arguments(
                                    new StringBuilder()
                                        .append(pitchLetter.charValue())
                                        .append(octave)
                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                        .append(duration)
                                        .toString()
                                ),
                                arguments(
                                    new StringBuilder()
                                        .append(Character.toLowerCase(pitchLetter.charValue()))
                                        .append(octave)
                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                        .append(duration)
                                        .toString()
                                )),
                            accidentals.stream()
                                .flatMap(accidental ->
                                    Stream.of(
                                        arguments(
                                            new StringBuilder()
                                                .append(pitchLetter.charValue())
                                                .append(accidental.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(Character.toLowerCase(pitchLetter.charValue()))
                                                .append(accidental.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        )
                                    )
                                )
                        );
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
                    final int duration = random.nextInt(1, 256);
                    return waveShapes.stream()
                        .flatMap(wave -> wave.stringValueAliases().stream()
                            .flatMap(waveStringAlias -> Stream.concat(
                                    Stream.of(
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(waveStringAlias)
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(duration)
                                                .toString()
                                        ),
                                        arguments(
                                            new StringBuilder()
                                                .append(waveStringAlias)
                                                .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                .append(note.charValue())
                                                .append(octave)
                                                .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                .toString()
                                        )),
                                    accidentals.stream()
                                        .flatMap(accidental ->
                                            Stream.of(
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(duration)
                                                        .toString()
                                                ),
                                                arguments(
                                                    new StringBuilder()
                                                        .append(waveStringAlias)
                                                        .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                        .append(note.charValue())
                                                        .append(accidental.charValue())
                                                        .append(octave)
                                                        .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                        .toString()
                                                )
                                            )))));
                });
            }
        }

        static final class AudioPitchStringAndWave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(0, 13);
                    final int duration = random.nextInt(1, 256);
                    return waveShapes.stream()
                        .flatMap(wave -> wave.stringValueAliases().stream()
                            .flatMap(waveStringAlias -> Stream.concat(
                                Stream.of(
                                    arguments(
                                        "",
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(duration)
                                            .toString(),
                                        wave
                                    ),
                                    arguments(
                                        new StringBuilder()
                                            .append(waveStringAlias)
                                            .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                            .append(pitchLetter.charValue())
                                            .append(octave)
                                            .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                            .toString(),
                                        wave
                                    )),
                                    accidentals.stream()
                                        .flatMap(accidental -> Stream.of(
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(duration)
                                                    .toString(),
                                                wave
                                            ),
                                            arguments(
                                                new StringBuilder()
                                                    .append(waveStringAlias)
                                                    .append(AudioString.Delimiter.WAVE_SHAPE_AND_PITCH.charValue())
                                                    .append(pitchLetter.charValue())
                                                    .append(accidental.charValue())
                                                    .append(octave)
                                                    .append(AudioString.Delimiter.VOICE_AND_DURATION.charValue())
                                                    .toString(),
                                                wave
                                            )
                                        )))));
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

    @ParameterizedTest(name = "AudioString.parse(\"{0}\", Wave.{1}) creates Optional of {2}")
    @ArgumentsSource(AudioStringTestArgsProvider.Valid.AudioPitchStringValueWithoutWaveShapePrefixAndAudio.class)
    void audioStringParseReturnsAudioObjectForValidAudioStringWithoutPrefixWithDefaultWave(
            String audioString,
            WaveShape wave,
            Audio audio
    ) {

        final Optional<Audio> parsedAudio = AudioString.parse(audioString, wave);

        assertTrue(
            parsedAudio.isPresent(),
            () -> "AudioString.parse(\"%s\") returned empty Optional."
                .formatted(audioString)
        );

        assertEquals(audio, parsedAudio.get());
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\", {1}) returns empty Optional")
    @ArgumentsSource(AudioStringTestArgsProvider.Invalid.AudioPitchStringAndWave.class)
    void audioStringParseReturnsEmptyOptionalForInvalidAudioStringAndWaveArgument(String audioString, WaveShape wave) {

        final Optional<Audio> parsedAudio = AudioString.parse(audioString, wave);

        assertTrue(
            parsedAudio.isEmpty(),
            () -> "AudioString.parse(\"%s\") returned non empty Optional."
                .formatted(audioString)
        );
    }

    @ParameterizedTest(name = "AudioString.parse(\"{0}\", {1}, {2}) returns Optional<{3}>")
    @ArgumentsSource(AudioStringTestArgsProvider.Valid.WaveShapePrefixedAudioScaleStringValueAndAudio.class)
    void audioStringParseScaleStringWithValidScaleReturnsValidAudioObject(
        String audioScaleString,
        WaveShape waveShape,
        Scale scale,
        Audio expectedAudioObject
    ) {

        final Optional<Audio> parsedAudio = AudioString.parse(audioScaleString, waveShape, scale);

        assertTrue(
            parsedAudio.isPresent(),
            () -> "AudioString.parse(\"%s\", %s) returned empty Optional."
                .formatted(audioScaleString, scale)
        );

        assertEquals(expectedAudioObject, parsedAudio.get());
    }
}
