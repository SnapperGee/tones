package sogott.tones;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

final class ScaleArgProvider {
    static final class Major {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) throws Exception {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.G, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty()), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.D, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.G, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.A, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(Note.C, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.F, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.G, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.D, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(Note.F, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.B, Accidental.FLAT), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.C, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(Note.C, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP), Scale.Major.Natural.G));
            }
        }
    }
}

final class ScaleTest {
    @Suite
    final static class Major {

        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.Major.Natural.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Major.Natural.class)
            void scaleMajorNaturalCorrectNotes(PitchWithoutOctave note,
                    Scale.Major.Natural naturalScale) {
                assertThat(naturalScale.notes(), hasItem(note));
            }
        }
    }
}
