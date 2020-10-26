package sayHelloExtended;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();

        people.add(new Bulgarian("Pesho"));
        people.add(new European("Pesho"));
        people.add(new Chinese("Pesho"));

        for (Person person : people) {
            print(person);
        }
    }

    private static void print(Person person) {
        System.out.println(person.sayHello());
    }
}
