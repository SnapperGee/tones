package sogott.tones;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class CliOptionTestArgsProvider {
    static final class EnumValuesWithLongOpt implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(CliOption.BPM, "bpm"),
                    arguments(CliOption.NOTE_BEAT_VALUE, "note-beat-value"),
                    arguments(CliOption.WAVE, "wave"),
                    arguments(CliOption.OUT, "out"),
                    arguments(CliOption.VERSION, "version"),
                    arguments(CliOption.QUIET, "quiet"),
                    arguments(CliOption.HELP, "help"));
        }
    }

    static final class EnumValuesWithFlag implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(CliOption.BPM, "b"),
                    arguments(CliOption.NOTE_BEAT_VALUE, "n"),
                    arguments(CliOption.WAVE, "w"),
                    arguments(CliOption.OUT, "o"),
                    arguments(CliOption.VERSION, "v"),
                    arguments(CliOption.QUIET, "q"),
                    arguments(CliOption.HELP, "h"));
        }
    }

    static final class EnumValuesWithArgCount implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(CliOption.BPM, 1),
                    arguments(CliOption.NOTE_BEAT_VALUE, 1),
                    arguments(CliOption.WAVE, 1),
                    arguments(CliOption.OUT, 1),
                    arguments(CliOption.VERSION, 0),
                    arguments(CliOption.QUIET, 0),
                    arguments(CliOption.HELP, 0));
        }
    }

    static final class EnumValuesWithArgName implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(CliOption.BPM, "INTEGER"),
                    arguments(CliOption.NOTE_BEAT_VALUE, "INTEGER"),
                    arguments(CliOption.WAVE, "WAVE"),
                    arguments(CliOption.OUT, "PATH"),
                    arguments(CliOption.VERSION, null),
                    arguments(CliOption.QUIET, null),
                    arguments(CliOption.HELP, null));
        }
    }

    static final class EnumValuesWithType implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(CliOption.BPM, Integer.class),
                    arguments(CliOption.NOTE_BEAT_VALUE, Integer.class),
                    arguments(CliOption.WAVE, WaveShape.class),
                    arguments(CliOption.OUT, Path.class),
                    arguments(CliOption.VERSION, String.class),
                    arguments(CliOption.QUIET, String.class),
                    arguments(CliOption.HELP, String.class));
        }
    }

    static final class EnumValuesWithDescription implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    arguments(CliOption.BPM, Integer.class),
                    arguments(CliOption.NOTE_BEAT_VALUE, Integer.class),
                    arguments(CliOption.WAVE, WaveShape.class),
                    arguments(CliOption.OUT, Path.class),
                    arguments(CliOption.VERSION, String.class),
                    arguments(CliOption.QUIET, String.class),
                    arguments(CliOption.HELP, String.class));
        }
    }
}

final class CliOptionTest {
    @ParameterizedTest(name = "CliOption.{0}.value().getLongOpt() returns \"{1}\"")
    @ArgumentsSource(CliOptionTestArgsProvider.EnumValuesWithLongOpt.class)
    void cliOptionLongOptValue(CliOption cliOption, String longOpt) {
        assertEquals(longOpt, cliOption.value().getLongOpt());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getOpt() returns \"{1}\"")
    @ArgumentsSource(CliOptionTestArgsProvider.EnumValuesWithFlag.class)
    void cliOptionFlagValue(CliOption cliOption, String flag) {
        assertEquals(flag, cliOption.value().getOpt());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getArgs() returns {1}")
    @ArgumentsSource(CliOptionTestArgsProvider.EnumValuesWithArgCount.class)
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
    @ArgumentsSource(CliOptionTestArgsProvider.EnumValuesWithArgName.class)
    void cliOptionArgName(CliOption cliOption, String argName) {
        assertEquals(argName, cliOption.value().getArgName());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getType() returns {1}")
    @ArgumentsSource(CliOptionTestArgsProvider.EnumValuesWithType.class)
    void cliOptionArgName(CliOption cliOption, Class<?> type) {
        assertSame(type, cliOption.value().getType());
    }

    @ParameterizedTest(name = "CliOption.{0}.value().getDescirption() returns non blank String")
    @EnumSource(CliOption.class)
    void cliOptionDescriptionIsNonBlankString(CliOption cliOption) {
        assertThat(cliOption.value().getDescription(), is(not(blankOrNullString())));
    }

    @Test
    @DisplayName("CliOption.OUT converter passed pre-existing path throws FileAlreadyExistsException")
    void cliOptionOutConverterPassedPreExistingPathThrowsFileAlreadyExistsException(@TempDir Path tempDir) throws IOException {
        final Path filePath = tempDir.resolve("TEST_FILE.wav");
        filePath.toFile().createNewFile();
        assertThrows(FileAlreadyExistsException.class, () -> CliOption.OUT.value().getConverter().apply(filePath.toString()));
    }
}
