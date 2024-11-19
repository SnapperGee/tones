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
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.SHARP), Scale.Major.Natural.A),

                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.SHARP), Scale.Major.Natural.B),

                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Natural.C),

                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.D),

                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.SHARP), Scale.Major.Natural.E),

                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.F),

                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.G));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Major.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Flat.A),

                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Major.Flat.B),

                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.C),

                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Flat.D),

                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Major.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Major.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Major.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Major.Flat.E),

                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Major.Flat.G));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.SHARP), Scale.Major.Sharp.C),

                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Major.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.SHARP), Scale.Major.Sharp.F));
            }
        }
    }

    static final class Minor {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.A),

                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Minor.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Minor.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Natural.B),

                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Minor.Natural.C),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.C),

                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.D),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Natural.D),

                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Minor.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Natural.E),

                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Minor.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Minor.Natural.F),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Natural.F),

                        arguments(new PitchWithoutOctave(PitchClass.G, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Natural.G),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Natural.G));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.A),

                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.C, Optional.empty()), Scale.Minor.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.B),

                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Flat.D),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.FLAT), Scale.Minor.Flat.D),

                        arguments(new PitchWithoutOctave(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.F, Optional.empty()), Scale.Minor.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.B, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchWithoutOctave(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.E));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchWithoutOctave(PitchClass.F, Accidental.SHARP), Scale.Minor.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.G, Accidental.SHARP), Scale.Minor.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.A, Optional.empty()), Scale.Minor.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.B, Optional.empty()), Scale.Minor.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.C, Accidental.SHARP), Scale.Minor.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.D, Optional.empty()), Scale.Minor.Sharp.F),
                        arguments(new PitchWithoutOctave(PitchClass.E, Optional.empty()), Scale.Minor.Sharp.F));
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
                    Scale.Major.Natural majorNaturalScale) {
                assertThat(majorNaturalScale.notes(), hasItem(note));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Major.Flat.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Major.Flat.class)
            void scaleMajorFlatCorrectNotes(PitchWithoutOctave note,
                    Scale.Major.Flat majorFlatScale) {
                assertThat(majorFlatScale.notes(), hasItem(note));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Major.Sharp.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Major.Sharp.class)
            void scaleMajorSharpCorrectNotes(PitchWithoutOctave note,
                    Scale.Major.Sharp majorSharpScale) {
                assertThat(majorSharpScale.notes(), hasItem(note));
            }
        }
    }

    @Suite
    final static class Minor {
        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.Minor.Natural.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Minor.Natural.class)
            void scaleMinorNaturalCorrectNotes(PitchWithoutOctave note,
                    Scale.Minor.Natural minorNaturalScale) {
                assertThat(minorNaturalScale.notes(), hasItem(note));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Minor.Flat.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Minor.Flat.class)
            void scaleMinorFlatCorrectNotes(PitchWithoutOctave note,
                    Scale.Minor.Flat minorFlatScale) {
                assertThat(minorFlatScale.notes(), hasItem(note));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Minor.Sharp.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Minor.Sharp.class)
            void scaleMinorSharpCorrectNotes(PitchWithoutOctave note,
                    Scale.Minor.Sharp minorSharpScale) {
                assertThat(minorSharpScale.notes(), hasItem(note));
            }
        }
    }
}
