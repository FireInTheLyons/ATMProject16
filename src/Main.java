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
                createNewAccount();
                break;
            case 2:
                depositCash();
                break;
            case 3:
                withdrawCash();
                break;
            case 4:
                displayBalance();
                break;
            default:
                System.out.println("Unknown error has occurred.");
        }
    }

    private void createNewAccount() {
        String forename, surname, ppsno, accountType = "";
        double initialDeposit = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Please enter an account type (either checking or savings) : ");
            accountType = keyboard.nextLine();
            if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
                valid = true;
            } else {
                System.out.println("Invalid account type selected.\nIt has to be either 'checking'"
                        + "or 'savings'. \nPlease choose again.");
            }
        }
        System.out.println("Please enter your first name: ");
        forename = keyboard.nextLine();
        System.out.print("Please enter your last name: ");
        surname = keyboard.nextLine();
        System.out.print("Please enter your PPS Number: ");
        ppsno = keyboard.nextLine();
        valid = false;
        while (!valid) {
            System.out.print("Please enter an initial deposit: ");
            try {
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Deposit must be a number.");
            }
            if (accountType.equalsIgnoreCase("checking")) {
                if (initialDeposit < 200) {
                    System.out.println("Checking accounts require a minimum of 200 euros to open.");
                } else {
                    valid = true;
                }
            } else if (accountType.equalsIgnoreCase("savings")) {
                if (initialDeposit < 100) {
                    System.out.println("Savings accounts require a minimum of 100 euros to open.");
                } else {
                    valid = true;
                }
            }
        }
        // Now we can create an account.
        Account account;
        if (accountType.equalsIgnoreCase("checking")) {
            account = new checkingAccount(initialDeposit);
        } else {
            account = new savingsAccount(initialDeposit);
        }
        Person person = new Person(forename, surname, ppsno, account);
    }

    private void depositCash() {

    }

    private void withdrawCash() {

    }

    private void displayBalance() {

    }
}
