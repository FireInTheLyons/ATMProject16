/**
 * Author : John Lyons
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Instance Variables
    Scanner keyboard = new Scanner(System.in);
    ATM atm = new ATM();
    boolean exit;

    public static void main(String args[]) {
        // calls launchMain.
        Main main = new Main();
        main.launchMain();
    }

    public void launchMain() {
        // calls printOpeningMessage and printMainMenu
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
        System.out.println("\n#########################################################");
        System.out.println("#- - - Welcome to the Java Virtual ATM Application - - -#");
        System.out.println("#########################################################");

    }

    private void printMainMenu() {
        displayHeader(" Please choose one of the following options :  ");
        System.out.println("\n1) Create a New Account.");
        System.out.println("2) Deposit Cash.");
        System.out.println("3) Withdraw Cash.");
        System.out.println("4) Display Account Balance.");
        System.out.println("0) Exit the application.");

    }

    private int getInput() {
        // returns an integer value to launchMain
        // (cont'd) which then calls selectChoice with that choice.
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
        // This will call one of the actions based on what was selected.
        switch (choice) {
            case 0:
                // exit
                System.out.println("Thank you for using our ATM application! Goodbye!");
                System.exit(0);
                break;
            case 1: {
                try {
                    createNewAccount();
                } catch (InvalidAccountTypeException ex) {
                    System.out.println("Account was not created successfully.");
                }
            }

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

    private String askQuestion(String question, List<String> answers) {
        // Takes a Question, prints it out and poses it to the user.
        String response = "";
        Scanner input = new Scanner(System.in);
        boolean choices = !((answers == null) || answers.size() == 0);
        boolean firstRun = true;

        do {
            if (!firstRun) {
                System.out.println("Invalid selection. Please try again.");
            }
            System.out.print(question);
            if (choices) {
                System.out.print("(");
                for (int i = 0; i < answers.size(); ++i) {
                    System.out.print(answers.get(i) + "/");
                }
                System.out.print(answers.get(answers.size() - 1));
                System.out.print("): ");
            }
            response = input.nextLine();
            firstRun = false;
            if (!choices) {
                break;
            }
        } while (!answers.contains(response));
        return response;

    }

    private double getDeposit(String accountType) {
        // This is given an account type and it keeps going
        // (cont'd) until it gets the one that it needs.
        double initialDeposit = 0;
        Boolean valid = false;
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
        return initialDeposit;
    }

    private void createNewAccount() throws InvalidAccountTypeException {
        // This will go through and ask all the questions until

        displayHeader("Create an Account");

        // Get account information
        String accountType = askQuestion("\nPlease enter an account type : ", Arrays.asList("checking", "savings"));
        String forename = askQuestion("Please enter your first name: ", null);
        String surname = askQuestion("Please enter your last name: ", null);
        String ppsno = askQuestion("Please enter your PPS Number: ", null);

        double initialDeposit = getDeposit(accountType);

        // Now we can create an account.
        Account account;
        if (accountType.equalsIgnoreCase("checking")) {
            account = new checkingAccount(initialDeposit);
        } else if (accountType.equalsIgnoreCase("savings")) {
            account = new savingsAccount(initialDeposit);
        } else {
            throw new InvalidAccountTypeException();
            // this is an exception type that I created.
        }
        Person person = new Person(forename, surname, ppsno, account);
        atm.addPerson(person);
    }

    private double getAmount(String question) {
        System.out.println(question);
        double amount = 0;
        try {
            amount = Double.parseDouble(keyboard.nextLine());
        } catch (NumberFormatException e) {
            // ie. it would catch a problem if you, for example, entered
            // a String of letters.
            amount = 0;
        }
        return amount;
    }

    private void depositCash() {

        displayHeader("Deposit Cash");
        // Note: Need to ask which account they want to deposit into.
        int account = selectAccount();
        // that will return the account that we want
        // to make a deposit to.
        if (account >= 0) {
            double amount = getAmount("\nHow much would you like to deposit? : ");
            // BELOW...
            // Step 1: From the atm class it will get a Persons account,
            // Step 2: then get their checking or savings account,
            // Step 3: then make a deposit on it with the desired amount.
            atm.getPerson(account).getAccount().deposit(amount);
        }
    }

    private void withdrawCash() {

        displayHeader("Withdraw Cash");
        int account = selectAccount();

        if (account >= 0) {
            double amount = getAmount("How much would you like to withdraw? : ");

            atm.getPerson(account).getAccount().withdraw(amount);
        }
    }

    private void displayBalance() {

        displayHeader("All ATM Accounts");
        int account = selectAccount();

        if (account >= 0) {
            displayHeader("Account Details");
            System.out.println(atm.getPerson(account));
            //System.out.println(atm.getPerson(account).getAccount());
        }

    }

    private void displayHeader(String message) {
        System.out.println();
        int width = message.length() + 6; //3 on each side
        StringBuilder sb = new StringBuilder(); // found StringBuilder online
        sb.append("+"); // plus at the start.
        for (int i = 0; i < width; ++i) {
            sb.append("-");
        }
        sb.append("+"); // plus at the end.
        System.out.println(sb.toString());
        System.out.println("|   " + message + "   |");
        System.out.println(sb.toString());
    }

    private int selectAccount() {

        ArrayList<Person> persons = atm.getPersons();
        // so for each person that's in the list it will
        // print out a line with their info.
        // The list will be numbered, incrementally.
        // and we'll pick an account based on the number.
        if (persons.size() <= 0) {
            // ie. nobody has made an account yet.

            System.out.println("\nNo accounts are currently present.");
            return -1; // minus one to go backwards.
        } else {
            System.out.println("\nPlease choose an account based on the number assigned . . . \n");
        }

        for (int i = 0; i < persons.size(); i++) {
            System.out.println("\t" + "#" + (i + 1) + " -- " + persons.get(i).personInfo());
        }
        int account;
        System.out.print("\nPlease enter your selection : ");
        try {
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        } catch (NumberFormatException e) {
            account = -1; // go backwards
        }

        if (account < 0 || account > persons.size()) {
            System.out.println("Invalid account selected.");
            account = -1;
        }

        return account;
    }
}
