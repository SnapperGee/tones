package sogott.tones;

import java.util.List;

final record Scale(List<PitchClass> pitchClasses, int octave) {

    private final static int TOTAL_SEMITONES_IN_OCTAVE = 12;

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
        final int pitchClassDiff = targetSemitones - tonicSemitones >= 0
            ? targetSemitones - tonicSemitones
            : targetSemitones - tonicSemitones + TOTAL_SEMITONES_IN_OCTAVE;

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
