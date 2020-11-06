package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.util.ArrayList;

public class ListRemoveController {

    public Button removeBtn;
    public ChoiceBox<String> listChoiceBox;

    ArrayList<Main.ListObj> list;

    @FXML void initialize() {
        list = Main.loadList();
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            String item = list.get(i).name;
            System.out.println(item);
            String listItem =(i + 1) + ". " + item;
            listChoiceBox.getItems().add(listItem);
        }
    }

    @FXML protected void handleRemoveBtnAction(javafx.event.ActionEvent event) {
        try {
            int toRemove = listChoiceBox.getSelectionModel().getSelectedIndex();
            System.out.println(toRemove);
            if (toRemove < list.size()) {
                System.out.println("Removing item " + (toRemove + 1) + ": " + list.get(toRemove).name); // output to user the item being removed
                list.remove((toRemove)); // remove item from list
                Main.listSave(list);
                listChoiceBox.getItems().clear();
                initialize();
            }
        } catch (Exception e) {
            System.out.println("Error removing item");
            System.out.println(e);
        }
    }
}
