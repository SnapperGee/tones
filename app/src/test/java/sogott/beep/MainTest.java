package sogott.beep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void appHasAGreeting() {
        final Main classUnderTest = new Main();
        assertNotNull(classUnderTest);
    }
}
