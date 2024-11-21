package sogott.tones;

import java.util.Optional;

final record PitchClassAndAccidental(PitchClass pitchClass, Optional<Accidental> accidental) {
    PitchClassAndAccidental {
        if (pitchClass == null) {
            throw new IllegalArgumentException("Null " + PitchClass.class.getSimpleName());
        }

        if (accidental == null) {
            throw new IllegalArgumentException(
                    "Null %s<%s>".formatted(Optional.class.getSimpleName(),
                            Accidental.class.getSimpleName()));
        }
    }

    PitchClassAndAccidental(PitchClass pitchClass, Accidental accidental) {
        this(pitchClass, Optional.of(accidental));
    }
}
