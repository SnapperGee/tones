package sogott.tones;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class AccidentalTestArgsProvider {
    static final class CharValues implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments('+', Accidental.SHARP),
                    arguments('=', Accidental.NATURAL),
                    arguments('-', Accidental.FLAT));
        }
    }

    static final class OffSets implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(1, Accidental.SHARP),
                    arguments(0, Accidental.NATURAL),
                    arguments(-1, Accidental.FLAT));
        }
    }

    static final class SymbolChars implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments('♯', Accidental.SHARP),
                    arguments('♮', Accidental.NATURAL),
                    arguments('♭', Accidental.FLAT));
        }
    }
}

final class AccidentalTest {
    static Stream<Character> accidentalChars() {
        return Stream.of('+', '-', '=');
    }

    static Stream<Character> nonAccidentalChars() {
        return IntStream.rangeClosed(32, 126)
                .filter(cp -> cp != '+' && cp != '-' && cp != '=')
                .mapToObj(cp -> (char) cp);
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

    @ParameterizedTest(name = "Accidental.{1}.symbolChar() is ''{0}''")
    @ArgumentsSource(AccidentalTestArgsProvider.SymbolChars.class)
    void accidentalSymbolChar(char symbolChar, Accidental accidental) {
        assertEquals(accidental.symbolChar(), symbolChar);
    }

    @ParameterizedTest(name = "Accidental.from(''{0}'') returns Optional<Accidental.{1}>")
    @ArgumentsSource(AccidentalTestArgsProvider.CharValues.class)
    void accidentalFrom(char charValue, Accidental accidental) {
        final Optional<Accidental> optionalAccidental = Accidental.fromChar(charValue);

        assertTrue(optionalAccidental.isPresent(),
                () -> "Accidental.from('%c') returns non present optional.".formatted(charValue));
        assertSame(accidental, optionalAccidental.get());
    }

    @ParameterizedTest(name = "Accidental.isAccidentalChar(''{0}'') returns true")
    @MethodSource("accidentalChars")
    void accidentalIsAccidentalCharReturnsTrueForAccidentalChar(char accidentalChar) {
        assertTrue(Accidental.isAccidentalChar(accidentalChar));
    }

    @ParameterizedTest(name = "Accidental.isAccidentalChar(''{0}'') returns false")
    @MethodSource("nonAccidentalChars")
    void accidentalIsAccidentalCharReturnsFalseForNonAccidentalChar(char nonAccidentalChar) {
        assertFalse(Accidental.isAccidentalChar(nonAccidentalChar));
    }

    @ParameterizedTest(name = "Accidental.fromChar(''{0}'') returns empty optional")
    @MethodSource("nonAccidentalChars")
    void accidentalFromCharReturnsEmptyOptionalForNonAccidentalChar(char nonAccidentalChar) {
        final Optional<Accidental> fromCharOptional = Accidental.fromChar(nonAccidentalChar);
        final boolean fromCharOptionalShouldBeEmpty = fromCharOptional.isEmpty();
        assertTrue(fromCharOptionalShouldBeEmpty);
    }
}
