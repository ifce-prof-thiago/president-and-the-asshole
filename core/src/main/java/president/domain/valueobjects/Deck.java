package president.domain.valueobjects;

import president.domain.entity.Card;

public class Deck implements ValueObject {

    private final Card[] cards;

    private Deck() {
        this.cards = new Card[52];

        for (final var card : CardValue.values()) {
            for (final var suit : Suit.values()) {
                cards[card.ordinal() + suit.ordinal() * 13] = Card.of(card, suit);
            }
        }
    }


    public static Deck of() {
        return new Deck();
    }

    public Card[] getCards() {
        return cards;
    }


}
