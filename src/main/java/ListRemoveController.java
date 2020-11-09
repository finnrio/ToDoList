package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.ArrayList;

public class ListRemoveController {

    public Button removeBtn;
    public ChoiceBox<String> listChoiceBox;

    @FXML
    void initialize() {
        System.out.println("Initialising");
        listChoiceBox.getItems().clear();
        ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            String item = list.get(i).name;
            System.out.println("item name: " + item);
            System.out.println("item position: " + i);

            String listItem = (i + 1) + ". " + item;
            listChoiceBox.getItems().add(listItem);
        }
    }

    @FXML
    protected void handleRemoveBtnAction(javafx.event.ActionEvent event) {
        ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
        try {
            int toRemove = listChoiceBox.getSelectionModel().getSelectedIndex();
            System.out.println(toRemove);
            if (toRemove < list.size()) {
                removeItem(list, toRemove);
                Main.listSave(list, Main.listFile);
                initialize();
            }
        } catch (Exception e) {
            System.out.println("Error removing item");
            System.out.println(e.toString());
        }
    }

    public static ArrayList<Main.ListObj> removeItem(ArrayList<Main.ListObj> list, int itemPosition) {
        System.out.println("Removing item: " + list.get(itemPosition).name); // output to user the item being removed
        list.remove((itemPosition)); // remove item from list
        return list;
    }

    public static ArrayList<Main.ListObj> clearList(ArrayList<Main.ListObj> list) throws IOException {
        int listLength = list.size();
        for (int i = 0; i < listLength; i++) {
            removeItem(list, 0);
        }
        return list;
    }

    public void handleClearBtnAction(ActionEvent actionEvent) {
        ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
        try {
            System.out.println("Clearing list.");
            clearList(list);
            Main.listSave(list, Main.listFile);
            initialize();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
