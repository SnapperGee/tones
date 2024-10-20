package sogott.beep;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.in;

final class WaveTest {
    @Test
    void sinWaveStringValueIsEnumValueName() {
        final String sinWaveStringValue = Wave.SIN.stringValue();
        final String sinWaveEnumValueName = Wave.SIN.name();
        assertEquals(sinWaveEnumValueName, sinWaveStringValue);
    }

    @Test
    void sinWaveEnumValueNameInStringValueAliases() {
        final Set<String> sinWaveStringValueAliases = Wave.SIN.stringValueAliases();
        final String sinWaveEnumValueName = Wave.SIN.name();
        assertThat(sinWaveEnumValueName, is(in(sinWaveStringValueAliases)));
    }
}
