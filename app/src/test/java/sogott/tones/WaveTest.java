package sogott.tones;

import java.util.Optional;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.random.RandomGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class WaveArgProvider {

    final private static RandomGenerator random = RandomGenerator.getDefault();

    final static class EnumValuesWithUpperCaseStringValue implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Wave.SIN, Wave.SIN.name()),
                    arguments(Wave.SQUARE, "SQR"),
                    arguments(Wave.TRIANGLE, "TRI"),
                    arguments(Wave.SAW_UP, "SUP"),
                    arguments(Wave.SAW_DOWN, "SDN"));
        }
    }

    final static class EnumValuesWithLowerCaseStringValue implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Wave.SIN, Wave.SIN.name().toLowerCase()),
                    arguments(Wave.SQUARE, "sqr"),
                    arguments(Wave.TRIANGLE, "tri"),
                    arguments(Wave.SAW_UP, "sup"),
                    arguments(Wave.SAW_DOWN, "sdn"));
        }
    }

    final static class EnumValuesWithUpperCaseStringAliases implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Wave.SIN, Set.of(Wave.SIN.name())),
                    arguments(Wave.SQUARE, Set.of(Wave.SQUARE.name(), Wave.SQUARE.stringValue())),
                    arguments(Wave.TRIANGLE, Set.of(Wave.TRIANGLE.name(), Wave.TRIANGLE.stringValue())),
                    arguments(Wave.SAW_UP, Set.of(Wave.SAW_UP.name(), Wave.SAW_UP.stringValue(), "SAWUP")),
                    arguments(Wave.SAW_DOWN, Set.of(Wave.SAW_DOWN.name(), Wave.SAW_DOWN.stringValue(), "SAWDOWN")));
        }
    }

    final static class EnumValuesWithLowerCaseStringAliases implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Wave.SIN, Set.of(Wave.SIN.name().toLowerCase())),
                    arguments(Wave.SQUARE,
                            Set.of(Wave.SQUARE.name().toLowerCase(), Wave.SQUARE.stringValue().toLowerCase())),
                    arguments(Wave.TRIANGLE,
                            Set.of(Wave.TRIANGLE.name().toLowerCase(), Wave.TRIANGLE.stringValue().toLowerCase())),
                    arguments(Wave.SAW_UP,
                            Set.of(Wave.SAW_UP.name().toLowerCase(), Wave.SAW_UP.stringValue().toLowerCase(), "sawup")),
                    arguments(Wave.SAW_DOWN, Set.of(Wave.SAW_DOWN.name().toLowerCase(),
                            Wave.SAW_DOWN.stringValue().toLowerCase(), "sawdown")));
        }
    }

    final static class EnumValuesWithUpperCaseStringAlias implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Wave.SIN.name(), Wave.SIN),
                    arguments(Wave.SQUARE.name(), Wave.SQUARE),
                    arguments("SQR", Wave.SQUARE),
                    arguments(Wave.TRIANGLE.name(), Wave.TRIANGLE),
                    arguments("TRI", Wave.TRIANGLE),
                    arguments(Wave.SAW_UP.name(), Wave.SAW_UP),
                    arguments("SUP", Wave.SAW_UP),
                    arguments("SAWUP", Wave.SAW_UP),
                    arguments(Wave.SAW_DOWN.name(), Wave.SAW_DOWN),
                    arguments("SDN", Wave.SAW_DOWN),
                    arguments("SAWDOWN", Wave.SAW_DOWN));
        }
    }

    final static class EnumValuesWithLowerCaseStringAlias implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Wave.SIN.name().toLowerCase(), Wave.SIN),
                    arguments(Wave.SQUARE.name().toLowerCase(), Wave.SQUARE),
                    arguments("sqr", Wave.SQUARE),
                    arguments(Wave.TRIANGLE.name().toLowerCase(), Wave.TRIANGLE),
                    arguments("tri", Wave.TRIANGLE),
                    arguments(Wave.SAW_UP.name().toLowerCase(), Wave.SAW_UP),
                    arguments("sup", Wave.SAW_UP),
                    arguments("sawup", Wave.SAW_UP),
                    arguments(Wave.SAW_DOWN.name().toLowerCase(), Wave.SAW_DOWN),
                    arguments("sdn", Wave.SAW_DOWN),
                    arguments("sawdown", Wave.SAW_DOWN));
        }
    }

    final static class PrefixedStrings implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Arrays.stream(Wave.values()).flatMap(wave -> Stream.concat(
                    wave.stringValueAliases().stream().map(stringValueAlias -> arguments(wave, stringValueAlias)),
                    wave.stringValueAliases().stream()
                            .flatMap(stringValueAlias -> IntStream.range(1, 7)
                                    .mapToObj(i -> arguments(wave, stringValueAlias
                                            + IntStream.rangeClosed(32, 126).limit(random.nextLong(1, 10))
                                                    .mapToObj(cp -> (char) cp).collect(StringBuilder::new,
                                                            StringBuilder::append, StringBuilder::append)
                                                    .toString())))));
        }
    }
}

final class WaveTest {
    @ParameterizedTest(name = "Wave.{0}.stringValue() = \"{1}\"")
    @ArgumentsSource(WaveArgProvider.EnumValuesWithUpperCaseStringValue.class)
    void waveStringValueIsValid(Wave wave, String expectedStringValue) {
        final String waveStringValue = wave.stringValue();
        assertEquals(expectedStringValue, waveStringValue);
    }

    @ParameterizedTest(name = "Wave.{0}.stringValueAliases() = {1}")
    @ArgumentsSource(WaveArgProvider.EnumValuesWithUpperCaseStringAliases.class)
    void waveStringValueAliasesIsValid(Wave wave, Set<String> expectedStringAliases) {
        final Set<String> waveStringAliases = wave.stringValueAliases();
        assertEquals(expectedStringAliases, waveStringAliases);
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns optional of Wave.{1}")
    @ArgumentsSource(WaveArgProvider.EnumValuesWithUpperCaseStringAlias.class)
    void waveParseUpperCaseStringReturnsOptionalOfWave(String waveString, Wave expectedWave) {
        final Optional<Wave> optionalWave = Wave.parse(waveString);
        assertThat(optionalWave.get(), is(expectedWave));
    }

    @ParameterizedTest(name = "Wave.parse(\"{0}\") returns optional of Wave.{1}")
    @ArgumentsSource(WaveArgProvider.EnumValuesWithLowerCaseStringAlias.class)
    void waveParseLowerCaseStringReturnsOptionalOfWave(String waveString, Wave expectedWave) {
        final Optional<Wave> optionalWave = Wave.parse(waveString);
        assertThat(optionalWave.get(), is(expectedWave));
    }

    @ParameterizedTest(name = "Wave.{0}.prefixes(\"{1}\") returns true")
    @ArgumentsSource(WaveArgProvider.PrefixedStrings.class)
    void prefixesReturnsTrueForPrefixedStrings(Wave wave, String prefixedString) {
        assertTrue(wave.prefixes(prefixedString),
                () -> "Wave.%s.prefixes(\"%s\") returns false".formatted(wave.name(), prefixedString));
    }
}
