package person;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        int age = Integer.parseInt(in.nextLine());

        try {
            Child child = new Child(name, age);
            System.out.println(child.toString());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
