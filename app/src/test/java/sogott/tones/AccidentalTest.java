package sogott.tones;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class AccidentalTestArgsProvider {
    final static class CharValues implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments('+', Accidental.SHARP),
                    arguments('-', Accidental.FLAT));
        }
    }

    final static class OffSets implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(1, Accidental.SHARP),
                    arguments(-1, Accidental.FLAT));
        }
    }
}

final class AccidentalTest {
    static Stream<Character> nonAccidentalChars() {
        return IntStream.rangeClosed(32, 126)
                .filter(i -> i != '+' && i != '-')
                .mapToObj(i -> (char) i);
    }

    @ParameterizedTest(name = "Accidental.{1}.charValue() is ''{0}''")
    @ArgumentsSource(AccidentalTestArgsProvider.CharValues.class)
    void accidentalCharValue(char charValue, Accidental accidental) {
        assertSame(accidental.charValue(), charValue);
    }

    @ParameterizedTest(name = "Accidental.{1}.offset() is {0}")
    @ArgumentsSource(AccidentalTestArgsProvider.OffSets.class)
    void accidentalOffSet(int offset, Accidental accidental) {
        assertSame(accidental.offset(), offset);
    }

    @ParameterizedTest(name = "Accidental.from(''{0}'') returns Optional<Accidental.{1}>")
    @ArgumentsSource(AccidentalTestArgsProvider.CharValues.class)
    void accidentalFrom(char charValue, Accidental accidental) {
        final Optional<Accidental> optionalAccidental = Accidental.fromChar(charValue);

        assertTrue(optionalAccidental.isPresent(),
                () -> "Accidental.from('%c') returns non present optional.".formatted(charValue));
        assertSame(accidental, optionalAccidental.get());
    }

    @Test
    void accidentalIsAccidentalCharReturnsTrueForPlus() {
        final boolean isAccidentalCharShouldBeTrue = Accidental.isAccidentalChar('+');
        assertTrue(isAccidentalCharShouldBeTrue);
    }

    @Test
    void accidentalIsAccidentalCharReturnsTrueForMinus() {
        final boolean isAccidentalCharShouldBeTrue = Accidental.isAccidentalChar('-');
        assertTrue(isAccidentalCharShouldBeTrue);
    }

    @ParameterizedTest(name = "Accidental.isAccidentalChar(''{0}'') returns false")
    @MethodSource("nonAccidentalChars")
    void accidentalIsAccidentalCharReturnsFalseForNonAccidentalChar(char nonAccidentalChar) {
        final boolean isAccidentalCharShouldBeFalse = Accidental.isAccidentalChar(nonAccidentalChar);
        assertFalse(isAccidentalCharShouldBeFalse);
    }

    @ParameterizedTest(name = "Accidental.fromChar(''{0}'') returns empty optional")
    @MethodSource("nonAccidentalChars")
    void accidentalFromCharReturnsEmptyOptionalForNonAccidentalChar(char nonAccidentalChar) {
        final Optional<Accidental> fromCharOptional = Accidental.fromChar(nonAccidentalChar);
        final boolean fromCharOptionalShouldBeEmpty = fromCharOptional.isEmpty();
        assertTrue(fromCharOptionalShouldBeEmpty);
    }
}
