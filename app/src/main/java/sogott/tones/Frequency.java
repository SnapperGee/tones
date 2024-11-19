package sogott.tones;

/**
 * Class consisting exclusively of static methods used for converting
 * {@link Pitch} and {@link Pitch} properties ({@link PitchClass},
 * {@link Accidental},
 * and {@code int} octave) to {@code double} frequencies.
 *
 * @see PitchClass
 * @see Accidental
 * @see Pitch
 */
final class Frequency {

    /**
     * Returns a {@code double} frequency derived from a {@link PitchClass},
     * {@link Accidental} or {@code null}, and an {@code int} octave. If
     * {@code null} is passed as the {@link Accidental} argument, then that
     * represents a <i>natural</i> &natural; note (a note that's neither a
     * <i>sharp</i> &sharp; nor <i>flat</i> &flat;). The {@link Accidental}
     * argument is optional can be omitted to default it to {@code null} and
     * treat the note as a <i>natural</i> &natural;.
     *
     * @param note       The {@link PitchClass} of the frequency to create.
     *
     * @param accidental The {@link Accidental} of the note of the frequency to
     *                   create.
     *
     * @param octave     The {@code int} octave of the note of the frequency to
     *                   create.
     *
     * @return A {@code double} frequency derived from the passed
     *         {@link PitchClass},
     *         {@link Accidental} or {@code null}, and {@code int} octave.
     *
     * @throws IllegalArgumentException If the passed {@link PitchClass} argument is
     *                                  {@code null} or the {@code int} octave
     *                                  argument is negative (less than 0).
     *
     * @see PitchClass
     * @see Accidental
     */
    static double from(PitchClass note, Accidental accidental, int octave) {
        if (note == null) {
            throw new IllegalArgumentException(
                    "Null %s.".formatted(PitchClass.class.getSimpleName()));
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
     * Returns a {@code double} frequency derived from a {@link PitchClass} and an
     * {@code int} octave to a {@code double}. The note is assumed to be
     * <i>natural</i> &natural; with no {@link Accidental} (a note that's
     * neither a <i>sharp</i> &sharp; nor <i>flat</i> &flat;).
     *
     * @param note   The {@link PitchClass} of the frequency to create.
     *
     * @param octave The {@code int} octave of the note of the frequency to
     *               create.
     *
     * @return A {@code double} frequency derived from the passed
     *         {@link PitchClass},
     *         and {@code int} octave.
     *
     * @throws IllegalArgumentException If the passed {@link PitchClass} argument is
     *                                  {@code null} or the {@code int} octave
     *                                  argument is negative (less than 0).
     *
     * @see PitchClass
     */
    static double from(PitchClass note, int octave) {
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

    private Frequency() {
        throw new UnsupportedOperationException(
                "%s is a static class and cannot be instantiated.".formatted(Frequency.class.getSimpleName()));
    }
}
