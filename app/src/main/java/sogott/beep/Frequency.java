package sogott.beep;

import java.util.OptionalDouble;

/**
 * Class consisting exclusively of static methods used for converting
 * {@link Pitch} and {@link Pitch} properties ({@link Note}, {@link Accidental},
 * and {@code int} octave) to {@code double} frequencies.
 *
 * @see Note
 * @see Accidental
 * @see Pitch
 */
final class Frequency {

    /**
     * Returns a {@code double} frequency derived from a {@link Note},
     * {@link Accidental} or {@code null}, and an {@code int} octave. If
     * {@code null} is passed as the {@link Accidental} argument, then that
     * represents a <i>natural</i> &natural; note (a note that's neither a
     * <i>sharp</i> &sharp; nor <i>flat</i> &flat;). The {@link Accidental}
     * argument is optional can be omitted to default it to {@code null} and
     * treat the note as a <i>natural</i> &natural;.
     *
     * @param note       The {@link Note} of the frequency to create.
     *
     * @param accidental The {@link Accidental} of the note of the frequency to
     *                   create.
     *
     * @param octave     The {@code int} octave of the note of the frequency to
     *                   create.
     *
     * @return A {@code double} frequency derived from the passed {@link Note},
     *         {@link Accidental} or {@code null}, and {@code int} octave.
     *
     * @throws IllegalArgumentException If the passed {@link Note} argument is
     *                                  {@code null} or the {@code int} octave
     *                                  argument is negative (less than 0).
     *
     * @see Note
     * @see Accidental
     */
    static double from(Note note, Accidental accidental, int octave) {
        if (note == null) {
            throw new IllegalArgumentException(
                    "Null %s.".formatted(Note.class.getSimpleName()));
        }

        if (octave < 0) {
            throw new IllegalArgumentException("Negative octave: %d".formatted(octave));
        }

        final int octaveOffset = (octave - 4) * 12;
        final double noteFrequency = 440
                * Math.pow(2,
                        (note.offset() + (accidental == null ? 0 : accidental.offset()) + octaveOffset)
                                / 12.0);
        return noteFrequency;
    }

    /**
     * Returns a {@code double} frequency derived from a {@link Note} and an
     * {@code int} octave to a {@code double}. The note is assumed to be
     * <i>natural</i> &natural; with no {@link Accidental} (a note that's
     * neither a <i>sharp</i> &sharp; nor <i>flat</i> &flat;).
     *
     * @param note   The {@link Note} of the frequency to create.
     *
     * @param octave The {@code int} octave of the note of the frequency to
     *               create.
     *
     * @return A {@code double} frequency derived from the passed {@link Note},
     *         and {@code int} octave.
     *
     * @throws IllegalArgumentException If the passed {@link Note} argument is
     *                                  {@code null} or the {@code int} octave
     *                                  argument is negative (less than 0).
     *
     * @see Note
     */
    static double from(Note note, int octave) {
        return from(note, null, octave);
    }

    /**
     * Derives a {@code double} frequency from a {@link Pitch} object.
     *
     * @param pitch The {@link Pitch} object to derive the {@code double}
     *              frequency from.
     *
     * @return A {@code double} frequency derived from the passed {@link Pitch}
     *         object.
     *
     * @throws IllegalArgumentException If passed {@link Pitch} argument is
     *                                  {@code null}.
     *
     * @see Pitch
     */
    static double from(Pitch pitch) {
        if (pitch == null) {
            throw new IllegalArgumentException(
                    "Null %s.".formatted(Pitch.class.getSimpleName()));
        }

        return from(pitch.note(), pitch.accidental(), pitch.octave());
    }

    /**
     * Attempts to derive {@code double} frequency from a {@link Note}
     * {@code char}, {@link Accidental} {@code char}, and an {@code int} octave.
     * If the passed arguments can't be converted to a frequency, then an empty
     * {@link OptionalDouble} is returned, otherwise it contains the frequency
     * {@code double} value. The passed {@link Accidental} {@code char} argument
     * is used to make the note a <i>sharp</i> &sharp; or <i>flat</i> &flat; or
     * can be omitted if it's a <i>natural</i> &natural;. The
     * {@code accidentalChar} argument is optional and can be omitted if the
     * note is <i>natural</i> &natural; with no {@link Accidental} (neither a
     * <i>sharp</i> &sharp; nor <i>flat</i> &flat;).
     *
     * @param noteChar       The {@link Note} {@code char} of the frequency to
     *                       create.
     *
     * @param accidentalChar The {@link Accidental} {@code char} of the note of the
     *                       frequency to create.
     *
     * @param octave         The {@code int} octave of the note of the frequency to
     *                       create.
     *
     * @return A {@code double} frequency derived from the passed {@link Note}
     *         {@code char}, {@link Accidental} {@code char}, and {@code int}
     *         octave.
     *
     * @see Note
     * @see Accidental
     */
    static OptionalDouble from(char noteChar, char accidentalChar, int octave) {
        return Note.fromChar(noteChar)
                .flatMap(note -> Accidental.fromChar(accidentalChar)
                        .map(accidental -> OptionalDouble.of(from(note, accidental, octave))))
                .orElse(OptionalDouble.empty());
    }

    /**
     * Attempts to derive a {@code double} frequency from a {@link Note}
     * {@code char} and {@code int} octave. If the passed arguments can't be
     * converted to a frequency, then an empty {@link OptionalDouble} is returned,
     * otherwise it contains the frequency {@code double} value. The note is
     * assumed to be <i>natural</i> &natural; with no {@link Accidental} (a note
     * that's neither a <i>sharp</i> &sharp; nor <i>flat</i> &flat;).
     *
     * @param noteChar The {@link Note} {@code char} of the frequency to create.
     *
     * @param octave   The {@code int} octave of the note of the frequency to
     *                 create.
     *
     * @return An {@link OptionalDouble} containing the {@code double} frequency
     *         derived from the passed {@link Note} {@code char},
     *         {@link Accidental} {@code char}, and {@code int} octave.
     *
     * @see Note
     */
    static OptionalDouble from(char noteChar, int octave) {
        return Note.fromChar(noteChar)
                .map(note -> OptionalDouble.of(from(note, octave)))
                .orElse(OptionalDouble.empty());
    }

    /**
     * Attempts to derive a {@code double} frequency from a {@link Pitch}
     * {@code String}. If the passed {@code String} can't be converted to a
     * frequency, then an empty {@link OptionalDouble} is returned, otherwise it
     * contains the frequency {@code double} value.
     *
     * @param aString The {@code String} to attempt to derive a
     *                {@code double} frequency from.
     *
     * @return A {@code double} frequency derived from the passed {@code String}.
     *
     * @see Pitch
     */
    static OptionalDouble parse(String aString) {
        return Pitch.parse(aString)
                .map(pitch -> OptionalDouble.of(from(pitch)))
                .orElse(OptionalDouble.empty());
    }

    private Frequency() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated.".formatted(Frequency.class.getSimpleName()));
    }
}
