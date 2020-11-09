import main.java.AddToListController;
import main.java.ListRemoveController;
import main.java.Main;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class MainTest {

    @Test
    @DisplayName("Simple test")
    @Disabled
    void shouldShowSimpleAssertion() {
        Assertions.assertEquals(1, 1);
    }

    @Nested
    @DisplayName("List tests")
    class listTests {

        @Test
        @DisplayName("List loaded")
        public void loadListTest() {
            ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
            Assertions.assertNotNull(list);
        }

        @Test
        @DisplayName("List saved")
        public void saveListTest() throws IOException {
            ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
            Main.listSave(list, "MyToDoList");
            long timeModified = System.currentTimeMillis();
            timeModified = (timeModified + 500)/1000; // this converts it to seconds rather than milliseconds as there may be a delay of milliseconds.
            File listFile = new File("MyToDoList.txt");
            long lastModified = listFile.lastModified();
            lastModified = (lastModified + 500)/1000;
            System.out.println("File was last modified at: " + lastModified);
            System.out.println("List save was executed at: " + timeModified);
            Assertions.assertEquals(timeModified, lastModified);
        }

        @Test
        @DisplayName("Add to list function")
        public void addToListTest() throws IOException {
            ArrayList<Main.ListObj> oldList = Main.loadList(Main.listFile);
            String testItem = "Test item";
            AddToListController.addToList(oldList, testItem);
            Main.listSave(oldList, "MyToDoList");
            ArrayList<Main.ListObj> newList = Main.loadList(Main.listFile);

            Assertions.assertNotEquals(oldList, newList);
        }

        @Test
        @DisplayName("Remove from list function")
        public void removeFromListTest() throws IOException {
            ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
            int itemToRemove = list.size()-1;
            System.out.println(itemToRemove);
            ListRemoveController.removeItem(list, itemToRemove);

        }
    }
}