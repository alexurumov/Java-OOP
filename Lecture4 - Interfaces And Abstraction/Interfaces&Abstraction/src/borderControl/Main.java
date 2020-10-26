package borderControl;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Set<Identifiable> entrants = new HashSet<>();

        String line = in.nextLine();

        while (!line.equals("End")) {

            String[] entrantInfo = line.split("\\s+");

            Identifiable entrant;
            if (entrantInfo.length == 3) {
                String name = entrantInfo[0];
                int age = Integer.parseInt(entrantInfo[1]);
                String id = entrantInfo[2];
                entrant = new Citizen(name, age, id);
            } else {
                String model = entrantInfo[0];
                String id = entrantInfo[1];
                entrant = new Robot(id, model);
            }
            entrants.add(entrant);

            line = in.nextLine();
        }

        String fakeId = in.nextLine();
        entrants.stream().filter(e -> e.getId().endsWith(fakeId)).forEach(e -> System.out.println(e.getId()));
    }
}
