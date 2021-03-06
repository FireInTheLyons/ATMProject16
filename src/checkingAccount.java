/**
 * Author : John Lyons
 */

public class checkingAccount extends Account {
    private static String accountType = "Checking";

    checkingAccount(double initialDeposit) {
        //super();
        this.setBalance(initialDeposit);
        this.checkInterest(0);
        //if(initialDeposit > 10000) {
        //this.setInterest(0.05);
        //} else {
        //this.setInterest(0.02);
        //}
    }


    public String toString() {
        return "Account Type : " + accountType + " Account\n" +
                "Account Number : " + this.getAccountNumber() + "\n" +
                "Balance : " + this.getBalance() + "\n" +
                "Interest : " + this.getInterest() + "%\n";
    }
}


