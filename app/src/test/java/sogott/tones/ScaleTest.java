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
                        arguments(Scale.Major.Natural.A, new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.B, new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.C, new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Natural.D, new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.E, new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.F, new PitchClass[] {
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Natural.G, new PitchClass[] {
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP)
                        }));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Flat.A, new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.B, new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.C, new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.FLAT),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT)
                        }),

                        arguments(Scale.Major.Flat.D, new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.E, new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL)
                        }),

                        arguments(Scale.Major.Flat.G, new PitchClass[] {
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.FLAT),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL)
                        }));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Sharp.C, new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP),
                                new PitchClass(PitchLetter.E, Accidental.SHARP),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.SHARP),
                                new PitchClass(PitchLetter.B, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Sharp.F, new PitchClass[] {
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.SHARP),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP),
                                new PitchClass(PitchLetter.E, Accidental.SHARP)
                        }));
            }
        }
    }

    static final class Minor {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Natural.A, new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.B, new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.C, new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Natural.D, new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.E, new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL)
                        }),

                        arguments(Scale.Minor.Natural.F, new PitchClass[] {
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Natural.G, new PitchClass[] {
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL)
                        }));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Flat.A, new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.FLAT),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.B, new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.D, new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.E, new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.FLAT),
                                new PitchClass(PitchLetter.D, Accidental.FLAT)
                        }));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Sharp.F, new PitchClass[] {
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL)
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
                    PitchClass[] pitchClasses) {
                assertThat(majorNaturalScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Major.Flat.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Flat.class)
            void scaleMajorFlatCorrectNotes(Scale.Major.Flat majorFlatScale,
                    PitchClass[] pitchClasses) {
                assertThat(majorFlatScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Major.Sharp.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Sharp.class)
            void scaleMajorSharpCorrectNotes(Scale.Major.Sharp majorSharpScale,
                    PitchClass[] pitchClasses) {
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
                    PitchClass[] pitchClasses) {
                assertThat(minorNaturalScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Minor.Flat.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Flat.class)
            void scaleMinorFlatCorrectNotes(Scale.Minor.Flat minorFlatScale,
                    PitchClass[] pitchClasses) {
                assertThat(minorFlatScale.pitchClasses(), contains(pitchClasses));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Minor.Sharp.{0} contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Sharp.class)
            void scaleMinorSharpCorrectNotes(Scale.Minor.Sharp minorSharpScale,
                    PitchClass[] pitchClasses) {
                assertThat(minorSharpScale.pitchClasses(), contains(pitchClasses));
            }
        }
    }
}
