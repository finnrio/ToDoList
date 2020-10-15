import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class MainMenu {

    static String menu() { // method to print menu and get users choice
        Scanner input = new Scanner(System.in); // Scanner to get an input
        String menu = ("\n==============================" +
                "\nWelcome to the To Do list!" +
                "\nWhat would you like to do?" +
                "\n  1. " +
                "\n  2. " +
                "\n  3. " +
                "\n  4. Exit Program" +
                "\n==============================");
        System.out.println(menu);
        return (input.next());
    }

    static String listAdd() { // method that asks for and returns a string, used to get an item to add to the list
        Scanner input = new Scanner(System.in); // Scanner to get an input
        System.out.println("What would you like to add to the list?");
        return(input.next());
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

    public static void main(String[] args) {
        boolean exitProg = false;  // bool to control if the program is running

        ArrayList<String> list = new ArrayList<>();

        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> list.add(listAdd()); // uses the listAdd method to get an item to add to the list and uses the ArrayList Add() method to add the item to the list
                case "2" -> listPrint(list); // listPrint method with the list as a parameter
                case "3" -> listRemove(list); // run listRemove method that removes an item from a list and set this to the list variable
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
