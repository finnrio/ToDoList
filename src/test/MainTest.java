import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class MainTest {
    @Test
    @DisplayName("Simple test")
    void shouldShowSimpleAssertion() {
        Assertions.assertEquals(1, 1);
    }
    @Test
    @Disabled
    void anotherSimpleTest () {
        Assertions.assertNotEquals(1, 5);
    }

}