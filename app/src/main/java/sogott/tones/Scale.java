package sogott.tones;

import java.util.List;

interface Scale {
    List<PitchClassAndAccidental> pitchClasses();

    final static class Major {
        static enum Natural implements Scale {
            A(List.of(
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP))),
            B(List.of(
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.SHARP))),
            C(List.of(
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL))),
            D(List.of(
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP))),
            E(List.of(
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP))),
            F(List.of(
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL))),
            G(List.of(
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP)));

            final private List<PitchClassAndAccidental> _pitchClass;

            private Natural(List<PitchClassAndAccidental> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClassAndAccidental> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Flat implements Scale {
            A(List.of(
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL))),
            B(List.of(
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL))),
            C(List.of(
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT))),
            D(List.of(
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL))),
            E(List.of(
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL))),
            G(List.of(
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL)));

            final private List<PitchClassAndAccidental> _pitchClass;

            private Flat(List<PitchClassAndAccidental> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClassAndAccidental> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Sharp implements Scale {
            C(List.of(
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.SHARP))),
            F(List.of(
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.SHARP)));

            final private List<PitchClassAndAccidental> _pitchClass;

            private Sharp(List<PitchClassAndAccidental> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClassAndAccidental> pitchClasses() {
                return this._pitchClass;
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
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL))),
            B(List.of(
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL))),
            C(List.of(
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT))),
            D(List.of(
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL))),
            E(List.of(
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL))),
            F(List.of(
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT))),
            G(List.of(
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL)));

            final private List<PitchClassAndAccidental> _pitchClass;

            private Natural(List<PitchClassAndAccidental> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClassAndAccidental> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Flat implements Scale {
            A(List.of(
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT))),
            B(List.of(
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT))),
            D(List.of(
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT))),
            E(List.of(
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.FLAT),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.FLAT)));

            final private List<PitchClassAndAccidental> _pitchClass;

            private Flat(List<PitchClassAndAccidental> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClassAndAccidental> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Sharp implements Scale {
            F(List.of(
                    new PitchClassAndAccidental(PitchLetter.F, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.G, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.A, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.B, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.C, Accidental.SHARP),
                    new PitchClassAndAccidental(PitchLetter.D, Accidental.NATURAL),
                    new PitchClassAndAccidental(PitchLetter.E, Accidental.NATURAL)));

            final private List<PitchClassAndAccidental> _pitchClass;

            private Sharp(List<PitchClassAndAccidental> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClassAndAccidental> pitchClasses() {
                return this._pitchClass;
            }
        }

        private Minor() {
            throw new UnsupportedOperationException(
                    Minor.class.getSimpleName() + " is static an cannot be instantiated.");
        }
    }
}
