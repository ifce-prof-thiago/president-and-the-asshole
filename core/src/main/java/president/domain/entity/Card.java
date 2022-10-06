package president.domain.entity;

import president.domain.valueobjects.CardValue;
import president.domain.valueobjects.Suit;
import president.domain.valueobjects.identifier.CardId;

public class Card {

    private final CardId cardId;
    private final CardValue cardValue;
    private final Suit suit;

    private Card(final CardId cardId, final CardValue cardValue, final Suit suit) {
        this.cardId = cardId;
        this.cardValue = cardValue;
        this.suit = suit;
    }

    public static Card of(final CardValue cardValue, final Suit suit) {
        return new Card(CardId.of(), cardValue, suit);
    }

    public CardId getCardId() {
        return cardId;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

}
