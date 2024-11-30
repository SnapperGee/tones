package sogott.tones;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PitchClassTestArgsProvider {
    private static final List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private static final List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    static final RandomGenerator random = RandomGenerator.getDefault();

    static PitchLetter randomPitchLetter() {
        return pitchLetters.get(random.nextInt(pitchLetters.size()));
    }

    static Accidental randomAccidental() {
        return accidentals.get(random.nextInt(accidentals.size()));
    }

    static final class Valid {
        static final class PitchLetterAndAccidental implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter, accidental)));
            }
        }

        static final class PitchLetterAndAccidentalWithPitchClassStringPrefix implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> {
                            final String pitchLetterString = Character.toString(pitchLetter.charValue());

                            return Stream.concat(
                                Stream.concat(
                                    Stream.of(arguments(
                                        pitchLetter,
                                        Accidental.NATURAL,
                                        pitchLetterString,
                                        pitchLetterString
                                    )),
                                    Util.randomStrings(7, 1, 6, accidentals.stream().mapToInt(accidental -> accidental.charValue()).toArray())
                                        .map(aString ->
                                            arguments(
                                                pitchLetter,
                                                Accidental.NATURAL,
                                                pitchLetterString,
                                                pitchLetterString + aString))
                                ),
                                accidentals.stream()
                                    .flatMap(accidental ->
                                        {
                                            final String pitchClassString = pitchLetterString + accidental.charValue();

                                            return Stream.concat(
                                                Stream.of(
                                                    arguments(
                                                        pitchLetter,
                                                        accidental,
                                                        pitchClassString,
                                                        pitchClassString)
                                                    ),
                                                Util.randomStrings(7, 1, 6)
                                                    .map(aString ->
                                                        arguments(
                                                            pitchLetter,
                                                            accidental,
                                                            pitchClassString,
                                                            pitchClassString + aString))
                                            );
                                        }
                                    )
                                );
                            }
                        );
            }
        }

        static final class PitchLetter implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter)));
            }
        }

        static final class PitchLetterAndObject implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                                .map(accidental -> arguments(pitchLetter,
                                        new PitchClass(pitchLetter, Accidental.NATURAL))));
            }
        }
    }

    static final class Invalid {
        static final class NonPrefixedString implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream()
                        .flatMap(pitchLetter -> accidentals.stream()
                            .flatMap(accidental ->
                                {
                                    final String pitchClassString = new StringBuilder(2)
                                        .append(pitchLetter.charValue())
                                        .append(accidental.charValue())
                                        .toString();

                                    return Stream.concat(
                                        IntStream.range(1, pitchClassString.length()).mapToObj(num -> arguments(pitchClassString.substring(num))),
                                        Util.randomStrings(7, 1, 6, pitchLetters.stream().mapToInt(pl -> pl.charValue()).toArray())
                                            .map(aString -> arguments(aString + pitchClassString))
                                    );
                                }
                            )
                        );
            }
        }
    }
}

final class PitchClassTest {
    @ParameterizedTest(name = "new PitchClass(PitchLetter.{0}, Accidental.{1}) does not throw")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetterAndAccidental.class)
    void pitchClassConstructorPassedValidPitchLetterAndAccidentalDoesNotThrow(PitchLetter pitchLetter,
            Accidental accidental) {
        assertDoesNotThrow(() -> new PitchClass(pitchLetter, accidental));
    }

    @ParameterizedTest(name = "new PitchClass(PitchLetter.{0}) does not throw")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetter.class)
    void pitchClassConstructorPassedValidPitchLetterDoesNotThrow(PitchLetter pitchLetter) {
        assertDoesNotThrow(() -> new PitchClass(pitchLetter));
    }

    @Test
    @DisplayName("new PitchClass(null, Accidental) throws IllegalArgumentException")
    void pitchClassConstructorPassedNullPitchLetterAndNonNullAccidentalThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new PitchClass(null, PitchClassTestArgsProvider.randomAccidental()));
    }

    @Test
    @DisplayName("new PitchClass(PitchLetter, null) throws IllegalArgumentException")
    void pitchClassConstructorPassedPitchLetterAndNullAccidentalThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new PitchClass(PitchClassTestArgsProvider.randomPitchLetter(), null));
    }

    @Test
    @DisplayName("new PitchClass(null) throws IllegalArgumentException")
    void pitchClassConstructorPassedNullAThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new PitchClass(null));
    }

    @ParameterizedTest(name = "new PitchClass(PitchLetter.{0}) returns {1}")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetterAndObject.class)
    void pitchClassConstructorPassedValidPitchLetterReturnsPitchClassWithPitchLetterAndNatural(PitchLetter pitchLetter,
            PitchClass expectedPitchClass) {
        assertEquals(expectedPitchClass, new PitchClass(pitchLetter));
    }

    @ParameterizedTest(name = "PitchClass.parsePrefix(\"{3}\") returns Optional<Entry[PitchClass[pitchLetter={0}, accidental={1}]=\"{2}\"]>")
    @ArgumentsSource(PitchClassTestArgsProvider.Valid.PitchLetterAndAccidentalWithPitchClassStringPrefix.class)
    void pitchClassParsePrefixPassedPrefixedStringReturnsOptionalPitchClassWithStringEntry(
            PitchLetter pitchLetter,
            Accidental accidental,
            String pitchClassString,
            String prefixedString
    ) {

        final PitchClass pitchClass = new PitchClass(pitchLetter, accidental);
        final Map.Entry<PitchClass, String> pitchClassAccidentalEntry = Map.entry(pitchClass, pitchClassString);
        final Optional<Map.Entry<PitchClass, String>> pitchClassAccidentalEntryOptional = PitchClass.parsePrefix(prefixedString);

        assertTrue(
            pitchClassAccidentalEntryOptional.isPresent(),
            () -> "PitchClass.parsePrefix(\"%s\") returned empty optional".formatted(pitchClassString)
        );

        assertEquals(pitchClassAccidentalEntryOptional.get(), pitchClassAccidentalEntry);

    }

    @ParameterizedTest(name = "PitchClass.parsePrefix(\"{0}\") returns empty Optional")
    @ArgumentsSource(PitchClassTestArgsProvider.Invalid.NonPrefixedString.class)
    void pitchClassParsePrefixPassedNonPrefixedStringReturnsEmptyOptional(String nonPrefixedString) {
        assertTrue(PitchClass.parsePrefix(nonPrefixedString).isEmpty());
    }

    @Test
    @DisplayName("PitchClass.parsePrefix(null) throws IllegalArgumentException")
    void pitchClassParsePrefixPassedNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> PitchClass.parsePrefix(null));
    }
}
