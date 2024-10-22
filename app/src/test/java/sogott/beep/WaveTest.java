package sogott.beep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.in;

final class WaveTest {
    @Test
    void sinWaveStringValueIsEnumValueName() {
        final String sinWaveStringValue = Wave.SIN.stringValue();
        final String sinWaveEnumValueName = Wave.SIN.name();
        assertEquals(sinWaveEnumValueName, sinWaveStringValue);
    }

    @Test
    void sinWaveEnumStringValueAliasesHasSize1() {
        final Set<String> sinWaveStringValueAliases = Wave.SIN.stringValueAliases();
        final int sinWaveStringValueAliasesSize = 1;
        assertThat(sinWaveStringValueAliases, hasSize(sinWaveStringValueAliasesSize));
    }

    @Test
    void sinWaveEnumValueNameInStringValueAliases() {
        final Set<String> sinWaveStringValueAliases = Wave.SIN.stringValueAliases();
        final String sinWaveEnumValueName = Wave.SIN.name();
        assertThat(sinWaveEnumValueName, is(in(sinWaveStringValueAliases)));
    }

    @Test
    void squareWaveStringValueIsSQRString() {
        final String squareWaveStringValue = Wave.SQUARE.stringValue();
        final String expectedSquareWaveStringValue = "SQR";
        assertEquals(expectedSquareWaveStringValue, squareWaveStringValue);
    }

    @Test
    void squareWaveEnumStringValueAliasesHasSize2() {
        final Set<String> squareWaveStringValueAliases = Wave.SQUARE.stringValueAliases();
        final int squareWaveStringValueAliasesSize = 2;
        assertThat(squareWaveStringValueAliases, hasSize(squareWaveStringValueAliasesSize));
    }

    @Test
    void squareWaveStringValueAliasesHasEnumValueNameAndStringValue() {
        final Set<String> squareWaveStringValueAliases = Wave.SQUARE.stringValueAliases();
        final String[] expectedSquareWaveStringValueAliases = { Wave.SQUARE.name(), Wave.SQUARE.stringValue() };
        assertThat(squareWaveStringValueAliases, hasItems(expectedSquareWaveStringValueAliases));
    }

    @Test
    void triangleWaveStringValueIsTRIString() {
        final String triangleWaveStringValue = Wave.TRIANGLE.stringValue();
        final String expectedTriangleWaveStringValue = "TRI";
        assertEquals(expectedTriangleWaveStringValue, triangleWaveStringValue);
    }

    @Test
    void triangleWaveEnumStringValueAliasesHasSize2() {
        final Set<String> triangleWaveStringValueAliases = Wave.TRIANGLE.stringValueAliases();
        final int triangleWaveStringValueAliasesSize = 2;
        assertThat(triangleWaveStringValueAliases, hasSize(triangleWaveStringValueAliasesSize));
    }

    @Test
    void triangleWaveStringValueAliasesHasEnumValueNameAndStringValue() {
        final Set<String> triangleWaveStringValueAliases = Wave.TRIANGLE.stringValueAliases();
        final String[] expectedTriangleWaveStringValueAliases = { Wave.TRIANGLE.name(), Wave.TRIANGLE.stringValue() };
        assertThat(triangleWaveStringValueAliases, hasItems(expectedTriangleWaveStringValueAliases));
    }

    @Test
    void sawUpWaveStringValueIsSUPString() {
        final String sawUpWaveStringValue = Wave.SAW_UP.stringValue();
        final String expectedSawUpWaveStringValue = "SUP";
        assertEquals(expectedSawUpWaveStringValue, sawUpWaveStringValue);
    }

    @Test
    void sawUpWaveEnumStringValueAliasesHasSize3() {
        final Set<String> sawUpWaveStringValueAliases = Wave.SAW_UP.stringValueAliases();
        final int sawUpWaveStringValueAliasesSize = 3;
        assertThat(sawUpWaveStringValueAliases, hasSize(sawUpWaveStringValueAliasesSize));
    }

    @Test
    void sawUpWaveStringValueAliasesHasCorrectStrings() {
        final Set<String> sawUpWaveStringValueAliases = Wave.SAW_UP.stringValueAliases();
        final String[] expectedSawUpWaveStringValueAliases = { Wave.SAW_UP.name(), Wave.SAW_UP.stringValue(),
                "SAWUP" };
        assertThat(sawUpWaveStringValueAliases, hasItems(expectedSawUpWaveStringValueAliases));
    }

    @Test
    void sawDownWaveStringValueIsSDNString() {
        final String sawDownWaveStringValue = Wave.SAW_DOWN.stringValue();
        final String expectedSawUpWaveStringValue = "SDN";
        assertEquals(expectedSawUpWaveStringValue, sawDownWaveStringValue);
    }

    @Test
    void sawDownWaveEnumStringValueAliasesHasSize3() {
        final Set<String> sawDownWaveStringValueAliases = Wave.SAW_DOWN.stringValueAliases();
        final int sawDownWaveStringValueAliasesSize = 3;
        assertThat(sawDownWaveStringValueAliases, hasSize(sawDownWaveStringValueAliasesSize));
    }

    @Test
    void sawDownWaveStringValueAliasesHasCorrectStrings() {
        final Set<String> sawDownWaveStringValueAliases = Wave.SAW_DOWN.stringValueAliases();
        final String[] expectedSawDownWaveStringValueAliases = { Wave.SAW_DOWN.name(), Wave.SAW_DOWN.stringValue(),
                "SAWDOWN" };
        assertThat(sawDownWaveStringValueAliases, hasItems(expectedSawDownWaveStringValueAliases));
    }

    @Test
    void parseUpperCaseSinStringsReturnsSinWave() {
        final Optional<Wave> optionalWave = Wave.parse("SIN");
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SIN;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.SQUARE")
    @ValueSource(strings = { "SQUARE", "SQR" })
    void parseUpperCaseSquareStringsReturnsSquareWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SQUARE;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.TRIANGLE")
    @ValueSource(strings = { "TRIANGLE", "TRI" })
    void parseUpperCaseTriangleStringsReturnsTriangleWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.TRIANGLE;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.SAW_UP")
    @ValueSource(strings = { "SAW_UP", "SUP", "SAWUP" })
    void parseUpperCaseSawUpStringsReturnsSawUpWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SAW_UP;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.SAW_DOWN")
    @ValueSource(strings = { "SAW_DOWN", "SDN", "SAWDOWN" })
    void parseUpperCaseSawDownStringsReturnsSawDownWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SAW_DOWN;

        assertEquals(expectedWaveValue, waveValue);
    }

    @Test
    void parseLowerCaseSinStringsReturnsSinWave() {
        final Optional<Wave> optionalWave = Wave.parse("sin");
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SIN;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.SQUARE")
    @ValueSource(strings = { "square", "sqr" })
    void parseLowerCaseSquareStringsReturnsSquareWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SQUARE;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.TRIANGLE")
    @ValueSource(strings = { "triangle", "tri" })
    void parseLowerCaseTriangleStringsReturnsTriangleWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.TRIANGLE;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.SAW_UP")
    @ValueSource(strings = { "saw_up", "sup", "sawup" })
    void parseLowerCaseSawUpStringsReturnsSawUpWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SAW_UP;

        assertEquals(expectedWaveValue, waveValue);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns Optional of Wave.SAW_DOWN")
    @ValueSource(strings = { "saw_down", "sdn", "sawdown" })
    void parseLowerCaseSawDownStringsReturnsSawDownWave(String waveStringValue) {
        final Optional<Wave> optionalWave = Wave.parse(waveStringValue);
        final boolean optionalWaveIsPresent = optionalWave.isPresent();

        assertTrue(optionalWaveIsPresent);

        final Wave waveValue = optionalWave.get();
        final Wave expectedWaveValue = Wave.SAW_DOWN;

        assertEquals(expectedWaveValue, waveValue);
    }
}
