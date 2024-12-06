package sogott.tones;

import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

final class ScaleTestArgsProvider {
    static final List<ScalePitchClasses> scalePitchClasses = unmodifiableList(asList(ScalePitchClasses.values()));

    static final RandomGenerator random = RandomGenerator.getDefault();

    static final class ScalesWithValidPitchIndexesAndOctaves implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return scalePitchClasses.stream()
            .flatMap(scales -> scales.pitchLetterAccidentalMap().values().stream()
                .flatMap(pitchLetterAccidentalMap -> pitchLetterAccidentalMap.values().stream().map(
                    pitchClasses ->
                    {
                        final int octave = random.nextInt(0, 7);

                        return IntStream.rangeClosed((pitchClasses.size() - 1) * -octave, (pitchClasses.size() - 1) * octave)
                            .mapToObj(scalePitchIndex ->
                            {
                                final int computedIndex = Math.floorMod(scalePitchIndex, pitchClasses.size());
                                final int computedOctave = octave + Math.floorDiv(scalePitchIndex, pitchClasses.size());
                                final PitchClass computedPitchClass = pitchClasses.get(computedIndex);
                                return arguments(pitchClasses, octave, scalePitchIndex, new Pitch(computedPitchClass, computedOctave));
                            });
                    }
                ))
                .flatMap(s -> s));
        }
    }

    static final class ScalesWithInvalidPitchIndexesAndOctaves implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return scalePitchClasses.stream()
            .flatMap(scales -> scales.pitchLetterAccidentalMap().values().stream()
                .flatMap(pitchLetterAccidentalMap -> pitchLetterAccidentalMap.values().stream().map(
                    pitchClasses ->
                        {
                            final int octave = random.nextInt(12);
                            final int minInvalidIndex = -(pitchClasses.size() * octave + 1);
                            return random.ints(20, minInvalidIndex * 3, minInvalidIndex).mapToObj(
                                index -> arguments(pitchClasses, octave, index));
                        }
                ))
                .flatMap(s -> s));
        }
    }
}

final class ScaleTest {
    @Test
    @DisplayName("Scale(null, 0 <= # <= 12) throws IllegalArgumentException")
    void scaleConstructorPassedNullPitchClassesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Scale(null, ScaleTestArgsProvider.random.nextInt(13)));
    }

    @Test
    @DisplayName("Scale(empty list, 0 <= # <= 12) throws IllegalArgumentException")
    void scaleConstructorPassedEmptyPitchClassesListThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Scale(List.of(), ScaleTestArgsProvider.random.nextInt(13)));
    }

    @Test
    @DisplayName("Scale(List<PitchClass>, -12 <= # < 0) throws IllegalArgumentException")
    void scaleConstructorPassedNegativeOctaveThrowsIllegalArgumentException() {
        PitchClass pitchClassList = mock();
        assertThrows(IllegalArgumentException.class, () -> new Scale(List.of(pitchClassList), ScaleTestArgsProvider.random.nextInt(-12, 0)));
    }

    @Disabled
    @ParameterizedTest(name = "Scale(pitchClasses={0}, octave={1}).pitch({2}) returns {3}")
    @ArgumentsSource(ScaleTestArgsProvider.ScalesWithValidPitchIndexesAndOctaves.class)
    void scalePitchPassedValidIndex(
        List<PitchClass> pitchClasses,
        int scaleOctave,
        int pitchIndex,
        Pitch expectedPitch
    ) {
        final Scale scale = new Scale(pitchClasses, scaleOctave);
        final Pitch retrievedPitch = scale.pitch(pitchIndex);
        assertEquals(expectedPitch, retrievedPitch);
    }

    @Disabled
    @ParameterizedTest(name = "Scale(pitchClasses={0}, octave={1}).pitch({2}) throws IndexOutOfBoundsException")
    @ArgumentsSource(ScaleTestArgsProvider.ScalesWithInvalidPitchIndexesAndOctaves.class)
    void scalePitchPassedInvalidIndexThrowsIndexOutOfBoundsException(
        List<PitchClass> pitchClasses,
        int scaleOctave,
        int pitchIndex
    ) {
        final Scale scale = new Scale(pitchClasses, scaleOctave);
        assertThrows(IndexOutOfBoundsException.class, () -> scale.pitch(pitchIndex));
    }
}
