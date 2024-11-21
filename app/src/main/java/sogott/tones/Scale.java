package sogott.tones;

import java.util.List;

interface Scale {
    List<PitchClassAndAccidental> pitchClassesAndAccidentals();

    final static class Major {
        static enum Natural implements Scale {
            A(List.of(
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP))),
            B(List.of(
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.SHARP))),
            C(List.of(
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL))),
            D(List.of(
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP))),
            E(List.of(
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.SHARP))),
            F(List.of(
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL))),
            G(List.of(
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
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
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL))),
            B(List.of(
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL))),
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
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL))),
            E(List.of(
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL))),
            G(List.of(
                    new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL)));

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
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
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
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL))),
            B(List.of(
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL))),
            C(List.of(
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT))),
            D(List.of(
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL))),
            E(List.of(
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL))),
            F(List.of(
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT))),
            G(List.of(
                    new PitchClassAndAccidental(PitchClass.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL)));

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
                    new PitchClassAndAccidental(PitchClass.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT))),
            D(List.of(
                    new PitchClassAndAccidental(PitchClass.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.FLAT))),
            E(List.of(
                    new PitchClassAndAccidental(PitchClass.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchClass.F, Accidental.NATURAL),
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
                    new PitchClassAndAccidental(PitchClass.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchClass.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchClass.E, Accidental.NATURAL)));

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
