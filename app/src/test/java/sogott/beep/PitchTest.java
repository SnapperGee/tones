package sogott.beep;

import java.util.stream.Stream;
import java.util.Arrays;
import java.util.random.RandomGenerator;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class PitchArgProvider {
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
                        arguments(Accidental.FLAT, random.nextInt(13)));
            }
        }

        final static class NoteAccidentalAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return Arrays.stream(Note.values()).flatMap(note -> Arrays.stream(Accidental.values())
                        .map(accidental -> {
                            final int octave = random.nextInt(13);
                            final String stringValue = "%c%c%d".formatted(note.charValue(), accidental.charValue(),
                                    octave);
                            return arguments(note, accidental, octave, stringValue);
                        }));
            }
        }

        final static class NoteAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return Arrays.stream(Note.values()).map(note -> {
                    final int octave = random.nextInt(13);
                    final String stringValue = "%c%d".formatted(note.charValue(), octave);
                    return arguments(note, octave, stringValue);
                });
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

        final static class NoteAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAndOctave(-12, 0);
            }
        }

        private Invalid() {
        }
    }

    private PitchArgProvider() {
    }
}

final class PitchTest {

    ////////////////////////
    // Valid Constructors //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) does not throw")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructorWithNoteNonNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, Accidental accidental,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(note, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, null, {1}) does not throw")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAndOctave.class)
    void pitchConstructorWithNoteNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, int octave) {
        assertDoesNotThrow(() -> new Pitch(note, null, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, {1}) does not throw")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAndOctave.class)
    void pitchConstructorWithNoteAndNonNegativeOctaveDoesNotThrow(Note note, int octave) {
        assertDoesNotThrow(() -> new Pitch(note, octave));
    }

    //////////////////////////
    // Invalid Constructors //
    //////////////////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) throws IllegalArgumentException")
    @ArgumentsSource(PitchArgProvider.Invalid.NoteAccidentalAndNegativeOctave.class)
    void pitchConstructorWithNoteAccidentalAndNegativeOctaveThrows(Note note, Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(note, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(null, Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchArgProvider.Valid.AccidentalAndOctave.class)
    void pitchConstructorWithNullNoteAccidentalAndNonNegativeOctaveThrows(Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchArgProvider.Invalid.NoteAndNegativeOctave.class)
    void pitchConstructorWithNoteAndNegativeOctaveThrows(Note note, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(note, octave));
    }

    @RepeatedTest(5)
    void pitchConstructorWithNullNoteAndNonNegativeOctaveThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, PitchArgProvider.random.nextInt(13)));
    }

    ////////////////////////
    // Properties/Getters //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).note() is Note.{0}")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsNote(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final Note noteProperty = pitch.note();
        assertSame(note, noteProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).accidental() is Accidental.{1}")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsAccidental(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final Accidental accidentalProperty = pitch.accidental();
        assertSame(accidental, accidentalProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).octave() is {2}")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsDuration(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final int octaveProperty = pitch.octave();
        assertSame(octave, octaveProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).stringValue() = \"{3}\"")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctaveStringValue.class)
    void pitchConstructedWithNoteAccidentalAndOctaveStringValuePropertyReturnsStringValue(Note note,
            Accidental accidental,
            int octave, String stringValue) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final String stringValueProperty = pitch.stringValue();
        assertEquals(stringValue, stringValueProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, null, {1}).stringValue() = \"{2}\"")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAndOctaveStringValue.class)
    void pitchConstructedWithNoteAndOctaveStringValuePropertyReturnsStringValue(Note note, int octave,
            String stringValue) {
        final Pitch pitch = new Pitch(note, null, octave);
        final String stringValueProperty = pitch.stringValue();
        assertEquals(stringValue, stringValueProperty);
    }
}
