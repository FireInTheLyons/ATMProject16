/**
 * Author : John Lyons
 */

public class Person {

    private final String forename;
    private final String surname;
    private final String ppsno;
    private final Account account;

    Person(String forename, String surname, String ppsno, Account account) {
        this.forename = forename;
        this.surname = surname;
        this.ppsno = ppsno;
        this.account = account;
    }

    @Override
    public String toString() {
        return "\n - Customer Information - \n" +
                "First Name : " + forename +
                "\nLast Name : " + surname +
                "\nPPS Number : " + ppsno + "\n" +
                account;
    }

    public String personInfo() {
        return
                "Name : " + forename +
                        " " + surname +
                        " - " +
                        "Account Number : " + account.getAccountNumber();
    }

    Account getAccount() {
        return account;
    }

}
