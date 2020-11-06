import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainMenuController {

    @FXML public BorderPane mainPane;

    //ArrayList<MainMenu.ListObj> toDoList = MainMenu.loadList(); // declare the current list with the loadList method

    @FXML protected void handleQuitButtonAction(javafx.event.ActionEvent event) {
        Platform.exit();
    }

    @FXML protected void handleBtn1Action(javafx.event.ActionEvent event) {
        System.out.println("Add to list button clicked");
        Main object = new Main();
        Pane view = object.getPane("AddToList");
        mainPane.setCenter(view);
    }

    @FXML protected void handleBtn2Action(javafx.event.ActionEvent event) {
        System.out.println("Print list button clicked");
        Main object = new Main();
        Pane view = object.getPane("PrintList");
        mainPane.setCenter(view);
    }

    @FXML protected void handleBtn3Action(javafx.event.ActionEvent event) {
        System.out.println("List remove button clicked");
        Main object = new Main();
        Pane view = object.getPane("ListRemove");
        mainPane.setCenter(view);
    }

}
