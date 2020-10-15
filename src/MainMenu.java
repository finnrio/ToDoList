import java.util.Scanner;

public class MainMenu {
    // method to print menu and get users choice
    static String menu() {
        // Scanner to get an input
        Scanner input = new Scanner(System.in);
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

    public static void main(String[] args) {
        boolean exitProg = false;  // bool to control if the program is running
        do{ // do loop that returns the user to the menu until they chose to exit the program
            String menuChoice = menu();
            switch (menuChoice) {
                case "1" -> System.out.println("option 1");
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
