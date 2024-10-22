package sogott.beep;

import java.util.stream.Stream;
import java.util.Arrays;
import java.util.random.RandomGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class PitchTest {
    final static RandomGenerator random = RandomGenerator.getDefault();

    private static Stream<Arguments> accidentalAndNonNegativeOctave() {
        return Arrays.stream(Accidental.values()).map(accidental -> arguments(accidental, random.nextInt(13)));
    }

    private static Stream<Arguments> noteAccidentalAndOctave(int octaveOrigin, int octaveBound) {
        return Arrays.stream(Note.values()).flatMap(note -> Arrays.stream(Accidental.values())
                .map(accidental -> arguments(note, accidental, random.nextInt(octaveOrigin, octaveBound))));
    }

    private static Stream<Arguments> noteAndOctave(int octaveOrigin, int octaveBound) {
        return Arrays.stream(Note.values()).map(note -> arguments(note, random.nextInt(octaveOrigin, octaveBound)));
    }

    private static Stream<Arguments> noteAccidentalAndNonNegativeOctave() {
        return noteAccidentalAndOctave(0, 13);
    }

    private static Stream<Arguments> noteAndNonNegativeOctave() {
        return noteAndOctave(0, 13);
    }

    private static Stream<Arguments> noteAccidentalAndNegativeOctave() {
        return noteAccidentalAndOctave(-12, 0);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) does not throw")
    @MethodSource("noteAccidentalAndNonNegativeOctave")
    void pitchConstructorWithNoteNonNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, Accidental accidental,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(note, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, null, {1}) does not throw")
    @MethodSource("noteAndNonNegativeOctave")
    void pitchConstructorWithNoteNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, int octave) {
        assertDoesNotThrow(() -> new Pitch(note, null, octave));
    }

    @ParameterizedTest(name = "new Pitch(null, Note.{0}, {1}) throws IllegalArgumentException")
    @MethodSource("accidentalAndNonNegativeOctave")
    void pitchConstructorWithNullNoteAccidentalAndNonNegativeOctaveThrows(Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) throws IllegalArgumentException")
    @MethodSource("noteAccidentalAndNegativeOctave")
    void pitchConstructorWithNoteAccidentalAndNegativeOctaveThrows(Note note, Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(note, accidental, octave));
    }
}
