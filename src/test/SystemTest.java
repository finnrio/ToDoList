import main.java.AddToListController;
import main.java.ListRemoveController;
import main.java.Main;
import org.junit.jupiter.api.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SystemTest {

    static int items = 100;
    static String fileName = "SystemTestList";

    @BeforeAll
    public static void before() throws IOException {
        FileWriter deleteFile = new FileWriter(fileName + ".txt");
        deleteFile.close();
    }


    @Test
    @DisplayName("Load test")
    @Order(1)
    void loadTest() throws IOException {
        ArrayList<Main.ListObj> tempList = Main.loadList(fileName);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < items; i++){
            String toAdd = String.valueOf(i);
            AddToListController.addToList(tempList, toAdd);
        }
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        Main.listSave(tempList, fileName);
        System.out.println("System took: " + timeTaken + " milliseconds to add " + items + " items. ");
    }

    @Test
    @DisplayName("Save test")
    @Order(2)
    void saveTest() throws IOException {
        ArrayList<Main.ListObj> tempList = Main.loadList(fileName);
        long startTime = System.currentTimeMillis();
        Main.listSave(tempList, fileName);
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("System took: " + timeTaken + " milliseconds to save a list with " + items + " items. ");
    }

    @Test
    @DisplayName("Remove test")
    @Order(3)
    void removeTest() {
        ArrayList<Main.ListObj> tempList = Main.loadList(fileName);
        try{
            ListRemoveController.removeItem(tempList, 0);
            tempList = ListRemoveController.clearList();
            Main.listSave(tempList, fileName);
            System.out.println("System successfully removed an item from the list and has now cleared the entire list");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}