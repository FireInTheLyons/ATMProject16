/**
 * Author : John Lyons
 */

public class Account {
    private static int numberOfAccounts = 1000000;
    private double balance = 0;
    private double interest = 0.02;
    private int accountNumber;

    Account() {
        accountNumber = numberOfAccounts++;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest * 100;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void withdraw(double amount) {
        if (amount + 5 > balance) {
            System.out.println("You have insufficient funds.");
            return;
        }
        balance -= amount + 5;
        checkInterest(0);
        System.out.println("You have withdrawn €" + amount + " and incurred a fee of five euros.");
        System.out.println("You now have a balance of €" + balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error. Please specify an amount greater than 0.");
            return;
        }
        checkInterest(amount);
        amount = amount + amount * interest;
        balance += amount;
        System.out.println("You have deposited €" + amount + " euros with an interest rate of " + (interest * 100) + "%");
        System.out.println("You now have a balance of €" + balance);
    }

    public void checkInterest(double amount) {
        if (balance + amount > 10000) {
            interest = 0.05;
        } else {
            interest = 0.02;
        }
    }
}
