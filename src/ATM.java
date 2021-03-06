/**
 * Author : John Lyons
 */

import java.util.ArrayList;

public class ATM {

    ArrayList<Person> persons = new ArrayList<Person>();

    void addPerson(Person person) {
        persons.add(person);
    }

    Person getPerson(int account) {
        return persons.get(account);
    }

    ArrayList<Person> getPersons() {
        return persons;
    }

}
