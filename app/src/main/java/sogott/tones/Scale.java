package sogott.tones;

import java.util.Optional;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;

enum Scale {
    MAJOR(new EnumMap<PitchLetter, EnumMap<Accidental, List<PitchClass>>>(Map.ofEntries(
            Map.entry(PitchLetter.A, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.B, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.C, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.D, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.E, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.F, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.E, Accidental.NATURAL),
                    new PitchClass(PitchLetter.F, Accidental.SHARP),
                    new PitchClass(PitchLetter.G, Accidental.SHARP),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.NATURAL),
                    new PitchClass(PitchLetter.C, Accidental.SHARP),
                    new PitchClass(PitchLetter.D, Accidental.SHARP)
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
            ))),
            Map.entry(PitchLetter.G, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            )))
        ))),

    MINOR(new EnumMap<PitchLetter, EnumMap<Accidental, List<PitchClass>>>(Map.ofEntries(
            Map.entry(PitchLetter.A, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.B, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.C, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL),
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.FLAT),
                    new PitchClass(PitchLetter.B, Accidental.FLAT)
                ))
            ))),
            Map.entry(PitchLetter.D, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.E, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.F, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
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
            ))),
            Map.entry(PitchLetter.G, new EnumMap<Accidental, List<PitchClass>>(Map.ofEntries(
                Map.entry(Accidental.NATURAL, List.of(
                    new PitchClass(PitchLetter.G, Accidental.NATURAL),
                    new PitchClass(PitchLetter.A, Accidental.NATURAL),
                    new PitchClass(PitchLetter.B, Accidental.FLAT),
                    new PitchClass(PitchLetter.C, Accidental.NATURAL),
                    new PitchClass(PitchLetter.D, Accidental.NATURAL),
                    new PitchClass(PitchLetter.E, Accidental.FLAT),
                    new PitchClass(PitchLetter.F, Accidental.NATURAL)
                ))
            )))
        )));

    private final Map<PitchLetter, Map<Accidental, List<PitchClass>>> _maps;

    private Scale(EnumMap<PitchLetter, EnumMap<Accidental, List<PitchClass>>> maps) {
        this._maps = unmodifiableMap(maps);
    }

    Optional<List<PitchClass>> of(PitchClass pitchClass) {
        return Optional.ofNullable(this._maps.get(pitchClass.letter())).map(accidentalMap -> accidentalMap.get(pitchClass.accidental()));
    }
}
