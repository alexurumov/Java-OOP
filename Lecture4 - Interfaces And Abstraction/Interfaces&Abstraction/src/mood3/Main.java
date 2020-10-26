package mood3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] input = in.nextLine().split(" \\| ");

        Character hero;
        if (input[1].equals("Demon")) {
            hero = new Demon(input[0], Integer.parseInt(input[3]), Double.parseDouble(input[2]));
        } else {
            hero = new Archangel(input[0], Integer.parseInt(input[3]), Integer.parseInt(input[2]));
        }

        System.out.println(hero.toString());
    }
}
