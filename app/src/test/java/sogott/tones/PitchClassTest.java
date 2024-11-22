package sogott.tones;

import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PitchClassTestArgsProvider {
    private final static List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static RandomGenerator random = RandomGenerator.getDefault();

    final static class Valid {
        final static class QualifiedUpperCasePitchClassStringAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(
                                        new StringBuilder(2).append(pitchLetter.charValue())
                                                .append(accidental.charValue()).toString(),
                                        new PitchClass(pitchLetter, accidental))));
            }
        }

        final static class SoloUpperCasePitchLetterStringAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().map(pitchLetter -> arguments(Character.toString(pitchLetter.charValue()),
                        new PitchClass(pitchLetter, Accidental.NATURAL)));
            }
        }
    }
}

final class PitchClassTest {
    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns Optional<{1}>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.QualifiedUpperCasePitchClassStringAndObject.class)
    void pitchClassParseStringOfQualifiedValidLetterReturnsPresentOptional(
            String pitchLetterString,
            PitchClass expectedPitchClass) {
        final Optional<PitchClass> pitchClassOptional = PitchClass.parse(pitchLetterString);
        assertTrue(pitchClassOptional.isPresent(),
                () -> "PitchClass.parse(\"%s\") returns non present Optional".formatted(pitchLetterString));
        assertEquals(expectedPitchClass, pitchClassOptional.get());
    }

    @ParameterizedTest(name = "PitchClass.parse(\"{0}\") returns Optional<{1}>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.SoloUpperCasePitchLetterStringAndObject.class)
    void pitchClassParseStringOfSoloValidLetterReturnsPresentOptional(
            String soloPitchLetterString,
            PitchClass expectedPitchClass) {
        final Optional<PitchClass> pitchClassOptional = PitchClass.parse(soloPitchLetterString);
        assertTrue(pitchClassOptional.isPresent(),
                () -> "PitchClass.parse(\"%s\") returns non present Optional".formatted(soloPitchLetterString));
        assertEquals(expectedPitchClass, pitchClassOptional.get());
    }
}
