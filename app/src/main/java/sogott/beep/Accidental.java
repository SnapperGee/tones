package sogott.beep;

import java.util.Optional;

enum Accidental {
    SHARP('+', 1),
    FLAT('-', -1);

    final private char _char;
    final private int _offset;

    private Accidental(char charValue, int offset) {
        this._char = charValue;
        this._offset = offset;
    }

    int offset() {
        return this._offset;
    }

    char charValue() {
        return this._char;
    }

    static Optional<Accidental> fromChar(char aChar) {
        return SHARP.charValue() == aChar
                ? Optional.of(SHARP)
                : FLAT.charValue() == aChar
                        ? Optional.of(FLAT)
                        : Optional.empty();
    }
}
