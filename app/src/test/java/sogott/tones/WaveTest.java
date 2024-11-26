package sogott.tones;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class WaveTestArgsProvider {

    final static RandomGenerator random = RandomGenerator.getDefault();

    final static class EnumValuesWithUpperCaseStringValue implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(WaveShape.SINE, "SIN"),
                    arguments(WaveShape.SQUARE, "SQR"),
                    arguments(WaveShape.TRIANGLE, "TRI"),
                    arguments(WaveShape.SAW_UP, "SUP"),
                    arguments(WaveShape.SAW_DOWN, "SDN"));
        }
    }

    final static class EnumValuesWithLowerCaseStringValue implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(WaveShape.SINE, "sin"),
                    arguments(WaveShape.SQUARE, "sqr"),
                    arguments(WaveShape.TRIANGLE, "tri"),
                    arguments(WaveShape.SAW_UP, "sup"),
                    arguments(WaveShape.SAW_DOWN, "sdn"));
        }
    }

    final static class EnumValuesWithUpperCaseStringAliases implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(WaveShape.SINE, Set.of(WaveShape.SINE.name(), WaveShape.SINE.stringValue())),
                    arguments(WaveShape.SQUARE, Set.of(WaveShape.SQUARE.name(), WaveShape.SQUARE.stringValue())),
                    arguments(WaveShape.TRIANGLE, Set.of(WaveShape.TRIANGLE.name(), WaveShape.TRIANGLE.stringValue())),
                    arguments(WaveShape.SAW_UP, Set.of(WaveShape.SAW_UP.name(), WaveShape.SAW_UP.stringValue(), "SAWUP")),
                    arguments(WaveShape.SAW_DOWN, Set.of(WaveShape.SAW_DOWN.name(), WaveShape.SAW_DOWN.stringValue(), "SAWDOWN")));
        }
    }

    final static class EnumValuesWithLowerCaseStringAliases implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(WaveShape.SINE, Set.of(WaveShape.SINE.name().toLowerCase(), WaveShape.SINE.stringValue().toLowerCase())),
                    arguments(WaveShape.SQUARE,
                            Set.of(WaveShape.SQUARE.name().toLowerCase(), WaveShape.SQUARE.stringValue().toLowerCase())),
                    arguments(WaveShape.TRIANGLE,
                            Set.of(WaveShape.TRIANGLE.name().toLowerCase(), WaveShape.TRIANGLE.stringValue().toLowerCase())),
                    arguments(WaveShape.SAW_UP,
                            Set.of(WaveShape.SAW_UP.name().toLowerCase(), WaveShape.SAW_UP.stringValue().toLowerCase(), "sawup")),
                    arguments(WaveShape.SAW_DOWN, Set.of(WaveShape.SAW_DOWN.name().toLowerCase(),
                            WaveShape.SAW_DOWN.stringValue().toLowerCase(), "sawdown")));
        }
    }

    final static class EnumValuesWithUpperCaseStringAlias implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(WaveShape.SINE.name(), WaveShape.SINE),
                    arguments(WaveShape.SINE.stringValue(), WaveShape.SINE),
                    arguments(WaveShape.SQUARE.name(), WaveShape.SQUARE),
                    arguments("SQR", WaveShape.SQUARE),
                    arguments(WaveShape.TRIANGLE.name(), WaveShape.TRIANGLE),
                    arguments("TRI", WaveShape.TRIANGLE),
                    arguments(WaveShape.SAW_UP.name(), WaveShape.SAW_UP),
                    arguments("SUP", WaveShape.SAW_UP),
                    arguments("SAWUP", WaveShape.SAW_UP),
                    arguments(WaveShape.SAW_DOWN.name(), WaveShape.SAW_DOWN),
                    arguments("SDN", WaveShape.SAW_DOWN),
                    arguments("SAWDOWN", WaveShape.SAW_DOWN));
        }
    }

    final static class EnumValuesWithLowerCaseStringAlias implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(WaveShape.SINE.name().toLowerCase(), WaveShape.SINE),
                    arguments(WaveShape.SINE.stringValue().toLowerCase(), WaveShape.SINE),
                    arguments(WaveShape.SQUARE.name().toLowerCase(), WaveShape.SQUARE),
                    arguments("sqr", WaveShape.SQUARE),
                    arguments(WaveShape.TRIANGLE.name().toLowerCase(), WaveShape.TRIANGLE),
                    arguments("tri", WaveShape.TRIANGLE),
                    arguments(WaveShape.SAW_UP.name().toLowerCase(), WaveShape.SAW_UP),
                    arguments("sup", WaveShape.SAW_UP),
                    arguments("sawup", WaveShape.SAW_UP),
                    arguments(WaveShape.SAW_DOWN.name().toLowerCase(), WaveShape.SAW_DOWN),
                    arguments("sdn", WaveShape.SAW_DOWN),
                    arguments("sawdown", WaveShape.SAW_DOWN));
        }
    }

    final static class PrefixedStrings implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Arrays.stream(WaveShape.values()).flatMap(wave -> Stream.concat(
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
    @ParameterizedTest(name = "WaveShape.{0}.stringValue() = \"{1}\"")
    @ArgumentsSource(WaveTestArgsProvider.EnumValuesWithUpperCaseStringValue.class)
    void waveShapeStringValueIsValid(WaveShape waveShape, String expectedStringValue) {
        final String waveStringValue = waveShape.stringValue();
        assertEquals(expectedStringValue, waveStringValue);
    }

    @ParameterizedTest(name = "WaveShape.{0}.stringValueAliases() = {1}")
    @ArgumentsSource(WaveTestArgsProvider.EnumValuesWithUpperCaseStringAliases.class)
    void waveShapeStringValueAliasesIsValid(WaveShape waveShape, Set<String> expectedStringAliases) {
        final Set<String> waveStringAliases = waveShape.stringValueAliases();
        assertEquals(expectedStringAliases, waveStringAliases);
    }

    @ParameterizedTest(name = "WaveShape.parse(\"{0}\") returns optional of Wave.{1}")
    @ArgumentsSource(WaveTestArgsProvider.EnumValuesWithUpperCaseStringAlias.class)
    void waveShapeParseUpperCaseStringReturnsOptionalOfWave(String waveString, WaveShape expectedWave) {
        final Optional<WaveShape> optionalWave = WaveShape.parse(waveString);
        assertThat(optionalWave.orElse(null), is(expectedWave));
    }

    @ParameterizedTest(name = "WaveShape.parse(\"{0}\") returns optional of Wave.{1}")
    @ArgumentsSource(WaveTestArgsProvider.EnumValuesWithLowerCaseStringAlias.class)
    void waveShapeParseLowerCaseStringReturnsOptionalOfWave(String waveString, WaveShape expectedWave) {
        final Optional<WaveShape> optionalWave = WaveShape.parse(waveString);
        assertThat(optionalWave.orElse(null), is(expectedWave));
    }

    @ParameterizedTest(name = "WaveShape.{0}.prefixes(\"{1}\") returns true")
    @ArgumentsSource(WaveTestArgsProvider.PrefixedStrings.class)
    void prefixesReturnsTrueForPrefixedStrings(WaveShape waveShape, String prefixedString) {
        assertTrue(waveShape.prefixes(prefixedString),
                () -> "Wave.%s.prefixes(\"%s\") returns false".formatted(waveShape.name(), prefixedString));
    }
}
