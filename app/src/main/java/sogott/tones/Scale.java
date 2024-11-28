package sogott.tones;

import java.util.Optional;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

enum Scale {
    MAJOR(new EnumMap<PitchLetter, Map<Accidental, List<PitchClass>>>(Map.ofEntries(
            Map.entry(PitchLetter.A, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP)
                ))
            )))),
            Map.entry(PitchLetter.B, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.SHARP)
                ))
            )))),
            Map.entry(PitchLetter.C, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL)
                )),
                Map.entry(Accidental.SHARP, List.of(
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP),
                    new PitchClass(PitchLetter.E, Accidental.SHARP),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.SHARP),
                    new PitchClass(PitchLetter.B, Accidental.SHARP)
                ))
            )))),
            Map.entry(PitchLetter.D, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP)
                ))
            )))),
            Map.entry(PitchLetter.E, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP)
                ))
            )))),
            Map.entry(PitchLetter.F, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL)
                )),
                Map.entry(Accidental.SHARP, List.of(
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.SHARP),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP),
                    new PitchClass(PitchLetter.E, Accidental.SHARP)
                ))
            )))),
            Map.entry(PitchLetter.G, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP)
                ))
            ))))
        ))),

    MINOR(new EnumMap<PitchLetter, Map<Accidental, List<PitchClass>>>(Map.ofEntries(
            Map.entry(PitchLetter.A, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL)
                ))
            )))),
            Map.entry(PitchLetter.B, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL)
                ))
            )))),
            Map.entry(PitchLetter.C, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT)
                ))
            )))),
            Map.entry(PitchLetter.D, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.FLAT),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.FLAT)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL)
                ))
            )))),
            Map.entry(PitchLetter.E, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.FLAT, List.of(
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.FLAT),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.FLAT),
                    new PitchClass(PitchLetter.D, Accidental.FLAT)
                )),
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL)
                ))
            )))),
            Map.entry(PitchLetter.F, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.FLAT),
                    new PitchClass(PitchLetter.E, Accidental.FLAT)
                )),
                Map.entry(Accidental.SHARP, List.of(
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.NATURAL)
                ))
            )))),
            Map.entry(PitchLetter.G, unmodifiableMap(new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL)
                ))
            ))))
        )));

    private final Map<PitchLetter, Map<Accidental, List<PitchClass>>> _accidentalPitchClassMap;

    private Scale(EnumMap<PitchLetter, Map<Accidental, List<PitchClass>>> accidentalPitchClassMap) {
        this._accidentalPitchClassMap = unmodifiableMap(accidentalPitchClassMap);
    }

    List<PitchClass> of(PitchClass pitchClass) {

        if (pitchClass == null) {
            throw new IllegalArgumentException("Null " + PitchClass.class.getSimpleName());
        }

        return Optional.ofNullable(this._accidentalPitchClassMap.get(pitchClass.letter()))
            .map(accidentalMap -> accidentalMap.get(pitchClass.accidental()))
            .orElseGet(() -> switch(pitchClass.letter()) {
                case A ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.G).get(Accidental.SHARP);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.B).get(Accidental.FLAT);
                        default -> throw new RuntimeException("Error parsing %s.%s.of(%s)".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
                case B ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.A).get(Accidental.SHARP);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.C).get(Accidental.NATURAL);
                        default -> throw new RuntimeException("%s.%s.of(%s) error".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
                case C ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.B).get(Accidental.NATURAL);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.D).get(Accidental.FLAT);
                        default -> throw new RuntimeException("%s.%s.of(%s) error".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
                case D ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.C).get(Accidental.SHARP);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.E).get(Accidental.FLAT);
                        default -> throw new RuntimeException("%s.%s.of(%s) error".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
                case E ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.D).get(Accidental.SHARP);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.F).get(Accidental.NATURAL);
                        default -> throw new RuntimeException("%s.%s.of(%s) error".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
                case F ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.E).get(Accidental.NATURAL);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.G).get(Accidental.FLAT);
                        default -> throw new RuntimeException("%s.%s.of(%s) error".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
                case G ->
                    switch(pitchClass.accidental()) {
                        case FLAT -> this._accidentalPitchClassMap.get(PitchLetter.F).get(Accidental.SHARP);
                        case SHARP -> this._accidentalPitchClassMap.get(PitchLetter.A).get(Accidental.FLAT);
                        default -> throw new RuntimeException("%s.%s.of(%s) error".formatted(Scale.class.getSimpleName(), this.name(), pitchClass));
                    };
            }
        );
    }
}
