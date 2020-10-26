package animalFarm;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String name = in.nextLine();
        int age = Integer.parseInt(in.nextLine());

        try {
            Chicken chicken = new Chicken(name, age);
            System.out.println(chicken.toString());
        } catch (InvalidParameterException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
