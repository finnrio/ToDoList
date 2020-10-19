import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class MainMenu {

    public static class ListObj { // class for list items
        String name;
        LocalDateTime dateTime;
        String formattedDateTime;
    }

    static String menu() { // method to print menu and get users choice
        Scanner input = new Scanner(System.in); // Scanner to get an input
        String menu = ("\n=====================================" +
                "\nWelcome to the To Do list!" +
                "\nWhat would you like to do?" +
                "\n  1. Add to the list" +
                "\n  2. View the list" +
                "\n  3. Delete an item from the list" +
                "\n  4. Save list and exit the program" +
                "\n=====================================");
        System.out.println(menu);
        return (input.next());
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

    static void listSave(List<ListObj> list) { // method to write a list to a file
        File file = new File("MyToDoList.txt"); // Declare the file used to store the list
        // FileWriter deleteFile = new FileWriter(file); // this is used to remove the current contents of the file. Which will later be removed when we load a list from a file.
        try{
            int linesToWrite = list.size(); // get the size of the to do list to determine how many lines the program needs to write to the file
            int line = 0; // declare a line to write too
            for(int i = 0; i < linesToWrite; i++) {
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

    static void listAdd(ArrayList<ListObj> list) { // method that asks for and returns a string, used to get an item to add to the list
        Scanner input = new Scanner(System.in); // Scanner to get an input
        System.out.println("What would you like to add to the list?");
        String itemName = input.nextLine(); // input to get the new item for the list from user
        ListObj itemObj = createListObj(itemName); // creates object
        itemObj.dateTime = LocalDateTime.now(); // set new objects date and time stamp
        itemObj.formattedDateTime = formatDateTime(itemObj.dateTime); // set new objects formatted date and time
        list.add(itemObj); // add new object to list
    }

    static void listPrint(List<ListObj> list) { // method to output the list to the user
        for(int i = 0; i < list.size(); i++){ // for loop that runs the size of the list
            ListObj item = list.get(i); // get item in list
            String itemName = item.name; // get name of item object
            String dateTime = item.formattedDateTime; // get formatted date and time stamp of item object
            int position = i + 1; // get position in list
            System.out.println(position + ". " + itemName + "\n   " + dateTime); // prints the list item position followed by the name and date and time stamp
        }
    }

    static String formatDateTime(LocalDateTime DT) { // method to format
        // for date and time stamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        return DT.format(formatter); // return the formatted date and time
    }

    static void listRemove(List<ListObj> list) { // method to remove a specific item from a list
        Scanner input = new Scanner(System.in); // Scanner to get an input
        System.out.println("What is the item number of the item you want to delete?");
        int itemNo = input.nextInt(); // get the item the user wants to remove
        itemNo--; // list index starts at 0 and item numbers are one above
        if(itemNo > -1 && itemNo < list.size()) {
            System.out.println("Removing item " + (itemNo + 1) + ": " + list.get(itemNo).name); // output to user the item being removed
            list.remove((itemNo)); // remove item from list
        }else{ //
            System.out.println("Error, returning to menu");
        }
    }

    public static void main(String[] args) {
        boolean exitProg = false;  // bool to control if the program is running

        ArrayList<ListObj> toDoList = loadList(); // declare the current list with the loadList method

        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> listAdd(toDoList); // uses the listAdd method to add an item to the list
                case "2" -> listPrint(toDoList); // listPrint method with the list as a parameter
                case "3" -> listRemove(toDoList); // run listRemove method that removes an item from a list and set this to the list variable
                case "4" -> { // this case saves the list to a file when you are finished with the program
                    System.out.println("Goodbye");
                    listSave(toDoList); // method to save the toDoList list to a file
                    exitProg = true;
                }
                // default catch all for any invalid inputs the the menu
                default -> System.out.println("Error try again");
            }
        }while(!exitProg);
    }
}