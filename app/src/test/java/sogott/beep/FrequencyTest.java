package sogott.beep;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class FrequencyArgProvider {
    final static class NoteAccidentalOctaveFrequency implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    arguments(Note.A, Accidental.FLAT, 0, 25.96),
                    arguments(Note.A, null, 0, 27.5),
                    arguments(Note.A, Accidental.SHARP, 0, 29.14),
                    arguments(Note.A, Accidental.FLAT, 1, 51.91),
                    arguments(Note.A, null, 1, 55),
                    arguments(Note.A, Accidental.SHARP, 1, 58.27),
                    arguments(Note.A, Accidental.FLAT, 2, 103.83),
                    arguments(Note.A, null, 2, 110),
                    arguments(Note.A, Accidental.SHARP, 2, 116.54),
                    arguments(Note.A, Accidental.FLAT, 3, 207.65),
                    arguments(Note.A, null, 3, 220),
                    arguments(Note.A, Accidental.SHARP, 3, 233.08),
                    arguments(Note.A, Accidental.FLAT, 4, 415.3),
                    arguments(Note.A, null, 4, 440),
                    arguments(Note.A, Accidental.SHARP, 4, 466.16),
                    arguments(Note.A, Accidental.FLAT, 5, 830.61),
                    arguments(Note.A, null, 5, 880),
                    arguments(Note.A, Accidental.SHARP, 5, 932.33),
                    arguments(Note.A, Accidental.FLAT, 6, 1661.22),
                    arguments(Note.A, null, 6, 1760),
                    arguments(Note.A, Accidental.SHARP, 6, 1864.66),
                    arguments(Note.A, Accidental.FLAT, 7, 3322.44),
                    arguments(Note.A, null, 7, 3520),
                    arguments(Note.A, Accidental.SHARP, 7, 3729.31),
                    arguments(Note.A, Accidental.FLAT, 8, 6644.88),
                    arguments(Note.A, null, 8, 7040),
                    arguments(Note.A, Accidental.SHARP, 8, 7458.62),
                    arguments(Note.B, Accidental.FLAT, 0, 29.14),
                    arguments(Note.B, null, 0, 30.87),
                    arguments(Note.B, Accidental.SHARP, 0, 32.7),
                    arguments(Note.B, Accidental.FLAT, 1, 58.27),
                    arguments(Note.B, null, 1, 61.74),
                    arguments(Note.B, Accidental.SHARP, 1, 65.41),
                    arguments(Note.B, Accidental.FLAT, 2, 116.54),
                    arguments(Note.B, null, 2, 123.47),
                    arguments(Note.B, Accidental.SHARP, 2, 130.81),
                    arguments(Note.B, Accidental.FLAT, 3, 233.08),
                    arguments(Note.B, null, 3, 246.95),
                    arguments(Note.B, Accidental.SHARP, 3, 261.63),
                    arguments(Note.B, Accidental.FLAT, 4, 466.16),
                    arguments(Note.B, null, 4, 493.88),
                    arguments(Note.B, Accidental.SHARP, 4, 523.25),
                    arguments(Note.B, Accidental.FLAT, 5, 932.33),
                    arguments(Note.B, null, 5, 987.77),
                    arguments(Note.B, Accidental.SHARP, 5, 1046.5),
                    arguments(Note.B, Accidental.FLAT, 6, 1864.66),
                    arguments(Note.B, null, 6, 1975.53),
                    arguments(Note.B, Accidental.SHARP, 6, 2093),
                    arguments(Note.B, Accidental.FLAT, 7, 3729.31),
                    arguments(Note.B, null, 7, 3951.07),
                    arguments(Note.B, Accidental.SHARP, 7, 4186.01),
                    arguments(Note.B, Accidental.FLAT, 8, 7458.62),
                    arguments(Note.B, null, 8, 7902.13),
                    arguments(Note.B, Accidental.SHARP, 8, 8372.02));
        }
    }
}

final class FrequencyTest {
    @ParameterizedTest(name = "Frequency.from({0}, {1}, {2}) returns ≅ {3}")
    @ArgumentsSource(FrequencyArgProvider.NoteAccidentalOctaveFrequency.class)
    void cliOptionLongOptValue(Note note, Accidental accidental, int octave, double expectedFrequency) {
        final double frequency = Frequency.from(note, accidental, octave);
        assertThat(frequency, is(closeTo(expectedFrequency, 0.01)));
    }
}
