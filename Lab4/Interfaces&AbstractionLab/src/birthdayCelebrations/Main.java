package birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        List<Birthable> birthables = new ArrayList<>();

        while (!line.equals("End")) {

            String[] input = line.split("\\s+");

            String type = input[0];

            Birthable birthable;

            if (type.equals("Citizen")) {
                birthable = new Citizen(input[1], Integer.parseInt(input[2]), input[3], input[4]);
                birthables.add(birthable);
            } else if (type.equals("Pet")) {
                birthable = new Pet(input[1], input[2]);
                birthables.add(birthable);
            }

            line = in.nextLine();
        }

        String birthYear = in.nextLine();

        for (Birthable birthable : birthables) {
            if (birthable.getBirthDate().contains(birthYear)) {
                System.out.println(birthable.getBirthDate());
            }
        }
    }
}
