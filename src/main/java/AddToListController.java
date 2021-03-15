package main.java;

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
    public static ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);

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
        String itemName = newItemTB.getText();
        addToList(list, itemName);
        Main.listSave(list, "MyToDoList");
        addedLabel.setText("Item added");
        newItemTB.clear();
    }

    public static ArrayList<Main.ListObj> addToList(ArrayList<Main.ListObj> list, String itemName) throws IOException {
        Main.ListObj itemObj = createListObj(itemName); // creates new item object
        itemObj.dateTime = LocalDateTime.now(); // set new objects date and time stamp
        itemObj.formattedDateTime = formatDateTime(itemObj.dateTime); // set new objects formatted date and time
        list.add(itemObj);
        return list;
    }
}