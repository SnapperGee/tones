package sogott.beep;

import java.util.stream.Stream;
import java.util.Arrays;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class ArgProvider {
    final static RandomGenerator random = RandomGenerator.getDefault();

    private static Stream<Arguments> noteAccidentalAndOctave(int octaveOrigin, int octaveBound) {
        return Arrays.stream(Note.values()).flatMap(note -> Arrays.stream(Accidental.values())
                .map(accidental -> arguments(note, accidental, random.nextInt(octaveOrigin, octaveBound))));
    }

    private static Stream<Arguments> noteAndOctave(int octaveOrigin, int octaveBound) {
        return Arrays.stream(Note.values()).map(note -> arguments(note, random.nextInt(octaveOrigin, octaveBound)));
    }

    final static class Valid {
        final static class NoteAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAccidentalAndOctave(0, 13);
            }
        }

        final static class NoteAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAndOctave(0, 13);
            }
        }

        final static class AccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return Stream.of(
                        arguments(Accidental.SHARP, random.nextInt(13)),
                        arguments(Accidental.FLAT, random.nextInt(13)),
                        arguments(null, random.nextInt(13)));
            }
        }

        private Valid() {
        }
    }

    final static class Invalid {
        final static class NoteAccidentalAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAccidentalAndOctave(-12, 0);
            }
        }

        private Invalid() {
        }
    }

    private ArgProvider() {
    }
}

final class PitchTest {
    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) does not throw")
    @ArgumentsSource(ArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructorWithNoteNonNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, Accidental accidental,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(note, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, null, {1}) does not throw")
    @ArgumentsSource(ArgProvider.Valid.NoteAndOctave.class)
    void pitchConstructorWithNoteNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, int octave) {
        assertDoesNotThrow(() -> new Pitch(note, null, octave));
    }

    @ParameterizedTest(name = "new Pitch(null, Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(ArgProvider.Valid.AccidentalAndOctave.class)
    void pitchConstructorWithNullNoteAccidentalAndNonNegativeOctaveThrows(Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) throws IllegalArgumentException")
    @ArgumentsSource(ArgProvider.Invalid.NoteAccidentalAndNegativeOctave.class)
    void pitchConstructorWithNoteAccidentalAndNegativeOctaveThrows(Note note, Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(note, accidental, octave));
    }
}
