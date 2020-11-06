import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddToListController {

    public TextField newItemTB;
    public Label addedLabel;

    static String formatDateTime(LocalDateTime DT) { // method to format
        // for date and time stamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        return DT.format(formatter); // return the formatted date and time
    }

    static Main.ListObj createListObj(String itemName) { // method to create an object
        Main.ListObj itemObj = new Main.ListObj(); // create a new object
        itemObj.name = itemName; // set new objects name
        return itemObj;
    }

    public void handleAddButtonAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Add button clicked.");
        ArrayList<Main.ListObj> list = Main.loadList();
        String itemName = newItemTB.getText();
        Main.ListObj itemObj = createListObj(itemName); // creates new item object
        itemObj.dateTime = LocalDateTime.now(); // set new objects date and time stamp
        itemObj.formattedDateTime = formatDateTime(itemObj.dateTime); // set new objects formatted date and time
        list.add(itemObj);
        Main.listSave(list);
        System.out.println("New list saved.");
        addedLabel.setText("Item added");
        newItemTB.clear();
    }
}