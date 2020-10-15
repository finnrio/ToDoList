import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class

public class MainMenu {

    static String menu() { // method to print menu and get users choice
        Scanner input = new Scanner(System.in); // Scanner to get an input
        String menu = ("==============================" +
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

    public static void main(String[] args) {


        boolean exitProg = false;  // bool to control if the program is running

        ArrayList<String> list = new ArrayList<>();

        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> list.add(listAdd()); // uses the listAdd method to get an item to add to the list and uses the ArrayList Add() method to add the item to the list
                case "2" -> System.out.println("option 2");
                case "3" -> System.out.println("option 3");
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
