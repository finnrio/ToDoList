import de.saxsys.javafx.test.JfxRunner;
import de.saxsys.javafx.test.TestInJfxThread;
import main.java.Main;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import java.util.ArrayList;

@RunWith(JfxRunner.class)
class MainTest {
    @Test
    @DisplayName("Simple test")
    void shouldShowSimpleAssertion() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    @Disabled
    void anotherSimpleTest() {
        Assertions.assertNotEquals(1, 5);
    }

    @Test
    @TestInJfxThread
    @DisplayName("List loaded")
    public void loadListTest() {
        ArrayList<Main.ListObj> list = Main.loadList();

        Assertions.assertNotNull(list);
    }

}