package president.domain.entity;

import president.domain.valueobjects.CardValue;
import president.domain.valueobjects.identifier.PlayerId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player implements  Comparable<Player> {

    private PlayerId playerId;
    private String nickName;
    private List<Card> cards;
    private Card choiceCard;

    private Player(
            final PlayerId playerId,
            final String nickName,
            final List<Card> cards,
            final Card choiceCard) {

        this.playerId = playerId;
        this.nickName = nickName;
        this.cards = cards;
        this.choiceCard = choiceCard;

    }

    public static Player of(final String nickName) {

        return new Player(
                PlayerId.of(),
                nickName,
                new ArrayList<>(),
                null
        );

    }

    public void choiceCard(final Card card) {
        this.choiceCard = card;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getNickName() {
        return nickName;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getChoiceCard() {
        return choiceCard;
    }

    @Override
    public int compareTo(final Player o) {
        return o.choiceCard.getCardValue().getValue() - this.choiceCard.getCardValue().getValue();
    }
}
