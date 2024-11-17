package sogott.tones;

import java.util.List;
import java.util.Optional;

final record PitchWithoutOctave(Note note, Optional<Accidental> accidental) {
    PitchWithoutOctave(Note note, Accidental accidental) {
        this(note, Optional.of(accidental));
    }
}

final class Scale {
    final static class Major {
        static enum Natural {
            A(List.of(
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Accidental.SHARP))),
            B(List.of(
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Accidental.SHARP),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Accidental.SHARP),
                    new PitchWithoutOctave(Note.A, Accidental.SHARP))),
            C(List.of(
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()))),
            D(List.of(
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP))),
            E(List.of(
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Accidental.SHARP),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Accidental.SHARP))),
            F(List.of(
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()))),
            G(List.of(
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP)));

            final private List<PitchWithoutOctave> _notes;

            private Natural(List<PitchWithoutOctave> notes) {
                this._notes = notes;
            }

            List<PitchWithoutOctave> notes() {
                return this._notes;
            }
        }

        static enum Flat {
            A(List.of(
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()))),
            B(List.of(
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()))),
            C(List.of(
                    new PitchWithoutOctave(Note.C, Accidental.FLAT),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Accidental.FLAT),
                    new PitchWithoutOctave(Note.G, Accidental.FLAT),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT))),
            D(List.of(
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Accidental.FLAT),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()))),
            E(List.of(
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()))),
            G(List.of(
                    new PitchWithoutOctave(Note.G, Accidental.FLAT),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Accidental.FLAT),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty())));

            final private List<PitchWithoutOctave> _notes;

            private Flat(List<PitchWithoutOctave> notes) {
                this._notes = notes;
            }

            List<PitchWithoutOctave> notes() {
                return this._notes;
            }
        }

        static enum Sharp {
            C(List.of(
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Accidental.SHARP),
                    new PitchWithoutOctave(Note.E, Accidental.SHARP),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Accidental.SHARP),
                    new PitchWithoutOctave(Note.A, Accidental.SHARP),
                    new PitchWithoutOctave(Note.B, Accidental.SHARP))),
            F(List.of(
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Accidental.SHARP),
                    new PitchWithoutOctave(Note.A, Accidental.SHARP),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Accidental.SHARP),
                    new PitchWithoutOctave(Note.E, Accidental.SHARP)));

            final private List<PitchWithoutOctave> _notes;

            private Sharp(List<PitchWithoutOctave> notes) {
                this._notes = notes;
            }

            List<PitchWithoutOctave> notes() {
                return this._notes;
            }
        }

        private Major() {
            throw new UnsupportedOperationException(
                    "%s is static an cannot be instantiated.".formatted(Major.class.getSimpleName()));
        }
    }

    final static class Minor {
        static enum Natural {
            A(List.of(
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()))),
            B(List.of(
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()))),
            C(List.of(
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT))),
            D(List.of(
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()))),
            E(List.of(
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()))),
            F(List.of(
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT))),
            G(List.of(
                    new PitchWithoutOctave(Note.G, Optional.empty()),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty()),
                    new PitchWithoutOctave(Note.F, Accidental.SHARP)));

            final private List<PitchWithoutOctave> _notes;

            private Natural(List<PitchWithoutOctave> notes) {
                this._notes = notes;
            }

            List<PitchWithoutOctave> notes() {
                return this._notes;
            }
        }

        static enum Flat {
            A(List.of(
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Accidental.FLAT),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Accidental.FLAT),
                    new PitchWithoutOctave(Note.G, Accidental.FLAT))),
            B(List.of(
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Optional.empty()),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Accidental.FLAT),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT))),
            D(List.of(
                    new PitchWithoutOctave(Note.D, Accidental.FLAT),
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Accidental.FLAT),
                    new PitchWithoutOctave(Note.G, Accidental.FLAT),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Accidental.FLAT))),
            E(List.of(
                    new PitchWithoutOctave(Note.E, Accidental.FLAT),
                    new PitchWithoutOctave(Note.F, Optional.empty()),
                    new PitchWithoutOctave(Note.G, Accidental.FLAT),
                    new PitchWithoutOctave(Note.A, Accidental.FLAT),
                    new PitchWithoutOctave(Note.B, Accidental.FLAT),
                    new PitchWithoutOctave(Note.C, Accidental.FLAT),
                    new PitchWithoutOctave(Note.D, Accidental.FLAT)));

            final private List<PitchWithoutOctave> _notes;

            private Flat(List<PitchWithoutOctave> notes) {
                this._notes = notes;
            }

            List<PitchWithoutOctave> notes() {
                return this._notes;
            }
        }

        static enum Sharp {
            F(List.of(
                    new PitchWithoutOctave(Note.F, Accidental.SHARP),
                    new PitchWithoutOctave(Note.G, Accidental.SHARP),
                    new PitchWithoutOctave(Note.A, Optional.empty()),
                    new PitchWithoutOctave(Note.B, Optional.empty()),
                    new PitchWithoutOctave(Note.C, Accidental.SHARP),
                    new PitchWithoutOctave(Note.D, Optional.empty()),
                    new PitchWithoutOctave(Note.E, Optional.empty())));

            final private List<PitchWithoutOctave> _notes;

            private Sharp(List<PitchWithoutOctave> notes) {
                this._notes = notes;
            }

            List<PitchWithoutOctave> notes() {
                return this._notes;
            }
        }

        private Minor() {
            throw new UnsupportedOperationException(
                    "%s is static an cannot be instantiated.".formatted(Minor.class.getSimpleName()));
        }
    }
}
