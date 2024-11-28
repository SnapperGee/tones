package sogott.tones;

import java.util.Optional;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

final class ScaleTestArgsProvider {
    static final class Major {
        static final class Natural implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(
                            new PitchClass(PitchLetter.A, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.B, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.SHARP)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.C, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.D, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.E, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.F, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.G, Accidental.NATURAL),
                            new PitchClass[] {
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
                        arguments(
                            new PitchClass(PitchLetter.A, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.B, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.C, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.FLAT),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.D, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.E, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.G, Accidental.FLAT),
                            new PitchClass[] {
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
                        arguments(
                            new PitchClass(PitchLetter.C, Accidental.SHARP),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.SHARP),
                                new PitchClass(PitchLetter.E, Accidental.SHARP),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.SHARP),
                                new PitchClass(PitchLetter.A, Accidental.SHARP),
                                new PitchClass(PitchLetter.B, Accidental.SHARP)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.F, Accidental.SHARP),
                            new PitchClass[] {
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
                        arguments(
                            new PitchClass(PitchLetter.A, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.B, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.SHARP),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.C, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.D, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.NATURAL),
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.E, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.E, Accidental.NATURAL),
                                new PitchClass(PitchLetter.F, Accidental.SHARP),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.B, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.NATURAL)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.F, Accidental.NATURAL),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.NATURAL),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.G, Accidental.NATURAL),
                            new PitchClass[] {
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
                        arguments(
                            new PitchClass(PitchLetter.A, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.FLAT),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.B, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.B, Accidental.FLAT),
                                new PitchClass(PitchLetter.C, Accidental.NATURAL),
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.NATURAL),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.D, Accidental.FLAT),
                            new PitchClass[] {
                                new PitchClass(PitchLetter.D, Accidental.FLAT),
                                new PitchClass(PitchLetter.E, Accidental.FLAT),
                                new PitchClass(PitchLetter.F, Accidental.FLAT),
                                new PitchClass(PitchLetter.G, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.FLAT),
                                new PitchClass(PitchLetter.A, Accidental.NATURAL),
                                new PitchClass(PitchLetter.C, Accidental.FLAT)
                        }),

                        arguments(
                            new PitchClass(PitchLetter.E, Accidental.FLAT),
                            new PitchClass[] {
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
                        arguments(
                            new PitchClass(PitchLetter.F, Accidental.SHARP),
                            new PitchClass[] {
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
            @ParameterizedTest(name = "Scale.MAJOR.of({0}) contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Natural.class)
            void scaleMajorNaturalCorrectNotes(
                PitchClass rootPitchClass,
                PitchClass[] expectedPitchClasses) {
                    final List<PitchClass> pitchClasses = Scale.MAJOR.of(rootPitchClass);
                    assertThat(pitchClasses, contains(expectedPitchClasses));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.MAJOR.of({0}) contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Flat.class)
            void scaleMajorNaturalCorrectNotes(
                PitchClass rootPitchClass,
                PitchClass[] expectedPitchClasses) {
                    final List<PitchClass> pitchClasses = Scale.MAJOR.of(rootPitchClass);
                    assertThat(pitchClasses, contains(expectedPitchClasses));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.MAJOR.of({0}) contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Major.Sharp.class)
            void scaleMajorNaturalCorrectNotes(
                PitchClass rootPitchClass,
                PitchClass[] expectedPitchClasses) {
                    final List<PitchClass> pitchClasses = Scale.MAJOR.of(rootPitchClass);
                    assertThat(pitchClasses, contains(expectedPitchClasses));
            }
        }
    }

    @Suite
    final static class Minor {
        @Suite
        final static class Natural {
            @ParameterizedTest(name = "Scale.MINOR.of({0}) contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Natural.class)
            void scaleMajorNaturalCorrectNotes(
                PitchClass rootPitchClass,
                PitchClass[] expectedPitchClasses) {
                    final List<PitchClass> pitchClasses = Scale.MINOR.of(rootPitchClass);
                    assertThat(pitchClasses, contains(expectedPitchClasses));
            }
        }

        @Suite
        final static class Flat {
            @ParameterizedTest(name = "Scale.MINOR.of({0}) contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Flat.class)
            void scaleMajorNaturalCorrectNotes(
                PitchClass rootPitchClass,
                PitchClass[] expectedPitchClasses) {
                    final List<PitchClass> pitchClasses = Scale.MINOR.of(rootPitchClass);
                    assertThat(pitchClasses, contains(expectedPitchClasses));
            }
        }

        @Suite
        final static class Sharp {
            @ParameterizedTest(name = "Scale.MINOR.of({0}) contains {1}")
            @ArgumentsSource(ScaleTestArgsProvider.Minor.Sharp.class)
            void scaleMajorNaturalCorrectNotes(
                PitchClass rootPitchClass,
                PitchClass[] expectedPitchClasses) {
                    final List<PitchClass> pitchClasses = Scale.MINOR.of(rootPitchClass);
                    assertThat(pitchClasses, contains(expectedPitchClasses));
            }
        }
    }
}
