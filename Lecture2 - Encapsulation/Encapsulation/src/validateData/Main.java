package validateData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        List<Person> people = new ArrayList<>();

        while (n-- > 0) {
            String[] input = in.nextLine().split(" ");
            try {
                people.add(new Person(input[0], input[1], Integer.parseInt(input[2]), Double.parseDouble(input[3])));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        double bonus = Double.parseDouble(in.nextLine());

        for (Person person : people) {
            person.increaseSalary(bonus);
            System.out.println(person);
        }


    }
}
