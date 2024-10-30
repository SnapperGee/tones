package sogott.beep;

import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class PitchArgProvider {
    private final static List<Note> notes = unmodifiableList(Arrays.asList(Note.values()));
    private final static List<Accidental> accidentals = unmodifiableList(Arrays.asList(Accidental.values()));

    final static RandomGenerator random = RandomGenerator.getDefault();

    private static Stream<? extends Arguments> noteAccidentalAndOctave(int octaveOrigin, int octaveBound) {
        return notes.stream().flatMap(note -> {
            final int octave = random.nextInt(octaveOrigin, octaveBound);
            return Stream.concat(
                    Stream.of(arguments(note, null, octave)),
                    accidentals.stream()
                            .map(accidental -> arguments(note, accidental, octave)));
        });
    }

    private static Stream<? extends Arguments> noteCharAccidentalCharAndOctave(int octaveOrigin, int octaveBound) {
        return notes.stream().flatMap(note -> accidentals.stream()
                .map(accidental -> arguments(note.charValue(), accidental.charValue(),
                        random.nextInt(octaveOrigin, octaveBound), note, accidental)));
    }

    private static Stream<? extends Arguments> invalidNoteCharInvalidAccidentalCharWithOctave(
            int octaveOrigin, int octaveBound) {
        return IntStream.concat(
                IntStream.concat(
                        IntStream.rangeClosed(32, 40),
                        IntStream.rangeClosed(72, 96)),
                IntStream.rangeClosed(104, 127))
                .mapToObj(nonNoteCodePoint -> IntStream.rangeClosed(0, 127)
                        .filter(cp -> cp != '+' && cp != '-')
                        .mapToObj(nonAccidentalCodePoint -> arguments((char) nonNoteCodePoint,
                                (char) nonAccidentalCodePoint,
                                random.nextInt(octaveOrigin, octaveBound))))
                .flatMap(s -> s);
    }

    private static Stream<? extends Arguments> validNoteCharInvalidAccidentalCharWithOctave(
            int octaveOrigin, int octaveBound) {
        return IntStream.concat(
                IntStream.rangeClosed(41, 47),
                IntStream.rangeClosed(97, 103))
                .mapToObj(noteCodePoint -> IntStream.rangeClosed(0, 127)
                        .filter(cp -> cp != Accidental.SHARP.charValue() && cp != Accidental.FLAT.charValue())
                        .mapToObj(nonAccidentalCodePoint -> arguments((char) noteCodePoint,
                                (char) nonAccidentalCodePoint,
                                random.nextInt(octaveOrigin, octaveBound))))
                .flatMap(s -> s);
    }

    private static Stream<? extends Arguments> invalidNoteCharValidAccidentalCharValidOctave(
            int octaveOrigin, int octaveBound) {
        return IntStream.concat(
                IntStream.concat(
                        IntStream.rangeClosed(32, 40),
                        IntStream.rangeClosed(72, 96)),
                IntStream.rangeClosed(104, 127))
                .mapToObj(nonNoteCodePoint -> Stream.of(
                        arguments((char) nonNoteCodePoint, Accidental.SHARP.charValue(),
                                random.nextInt(octaveOrigin, octaveBound)),
                        arguments((char) nonNoteCodePoint, Accidental.FLAT.charValue(),
                                random.nextInt(octaveOrigin, octaveBound))))
                .flatMap(s -> s);
    }

    private static Stream<? extends Arguments> validNoteCharValidAccidentalCharWithOctave(
            int octaveOrigin, int octaveBound) {
        return IntStream.concat(
                IntStream.rangeClosed(32, 40),
                IntStream.rangeClosed(97, 103))
                .mapToObj(nonNoteCodePoint -> Stream.of(
                        arguments((char) nonNoteCodePoint, '+',
                                random.nextInt(octaveOrigin, octaveBound)),
                        arguments((char) nonNoteCodePoint, '-',
                                random.nextInt(octaveOrigin, octaveBound))))
                .flatMap(s -> s);
    }

    private static Stream<? extends Arguments> invalidNoteCharWithOctave(
            int octaveOrigin, int octaveBound) {
        return IntStream.concat(
                IntStream.concat(
                        IntStream.rangeClosed(32, 40),
                        IntStream.rangeClosed(72, 96)),
                IntStream.rangeClosed(104, 127))
                .mapToObj(nonNoteCodePoint -> arguments((char) nonNoteCodePoint,
                        random.nextInt(octaveOrigin, octaveBound)));
    }

    private static Stream<? extends Arguments> noteCharAndOctave(int octaveOrigin, int octaveBound) {
        return notes.stream().map(note -> arguments(note.charValue(),
                random.nextInt(octaveOrigin, octaveBound), note));
    }

    private static Stream<? extends Arguments> differingNotesAccidentalsAndOctaves(int octaveOrigin, int octaveBound) {
        final List<Accidental> accidentals = Arrays.asList(Accidental.values());

        return notes.stream().flatMap(note -> accidentals.stream()
                .map(accidental -> {
                    final int indexOfNote = notes.indexOf(note);
                    final int indexOfDifferentNote = random.ints(0, notes.size())
                            .filter(i -> i != indexOfNote).findFirst().orElseThrow();
                    final Note aDifferentNote = notes.get(indexOfDifferentNote);
                    final Accidental differentAccidental = accidental == Accidental.SHARP ? Accidental.FLAT
                            : Accidental.SHARP;
                    final int randomInt = random.nextInt(octaveOrigin, octaveBound);
                    final int differentRandomInt = random.ints(octaveOrigin, octaveBound)
                            .filter(i -> i != randomInt).findFirst().orElseThrow();
                    return arguments(note, aDifferentNote, accidental, differentAccidental,
                            randomInt, differentRandomInt);
                }));
    }

    private static Stream<? extends Arguments> differingNotesWithAccidentalAndOctave(int octaveOrigin,
            int octaveBound) {
        return notes.stream().flatMap(note -> accidentals.stream()
                .map(accidental -> {
                    final int indexOfNote = notes.indexOf(note);
                    final int indexOfDifferentNote = random.ints(0, notes.size())
                            .filter(i -> i != indexOfNote).findFirst().orElseThrow();
                    final Note aDifferentNote = notes.get(indexOfDifferentNote);
                    return arguments(note, aDifferentNote, accidental,
                            random.nextInt(octaveOrigin, octaveBound));
                }));
    }

    private static Stream<? extends Arguments> noteWithDifferingAccidentalsAndOctave(int octaveOrigin,
            int octaveBound) {
        return notes.stream().flatMap(note -> accidentals.stream()
                .map(accidental -> {
                    final Accidental differentAccidental = accidental == Accidental.SHARP ? Accidental.FLAT
                            : Accidental.SHARP;
                    return arguments(note, accidental, differentAccidental,
                            random.nextInt(octaveOrigin, octaveBound));
                }));
    }

    private static Stream<? extends Arguments> noteAccidentalWithDifferingOctaves(int octaveOrigin, int octaveBound) {
        return notes.stream().flatMap(note -> accidentals.stream()
                .map(accidental -> {
                    final int randomInt = random.nextInt(octaveOrigin, octaveBound);
                    final int differentRandomInt = random.ints(octaveOrigin, octaveBound)
                            .filter(i -> i != randomInt).findFirst().orElseThrow();
                    return arguments(note, accidental, randomInt, differentRandomInt);
                }));
    }

    private static Stream<? extends Arguments> noteAndOctave(int octaveOrigin, int octaveBound) {
        return notes.stream().map(note -> arguments(note, random.nextInt(octaveOrigin, octaveBound)));
    }

    final static class Valid {
        final static class NoteAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAccidentalAndOctave(0, 13);
            }
        }

        final static class NoteCharAccidentalCharAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteCharAccidentalCharAndOctave(0, 13);
            }
        }

        final static class NoteCharAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteCharAndOctave(0, 13);
            }
        }

        final static class NoteAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAndOctave(0, 13);
            }
        }

        final static class AccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return Stream.of(
                        arguments(Accidental.SHARP, random.nextInt(13)),
                        arguments(Accidental.FLAT, random.nextInt(13)));
            }
        }

        final static class NoteAccidentalAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> {
                    final int octave = random.nextInt(13);
                    final String stringValueNoAccidental = "%c%d".formatted(note.charValue(),
                            octave);

                    return Stream.concat(
                            Stream.of(arguments(note, null, octave, stringValueNoAccidental)),
                            accidentals.stream()
                                    .map(accidental -> {
                                        final String stringValueWithAccidental = "%c%c%d".formatted(note.charValue(),
                                                accidental.charValue(),
                                                octave);
                                        return arguments(note, accidental, octave, stringValueWithAccidental);
                                    }));
                });
            }
        }

        final static class PitchStringValues implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> {
                    final int octave = random.nextInt(13);
                    final String stringValueNoAccidental = "%c%d".formatted(note.charValue(),
                            octave);
                    return Stream.concat(
                            Stream.of(arguments(stringValueNoAccidental)),
                            accidentals.stream()
                                    .map(accidental -> {
                                        final String stringValueWithAccidental = "%c%c%d".formatted(note.charValue(),
                                                accidental.charValue(),
                                                octave);
                                        return arguments(stringValueWithAccidental);
                                    }));
                });
            }
        }

        final static class DifferingNotesAccidentalsAndOctaves implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return differingNotesAccidentalsAndOctaves(0, 12);
            }
        }

        final static class DifferingNotesWithAccidentalAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return differingNotesWithAccidentalAndOctave(0, 12);
            }
        }

        final static class NoteWithDifferingAccidentalsAndOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteWithDifferingAccidentalsAndOctave(0, 12);
            }
        }

        final static class NoteAccidentalWithDifferingOctaves implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAccidentalWithDifferingOctaves(0, 12);
            }
        }

        final static class NoteAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().map(note -> {
                    final int octave = random.nextInt(13);
                    final String stringValue = "%c%d".formatted(note.charValue(), octave);
                    return arguments(note, octave, stringValue);
                });
            }
        }

        final static class PitchWithNoteAndOctaveStringValue implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().map(note -> {
                    final int octave = random.nextInt(13);
                    final String stringValue = "%c%d".formatted(note.charValue(), octave);
                    return arguments(note, octave, stringValue);
                });
            }
        }

        private Valid() {
        }
    }

    final static class Invalid {
        final static class NoteAccidentalAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAccidentalAndOctave(-12, 0);
            }
        }

        final static class NoteAndNegativeOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return noteAndOctave(-12, 0);
            }
        }

        final static class NoteCharsAccidentalCharsAndOctaves implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return invalidNoteCharInvalidAccidentalCharWithOctave(-12, 0);
            }
        }

        final static class ValidNoteCharInvalidAccidentalCharValidOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return validNoteCharInvalidAccidentalCharWithOctave(0, 13);
            }
        }

        final static class InvalidNoteCharValidAccidentalCharValidOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return invalidNoteCharValidAccidentalCharValidOctave(0, 13);
            }
        }

        final static class ValidNoteChaAndAccidentalCharWithInvalidOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return validNoteCharValidAccidentalCharWithOctave(-12, 0);
            }
        }

        final static class InvalidNoteCharWithValidOctave implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return invalidNoteCharWithOctave(0, 13);
            }
        }

        final static class PitchStringValues implements ArgumentsProvider {
            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
                return notes.stream().flatMap(note -> accidentals.stream()
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

        private Invalid() {
        }
    }

    private PitchArgProvider() {
    }
}

final class PitchTest {

    ////////////////////////
    // valid constructors //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) does not throw")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructorWithNoteNonNullAccidentalAndNonNegativeOctaveDoesNotThrow(Note note, Accidental accidental,
            int octave) {
        assertDoesNotThrow(() -> new Pitch(note, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, {1}) does not throw")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAndOctave.class)
    void pitchConstructorWithNoteAndNonNegativeOctaveDoesNotThrow(Note note, int octave) {
        assertDoesNotThrow(() -> new Pitch(note, octave));
    }

    //////////////////////////
    // invalid constructors //
    //////////////////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) throws IllegalArgumentException")
    @ArgumentsSource(PitchArgProvider.Invalid.NoteAccidentalAndNegativeOctave.class)
    void pitchConstructorWithNoteAccidentalAndNegativeOctaveThrows(Note note, Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(note, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(null, Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchArgProvider.Valid.AccidentalAndOctave.class)
    void pitchConstructorWithNullNoteAccidentalAndNonNegativeOctaveThrows(Accidental accidental, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, accidental, octave));
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, {1}) throws IllegalArgumentException")
    @ArgumentsSource(PitchArgProvider.Invalid.NoteAndNegativeOctave.class)
    void pitchConstructorWithNoteAndNegativeOctaveThrows(Note note, int octave) {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(note, octave));
    }

    @Test
    @DisplayName("new Pitch(null, 0<=#<=12) throws IllegalArgumentException")
    void pitchConstructorWithNullNoteAndNonNegativeOctaveThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Pitch(null, PitchArgProvider.random.nextInt(13)));
    }

    ////////////////////////
    // properties/getters //
    ////////////////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).note() is Note.{0}")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsNote(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final Note noteProperty = pitch.note();
        assertSame(note, noteProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).accidental() is Accidental.{1}")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsAccidental(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final Accidental accidentalProperty = pitch.accidental();
        assertSame(accidental, accidentalProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).octave() is {2}")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchConstructedWithNoteAccidentalAndOctavePropertyReturnsDuration(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final int octaveProperty = pitch.octave();
        assertSame(octave, octaveProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).stringValue() = \"{3}\"")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctaveStringValue.class)
    void pitchConstructedWithNoteAccidentalAndOctaveStringValuePropertyReturnsStringValue(Note note,
            Accidental accidental, int octave, String stringValue) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final String stringValueProperty = pitch.stringValue();
        assertEquals(stringValue, stringValueProperty);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, null, {1}).stringValue() = \"{2}\"")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAndOctaveStringValue.class)
    void pitchConstructedWithNoteAndOctaveStringValuePropertyReturnsStringValue(Note note, int octave,
            String stringValue) {
        final Pitch pitch = new Pitch(note, null, octave);
        final String stringValueProperty = pitch.stringValue();
        assertEquals(stringValue, stringValueProperty);
    }

    ////////////
    // equals //
    ////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) equals same returns true")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchEqualsSameReturnsTrue(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final boolean equalsResults = pitch.equals(pitch);
        assertTrue(equalsResults);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}) equals equivalent returns true")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void pitchEqualsEquivalentReturnsTrue(Note note, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(note, accidental, octave);
        final Pitch equalPitch = new Pitch(note, accidental, octave);
        final boolean equalsResults = aPitch.equals(equalPitch);
        assertTrue(equalsResults);
    }

    @ParameterizedTest(name = "new Pitch(<Note.{0}>, <Accidental.{2}>, <{4}>) does not equal new Pitch(<Note.{1}>, <Accidental.{3}>, <{5}>)")
    @ArgumentsSource(PitchArgProvider.Valid.DifferingNotesAccidentalsAndOctaves.class)
    void pitchDoesNotEqualPitchWithDifferingNoteAccidentalAndOctave(Note aNote,
            Note anotherNote, Accidental anAccidental, Accidental anotherAccidental,
            int anOctave, int anotherOctave) {
        final Pitch aPitch = new Pitch(aNote, anAccidental, anOctave);
        final Pitch notEqualPitch = new Pitch(anotherNote, anotherAccidental,
                anotherOctave);
        final boolean equalsResults = aPitch.equals(notEqualPitch);
        assertFalse(equalsResults);
    }

    @ParameterizedTest(name = "new Pitch(<Note.{0}>, Accidental.{2}, {3}) does not equal new Pitch(<Note.{1}>, Accidental.{2}, {3})")
    @ArgumentsSource(PitchArgProvider.Valid.DifferingNotesWithAccidentalAndOctave.class)
    void pitchDoesNotEqualPitchWithDifferingNotes(Note aNote, Note anotherNote,
            Accidental accidental, int octave) {
        final Pitch aPitch = new Pitch(aNote, accidental, octave);
        final Pitch notEqualPitch = new Pitch(anotherNote, accidental, octave);
        final boolean equalsResults = aPitch.equals(notEqualPitch);
        assertFalse(equalsResults);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, <Accidental.{1}>, {3}) does not equal new Pitch(Note.{0}, <Accidental.{2}>, {3})")
    @ArgumentsSource(PitchArgProvider.Valid.NoteWithDifferingAccidentalsAndOctave.class)
    void pitchDoesNotEqualPitchWithDifferingNotes(Note note, Accidental anAccidental, Accidental anotherAccidental,
            int octave) {
        final Pitch aPitch = new Pitch(note, anAccidental, octave);
        final Pitch notEqualPitch = new Pitch(note, anotherAccidental, octave);
        final boolean equalsResults = aPitch.equals(notEqualPitch);
        assertFalse(equalsResults);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, <{2}>) does not equal new Pitch(Note.{0}, Accidental.{1}, <{3}>)")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalWithDifferingOctaves.class)
    void pitchDoesNotEqualPitchWithDifferingOctaves(Note note, Accidental accidental, int anOctave, int anotherOctave) {
        final Pitch aPitch = new Pitch(note, accidental, anOctave);
        final Pitch notEqualPitch = new Pitch(note, accidental, anotherOctave);
        final boolean equalsResults = aPitch.equals(notEqualPitch);
        assertFalse(equalsResults);
    }

    //////////////
    // hashCode //
    //////////////

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).hashCode() equals Objects.hash(Note.{0}, Accidental.{1}, {2})")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void hashCodesComputedFromNoteAccidentalAndOctave(Note note, Accidental accidental,
            int octave) {
        final Pitch pitch = new Pitch(note, accidental, octave);
        final int hashCodeComputedFromNoteAccidentalAndOctave = Objects.hash(note, accidental, octave);
        assertEquals(hashCodeComputedFromNoteAccidentalAndOctave, pitch.hashCode());
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, {2}).hashCode() is same for equal object")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctave.class)
    void equivalentPitchesHaveSameHashCode(Note note, Accidental accidental,
            int octave) {
        final Pitch aPitch = new Pitch(note, accidental, octave);
        final Pitch anotherEqualPitch = new Pitch(note, accidental, octave);
        final boolean hashCodesAreEqual = aPitch.hashCode() == anotherEqualPitch.hashCode();
        assertTrue(hashCodesAreEqual);
    }

    @ParameterizedTest(name = "new Pitch(<Note.{0}>, <Accidental.{2}>, <{4}>).hashCode() does not equal new Pitch(<Note.{1}>, <Accidental.{3}>, <{5}>).hashCode()")
    @ArgumentsSource(PitchArgProvider.Valid.DifferingNotesAccidentalsAndOctaves.class)
    void pitchesWithDifferingNotesAccidentalsAndOctavesHashCodesAreNotEqual(Note aNote,
            Note anotherNote, Accidental anAccidental, Accidental anotherAccidental,
            int anOctave, int anotherOctave) {
        final Pitch aPitch = new Pitch(aNote, anAccidental, anOctave);
        final Pitch notEqualPitch = new Pitch(anotherNote, anotherAccidental,
                anotherOctave);
        final boolean hashCodesAReNotEqual = aPitch.hashCode() != notEqualPitch.hashCode();
        assertTrue(hashCodesAReNotEqual);
    }

    @ParameterizedTest(name = "new Pitch(<Note.{0}>, Accidental.{2}, {3}).hashCode() does not equal new Pitch(<Note.{1}>, Accidental.{2}, {3}).hashCode()")
    @ArgumentsSource(PitchArgProvider.Valid.DifferingNotesWithAccidentalAndOctave.class)
    void pitchesWithDifferingNotesHashCodesAreNotEqual(Note aNote, Note anotherNote,
            Accidental accidental, int octave) {
        final Pitch aPitch = new Pitch(aNote, accidental, octave);
        final Pitch notEqualPitch = new Pitch(anotherNote, accidental, octave);
        final boolean hashCodesAReNotEqual = aPitch.hashCode() != notEqualPitch.hashCode();
        assertTrue(hashCodesAReNotEqual);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, <Accidental.{1}>, {3}).hashCode() does not equal new Pitch(Note.{0}, <Accidental.{2}>, {3}).hashCode()")
    @ArgumentsSource(PitchArgProvider.Valid.NoteWithDifferingAccidentalsAndOctave.class)
    void pitchesWithDifferingAccidentalsHashCodesAreNotEqual(Note note, Accidental anAccidental,
            Accidental anotherAccidental,
            int octave) {
        final Pitch aPitch = new Pitch(note, anAccidental, octave);
        final Pitch notEqualPitch = new Pitch(note, anotherAccidental, octave);
        final boolean hashCodesAReNotEqual = aPitch.hashCode() != notEqualPitch.hashCode();
        assertTrue(hashCodesAReNotEqual);
    }

    @ParameterizedTest(name = "new Pitch(Note.{0}, Accidental.{1}, <{2}>).hashCode() does not equal new Pitch(Note.{0}, Accidental.{1}, <{3}>).hashCode()")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalWithDifferingOctaves.class)
    void pitchesWithDifferingOctavesHashCodesAreNotEqual(Note note, Accidental accidental, int anOctave,
            int anotherOctave) {
        final Pitch aPitch = new Pitch(note, accidental, anOctave);
        final Pitch notEqualPitch = new Pitch(note, accidental, anotherOctave);
        final boolean hashCodesAReNotEqual = aPitch.hashCode() != notEqualPitch.hashCode();
        assertTrue(hashCodesAReNotEqual);
    }

    ////////////////
    // isParsable //
    ////////////////

    @ParameterizedTest(name = "Pitch.isParsable(\"{0}\") returns true")
    @ArgumentsSource(PitchArgProvider.Valid.PitchStringValues.class)
    void isParsableOfValidReturnsTrue(String stringValue) {
        final boolean pitchIsParsable = Pitch.isParsable(stringValue);
        assertTrue(pitchIsParsable);
    }

    @ParameterizedTest(name = "Pitch.isParsable(\"{0}\") returns false")
    @ArgumentsSource(PitchArgProvider.Invalid.PitchStringValues.class)
    @NullAndEmptySource
    void isParsableOfInvalidReturnsFalse(String stringValue) {
        final boolean pitchIsParsable = Pitch.isParsable(stringValue);
        assertFalse(pitchIsParsable);
    }

    ///////////
    // parse //
    ///////////

    @ParameterizedTest(name = "Pitch.parse(\"{3}\") returns new Pitch(Note.{0}, Accidental.{1}, {2})")
    @ArgumentsSource(PitchArgProvider.Valid.NoteAccidentalAndOctaveStringValue.class)
    void staticPitchParseMethodInitializesObject(Note note,
            Accidental accidental, int octave, String stringValue) {
        final Optional<Pitch> pitchOptional = Pitch.parse(stringValue);
        assertTrue(pitchOptional.isPresent());
        final Pitch pitch = new Pitch(note, accidental, octave);
        assertEquals(pitch, pitchOptional.get());
    }

    @ParameterizedTest(name = "Pitch.parse(\"{0}\") returns empty Optional")
    @ArgumentsSource(PitchArgProvider.Invalid.PitchStringValues.class)
    @NullAndEmptySource
    void staticPitchParseMethodReturnsEmptyOptionalIfPassedInvalidString(String invalidStringValue) {
        final Optional<Pitch> pitchOptional = Pitch.parse(invalidStringValue);
        assertTrue(pitchOptional.isEmpty());
    }

    ////////////
    // create //
    ////////////

    @ParameterizedTest(name = "Pitch.create(''{0}'', ''{1}'', {2}) returns new Pitch(Note.{3}, Accidental.{4}, {2})")
    @ArgumentsSource(PitchArgProvider.Valid.NoteCharAccidentalCharAndOctave.class)
    void staticPitchCreateMethodPassedValidNoteCharAccidentalCharAndOctaveInstantiatesObject(char noteChar,
            char accidentalChar, int octave, Note note, Accidental accidental) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, accidentalChar, octave);
        assertTrue(pitchOptional.isPresent());
        final Pitch expectedPitchObject = new Pitch(note, accidental, octave);
        assertEquals(expectedPitchObject, pitchOptional.get());
    }

    @ParameterizedTest(name = "Pitch.create(''{0}'', {1}) returns new Pitch(Note.{2}, null, {1})")
    @ArgumentsSource(PitchArgProvider.Valid.NoteCharAndOctave.class)
    void staticPitchCreateMethodPassedValidNoteCharAndOctaveInstantiatesObject(
            char noteChar, int octave, Note note) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, octave);
        assertTrue(pitchOptional.isPresent());
        final Pitch expectedPitchObject = new Pitch(note, null, octave);
        assertEquals(expectedPitchObject, pitchOptional.get());
    }

    @ParameterizedTest(name = "Pitch.create(''{0}'', ''{1}'', {2}) returns empty optional")
    @ArgumentsSource(PitchArgProvider.Invalid.NoteCharsAccidentalCharsAndOctaves.class)
    void staticPitchCreateMethodPassedInvalidNoteCharsAccidentalCharsAndOctavesReturnsEmptyOptional(
            char noteChar, char accidentalChar, int octave) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, accidentalChar, octave);
        assertTrue(pitchOptional.isEmpty());
    }

    @ParameterizedTest(name = "Pitch.create(''{0}'', ''{1}'', {2}) returns empty optional")
    @ArgumentsSource(PitchArgProvider.Invalid.ValidNoteCharInvalidAccidentalCharValidOctave.class)
    void staticPitchCreateMethodPassedNoteCharAndInvalidAccidentalCharWithValidOctavesReturnsEmptyOptional(
            char noteChar, char accidentalChar, int octave) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, accidentalChar, octave);
        assertTrue(pitchOptional.isEmpty());
    }

    @ParameterizedTest(name = "Pitch.create(''{0}'', ''{1}'', {2}) returns empty optional")
    @ArgumentsSource(PitchArgProvider.Invalid.InvalidNoteCharValidAccidentalCharValidOctave.class)
    void staticPitchCreateMethodPassedInvalidNoteCharValidAccidentalCharValidOctavesReturnsEmptyOptional(
            char noteChar, char accidentalChar, int octave) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, accidentalChar, octave);
        assertTrue(pitchOptional.isEmpty());
    }

    @ParameterizedTest(name = "Pitch.create(''{0}'', ''{1}'', {2}) returns empty optional")
    @ArgumentsSource(PitchArgProvider.Invalid.ValidNoteChaAndAccidentalCharWithInvalidOctave.class)
    void staticPitchCreateMethodPassedValidNoteCharAndAccidentalCharWithInvalidOctaveReturnsEmptyOptional(
            char noteChar, char accidentalChar, int octave) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, accidentalChar, octave);
        assertTrue(pitchOptional.isEmpty());
    }

    @ParameterizedTest(name = "Pitch.create(''{0}'', {1}) returns empty optional")
    @ArgumentsSource(PitchArgProvider.Invalid.InvalidNoteCharWithValidOctave.class)
    void staticPitchCreateMethodPassedInvalidNoteCharAndValidOctaveReturnsEmptyOptional(
            char noteChar, int octave) {
        final Optional<Pitch> pitchOptional = Pitch.create(noteChar, octave);
        assertTrue(pitchOptional.isEmpty());
    }
}
