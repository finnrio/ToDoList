import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class MainMenu {

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

    static void saveWriter(List<Object> newList, int line, File file) throws IOException { // method to save the current List to a file (MyToDoList.txt)
        FileWriter fw = new FileWriter(file, true); // open the file in append mode
        PrintWriter pw = new PrintWriter(fw); // declare the print writer
        Object lineToWrite = newList.get(line); // get the object that will be written
        pw.println(lineToWrite); // print the object to a new line
        pw.close(); // close the writer
    }

    static void listSave(List<Object> list) throws IOException { // method to write a list to a file
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

    static List loadList() throws FileNotFoundException { // create a new list by reading each line of a file to a new item in the list
        List list = new ArrayList(); // declare list
        try {
            File file = new File("MyToDoList.txt"); // declare file
            Scanner reader = new Scanner(file); // create a reader for the file
            while(reader.hasNextLine()){
                Object item = reader.nextLine(); // set the next line in file to the item object
                list.add(item); // add the item to the list
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();;
        }
        return(list); // return new list to program
    }

    static String listAdd() { // method that asks for and returns a string, used to get an item to add to the list
        Scanner input = new Scanner(System.in); // Scanner to get an input
        System.out.println("What would you like to add to the list?");
        return(input.nextLine());
    }

    static void listPrint(List<Object> list) { // method to output the list to the user
        for(int i = 0; i < list.size(); i++){ // for loop that runs the size of the list
            System.out.println(i+1 + ". " + list.get(i)); // prints the list item position followed by the item
        }
    }

    static void listRemove(List<Object> list) { // method to remove a specific item from a list
        Scanner input = new Scanner(System.in); // Scanner to get an input
        System.out.println("What is the item number of the item you want to delete?");
        int itemNo = input.nextInt(); // get the item the user wants to remove
        itemNo--; // list index starts at 0 and item numbers are one above
        if(itemNo > -1 && itemNo < list.size()) {
            System.out.println("Removing item " + (itemNo + 1) + ": " + list.get(itemNo)); // output to user the item being removed
            list.remove((itemNo)); // remove item from list
        }else{ //
            System.out.println("Error, returning to menu");
        }
    }

    public static void main(String[] args) throws IOException {
        boolean exitProg = false;  // bool to control if the program is running

        List toDoList = loadList(); // declare the current list as whatever is in the file

        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> toDoList.add(listAdd()); // uses the listAdd method to get an item to add to the list and uses the ArrayList Add() method to add the item to the list
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
