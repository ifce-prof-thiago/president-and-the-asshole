package president.domain.entity;

import president.domain.valueobjects.AccessConfig;
import president.domain.valueobjects.CardValue;
import president.domain.valueobjects.RoomLink;
import president.domain.valueobjects.Suit;
import president.domain.valueobjects.identifier.PlayerId;
import president.domain.valueobjects.identifier.RoomId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {

    private final RoomId roomId;
    private PlayerId ownerId;
    private final RoomLink roomLink;
    private AccessConfig accessConfig;
    private List<PlayerId> players;
    private List<Card> cardsToChoice;
    private List<PlayerId> playersToChoice;
    private Status status;

    private Room(
            final RoomId roomId,
            final PlayerId ownerId,
            final RoomLink roomLink,
            final AccessConfig accessConfig,
            final List<PlayerId> players) {

        this.roomId = roomId;
        this.ownerId = ownerId;
        this.roomLink = roomLink;
        this.accessConfig = accessConfig;
        this.players = players;
        this.cardsToChoice = new ArrayList<>();
        this.playersToChoice = new ArrayList<>(players);
        this.status = Status.WAITING;

    }

    public void toSorting() {

        if (status != Status.WAITING) {
            throw new IllegalStateException("Room is not waiting");
        }

        shuffleCardsToChoice();
        this.status = Status.IN_SORTING;

    }

    private void shuffleCardsToChoice() {

        for (final var cardValue : CardValue.values()) {
            cardsToChoice.add(Card.of(cardValue, Suit.CLUBS));
        }

        Collections.shuffle(cardsToChoice);

    }

    public static Room of(final PlayerId ownerId, final AccessConfig accessConfig) {

        final var roomId = RoomId.of();
        final var roomLink = RoomLink.of();
        final var players = new ArrayList<PlayerId>();
        players.add(ownerId);

        return new Room(
                roomId,
                ownerId,
                roomLink,
                accessConfig,
                players
        );

    }

    public Card choiceCard(final PlayerId playerId) {

        if (status != Status.IN_SORTING) {
            throw new IllegalStateException("Room is not in sorting");
        }

        if (!playersToChoice.contains(playerId)) {
            throw new IllegalArgumentException(
                    "Can't choice card for player " + playerId.getValue()
            );
        }

        final var card = cardsToChoice.get(0);
        cardsToChoice.remove(card);
        playersToChoice.remove(playerId);

        if (playersToChoice.isEmpty()) {
            toInGame();
        }

        return card;

    }

    public void toInGame() {

        if (status != Status.IN_SORTING || status != Status.ROUND_FINISHED) {
            throw new IllegalStateException("Room is not in sorting");
        }

        status = Status.IN_GAME;

    }

    public void addPlayer(final PlayerId playerId) {

        if (players.size() >= accessConfig.getMaxPlayers()) {
            throw new RuntimeException("This room is full!");
        }

        this.players.add(playerId);
        this.playersToChoice.add(playerId);

    }

    public RoomId getRoomId() {
        return roomId;
    }

    public PlayerId getOwnerId() {
        return ownerId;
    }

    public RoomLink getRoomLink() {
        return roomLink;
    }

    public AccessConfig getAccessConfig() {
        return accessConfig;
    }

    public List<PlayerId> getPlayers() {
        return players;
    }

    public enum Status {
        WAITING, IN_SORTING, IN_GAME, ROUND, ROUND_FINISHED, FINISHED

    }
}
