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
        Object lineToWrite = newList.get(line); // get the object that will be written
        pw.println(lineToWrite); // print the object to a new line
        pw.close(); // close the writer
    }

    static void listSave(List<ListObj> list) throws IOException { // method to write a list to a file
        File file = new File("MyToDoList.txt"); // Declare the file used to store the list
        FileWriter deleteFile = new FileWriter(file); // this is used to remove the current contents of the file. Which will later be removed when we load a list from a file.
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

    static List<ListObj> loadList() { // create a new list by reading each line of a file to a new item in the list
        List<ListObj> list = new ArrayList(); // declare list
        try {
            File file = new File("MyToDoList.txt"); // declare file
            Scanner reader = new Scanner(file); // create a reader for the file
            while(reader.hasNextLine()){
                String item = reader.nextLine(); // set the next line in file to the item object
                list.add(new ListObj()); // add the item to the list
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();;
        }
        return(list); // return new list to program
    }

    static List<ListObj> listAdd(ArrayList<ListObj> list) { // method that asks for and returns a string, used to get an item to add to the list
        Scanner input = new Scanner(System.in); // Scanner to get an input
        System.out.println("What would you like to add to the list?");
        String itemName = input.nextLine(); // input to get the new item for the list from user
        ListObj itemObj = new ListObj(); // create a new object
        itemObj.name = itemName; // set new objects name
        itemObj.dateTime = LocalDateTime.now(); // set new objects date and time stamp
        list.add(itemObj); // add new object to list
        return(list); // return new list to program
    }

    static void listPrint(List<ListObj> list) { // method to output the list to the user
        for(int i = 0; i < list.size(); i++){ // for loop that runs the size of the list
            ListObj item = list.get(i); // get item in list
            String itemName = item.name; // get name of item object
            String dateTime = formatDateTime(item.dateTime); // get formatted date and time stamp of item object
            int position = i + 1; // get position in list
            System.out.println(position + ". " + itemName + "\n   " + dateTime); // prints the list item position followed by the name and date and time stamp
        }
    }

    static String formatDateTime(LocalDateTime DT){ // method to formatt
        // for date and time stamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        String formattedDT = DT.format(formatter);
        return formattedDT;
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

    public static void main(String[] args) throws IOException {
        boolean exitProg = false;  // bool to control if the program is running

        ArrayList<ListObj> toDoList = new ArrayList<>(); // declare the current list

        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> listAdd(toDoList); // uses the listAdd method to add an item to the list
                case "2" -> listPrint(toDoList); // listPrint method with the list as a parameter
                case "3" -> listRemove(toDoList); // run listRemove method that removes an item from a list and set this to the list variable
                case "4" -> { // this case saves the list to a file when you are finished with the program
                    System.out.println("Goodbye");
                    //listSave(toDoList); // method to save the toDoList list to a file
                    exitProg = true;
                }
                // default catch all for any invalid inputs the the menu
                default -> System.out.println("Error try again");
            }
        }while(!exitProg);
    }
}