package sogott.tones;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

final class ScalePitchClassesTestArgsProvider {
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
                    })
                );
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
                        new PitchClass(PitchLetter.F, Accidental.FLAT),
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
                        new PitchClass(PitchLetter.G, Accidental.FLAT),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.G, Accidental.FLAT),
                            new PitchClass(PitchLetter.A, Accidental.FLAT),
                            new PitchClass(PitchLetter.B, Accidental.FLAT),
                            new PitchClass(PitchLetter.C, Accidental.FLAT),
                            new PitchClass(PitchLetter.D, Accidental.FLAT),
                            new PitchClass(PitchLetter.E, Accidental.FLAT),
                            new PitchClass(PitchLetter.F, Accidental.NATURAL)
                    })
                );
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                    arguments(
                        new PitchClass(PitchLetter.A, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.B, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.D, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.E, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.F, Accidental.SHARP),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.F, Accidental.SHARP),
                            new PitchClass(PitchLetter.G, Accidental.SHARP),
                            new PitchClass(PitchLetter.A, Accidental.SHARP),
                            new PitchClass(PitchLetter.B, Accidental.NATURAL),
                            new PitchClass(PitchLetter.C, Accidental.SHARP),
                            new PitchClass(PitchLetter.D, Accidental.SHARP),
                            new PitchClass(PitchLetter.E, Accidental.SHARP)
                    }),

                    arguments(
                        new PitchClass(PitchLetter.G, Accidental.SHARP),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.A, Accidental.FLAT),
                            new PitchClass(PitchLetter.B, Accidental.FLAT),
                            new PitchClass(PitchLetter.C, Accidental.NATURAL),
                            new PitchClass(PitchLetter.D, Accidental.FLAT),
                            new PitchClass(PitchLetter.E, Accidental.FLAT),
                            new PitchClass(PitchLetter.F, Accidental.NATURAL),
                            new PitchClass(PitchLetter.G, Accidental.NATURAL)
                    })
                );
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
                    })
                );
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
                        new PitchClass(PitchLetter.C, Accidental.FLAT),
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
                    }),

                    arguments(
                        new PitchClass(PitchLetter.F, Accidental.FLAT),
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
                        new PitchClass(PitchLetter.G, Accidental.FLAT),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.F, Accidental.SHARP),
                            new PitchClass(PitchLetter.G, Accidental.SHARP),
                            new PitchClass(PitchLetter.A, Accidental.NATURAL),
                            new PitchClass(PitchLetter.B, Accidental.NATURAL),
                            new PitchClass(PitchLetter.C, Accidental.SHARP),
                            new PitchClass(PitchLetter.D, Accidental.NATURAL),
                            new PitchClass(PitchLetter.E, Accidental.NATURAL)
                    })
                );
            }
        }

        static final class Sharp implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                    arguments(
                        new PitchClass(PitchLetter.A, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.B, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.C, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.D, Accidental.SHARP),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.E, Accidental.FLAT),
                            new PitchClass(PitchLetter.F, Accidental.NATURAL),
                            new PitchClass(PitchLetter.G, Accidental.FLAT),
                            new PitchClass(PitchLetter.A, Accidental.FLAT),
                            new PitchClass(PitchLetter.B, Accidental.FLAT),
                            new PitchClass(PitchLetter.C, Accidental.FLAT),
                            new PitchClass(PitchLetter.D, Accidental.FLAT)
                    }),

                    arguments(
                        new PitchClass(PitchLetter.E, Accidental.SHARP),
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
                        new PitchClass(PitchLetter.F, Accidental.SHARP),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.F, Accidental.SHARP),
                            new PitchClass(PitchLetter.G, Accidental.SHARP),
                            new PitchClass(PitchLetter.A, Accidental.NATURAL),
                            new PitchClass(PitchLetter.B, Accidental.NATURAL),
                            new PitchClass(PitchLetter.C, Accidental.SHARP),
                            new PitchClass(PitchLetter.D, Accidental.NATURAL),
                            new PitchClass(PitchLetter.E, Accidental.NATURAL)
                    }),

                    arguments(
                        new PitchClass(PitchLetter.G, Accidental.SHARP),
                        new PitchClass[] {
                            new PitchClass(PitchLetter.A, Accidental.FLAT),
                            new PitchClass(PitchLetter.B, Accidental.FLAT),
                            new PitchClass(PitchLetter.C, Accidental.FLAT),
                            new PitchClass(PitchLetter.D, Accidental.FLAT),
                            new PitchClass(PitchLetter.E, Accidental.FLAT),
                            new PitchClass(PitchLetter.F, Accidental.FLAT),
                            new PitchClass(PitchLetter.G, Accidental.FLAT)
                    })
                );
            }
        }
    }
}

final class ScalePitchClassesTest {

    @ParameterizedTest(name = "ScalePitchClasses.{0}.of(null) throws IllegalArgumentException")
    @EnumSource(ScalePitchClasses.class)
    void scaleOfNullThrowsIllegalArgumentException(ScalePitchClasses scalePitchClasses) {
            assertThrows(IllegalArgumentException.class, () -> scalePitchClasses.of(null));
    }

    @Suite
    @DisplayName("ScalePitchClasses.Major.Natural")
    final static class MajorNatural {
        @ParameterizedTest(name = "ScalePitchClasses.MAJOR.of({0}) contains {1}")
        @ArgumentsSource(ScalePitchClassesTestArgsProvider.Major.Natural.class)
        void scaleMajorNaturalCorrectNotes(
            PitchClass rootPitchClass,
            PitchClass[] expectedPitchClasses) {
                final List<PitchClass> pitchClasses = ScalePitchClasses.MAJOR.of(rootPitchClass);
                assertThat(pitchClasses, contains(expectedPitchClasses));
        }
    }

    @Suite
    @DisplayName("ScalePitchClasses.Major.Flat")
    final static class MajorFlat {
        @ParameterizedTest(name = "ScalePitchClasses.MAJOR.of({0}) contains {1}")
        @ArgumentsSource(ScalePitchClassesTestArgsProvider.Major.Flat.class)
        void scaleMajorNaturalCorrectNotes(
            PitchClass rootPitchClass,
            PitchClass[] expectedPitchClasses) {
                final List<PitchClass> pitchClasses = ScalePitchClasses.MAJOR.of(rootPitchClass);
                assertThat(pitchClasses, contains(expectedPitchClasses));
        }
    }

    @Suite
    @DisplayName("ScalePitchClasses.Major.Sharp")
    final static class MajorSharp {
        @ParameterizedTest(name = "ScalePitchClasses.MAJOR.of({0}) contains {1}")
        @ArgumentsSource(ScalePitchClassesTestArgsProvider.Major.Sharp.class)
        void scaleMajorNaturalCorrectNotes(
            PitchClass rootPitchClass,
            PitchClass[] expectedPitchClasses) {
                final List<PitchClass> pitchClasses = ScalePitchClasses.MAJOR.of(rootPitchClass);
                assertThat(pitchClasses, contains(expectedPitchClasses));
        }
    }

    @Suite
    @DisplayName("ScalePitchClasses.Minor.Natural")
    final static class MinorNatural {
        @ParameterizedTest(name = "ScalePitchClasses.MINOR.of({0}) contains {1}")
        @ArgumentsSource(ScalePitchClassesTestArgsProvider.Minor.Natural.class)
        void scaleMajorNaturalCorrectNotes(
            PitchClass rootPitchClass,
            PitchClass[] expectedPitchClasses) {
                final List<PitchClass> pitchClasses = ScalePitchClasses.MINOR.of(rootPitchClass);
                assertThat(pitchClasses, contains(expectedPitchClasses));
        }
    }

    @Suite
    @DisplayName("ScalePitchClasses.Minor.Flat")
    final static class MinorFlat {
        @ParameterizedTest(name = "ScalePitchClasses.MINOR.of({0}) contains {1}")
        @ArgumentsSource(ScalePitchClassesTestArgsProvider.Minor.Flat.class)
        void scaleMajorNaturalCorrectNotes(
            PitchClass rootPitchClass,
            PitchClass[] expectedPitchClasses) {
                final List<PitchClass> pitchClasses = ScalePitchClasses.MINOR.of(rootPitchClass);
                assertThat(pitchClasses, contains(expectedPitchClasses));
        }
    }

    @Suite
    @DisplayName("ScalePitchClasses.Minor.Sharp")
    final static class MinorSharp {
        @ParameterizedTest(name = "ScalePitchClasses.MINOR.of({0}) contains {1}")
        @ArgumentsSource(ScalePitchClassesTestArgsProvider.Minor.Sharp.class)
        void scaleMajorNaturalCorrectNotes(
            PitchClass rootPitchClass,
            PitchClass[] expectedPitchClasses) {
                final List<PitchClass> pitchClasses = ScalePitchClasses.MINOR.of(rootPitchClass);
                assertThat(pitchClasses, contains(expectedPitchClasses));
        }
    }
}
