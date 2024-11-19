package sogott.tones;

import java.util.List;
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

interface Scale {
        List<PitchClassAndAccidental> pitchClassesAndAccidentals();

        final static class Major {
                static enum Natural implements Scale {
                        A(List.of(
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP))),
                        B(List.of(
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP))),
                        C(List.of(
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()))),
                        D(List.of(
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP))),
                        E(List.of(
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP))),
                        F(List.of(
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()))),
                        G(List.of(
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP)));

                        final private List<PitchClassAndAccidental> _pitchClassesAndAccidentals;

                        private Natural(List<PitchClassAndAccidental> pitchClassesAndAccidentals) {
                                this._pitchClassesAndAccidentals = pitchClassesAndAccidentals;
                        }

                        public List<PitchClassAndAccidental> pitchClassesAndAccidentals() {
                                return this._pitchClassesAndAccidentals;
                        }
                }

                static enum Flat implements Scale {
                        A(List.of(
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()))),
                        B(List.of(
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()))),
                        C(List.of(
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT))),
                        D(List.of(
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()))),
                        E(List.of(
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()))),
                        G(List.of(
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty())));

                        final private List<PitchClassAndAccidental> _pitchClassesAndAccidentals;

                        private Flat(List<PitchClassAndAccidental> pitchClassesAndAccidentals) {
                                this._pitchClassesAndAccidentals = pitchClassesAndAccidentals;
                        }

                        public List<PitchClassAndAccidental> pitchClassesAndAccidentals() {
                                return this._pitchClassesAndAccidentals;
                        }
                }

                static enum Sharp implements Scale {
                        C(List.of(
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.SHARP))),
                        F(List.of(
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.SHARP)));

                        final private List<PitchClassAndAccidental> _pitchClassesAndAccidentals;

                        private Sharp(List<PitchClassAndAccidental> pitchClassesAndAccidentals) {
                                this._pitchClassesAndAccidentals = pitchClassesAndAccidentals;
                        }

                        public List<PitchClassAndAccidental> pitchClassesAndAccidentals() {
                                return this._pitchClassesAndAccidentals;
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
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()))),
                        B(List.of(
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()))),
                        C(List.of(
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT))),
                        D(List.of(
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()))),
                        E(List.of(
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()))),
                        F(List.of(
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT))),
                        G(List.of(
                                        new PitchClassAndAccidental(PitchClass.G, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty())));

                        final private List<PitchClassAndAccidental> _pitchClassesAndAccidentals;

                        private Natural(List<PitchClassAndAccidental> pitchClassesAndAccidentals) {
                                this._pitchClassesAndAccidentals = pitchClassesAndAccidentals;
                        }

                        public List<PitchClassAndAccidental> pitchClassesAndAccidentals() {
                                return this._pitchClassesAndAccidentals;
                        }
                }

                static enum Flat implements Scale {
                        A(List.of(
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT))),
                        B(List.of(
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT))),
                        D(List.of(
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT))),
                        E(List.of(
                                        new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.F, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                                        new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT)));

                        final private List<PitchClassAndAccidental> _pitchClassesAndAccidentals;

                        private Flat(List<PitchClassAndAccidental> pitchClassesAndAccidentals) {
                                this._pitchClassesAndAccidentals = pitchClassesAndAccidentals;
                        }

                        public List<PitchClassAndAccidental> pitchClassesAndAccidentals() {
                                return this._pitchClassesAndAccidentals;
                        }
                }

                static enum Sharp implements Scale {
                        F(List.of(
                                        new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.A, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.B, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                                        new PitchClassAndAccidental(PitchClass.D, Optional.empty()),
                                        new PitchClassAndAccidental(PitchClass.E, Optional.empty())));

                        final private List<PitchClassAndAccidental> _pitchClassesAndAccidentals;

                        private Sharp(List<PitchClassAndAccidental> pitchClassesAndAccidentals) {
                                this._pitchClassesAndAccidentals = pitchClassesAndAccidentals;
                        }

                        public List<PitchClassAndAccidental> pitchClassesAndAccidentals() {
                                return this._pitchClassesAndAccidentals;
                        }
                }

                private Minor() {
                        throw new UnsupportedOperationException(
                                        Minor.class.getSimpleName() + " is static an cannot be instantiated.");
                }
        }
}
