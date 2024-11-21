package sogott.tones;

import java.util.List;

interface Scale {
    List<PitchClass> pitchClasses();

    final static class Major {
        static enum Natural implements Scale {
            A(List.of(
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP))),
            B(List.of(
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.SHARP))),
            C(List.of(
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL))),
            D(List.of(
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP))),
            E(List.of(
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP))),
            F(List.of(
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL))),
            G(List.of(
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP)));

            final private List<PitchClass> _pitchClass;

            private Natural(List<PitchClass> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClass> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Flat implements Scale {
            A(List.of(
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL))),
            B(List.of(
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL))),
            C(List.of(
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT))),
            D(List.of(
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL))),
            E(List.of(
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL))),
            G(List.of(
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL)));

            final private List<PitchClass> _pitchClass;

            private Flat(List<PitchClass> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClass> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Sharp implements Scale {
            C(List.of(
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP),
                    new PitchClass(PitchLetter.E, Accidental.SHARP),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.SHARP),
                    new PitchClass(PitchLetter.B, Accidental.SHARP))),
            F(List.of(
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.SHARP),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP),
                    new PitchClass(PitchLetter.E, Accidental.SHARP)));

            final private List<PitchClass> _pitchClass;

            private Sharp(List<PitchClass> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClass> pitchClasses() {
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
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL))),
            B(List.of(
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL))),
            C(List.of(
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT))),
            D(List.of(
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL))),
            E(List.of(
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL))),
            F(List.of(
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT))),
            G(List.of(
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL)));

            final private List<PitchClass> _pitchClass;

            private Natural(List<PitchClass> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClass> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Flat implements Scale {
            A(List.of(
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT))),
            B(List.of(
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT))),
            D(List.of(
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.FLAT))),
            E(List.of(
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT)));

            final private List<PitchClass> _pitchClass;

            private Flat(List<PitchClass> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClass> pitchClasses() {
                return this._pitchClass;
            }
        }

        static enum Sharp implements Scale {
            F(List.of(
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL)));

            final private List<PitchClass> _pitchClass;

            private Sharp(List<PitchClass> pitchClasses) {
                this._pitchClass = pitchClasses;
            }

            public List<PitchClass> pitchClasses() {
                return this._pitchClass;
            }
        }

        private Minor() {
            throw new UnsupportedOperationException(
                    Minor.class.getSimpleName() + " is static an cannot be instantiated.");
        }
    }
}
