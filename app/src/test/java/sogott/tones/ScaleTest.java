package sogott.tones;

import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
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
                        IntStream.rangeClosed((pitchClasses.size() - 1) * -4, (pitchClasses.size() - 1) * 4).mapToObj(
                                index -> {
                                    final int octave = random.nextInt(4, 12);
                                    return arguments(pitchClasses, octave, index, new Pitch(pitchClasses.get(Math.floorMod(index, pitchClasses.size())), octave + Math.floorDiv(index, pitchClasses.size())));
                                })
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
