package sogott.tones;

import java.util.List;

/**
 * Makes it possible to create a {@link Pitch} object via an index of a
 * {@code List} of {@link PitchClass}es and a root octave int, correctly
 * incrementing and decrementing the octave of the created {@link Pitch}
 * object relative to the index and root octave. Essentially this class
 * makes it possible to have a {@code List} of {@link PitchClass}es that
 * correspond to a musical scale and then create {@link Pitch}es based
 * on a numeric integer value.
 *
 * <p>
 * For instance lets say you wanted to make it possible to create the pitches
 * of the A minor scale in the 3rd octave. You could pass a list of the pitch
 * classes (in the correct order) for the A minor scale and the int 3 to the
 * constructor of this class and then simply use an integer to create the
 * pitches for the scale. For instance, after creating such an object, passing
 * `0` to the {@link #pitch(int)} method would return the scale's tonic, A3, and
 * then passing `1` to `6` would return the rest of the scale's notes, correctly
 * setting the octave to `4` for the pitches for notes C through G.
 *
 * @param pitchClasses The {@link PitchClass} {@code List} that {@link Pitch}es
 *                     are derived from.
 *
 * @param octave The root octave {@code int} applied to index 0 and used to
 *               derive the octave of the created {@link Pitch}es.
 *
 * @see PitchClass
 * @see Pitch
 */
final record Scale(List<PitchClass> pitchClasses, int octave) {

    private final static int TOTAL_SEMITONES_IN_OCTAVE = 12;

    /**
     * Makes it possible to create a {@link Pitch} object via an index of a
     * {@code List} of {@link PitchClass}es and a root octave int, correctly
     * incrementing and decrementing the octave of the created {@link Pitch}
     * object relative to the index and root octave. Essentially this class
     * makes it possible to have a {@code List} of {@link PitchClass}es that
     * correspond to a musical scale and then create {@link Pitch}es based
     * on a numeric integer value.
     *
     * <p>
     * For instance lets say you wanted to make it possible to create the pitches
     * of the A minor scale in the 3rd octave. You could pass a list of the pitch
     * classes (in the correct order) for the A minor scale and the int 3 to the
     * constructor of this class and then simply use an integer to create the
     * pitches for the scale. For instance, after creating such an object, passing
     * `0` to the {@link #pitch(int)} method would return the scale's tonic, A3,
     * and then passing `1` to `6` would return the rest of the scale's notes,
     * correctly setting the octave to `4` for the pitches for notes C through G.
     *
     * @param pitchClasses The {@link PitchClass} {@code List} that
     *                     {@link Pitch}es are derived from.
     *
     * @param octave The root octave {@code int} applied to index 0 and used to
     *               derive the octave of the created {@link Pitch}es.
     *
     * @throws IllegalArgumentException If the {@link PitchClass} {@code List}
     *         is {@code null} or empty or the {@code octave int} is less
     *         negative (less than 0).
     */
    Scale {
        if (pitchClasses == null) {
            throw new IllegalArgumentException("Null pitch classes.");
        }

        if (pitchClasses.isEmpty()) {
            throw new IllegalArgumentException("Empty pitch classes.");
        }

        if (octave < 0) {
            throw new IllegalArgumentException("Negative octave: " + octave);
        }
    }

    /**
     * Creates a {@link Pitch} object derived from the {@link PitchClass} at
     * the given {@code index} with it's octave correctly incremented or
     * decremented as needed.
     *
     * <p>
     * For instance lets say this {@link Scale} object contains a {@code List}
     * of the {@link PitchClass}es (in the correct order) for the A minor scale
     * and `3` as the root octave. Then an integer can conveniently be used to
     * create the {@link Pitch}es for the A minor scale. Passing `0` to this
     * method of such an object would return the scale's tonic, A3, and then
     * passing `1` to `6` would return the rest of the scale's notes, correctly
     * setting the octave to `4` for the pitches for notes C through G.
     *
     * @param index The index of the {@link PitchClass} to derive a
     *              {@link Pitch} object from with its octave correctly
     *              incremented or decremented as needed.
     *
     * @return A {@link Pitch} object derived from the {@link PitchClass} at the
     *         given {@code index} with it's octave correctly incremented or
     *         decremented as needed.
     *
     * @throws IndexOutOfBoundsException If the provided {@code index} argument
     *         would result in a {@link Pitch} object with a negative octave.
     */
    Pitch pitch(int index) {
        final int size = this.pitchClasses.size();

        // Get the tonic PitchClass and determine its semitones
        final PitchClass tonic = this.pitchClasses.get(0);
        final int tonicSemitones = tonic.letter().semitonesFromC();

        // Baseline semitones from C0 to the tonic pitch at the specified octave
        final int baselineSemitones = (this.octave * TOTAL_SEMITONES_IN_OCTAVE) + tonicSemitones;

        // Determine target pitch class for this index
        final int pitchClassIndex = Math.floorMod(index, size);
        final PitchClass targetPitchClass = this.pitchClasses.get(pitchClassIndex);

        // Calculate how many full cycles of the scale we've gone through
        final int cycle = Math.floorDiv(index, size);

        // Semitones for the target pitch class
        final int targetSemitones = targetPitchClass.letter().semitonesFromC();

        // Find the difference in semitones between tonic and target
        final int semitoneDif = targetSemitones - tonicSemitones;
        final int pitchClassDiff = semitoneDif >= 0 ? semitoneDif
            : semitoneDif + TOTAL_SEMITONES_IN_OCTAVE;

        // Total semitones from C0
        final int totalSemitones = baselineSemitones + pitchClassDiff + (cycle * TOTAL_SEMITONES_IN_OCTAVE);

        final int computedOctave = totalSemitones / TOTAL_SEMITONES_IN_OCTAVE;
        final int semitonesWithinOctave = totalSemitones % TOTAL_SEMITONES_IN_OCTAVE;

        if (computedOctave < 0) {
            throw new IndexOutOfBoundsException(
                "Computed octave of index %d is negative: %d".formatted(index, computedOctave));
        }

        // Find a PitchClass in the scale that matches semitonesWithinOctave
        // Since the scale defines the pitches available, we rely on these pitchClasses.
        final PitchClass computedPitchClass = pitchClassFromSemitonesWithinOctave(semitonesWithinOctave);

        return new Pitch(computedPitchClass, computedOctave);
    }

    private PitchClass pitchClassFromSemitonesWithinOctave(int semitones) {
        // Search through the pitchClasses list to find a PitchClass whose totalSemitonesFromC
        // matches the desired semitones within the octave.
        // This allows sharps/flats to be correctly identified.
        for (final PitchClass pitchClass : pitchClasses) {
            // Normalize in case we wrap around octaves:
            int pitchClassSemitones = pitchClass.letter().semitonesFromC() % TOTAL_SEMITONES_IN_OCTAVE;

            if (pitchClassSemitones < 0) {
                pitchClassSemitones += TOTAL_SEMITONES_IN_OCTAVE;
            }

            if (pitchClassSemitones == semitones) {
                return pitchClass;
            }
        }

        throw new IllegalArgumentException(
            "No %s in the scale corresponds to %d semitones above C.".formatted(PitchClass.class.getSimpleName(), semitones));
    }
}
