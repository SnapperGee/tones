package sogott.tones;

import java.util.List;
import java.util.Optional;

final record PitchWithoutOctave(PitchClass note, Optional<Accidental> accidental) {
        PitchWithoutOctave(PitchClass note, Accidental accidental) {
                this(note, Optional.of(accidental));
        }
}

interface Scale {
        List<PitchWithoutOctave> notes();

        final static class Major {
                static enum Natural implements Scale {
                        A(List.of(
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.SHARP))),
                        B(List.of(
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.SHARP))),
                        C(List.of(
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()))),
                        D(List.of(
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP))),
                        E(List.of(
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.SHARP))),
                        F(List.of(
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()))),
                        G(List.of(
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP)));

                        final private List<PitchWithoutOctave> _notes;

                        private Natural(List<PitchWithoutOctave> notes) {
                                this._notes = notes;
                        }

                        public List<PitchWithoutOctave> notes() {
                                return this._notes;
                        }
                }

                static enum Flat implements Scale {
                        A(List.of(
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()))),
                        B(List.of(
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()))),
                        C(List.of(
                                        new PitchWithoutOctave(PitchClass.C, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT))),
                        D(List.of(
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()))),
                        E(List.of(
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()))),
                        G(List.of(
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty())));

                        final private List<PitchWithoutOctave> _notes;

                        private Flat(List<PitchWithoutOctave> notes) {
                                this._notes = notes;
                        }

                        public List<PitchWithoutOctave> notes() {
                                return this._notes;
                        }
                }

                static enum Sharp implements Scale {
                        C(List.of(
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.SHARP))),
                        F(List.of(
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.SHARP)));

                        final private List<PitchWithoutOctave> _notes;

                        private Sharp(List<PitchWithoutOctave> notes) {
                                this._notes = notes;
                        }

                        public List<PitchWithoutOctave> notes() {
                                return this._notes;
                        }
                }

                private Major() {
                        throw new UnsupportedOperationException(
                                        Major.class.getSimpleName() + " is static an cannot be instantiated.");
                }
        }

        final static class Minor {
                static enum Natural implements Scale {
                        A(List.of(
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()))),
                        B(List.of(
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()))),
                        C(List.of(
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT))),
                        D(List.of(
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()))),
                        E(List.of(
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()))),
                        F(List.of(
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT))),
                        G(List.of(
                                        new PitchWithoutOctave(PitchClass.G, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty())));

                        final private List<PitchWithoutOctave> _notes;

                        private Natural(List<PitchWithoutOctave> notes) {
                                this._notes = notes;
                        }

                        public List<PitchWithoutOctave> notes() {
                                return this._notes;
                        }
                }

                static enum Flat implements Scale {
                        A(List.of(
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT))),
                        B(List.of(
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT))),
                        D(List.of(
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.FLAT))),
                        E(List.of(
                                        new PitchWithoutOctave(PitchClass.E, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.F, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.A, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.B, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.FLAT),
                                        new PitchWithoutOctave(PitchClass.D, Accidental.FLAT)));

                        final private List<PitchWithoutOctave> _notes;

                        private Flat(List<PitchWithoutOctave> notes) {
                                this._notes = notes;
                        }

                        public List<PitchWithoutOctave> notes() {
                                return this._notes;
                        }
                }

                static enum Sharp implements Scale {
                        F(List.of(
                                        new PitchWithoutOctave(PitchClass.F, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.G, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.A, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.B, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.C, Accidental.SHARP),
                                        new PitchWithoutOctave(PitchClass.D, Optional.empty()),
                                        new PitchWithoutOctave(PitchClass.E, Optional.empty())));

                        final private List<PitchWithoutOctave> _notes;

                        private Sharp(List<PitchWithoutOctave> notes) {
                                this._notes = notes;
                        }

                        public List<PitchWithoutOctave> notes() {
                                return this._notes;
                        }
                }

                private Minor() {
                        throw new UnsupportedOperationException(
                                        Minor.class.getSimpleName() + " is static an cannot be instantiated.");
                }
        }
}
