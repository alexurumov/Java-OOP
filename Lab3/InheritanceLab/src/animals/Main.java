package animals;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        try {
            while (!line.equals("Beast!")) {

                String[] input = in.nextLine().split("\\s+");

                switch (line) {

                    case "Dog":
                        Dog dog = new Dog(input[0], Integer.parseInt(input[1]), input[2]);
                        System.out.println(dog.toString());
                        break;
                    case "Cat":
                        Cat cat = new Cat(input[0], Integer.parseInt(input[1]), input[2]);
                        System.out.println(cat.toString());
                        break;
                    case "Frog":
                        Frog frog = new Frog(input[0], Integer.parseInt(input[1]), input[2]);
                        System.out.println(frog.toString());
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(input[0], Integer.parseInt(input[1]), input[2]);
                        System.out.println(kitten.toString());
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(input[0], Integer.parseInt(input[1]), input[2]);
                        System.out.println(tomcat.toString());
                        break;

                }

                line = in.nextLine();
            }

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
