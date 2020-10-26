package cardSuit;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        CardSuits[] cards = CardSuits.values();

        System.out.println(in.nextLine() + ":");

        for (CardSuits card : cards) {
            System.out.printf("Ordinal value: %d; Name value: %s\n", card.ordinal(), card);
        }
    }
}
