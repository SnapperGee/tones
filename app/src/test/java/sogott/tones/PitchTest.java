package sogott.tones;

import java.util.stream.Stream;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.EmptySource;

import static java.util.Arrays.asList;
import static java.util.Objects.hash;
import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class PitchTestArgsProvider {
    final static List<PitchLetter> pitchLetters = unmodifiableList(asList(PitchLetter.values()));
    private final static List<Accidental> accidentals = unmodifiableList(asList(Accidental.values()));

    final static RandomGenerator random = RandomGenerator.getDefault();

    private static Stream<Arguments> pitchLetterAccidentalAndOctave(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> arguments(pitchLetter, accidental, random.nextInt(octaveOrigin, octaveBound))));
    }

    private static Stream<Arguments> differingNotesAccidentalsAndOctaves(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final int indexOfPitchLetter = pitchLetters.indexOf(pitchLetter);
                    final int indexOfDifferentPitchLetter = random.ints(0, pitchLetters.size())
                            .filter(i -> i != indexOfPitchLetter).findFirst().orElseThrow();
                    final PitchLetter differentPitchLetter = pitchLetters.get(indexOfDifferentPitchLetter);
                    final Accidental differentAccidental = accidental == Accidental.SHARP ? Accidental.FLAT
                            : Accidental.SHARP;
                    final int randomInt = random.nextInt(octaveOrigin, octaveBound);
                    final int differentRandomInt = random.ints(octaveOrigin, octaveBound)
                            .filter(i -> i != randomInt).findFirst().orElseThrow();
                    return arguments(pitchLetter, differentPitchLetter, accidental, differentAccidental,
                            randomInt, differentRandomInt);
                }));
    }

    private static Stream<Arguments> differingNotesWithAccidentalAndOctave(int octaveOrigin,
            int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final int indexOfPitchLetter = pitchLetters.indexOf(pitchLetter);
                    final int indexOfDifferentPitchLetter = random.ints(0, pitchLetters.size())
                            .filter(i -> i != indexOfPitchLetter).findFirst().orElseThrow();
                    final PitchLetter differentPitchLetter = pitchLetters.get(indexOfDifferentPitchLetter);
                    return arguments(pitchLetter, differentPitchLetter, accidental,
                            random.nextInt(octaveOrigin, octaveBound));
                }));
    }

    private static Stream<Arguments> noteWithDifferingAccidentalsAndOctave(int octaveOrigin,
            int octaveBound) {
        return pitchLetters.stream().flatMap(pitchLetter -> accidentals.stream()
                .map(accidental -> {
                    final Accidental differentAccidental = accidental == Accidental.SHARP ? Accidental.FLAT
                            : Accidental.SHARP;
                    return arguments(pitchLetter, accidental, differentAccidental,
                            random.nextInt(octaveOrigin, octaveBound));
                }));
    }

    private static Stream<Arguments> noteAccidentalWithDifferingOctaves(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream().flatMap(note -> accidentals.stream()
                .map(accidental -> {
                    final int randomInt = random.nextInt(octaveOrigin, octaveBound);
                    final int differentRandomInt = random.ints(octaveOrigin, octaveBound)
                            .filter(i -> i != randomInt).findFirst().orElseThrow();
                    return arguments(note, accidental, randomInt, differentRandomInt);
                }));
    }

    private static Stream<Arguments> noteAndOctave(int octaveOrigin, int octaveBound) {
        return pitchLetters.stream()
                .map(pitchLetter -> arguments(pitchLetter, random.nextInt(octaveOrigin, octaveBound)));
    }

    final static class Valid {
        final static class NoteAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterAccidentalAndOctave(0, 13);
            }
        }

        final static class NoteAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return noteAndOctave(0, 13);
            }
        }

        final static class AccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return Stream.of(
                        arguments(Accidental.SHARP, random.nextInt(13)),
                        arguments(Accidental.NATURAL, random.nextInt(13)),
                        arguments(Accidental.FLAT, random.nextInt(13)));
            }
        }

        final static class NoteAccidentalAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(13);
                    final String stringValueNoAccidental = "%c%d".formatted(pitchLetter.charValue(),
                            octave);

                    return Stream.concat(
                            Stream.of(arguments(pitchLetter, Accidental.NATURAL, octave, stringValueNoAccidental)),
                            accidentals.stream()
                                    .filter(accidental -> accidental != Accidental.NATURAL)
                                    .map(accidental -> {
                                        final String stringValueWithAccidental = "%c%c%d".formatted(
                                                pitchLetter.charValue(),
                                                accidental.charValue(),
                                                octave);
                                        return arguments(pitchLetter, accidental, octave, stringValueWithAccidental);
                                    }));
                });
            }
        }

        final static class PitchStringValues implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(pitchLetter -> {
                    final int octave = random.nextInt(13);
                    return accidentals.stream()
                            .map(accidental -> {
                                final String stringValueWithAccidental = accidental != Accidental.NATURAL
                                        ? "%c%c%d".formatted(
                                                pitchLetter.charValue(),
                                                accidental.charValue(),
                                                octave)
                                        : "%c%d".formatted(
                                                pitchLetter.charValue(),
                                                octave);
                                return arguments(stringValueWithAccidental);
                            });
                });
            }
        }

        final static class DifferingNotesAccidentalsAndOctaves implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return differingNotesAccidentalsAndOctaves(0, 12);
            }
        }

        final static class DifferingNotesWithAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return differingNotesWithAccidentalAndOctave(0, 12);
            }
        }

        final static class NoteWithDifferingAccidentalsAndOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return noteWithDifferingAccidentalsAndOctave(0, 12);
            }
        }

        final static class NoteAccidentalWithDifferingOctaves implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return noteAccidentalWithDifferingOctaves(0, 12);
            }
        }
    }

    final static class Invalid {
        final static class NoteAccidentalAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetterAccidentalAndOctave(-12, 0);
            }
        }

        final static class NoteAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return noteAndOctave(-12, 0);
            }
        }

        final static class PitchStringValues implements ArgumentsProvider {
            @Override
            public Stream<Arguments> provideArguments(ExtensionContext context) {
                return pitchLetters.stream().flatMap(note -> accidentals.stream()
                        .flatMap(accidental -> {
                            final int octave = random.nextInt(13);
                            final String firstString = "%1$c%1$c%2$c%3$d".formatted(note.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String secondString = "%1$c%2$c%2$c%3$d".formatted(note.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String thirdString = "%3$d%1$c%2$c%3$d".formatted(note.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String fourthString = "%1$c%1$c%2$d".formatted(note.charValue(),
                                    octave);
                            final String fifthString = "%1$c%2$c%3$d%1$c".formatted(note.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String sixthString = "%1$c%2$d%1$c".formatted(note.charValue(),
                                    octave);
                            final String seventhString = "%c %c %d".formatted(note.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String eighthString = "%c %d".formatted(note.charValue(),
                                    octave);
                            final String validStringWithAccidental = "%c%c%d".formatted(note.charValue(),
                                    accidental.charValue(),
                                    octave);
                            final String validStringNoAccidental = "%c%d".formatted(note.charValue(),
                                    octave);
                            return Stream.of(arguments(Character.toString(note.charValue())),
                                    arguments(Character.toString(accidental.charValue())),
                                    arguments(Integer.toString(octave)), arguments(firstString),
                                    arguments("\u0020"),
                                    arguments(validStringWithAccidental + validStringWithAccidental),
                                    arguments(secondString), arguments(thirdString),
                                    arguments(fourthString), arguments(fifthString), arguments(sixthString),
                                    arguments(seventhString), arguments(eighthString),
                                    arguments(validStringNoAccidental + validStringNoAccidental));
                        }));
            }
        }
    }
}

final class PitchTest {

    ////////////////////////
    // valid constructors //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) does not throw")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructorWithNoteNonNullAccidentalAndNonNegativeOctaveDoesNotThrow(PitchLetter pitchLetter,
            Accidental accidental,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(pitchLetter, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, {1}) does not throw")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAndOctave.class)
    void pitchConstructorWithNoteAndNonNegativeOctaveDoesNotThrow(PitchLetter pitchLetter, int octave) {
        assertDoesNotThrow(() -> new Pitch(pitchLetter, octave));
    }

    //////////////////////////
    // invalid constructors //
    //////////////////////////

    @Test
    @DisplayName("new Pitch(PitchLetter, null, 0 <= # <= 12) throws IllegalArgumentException")
    void pitchConstructorWithNullAccidentalThrows() {
        final PitchLetter pitchLetter = PitchTestArgsProvider.pitchLetters.get(PitchTestArgsProvider.random.nextInt(PitchTestArgsProvider.pitchLetters.size()));
        assertThrows(IllegalArgumentException.class, () -> new Pitch(pitchLetter, null, PitchTestArgsProvider.random.nextInt(13)));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) throws IllegalArgumentException")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.NoteAccidentalAndNegativeOctave.class)
    void pitchConstructorWithNoteAccidentalAndNegativeOctaveThrows(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(pitchLetter, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(null, Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchTestArgsProvider.Valid.AccidentalAndOctave.class)
    void pitchConstructorWithNullNoteAccidentalAndNonNegativeOctaveThrows(Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.NoteAndNegativeOctave.class)
    void pitchConstructorWithNoteAndNegativeOctaveThrows(PitchLetter pitchLetter, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(pitchLetter, octave));
    }

    @Test
    @DisplayName("new Pitch((PitchLetter), null, 0<=#<=12) throws IllegalArgumentException")
    void pitchConstructorWithNullPitchLetterAndValidOctaveThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Pitch((PitchLetter) null, PitchTestArgsProvider.random.nextInt(13)));
    }

    ////////////////////////
    // properties/getters //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).letter() is PitchLetter.{0}")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsNote(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertSame(pitchLetter, pitch.letter());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).accidental() is Accidental.{1}")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsAccidental(PitchLetter pitchLetter,
            Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertSame(accidental, pitch.accidental());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).octave() is {2}")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsDuration(PitchLetter pitchLetter,
            Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertSame(octave, pitch.octave());
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).stringValue() = \"{3}\"")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctaveStringValue.class)
    void pitchConstructedWithNoteAccidentalAndOctaveStringValuePropertyReturnsStringValue(PitchLetter pitchLetter,
            Accidental accidental, int octave, String stringValue) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertEquals(stringValue, pitch.stringValue());
    }

    ////////////
    // equals //
    ////////////

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) equals same returns true")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchEqualsSameReturnsTrue(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        assertTrue(pitch.equals(pitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}) equals equivalent returns true")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchEqualsEquivalentReturnsTrue(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, octave);
        final Pitch equalPitch = new Pitch(pitchLetter, accidental, octave);
        assertTrue(aPitch.equals(equalPitch));
    }

    @ParameterizedTest(name = "new Pitch(<PitchLetter.{0}>, <Accidental.{2}>, <{4}>) does not equal new Pitch(<Note.{1}>, <Accidental.{3}>, <{5}>)")
    @ArgumentsSource(PitchTestArgsProvider.Valid.DifferingNotesAccidentalsAndOctaves.class)
    void pitchDoesNotEqualPitchWithDifferingNoteAccidentalAndOctave(PitchLetter aPitchLetter,
            PitchLetter anotherPitchLetter, Accidental anAccidental, Accidental anotherAccidental,
            int anOctave, int anotherOctave) {
        final Pitch aPitch = new Pitch(aPitchLetter, anAccidental, anOctave);
        final Pitch notEqualPitch = new Pitch(anotherPitchLetter, anotherAccidental,
                anotherOctave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    @ParameterizedTest(name = "new Pitch(<PitchLetter.{0}>, Accidental.{2}, {3}) does not equal new Pitch(<Note.{1}>, Accidental.{2}, {3})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.DifferingNotesWithAccidentalAndOctave.class)
    void pitchDoesNotEqualPitchWithDifferingNotes(PitchLetter aPitchLetter, PitchLetter anotherPitchLetter,
            Accidental accidental, int octave) {
        final Pitch aPitch = new Pitch(aPitchLetter, accidental, octave);
        final Pitch notEqualPitch = new Pitch(anotherPitchLetter, accidental, octave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, <Accidental.{1}>, {3}) does not equal new Pitch(Note.{0}, <Accidental.{2}>, {3})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteWithDifferingAccidentalsAndOctave.class)
    void pitchDoesNotEqualPitchWithDifferingNotes(PitchLetter pitchLetter, Accidental anAccidental,
            Accidental anotherAccidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, anAccidental, octave);
        final Pitch notEqualPitch = new Pitch(pitchLetter, anotherAccidental, octave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, <{2}>) does not equal new Pitch(Note.{0}, Accidental.{1}, <{3}>)")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalWithDifferingOctaves.class)
    void pitchDoesNotEqualPitchWithDifferingOctaves(PitchLetter pitchLetter, Accidental accidental, int anOctave,
            int anotherOctave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, anOctave);
        final Pitch notEqualPitch = new Pitch(pitchLetter, accidental, anotherOctave);
        assertFalse(aPitch.equals(notEqualPitch));
    }

    //////////////
    // hashCode //
    //////////////

    // @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).hashCode() equals Objects.hash(Note.{0}, Accidental.{1}, {2})")
    // @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    // void hashCodesComputedFromNoteAccidentalAndOctave(PitchLetter pitchLetter, Accidental accidental,
    //         int octave) {
    //     final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
    //     final int computedHashCode = hash(pitchLetter, accidental, octave);
    //     assertEquals(computedHashCode, pitch.hashCode());
    // }

    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).hashCode() is same for same object")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void samePitchesHaveSameHashCode(PitchLetter pitchLetter, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(pitchLetter, accidental, octave);
        assertTrue(aPitch.hashCode() == aPitch.hashCode());
    }

    // @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).hashCode() is same for equal object")
    // @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    // void equivalentPitchesHaveSameHashCode(PitchLetter pitchLetter, Accidental accidental,
    //         int octave) {
    //     final Pitch aPitch = new Pitch(pitchLetter, accidental, octave);
    //     final Pitch anotherEqualPitch = new Pitch(pitchLetter, accidental, octave);
    //     assertTrue(aPitch.hashCode() == anotherEqualPitch.hashCode());
    // }

    ////////////////
    // isParsable //
    ////////////////

    @ParameterizedTest(name = "Pitch.isParsable(\"{0}\") returns true")
    @ArgumentsSource(PitchTestArgsProvider.Valid.PitchStringValues.class)
    void isParsableOfValidReturnsTrue(String stringValue) {
        assertTrue(Pitch.isParsable(stringValue));
    }

    @ParameterizedTest(name = "Pitch.isParsable(\"{0}\") returns false")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.PitchStringValues.class)
    @NullAndEmptySource
    void isParsableOfInvalidReturnsFalse(String stringValue) {
        assertFalse(Pitch.isParsable(stringValue));
    }

    ///////////
    // parse //
    ///////////

    @ParameterizedTest(name = "Pitch.parse(\"{3}\") returns new Pitch(Note.{0}, Accidental.{1}, {2})")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctaveStringValue.class)
    void staticPitchParseMethodInitializesObject(PitchLetter note,
            Accidental accidental, int octave, String stringValue) {
        final Optional<Pitch> pitchOptional = Pitch.parse(stringValue);
        assertTrue(pitchOptional.isPresent());
        final Pitch pitch = new Pitch(note, accidental, octave);
        assertEquals(pitch, pitchOptional.get());
    }

    @ParameterizedTest(name = "Pitch.parse(\"{0}\") returns empty Optional")
    @ArgumentsSource(PitchTestArgsProvider.Invalid.PitchStringValues.class)
    @EmptySource
    void staticPitchParseMethodReturnsEmptyOptionalIfPassedInvalidString(String invalidStringValue) {
        final Optional<Pitch> pitchOptional = Pitch.parse(invalidStringValue);
        assertTrue(pitchOptional.isEmpty());
    }

    @Test
    @DisplayName("Pitch.parse(null) throws IllegalArgumentException")
    void staticPitchParseMethodThrowsWhenPassedNull() {
        assertThrows(IllegalArgumentException.class, () -> Pitch.parse(null));
    }

    //////////////
    // toString //
    //////////////

    // TODO: Update arguments provider to have toString
    @ParameterizedTest(name = "new Pitch(PitchLetter.{0}, Accidental.{1}, {2}).toString() returns pretty String")
    @ArgumentsSource(PitchTestArgsProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchToStringReturnsPrettyString(PitchLetter pitchLetter, Accidental accidental, int octave) {
        final Pitch pitch = new Pitch(pitchLetter, accidental, octave);
        final String stringValue = accidental == Accidental.NATURAL
                ? "%c%d".formatted(pitchLetter.charValue(), octave)
                : "%c%c%d".formatted(pitchLetter.charValue(), accidental.charValue(), octave);
        final String toString = "%s {note=%s, accidental=%s, octave=%d, stringValue=\"%s\"}".formatted(
                Pitch.class.getSimpleName(),
                pitchLetter.name(), accidental.name(), octave, stringValue);
        assertEquals(toString, pitch.toString());
    }
}
