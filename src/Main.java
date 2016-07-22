/**
 * Author : John Lyons
 */

import java.util.Scanner;

public class Main {

    // Instance Variables
    Scanner keyboard = new Scanner(System.in);
    ATM atm = new ATM();
    boolean exit;

    public static void main(String args[]) {
        Main main = new Main();
        main.launchMain();
    }

    public void launchMain() {
        printOpeningMessage();
        while (!exit) {
            printMainMenu();
            int choice = getInput();
            selectChoice(choice);
        }
    }

    private void printOpeningMessage() {

        // this will only print at the very start of the
        // app running, and never again.
        System.out.println("\n---------------------------------------------------");
        System.out.println("--------- Welcome to the ATM Application ----------");
        System.out.println("---------------------------------------------------");

    }

    private void printMainMenu() {

        System.out.println("\nPlease choose one of the following options : ");
        System.out.println("\n1) Create a New Account.");
        System.out.println("2) Deposit Cash.");
        System.out.println("3) Withdraw Cash.");
        System.out.println("4) Display Account Balance.");
        System.out.println("0) Exit the application.");

    }

    private int getInput() {
        int choice = -1;
        do {
            System.out.println("\nEnter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 4) {
                System.out.println("Choice outside of range. Please choose again.");
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }

    private void selectChoice(int choice) {
        switch (choice) {
            case 0:
                // exit
                System.out.println("Thank you for using our ATM application! Goodbye!");
                System.exit(0);
                break;
            case 1:
                // createNewAccount
                break;
            case 2:
                // depositCash
                break;
            case 3:
                // withdrawCash
                break;
            case 4:
                // displayBalance
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }
}
