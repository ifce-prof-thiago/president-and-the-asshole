package president.domain.entity;

import president.domain.valueobjects.identifier.PlayerId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Comparable<Player> {

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

    public void addCard(final Card card) {
        this.cards.add(card);
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
    public int compareTo(final Player player) {
        return this.choiceCard.compareTo(player.choiceCard);
    }

}
