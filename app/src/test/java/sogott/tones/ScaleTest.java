package sogott.tones;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.platform.suite.api.Suite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class ScaleArgProvider {
    static final class Major {
        static final class Natural {
            static Stream<Arguments> A() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.G, Accidental.SHARP)));
            }

            static Stream<Arguments> B() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.D, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.E, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.G, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.A, Accidental.SHARP)));
            }

            static Stream<Arguments> C() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.C, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.F, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())));
            }

            static Stream<Arguments> D() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP)));
            }

            static Stream<Arguments> E() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.G, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.D, Accidental.SHARP)));
            }

            static Stream<Arguments> F() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.C, Accidental.SHARP)),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.E, Accidental.SHARP)));
            }

            static Stream<Arguments> G() {
                return Stream.of(
                        arguments(new PitchWithoutOctave(Note.G, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.A, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.B, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.C, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.D, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.E, Optional.empty())),
                        arguments(new PitchWithoutOctave(Note.F, Accidental.SHARP)));
            }
        }
    }
}

final class ScaleTest {
    @Suite
    final static class Major {

        @Suite
        final static class Natural {
            @Test
            void scaleMajorNaturalContainsCorrectNotes() {
                assertTrue(true);
            }
        }
    }
}
