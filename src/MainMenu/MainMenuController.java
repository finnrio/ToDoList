package MainMenu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class MainMenuController {

    @FXML public BorderPane mainPane;

    //ArrayList<MainMenu.ListObj> toDoList = MainMenu.loadList(); // declare the current list with the loadList method

    @FXML protected void handleQuitButtonAction(javafx.event.ActionEvent event) {
        Platform.exit();
    }

    @FXML protected void handleBtn1Action(javafx.event.ActionEvent event) {
        System.out.println("CLICKED");
        Button btn = new Button();
        btn.setText("THE BUTTON");
        mainPane.setCenter(btn);
    }

    @FXML protected void handleBtn2Action(javafx.event.ActionEvent event) {
        System.out.println("you clicked me!");
        MainMenu object = new MainMenu();
        Pane view = object.getPane("PrintList");
        mainPane.setCenter(view);
    }

    @FXML protected void handleBtn3Action(javafx.event.ActionEvent event) {
    }

}
