package sogott.beep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

final class AudioStringTest {
    @Test
    void AudioStringSilenceNoteCharConstIsQuestionMark() {
        assertSame('?', AudioString.SILENCE_NOTE_CHAR);
    }

    @Test
    void AudioStringDelineatorWaveShapeAndPitchCharConstIsRightAngleBracket() {
        assertSame('>', AudioString.Delineator.WAVE_SHAPE_AND_PITCH.charValue());
    }

    @Test
    void AudioStringDelineatorPitchAndDurationCharConstIsPeriod() {
        assertSame('.', AudioString.Delineator.PITCH_AND_DURATION.charValue());
    }
}
