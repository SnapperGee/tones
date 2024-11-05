package sogott.beep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class AudioStringTest {
    @Test
    void AudioStringSilenceNoteCharConstIsQuestionMark() {
        assertEquals('?', AudioString.SILENCE_NOTE_CHAR);
    }

    @Test
    void AudioStringDelineatorWaveShapeAndPitchCharConstIsRightAngleBracket() {
        assertEquals('>', AudioString.Delineator.WAVE_SHAPE_AND_PITCH.charValue());
    }

    @Test
    void AudioStringDelineatorPitchAndDurationCharConstIsPeriod() {
        assertEquals('.', AudioString.Delineator.PITCH_AND_DURATION.charValue());
    }
}
