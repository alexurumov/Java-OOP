package sayHello;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Person bg = new Bulgarian("Pesho");
        Person eu = new European("Pesho");
        Person ch = new Chinese("Pesho");

        List<Person> people = new ArrayList<>();
        people.add(bg);
        people.add(eu);
        people.add(ch);

        for (Person person : people) {
            System.out.println(person.sayHello());
        }
    }
}
