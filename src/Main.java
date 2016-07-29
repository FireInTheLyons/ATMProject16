/**
 * Author : John Lyons
 */

import java.util.ArrayList;
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
        atm.addPerson(person);
    }

    private void depositCash() {
        // Note: Need to ask which account they want to deposit into.
        int account = selectAccount();
        // that will return the account that we want
        // to make a deposit to.
        if (account >= 0) {
            System.out.println("Enter the amount that you would like to Deposit : ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                // ie. it would catch a problem if you, for example, entered
                // a String of letters.
                amount = 0;
            }
            // BELOW...
            // Step 1: From the atm class it will get a Persons account,
            // Step 2: then get their checking or savings account,
            // Step 3: then make a deposit on it with the desired amount.
            atm.getPerson(account).getAccount().deposit(amount);
        }
    }

    private void withdrawCash() {
        //pasted from above.
        int account = selectAccount();

        if (account >= 0) {
            System.out.println("Enter the amount that you would like to Deposit : ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {

                amount = 0;
            }

            atm.getPerson(account).getAccount().deposit(amount);
        }
    }

    private void displayBalance() {

    }

    private int selectAccount() {

        ArrayList<Person> persons = atm.getPersons();
        // so for each person that's in the list it will
        // print out a line with their info.
        // The list will be numbered, incrementally.
        // and we'll pick an account based on the number.
        if (persons.size() <= 0) {
            // ie. nobody has made an account yet.

            System.out.println("No accounts are currently present.");
            return -1; // minus one to go backwards.
        }
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i + 1) + "# " + persons.get(i).personInfo());
        }
        int account = 0;
        System.out.println("Please enter your selection : ");
        try {
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        } catch (NumberFormatException e) {
            account = -1; // backwards
        }
        return account;
    }
}
