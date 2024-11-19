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
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP), Scale.Major.Natural.A),

                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP), Scale.Major.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP), Scale.Major.Natural.B),

                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Natural.C),

                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.D),

                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Major.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP), Scale.Major.Natural.E),

                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.F),

                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Major.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Natural.G));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Major.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Flat.A),

                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Major.Flat.B),

                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.C),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.C),

                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Flat.D),

                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Major.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Major.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Major.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Major.Flat.E),

                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Major.Flat.G),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Major.Flat.G));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP), Scale.Major.Sharp.C),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.SHARP), Scale.Major.Sharp.C),

                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Major.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP), Scale.Major.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.SHARP), Scale.Major.Sharp.F));
            }
        }
    }

    static final class Minor {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Natural.A),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.A),

                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Minor.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Minor.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.B),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Natural.B),

                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Minor.Natural.C),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.C),

                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.D),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Natural.D),

                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Minor.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Natural.E),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Natural.E),

                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Minor.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Minor.Natural.F),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Natural.F),

                        arguments(new PitchClassAndAccidental(PitchClass.G, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Natural.G),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Natural.G));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT), Scale.Minor.Flat.A),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.A),

                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Optional.empty()), Scale.Minor.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.B),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.B),

                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Flat.D),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT), Scale.Minor.Flat.D),

                        arguments(new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.F, Optional.empty()), Scale.Minor.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT), Scale.Minor.Flat.E),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT), Scale.Minor.Flat.E));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP), Scale.Minor.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP), Scale.Minor.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.A, Optional.empty()), Scale.Minor.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.B, Optional.empty()), Scale.Minor.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP), Scale.Minor.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.D, Optional.empty()), Scale.Minor.Sharp.F),
                        arguments(new PitchClassAndAccidental(PitchClass.E, Optional.empty()), Scale.Minor.Sharp.F));
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
            void scaleMajorNaturalCorrectNotes(PitchClassAndAccidental pitchClassAndAccidental,
                    Scale.Major.Natural majorNaturalScale) {
                assertThat(majorNaturalScale.notes(), hasItem(pitchClassAndAccidental));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Major.Flat.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Major.Flat.class)
            void scaleMajorFlatCorrectNotes(PitchClassAndAccidental pitchClassAndAccidental,
                    Scale.Major.Flat majorFlatScale) {
                assertThat(majorFlatScale.notes(), hasItem(pitchClassAndAccidental));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Major.Sharp.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Major.Sharp.class)
            void scaleMajorSharpCorrectNotes(PitchClassAndAccidental pitchClassAndAccidental,
                    Scale.Major.Sharp majorSharpScale) {
                assertThat(majorSharpScale.notes(), hasItem(pitchClassAndAccidental));
            }
        }
    }

    @Suite
    final static class Minor {
        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.Minor.Natural.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Minor.Natural.class)
            void scaleMinorNaturalCorrectNotes(PitchClassAndAccidental pitchClassAndAccidental,
                    Scale.Minor.Natural minorNaturalScale) {
                assertThat(minorNaturalScale.notes(), hasItem(pitchClassAndAccidental));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Minor.Flat.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Minor.Flat.class)
            void scaleMinorFlatCorrectNotes(PitchClassAndAccidental pitchClassAndAccidental,
                    Scale.Minor.Flat minorFlatScale) {
                assertThat(minorFlatScale.notes(), hasItem(pitchClassAndAccidental));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Minor.Sharp.{1} contains {0}")
            @ArgumentsSource(ScaleArgProvider.Minor.Sharp.class)
            void scaleMinorSharpCorrectNotes(PitchClassAndAccidental pitchClassAndAccidental,
                    Scale.Minor.Sharp minorSharpScale) {
                assertThat(minorSharpScale.notes(), hasItem(pitchClassAndAccidental));
            }
        }
    }
}
