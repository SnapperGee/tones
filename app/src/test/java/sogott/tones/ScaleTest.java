package sogott.tones;

import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class ScaleTestArgsProvider {
    static final List<ScalePitchClasses> scalePitchClasses = unmodifiableList(asList(ScalePitchClasses.values()));

    private static final RandomGenerator random = RandomGenerator.getDefault();

    static final class PitchClassesIn2ndOrHigherOctaveWithNegativeIndexSingleOctaveLower implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return scalePitchClasses.stream()
            .flatMap(scales -> scales.pitchLetterAccidentalMap().values().stream()
                .flatMap(pitchLetterAccidentalMap -> pitchLetterAccidentalMap.values().stream().map(
                    pitchClasses ->
                        IntStream.rangeClosed(-(pitchClasses.size() - 1), -1).mapToObj(
                                negativeIndex -> {
                                    final int octave = random.nextInt(2, 12);
                                    return arguments(pitchClasses, octave, negativeIndex, new Pitch(pitchClasses.get(pitchClasses.size() + negativeIndex), octave - 1));
                                })
                ))
                .flatMap(s -> s));
        }
    }
}

final class ScaleTest {
    @ParameterizedTest(name = "Scale(pitchClasses={0}, octave={1}).pitch({2}) returns {3}")
    @ArgumentsSource(ScaleTestArgsProvider.PitchClassesIn2ndOrHigherOctaveWithNegativeIndexSingleOctaveLower.class)
    void scalePitchPassedSingleOctaveLowerNegativeIndex(
        List<PitchClass> pitchClasses,
        int scaleOctave,
        int pitchIndex,
        Pitch expectedPitch
    ) {
        final Scale scale = new Scale(pitchClasses, scaleOctave);
        final Pitch retrievedPitch = scale.pitch(pitchIndex);
        assertEquals(expectedPitch, retrievedPitch);
    }
}
