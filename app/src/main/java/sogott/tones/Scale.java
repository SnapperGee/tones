package sogott.tones;

import java.util.List;

final record Scale(List<PitchClass> pitchClasses, int octave) {
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
        final int octaveIncrement = Math.floorDiv(index, size);
        final int pitchClassIndex = Math.floorMod(index, size);
        final int computedOctave = this.octave + octaveIncrement;

        if (computedOctave < 0) {
            throw new IndexOutOfBoundsException(
                "Computed octave of index %d is negative: %d".formatted(index, computedOctave));
        }

        return new Pitch(this.pitchClasses.get(pitchClassIndex), computedOctave);
    }
}
