package deckOfCards;

public class Card {

    private CardRank rank;
    private CardSuits suit;
    private int power;

    public Card(CardRank rank, CardSuits suit) {
        this.rank = rank;
        this.suit = suit;
        this.power = this.rank.getPower() + this.suit.getPower();
    }

    public int getPower() {
        return power;
    }
}
