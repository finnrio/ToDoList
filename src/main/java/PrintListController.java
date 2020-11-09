package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class PrintListController {

    public HBox printListBackground;
    @FXML private Label listLabel;

    String toDoList;

    public PrintListController() { // method to output the list to the user
        ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
        toDoList = printList(list);
    }

    @FXML private void initialize() {
        listLabel.setText(toDoList);
    }

    public static String printList(ArrayList<Main.ListObj> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) { // for loop that runs the size of the list
            Main.ListObj item = list.get(i); // get item in list
            String itemName = item.name; // get name of item object
            String dateTime = item.formattedDateTime; // get formatted date and time stamp of item object
            int position = i + 1; // get position in list
            stringBuilder.append(position).append(". ").append(itemName).append("\n     ").append(dateTime).append("\n"); // prints the list item position followed by the name and date and time stamp
        }
        return stringBuilder.toString();
    }
}
