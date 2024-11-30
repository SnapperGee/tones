package sogott.tones;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Map;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class WaveShapeTestArgsProvider {

    static final RandomGenerator random = RandomGenerator.getDefault();

    private static final List<WaveShape> WAVE_SHAPES = unmodifiableList(asList(WaveShape.values()));

    static final class EnumValuesWithUpperCaseStringValue implements ArgumentsProvider {
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

    static final class EnumValuesWithLowerCaseStringValue implements ArgumentsProvider {
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

    static final class EnumValuesWithUpperCaseStringAliases implements ArgumentsProvider {
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

    static final class EnumValuesWithLowerCaseStringAliases implements ArgumentsProvider {
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

    static final class EnumValuesWithUpperCaseStringAlias implements ArgumentsProvider {
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

    static final class EnumValuesWithLowerCaseStringAlias implements ArgumentsProvider {
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

    static final class EnumValueWithPrefixedString implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return WAVE_SHAPES.stream()
                .flatMap(waveShape -> Stream.concat(
                    waveShape.stringValueAliases().stream().map(stringValueAlias ->
                        arguments(waveShape, stringValueAlias)),
                            waveShape.stringValueAliases().stream()
                                .flatMap(stringValueAlias ->
                                    random.ints(7, 1, 10)
                                        .mapToObj(i ->
                                            arguments(waveShape, stringValueAlias
                                                + random.ints(i, 32, 127)
                                                        .mapToObj(cp -> (char) cp)
                                                            .collect(
                                                                StringBuilder::new,
                                                                StringBuilder::append,
                                                                StringBuilder::append)
                                                            .toString()
                                            )
                                        )
                                )
                ));
        }
    }

    static final class WaveShapeAliasStringEntryAndPrefixedString implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return WAVE_SHAPES.stream()
                .flatMap(waveShape -> Stream.concat(
                    waveShape.stringValueAliases().stream().map(stringValueAlias ->
                        arguments(Map.entry(waveShape, stringValueAlias), stringValueAlias)),
                            waveShape.stringValueAliases().stream()
                                .flatMap(stringValueAlias ->
                                    random.ints(7, 1, 10)
                                        .mapToObj(i ->
                                            arguments(
                                                Map.entry(waveShape, stringValueAlias),
                                                stringValueAlias
                                                    + random.ints(i, 32, 127)
                                                            .mapToObj(cp -> (char) cp)
                                                                .collect(
                                                                    StringBuilder::new,
                                                                    StringBuilder::append,
                                                                    StringBuilder::append)
                                                                .toString()
                                            )
                                        )
                                )
                ));
        }
    }
}

final class WaveShapeTest {
    @ParameterizedTest(name = "WaveShape.{0}.stringValue() = \"{1}\"")
    @ArgumentsSource(WaveShapeTestArgsProvider.EnumValuesWithUpperCaseStringValue.class)
    void waveShapeStringValueIsValid(WaveShape waveShape, String expectedStringValue) {
        final String waveStringValue = waveShape.stringValue();
        assertEquals(expectedStringValue, waveStringValue);
    }

    @ParameterizedTest(name = "WaveShape.{0}.stringValueAliases() = {1}")
    @ArgumentsSource(WaveShapeTestArgsProvider.EnumValuesWithUpperCaseStringAliases.class)
    void waveShapeStringValueAliasesIsValid(WaveShape waveShape, Set<String> expectedStringAliases) {
        final Set<String> waveStringAliases = waveShape.stringValueAliases();
        assertEquals(expectedStringAliases, waveStringAliases);
    }

    @ParameterizedTest(name = "WaveShape.parse(\"{0}\") returns optional of Wave.{1}")
    @ArgumentsSource(WaveShapeTestArgsProvider.EnumValuesWithUpperCaseStringAlias.class)
    void waveShapeParseUpperCaseStringReturnsOptionalOfWave(String waveString, WaveShape expectedWave) {
        final Optional<WaveShape> optionalWave = WaveShape.parse(waveString);
        assertThat(optionalWave.orElse(null), is(expectedWave));
    }

    @ParameterizedTest(name = "WaveShape.parse(\"{0}\") returns optional of Wave.{1}")
    @ArgumentsSource(WaveShapeTestArgsProvider.EnumValuesWithLowerCaseStringAlias.class)
    void waveShapeParseLowerCaseStringReturnsOptionalOfWave(String waveString, WaveShape expectedWave) {
        final Optional<WaveShape> optionalWave = WaveShape.parse(waveString);
        assertThat(optionalWave.orElse(null), is(expectedWave));
    }

    @ParameterizedTest(name = "WaveShape.{0}.prefixes(\"{1}\") returns true")
    @ArgumentsSource(WaveShapeTestArgsProvider.EnumValueWithPrefixedString.class)
    void prefixesReturnsTrueForPrefixedStrings(WaveShape waveShape, String prefixedString) {
        assertTrue(waveShape.prefixes(prefixedString),
                () -> "Wave.%s.prefixes(\"%s\") returns false".formatted(waveShape.name(), prefixedString));
    }

    @ParameterizedTest(name = "WaveShape.parsePrefix(\"{1}\") returns Optional<{0}>")
    @ArgumentsSource(WaveShapeTestArgsProvider.WaveShapeAliasStringEntryAndPrefixedString.class)
    void prefixesReturnsTrueForPrefixedStrings(Map.Entry<WaveShape, String> waveShapeStringAliasEntry, String prefixedString) {
        final Optional<Map.Entry<WaveShape, String>> waveShapeStringAliasEntryOptional = WaveShape.parsePrefix(prefixedString);

        assertTrue(
            waveShapeStringAliasEntryOptional.isPresent(),
            () -> "WaveShape.parsePrefix(\"%s\") returned empty Optional".formatted(prefixedString)
        );

        assertEquals(waveShapeStringAliasEntry, waveShapeStringAliasEntryOptional.get());
    }
}
