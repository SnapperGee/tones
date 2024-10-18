package sogott.beep;

import java.util.EnumSet;

final class AudioStringSegment {
    final private static EnumSet<Note> _notes = EnumSet.allOf(Note.class);
    final private static EnumSet<Wave> _waves = EnumSet.allOf(Wave.class);

    /**
     * Returns {@code true} if string can be parsed as duration (an integer).
     *
     * @param aString String to check if it can be parsed as a duration (an
     *                integer).
     * @return {@code true} if string can be a parsed as duration (an integer).
     */
    boolean isParsableDuration(String aString) {
        return aString != null && aString.length() != 0 && aString.codePoints().allMatch(Character::isDigit);
    }

    boolean isParsableFrequency(String aString) {

        if (aString == null) {
            return false;
        }

        // if string isn't at least a note char and octave
        if (aString.length() < 2) {
            return false;
        }

        final char upperCaseLeadingChar = Character.toUpperCase(aString.charAt(0));

        // if leading char isn't valid note char
        if (_notes.stream()
                .noneMatch(note -> note.charValue() == upperCaseLeadingChar)) {
            return false;
        }

        // Check if the second character is '+' or '-'
        final int startIndex = aString.charAt(1) == '+' || aString.charAt(1) == '-' ? 2 : 1;

        // if second char is '+' or '-' and there aren't any chars left
        if (startIndex == 2 && aString.length() < 3) {
            return false;
        }

        // if there are only digit chars after leading or second '+' or '-' char
        return aString.codePoints().skip(startIndex).allMatch(Character::isDigit);
    }

    static boolean isParsableWaveShapePrefix(String aString) {
        if (aString == null) {
            return false;
        }

        if (aString.length() < 4) {
            return false;
        }

        if (!aString.endsWith(">")) {
            return false;
        }

        final String strippedString = aString.substring(0, aString.length() - 1);

        if (_waves.stream().noneMatch(wave -> wave.name().equalsIgnoreCase(strippedString))) {
            return false;
        }

        return true;
    }

    /**
     *
     * @author Snap
     * @param aString {@code String} to check if it's parsable to audio.
     * @return {@code true} if passed {@code String} can be parsed to audio.
     */
    static boolean isValid(String aString) {

        if (aString == null) {
            return false;
        }

        // String needs to be at least a note character, octave integer, period, and
        // duration integer
        if (aString.length() < 4) {
            return false;
        }

        // If there isn't exactly one period character
        if (aString.codePoints().filter(cp -> cp == '.').limit(2).count() != 1) {
            return false;
        }

        // If there is more than 1 right angle bracket/greater than character
        if (aString.codePoints().filter(cp -> cp == '>').limit(2).count() > 1) {
            return false;
        }

        final String[] splitString = aString.split("[.]");

        // String should have 1 period separating frequency and duration
        if (splitString.length != 2) {
            return false;
        }

        final String waveShapePrefixAndFrequency = splitString[0];
        final String durationSuffix = splitString[1];

        // frequency must be at least 2 chars and wave shape prefix can be omitted
        if (waveShapePrefixAndFrequency.length() < 2) {
            return false;
        }

        // Tail of String should be an integer for duration
        if (!durationSuffix.codePoints().allMatch(Character::isDigit)) {
            return false;
        }

        if (waveShapePrefixAndFrequency.endsWith(">")) {
            return false;
        }

        final String[] splitWaveShapePrefixAndFrequency = waveShapePrefixAndFrequency.split(">");

        // if part of string preceding duration suffix doesn't contain a right angle
        // bracket/greater than char
        if (splitWaveShapePrefixAndFrequency.length == 1) {
            final String frequency = splitWaveShapePrefixAndFrequency[0];

            if (frequency.length() < 2) {
                return false;
            }

            // if leading character isn't a valid note character
            if (!Character.isLetter(frequency.charAt(0)) || _notes.stream()
                    .noneMatch(note -> note.charValue() == Character.toUpperCase(frequency.charAt(0)))) {
                return false;
            }

            // if frequency is only 2 chars and second char isn't an octave
            if (frequency.length() == 2 && !Character.isDigit(frequency.charAt(1))) {
                return false;
            }

            if (!frequency.codePoints().skip(frequency.charAt(1) == '+' || frequency.charAt(1) == '-' ? 2 : 1)
                    .allMatch(Character::isDigit)) {
                return false;
            }
        }

        return true;
    }
}
