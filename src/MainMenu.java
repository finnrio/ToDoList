import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class
import java.io.File;  // Import the File class
import java.io.IOException; // Import the IOException class to handle errors (for files)

public class MainMenu {

    static String menu() { // method to print menu and get users choice
        Scanner input = new Scanner(System.in); // Scanner to get an input
        String menu = ("\n==================================" +
                "\nWelcome to the To Do list!" +
                "\nWhat would you like to do?" +
                "\n  1. Add to the list" +
                "\n  2. View the list" +
                "\n  3. Delete an item from the list" +
                "\n  4. Exit Program" +
                "\n==================================");
        System.out.println(menu);
        return (input.next());
    }

    static void listAdd() throws IOException { // method that asks for an item for the list and writes this to a file
        FileWriter listFile =  new FileWriter("MyToDoList.txt", true); // opens the file we want to write to
        PrintWriter printWriter = new PrintWriter(listFile); // uses the file from FileWriter with PrintWriter to print the items to the file. Using print with FileWrite will not append the file it will rewrite it.
        Scanner input = new Scanner(System.in); // Scanner that is used to get an input
        System.out.println("What would you like to add to the list?");
        printWriter.print(input.nextLine() + "\n"); // prints the input to the file and then starts a new line
        printWriter.close(); // close the file
    }

    static void listPrint(ArrayList<String> list) { // method to output the list to the user
        for(int i = 0; i < list.size(); i++){ // for loop that runs the size of the list
            System.out.println(i+1 + ". " + list.get(i)); // prints the list item position followed by the item
        }
    }

    static ArrayList<String> listRemove(ArrayList<String> list) { // method to remove a specific item from a list
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
        return(list); // return new list
    }

    public static void main(String[] args) throws IOException {
        boolean exitProg = false;  // bool to control if the program is running

        //ArrayList<String> list = new ArrayList<>();

        File listFile = new File("MyToDoList.txt");

        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> listAdd(); // uses the listAdd method to get an item write this to the listFile file
                //case "2" -> listPrint(list); // listPrint method with the list as a parameter
                //case "3" -> listRemove(list); // run listRemove method that removes an item from a list and set this to the list variable
                case "4" -> {
                    System.out.println("Goodbye");
                    exitProg = true;
                }
                // default catch all for any invalid inputs the the menu
                default -> System.out.println("Error try again");
            }
        }while(!exitProg);
    }
}
