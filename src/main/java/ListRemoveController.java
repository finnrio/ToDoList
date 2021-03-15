package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.util.ArrayList;

public class ListRemoveController {

    public Button removeBtn;
    public ChoiceBox<String> listChoiceBox;

    @FXML
    void initialize() {
        listChoiceBox.getItems().clear();
        ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            String item = list.get(i).name;
            String listItem = (i + 1) + ". " + item;
            listChoiceBox.getItems().add(listItem);
        }
    }

    @FXML
    protected void handleRemoveBtnAction(javafx.event.ActionEvent event) {
        ArrayList<Main.ListObj> list = Main.loadList(Main.listFile);
        try {
            int toRemove = listChoiceBox.getSelectionModel().getSelectedIndex();
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
        list.remove((itemPosition));
        return list;
    }


    public static ArrayList<Main.ListObj> clearList() {
        return new ArrayList<>();
    }

    public void handleClearBtnAction(ActionEvent actionEvent) {
        try {
            ArrayList<Main.ListObj> list = clearList();
            Main.listSave(list, Main.listFile);
            initialize();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
