package sogott.tones;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class CliOptionArgProvider {
    final static class EnumValuesWithLongOpt implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(CliOption.BPM, "bpm"),
                    arguments(CliOption.NOTE_BEAT_VALUE, "note-beat-value"),
                    arguments(CliOption.WAVE, "wave"),
                    arguments(CliOption.OUT, "out"),
                    arguments(CliOption.VERSION, "version"),
                    arguments(CliOption.HELP, "help"));
        }
    }

    final static class EnumValuesWithFlag implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(CliOption.BPM, "b"),
                    arguments(CliOption.NOTE_BEAT_VALUE, "n"),
                    arguments(CliOption.WAVE, "w"),
                    arguments(CliOption.OUT, "o"),
                    arguments(CliOption.VERSION, "v"),
                    arguments(CliOption.HELP, "h"));
        }
    }

    final static class EnumValuesWithArgCount implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(CliOption.BPM, 1),
                    arguments(CliOption.NOTE_BEAT_VALUE, 1),
                    arguments(CliOption.WAVE, 1),
                    arguments(CliOption.OUT, 1),
                    arguments(CliOption.VERSION, 0),
                    arguments(CliOption.HELP, 0));
        }
    }

    final static class EnumValuesWithArgName implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(CliOption.BPM, "INTEGER"),
                    arguments(CliOption.NOTE_BEAT_VALUE, "INTEGER"),
                    arguments(CliOption.WAVE, "WAVE"),
                    arguments(CliOption.OUT, "PATH"),
                    arguments(CliOption.VERSION, null),
                    arguments(CliOption.HELP, null));
        }
    }

    final static class EnumValuesWithType implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(CliOption.BPM, Integer.class),
                    arguments(CliOption.NOTE_BEAT_VALUE, Integer.class),
                    arguments(CliOption.WAVE, Wave.class),
                    arguments(CliOption.OUT, Path.class),
                    arguments(CliOption.VERSION, String.class),
                    arguments(CliOption.HELP, String.class));
        }
    }

    final static class EnumValuesWithDescription implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(CliOption.BPM, Integer.class),
                    arguments(CliOption.NOTE_BEAT_VALUE, Integer.class),
                    arguments(CliOption.WAVE, Wave.class),
                    arguments(CliOption.OUT, Path.class),
                    arguments(CliOption.VERSION, String.class),
                    arguments(CliOption.HELP, String.class));
        }
    }
}

final class CliOptionTest {
    @ParameterizedTest(name = "CliOption.{0}.value().getLongOpt() returns \"{1}\"")
    @ArgumentsSource(CliOptionArgProvider.EnumValuesWithLongOpt.class)
    void cliOptionLongOptValue(CliOption cliOption, String longOpt) {
        assertEquals(longOpt, cliOption.value().getLongOpt());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getOpt() returns \"{1}\"")
    @ArgumentsSource(CliOptionArgProvider.EnumValuesWithFlag.class)
    void cliOptionFlagValue(CliOption cliOption, String flag) {
        assertEquals(flag, cliOption.value().getOpt());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getArgs() returns {1}")
    @ArgumentsSource(CliOptionArgProvider.EnumValuesWithArgCount.class)
    void cliOptionArgCount(CliOption cliOption, int numOfArgs) {
        assertEquals(numOfArgs, cliOption.value().getArgs());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().isRequired() returns false")
    @EnumSource(CliOption.class)
    void cliOptionIsNotRequired(CliOption cliOption) {
        assertFalse(cliOption.value().isRequired());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().hasOptionalArg() returns false")
    @EnumSource(CliOption.class)
    void cliOptionHasNoOptionalArg(CliOption cliOption) {
        assertFalse(cliOption.value().hasOptionalArg());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getArgName() returns \"{1}\"")
    @ArgumentsSource(CliOptionArgProvider.EnumValuesWithArgName.class)
    void cliOptionArgName(CliOption cliOption, String argName) {
        assertEquals(argName, cliOption.value().getArgName());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getType() returns {1}")
    @ArgumentsSource(CliOptionArgProvider.EnumValuesWithType.class)
    void cliOptionArgName(CliOption cliOption, Class<?> type) {
        assertSame(type, cliOption.value().getType());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getDescirption() returns non blank String")
    @EnumSource(CliOption.class)
    void cliOptionDescriptionIsNonBlankString(CliOption cliOption) {
        assertNotNull(cliOption.value().getDescription(),
                () -> "%s.%s.value().getDescirption() returns null"
                        .formatted(cliOption.getDeclaringClass().getSimpleName(), cliOption.name()));
        assertFalse(cliOption.value().getDescription().isBlank(),
                () -> "%s.%s.value().getDescirption() does not return non blank String"
                        .formatted(cliOption.getDeclaringClass().getSimpleName(), cliOption.name()));
    }
}
