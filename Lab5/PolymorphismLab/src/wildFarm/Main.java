package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        List<Mammal> animals = new ArrayList<>();

        String line = in.nextLine();

        while (!line.equals("End")) {

            String[] animalInput = line.split("\\s+");

            Mammal mammal = null;

            String type = animalInput[0];
            String name = animalInput[1];
            Double weight = Double.parseDouble(animalInput[2]);
            String livingRegion = animalInput[3];

            if (animalInput.length == 5) {
                String breed = animalInput[4];
                mammal = new Cat(type, name, weight, livingRegion, breed);
            } else {

                switch (type) {
                    case "Mouse":
                        mammal = new Mouse(type, name, weight, livingRegion);
                        break;
                    case "Zebra":
                        mammal = new Zebra(type, name, weight, livingRegion);
                        break;
                    case "Tiger":
                        mammal = new Tiger(type, name, weight, livingRegion);
                        break;
                    default:
                        break;
                }
            }

            if (mammal != null) {
                animals.add(mammal);
            }

            String[] foodInput = in.nextLine().split("\\s+");

            Food food = null;

            if (foodInput[0].equals("Meat")) {
                food = new Meat(Integer.parseInt(foodInput[1]));
            } else {
                food = new Vegetable((Integer.parseInt(foodInput[1])));
            }

            mammal.makeSound();
            try {
                mammal.eat(food);
            } catch (IllegalArgumentException error) {
                System.out.println(error.getMessage());
            }

            line = in.nextLine();

        }

        animals.forEach(System.out::println);
    }
}
