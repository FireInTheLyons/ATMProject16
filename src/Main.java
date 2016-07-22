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

    }

    private void printMainMenu() {

    }

    private int getInput() {
        return 0;
    }

    private void selectChoice(int choice) {

    }

}
