package sogott.beep;

import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class AccidentalTest {
    static char[] nonAccidentalChars() {
        return IntStream.rangeClosed(32, 126)
                .filter(i -> i != '+' && i != '-')
                .mapToObj(i -> (char) i)
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString()
                .toCharArray();
    }

    @Test
    void accidentalSharpCharValueEqualsPlus() {
        final char sharpCharValue = Accidental.SHARP.charValue();
        final char expectedAccidentalSharpCharValue = '+';
        assertEquals(expectedAccidentalSharpCharValue, sharpCharValue);
    }

    @Test
    void accidentalFlatCharValueEqualsPlus() {
        final char flatCharValue = Accidental.FLAT.charValue();
        final char expectedAccidentalFlatCharValue = '-';
        assertEquals(expectedAccidentalFlatCharValue, flatCharValue);
    }

    @Test
    void accidentalSharpOffsetEquals1() {
        final int accidentalSharpOffsetValue = Accidental.SHARP.offset();
        final int expectedAccidentalSharpOffsetValue = 1;
        assertEquals(expectedAccidentalSharpOffsetValue, accidentalSharpOffsetValue);
    }

    @Test
    void accidentalFlatOffsetEqualsNegative1() {
        final int accidentalFlatOffsetValue = Accidental.FLAT.offset();
        final int expectedAccidentalFlatOffsetValue = -1;
        assertEquals(expectedAccidentalFlatOffsetValue, accidentalFlatOffsetValue);
    }

    @Test
    void accidentalFromCharPlusSignReturnsSharp() {
        final Optional<Accidental> optionalAccidental = Accidental.fromChar('+');
        final boolean optionalAccidentalIsPresent = optionalAccidental.isPresent();

        assertTrue(optionalAccidentalIsPresent);

        final Accidental noteValue = optionalAccidental.get();
        final Accidental expectedAccidentalValue = Accidental.SHARP;

        assertEquals(expectedAccidentalValue, noteValue);
    }

    @Test
    void accidentalFromCharMinusSignReturnsFlat() {
        final Optional<Accidental> optionalAccidental = Accidental.fromChar('-');
        final boolean optionalAccidentalIsPresent = optionalAccidental.isPresent();

        assertTrue(optionalAccidentalIsPresent);

        final Accidental noteValue = optionalAccidental.get();
        final Accidental expectedAccidentalValue = Accidental.FLAT;

        assertEquals(expectedAccidentalValue, noteValue);
    }

    @ParameterizedTest(name = "Accidental.fromChar(''{0}'') returns empty optional")
    @MethodSource("nonAccidentalChars")
    void accidentalFromCharReturnsEmptyOptionalForNonAccidentalChar(char nonAccidentalChar) {
        final Optional<Accidental> fromCharOptional = Accidental.fromChar(nonAccidentalChar);
        final boolean fromCharOptionalShouldBeEmpty = fromCharOptional.isEmpty();
        assertTrue(fromCharOptionalShouldBeEmpty);
    }
}
