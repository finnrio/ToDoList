package MainMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Application {
    private Pane view;

    public static void main(String[] args) {

        launch(args); // launch the GUI (stage)
    }

    @Override
    public void start(Stage stage) throws IOException {
        initUI(stage);
    }

    private void initUI(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("To Do List");
        stage.setScene(scene);
        stage.show();
    }

    public Pane getPane (String fileName) {
        try {
            URL fileURL = MainMenu.class.getResource("/MainMenu/" + fileName + ".fxml");
            if (fileURL == null){
                throw new java.io.FileNotFoundException("FXML file not found.");
            }
            new FXMLLoader();
            view = FXMLLoader.load(fileURL);
        } catch (Exception e) {
            System.out.println("No page " + fileName + " please check MainMenu.java.");
            System.out.println(e);
        }
        return view;
    }

    public static class ListObj { // class for list items
        String name;
        LocalDateTime dateTime;
        String formattedDateTime;
    }

    static void saveWriter(List<ListObj> newList, int line, File file) throws IOException { // method to save the current List to a file (MyToDoList.txt)
        FileWriter fw = new FileWriter(file, true); // open the file in append mode
        PrintWriter pw = new PrintWriter(fw); // declare the print writer
        ListObj item = newList.get(line); // get the object that will be written
        String itemName = item.name; // get name of item object
        String dateTime = item.formattedDateTime; // get formatted date and time stamp of item object
        pw.println(itemName); // print the object name to a new line
        pw.println(dateTime); // print the object dateTime to a new line
        pw.close(); // close the writer
    }

    static void listSave(List<ListObj> list) throws IOException { // method to write a list to a file
        File file = new File("MyToDoList.txt"); // Declare the file used to store the list
        FileWriter deleteFile = new FileWriter(file); // this is used to remove the current contents of the file. Which will later be removed when we load a list from a file.
        try {
            int linesToWrite = list.size(); // get the size of the to do list to determine how many lines the program needs to write to the file
            int line = 0; // declare a line to write too
            for (int i = 0; i < linesToWrite; i++) {
                saveWriter(list, line, file); // method that gets a list and file to write the list to. Then the line variable is used to determine what object in the list is written to the file
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<ListObj> loadList() { // create a new list by reading a file
        ArrayList<ListObj> list = new ArrayList<>(); // declare list
        try {
            File file = new File("MyToDoList.txt"); // declare file
            // this block of code is used to get the number of lines in the file
            BufferedReader reader1 = new BufferedReader(new FileReader(file)); // create a reader for the file.
            int noOfLines = 0;
            while (reader1.readLine() != null) { //while there is a line in front of the reader increment the noOfLines variable by 1
                noOfLines++;
            }
            reader1.close(); //close the reader
            // this block of code is used to write the list from the lines in the file
            BufferedReader reader2 = new BufferedReader(new FileReader(file)); // create a reader for the file
            noOfLines = noOfLines / 2;
            for (int i = 0; i < noOfLines; i++) {
                String currentLine = reader2.readLine(); // set current line
                ListObj listObj = createListObj(currentLine); // create object with the name of the current line
                currentLine = reader2.readLine(); // set current line to the next line
                listObj.formattedDateTime = currentLine; // set the current line to the date and time of the object
                list.add(listObj); // add the object to the list
            }
            reader2.close(); //close the reader
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list; // return new list to program
    }

    static ListObj createListObj(String itemName) { // method to create an object
        ListObj itemObj = new ListObj(); // create a new object
        itemObj.name = itemName; // set new objects name
        return itemObj;
    }
}