package deckOfCards;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String rank = in.nextLine();
        String suit = in.nextLine();

        Card card = new Card(CardRank.valueOf(rank), CardSuits.valueOf(suit));

        System.out.printf("Card name: %s of %s; Card power: %d\n", rank, suit, card.getPower());

    }
}
