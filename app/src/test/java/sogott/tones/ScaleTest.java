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
import static org.hamcrest.Matchers.contains;

final class ScaleArgProvider {
    static final class Major {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Natural.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty())
                        }),

                        arguments(Scale.Major.Natural.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Natural.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty())
                        }),

                        arguments(Scale.Major.Natural.G, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP)
                        }));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Flat.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty())
                        }),

                        arguments(Scale.Major.Flat.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty())
                        }),

                        arguments(Scale.Major.Flat.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT)
                        }),

                        arguments(Scale.Major.Flat.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty())
                        }),

                        arguments(Scale.Major.Flat.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty())
                        }),

                        arguments(Scale.Major.Flat.G, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty())
                        }));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Major.Sharp.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.SHARP)
                        }),

                        arguments(Scale.Major.Sharp.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.SHARP)
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
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty())
                        }),

                        arguments(Scale.Minor.Natural.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty())
                        }),

                        arguments(Scale.Minor.Natural.C, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Natural.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty())
                        }),

                        arguments(Scale.Minor.Natural.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty())
                        }),

                        arguments(Scale.Minor.Natural.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Natural.G, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty())
                        }));
            }
        }

        static final class Flat implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Flat.A, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.B, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.D, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT)
                        }),

                        arguments(Scale.Minor.Flat.E, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT)
                        }));
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Scale.Minor.Sharp.F, new PitchClassAndAccidental[] {
                                new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                new PitchClassAndAccidental(PitchClass.E, Optional.empty())
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
            @ArgumentsSource(ScaleArgProvider.Major.Natural.class)
            void scaleMajorNaturalCorrectNotes(Scale.Major.Natural majorNaturalScale,
                    PitchClassAndAccidental[] pitchClassesAndAccidentals) {
                assertThat(majorNaturalScale.pitchClassesAndAccidentals(), contains(pitchClassesAndAccidentals));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Major.Flat.{0} contains {1}")
            @ArgumentsSource(ScaleArgProvider.Major.Flat.class)
            void scaleMajorFlatCorrectNotes(Scale.Major.Flat majorFlatScale,
                    PitchClassAndAccidental[] pitchClassesAndAccidentals) {
                assertThat(majorFlatScale.pitchClassesAndAccidentals(), contains(pitchClassesAndAccidentals));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Major.Sharp.{0} contains {1}")
            @ArgumentsSource(ScaleArgProvider.Major.Sharp.class)
            void scaleMajorSharpCorrectNotes(Scale.Major.Sharp majorSharpScale,
                    PitchClassAndAccidental[] pitchClassesAndAccidentals) {
                assertThat(majorSharpScale.pitchClassesAndAccidentals(), contains(pitchClassesAndAccidentals));
            }
        }
    }

    @Suite
    final static class Minor {
        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.Minor.Natural.{0} contains {1}")
            @ArgumentsSource(ScaleArgProvider.Minor.Natural.class)
            void scaleMinorNaturalCorrectNotes(
                    Scale.Minor.Natural minorNaturalScale,
                    PitchClassAndAccidental[] pitchClassesAndAccidentals) {
                assertThat(minorNaturalScale.pitchClassesAndAccidentals(), contains(pitchClassesAndAccidentals));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.Minor.Flat.{0} contains {1}")
            @ArgumentsSource(ScaleArgProvider.Minor.Flat.class)
            void scaleMinorFlatCorrectNotes(Scale.Minor.Flat minorFlatScale,
                    PitchClassAndAccidental[] pitchClassesAndAccidentals) {
                assertThat(minorFlatScale.pitchClassesAndAccidentals(), contains(pitchClassesAndAccidentals));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.Minor.Sharp.{0} contains {1}")
            @ArgumentsSource(ScaleArgProvider.Minor.Sharp.class)
            void scaleMinorSharpCorrectNotes(Scale.Minor.Sharp minorSharpScale,
                    PitchClassAndAccidental[] pitchClassesAndAccidentals) {
                assertThat(minorSharpScale.pitchClassesAndAccidentals(), contains(pitchClassesAndAccidentals));
            }
        }
    }
}
