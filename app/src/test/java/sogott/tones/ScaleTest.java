package sogott.tones;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

final class ScaleTestArgsProvider {
    static final class Major {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Natural.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Natural.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Natural.G, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP)
                        }));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Flat.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT)
                        }),

                        arguments(Scale.Major.Flat.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.G, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL)
                        }));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Sharp.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Sharp.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.SHARP)
                        }));
            }
        }
    }

    static final class Minor {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Natural.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Natural.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Natural.G, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL)
                        }));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Flat.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT)
                        }));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Sharp.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                                new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL)
                        }));
            }
        }
    }
}

final class ScaleTest {
    @Suite
    final static class Major {
        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.Major.Natural.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Natural.class)
            void scaleMajorNaturalCorrectNotes(Scale.Major.Natural majorNaturalScale,
                    PitchClassAndAccidental[] pitchClasses) {
                assertThat(majorNaturalScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Major.Flat.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Flat.class)
            void scaleMajorFlatCorrectNotes(Scale.Major.Flat majorFlatScale,
                    PitchClassAndAccidental[] pitchClasses) {
                assertThat(majorFlatScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Major.Sharp.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Sharp.class)
            void scaleMajorSharpCorrectNotes(Scale.Major.Sharp majorSharpScale,
                    PitchClassAndAccidental[] pitchClasses) {
                assertThat(majorSharpScale.pitchClasses(), contains(pitchClasses));
            }
        }
    }

    @Suite
    final static class Minor {
        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.Minor.Natural.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Natural.class)
            void scaleMinorNaturalCorrectNotes(
                    Scale.Minor.Natural minorNaturalScale,
                    PitchClassAndAccidental[] pitchClasses) {
                assertThat(minorNaturalScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Minor.Flat.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Flat.class)
            void scaleMinorFlatCorrectNotes(Scale.Minor.Flat minorFlatScale,
                    PitchClassAndAccidental[] pitchClasses) {
                assertThat(minorFlatScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Minor.Sharp.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Sharp.class)
            void scaleMinorSharpCorrectNotes(Scale.Minor.Sharp minorSharpScale,
                    PitchClassAndAccidental[] pitchClasses) {
                assertThat(minorSharpScale.pitchClasses(), contains(pitchClasses));
            }
        }
    }
}
