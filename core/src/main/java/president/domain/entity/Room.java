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
    private Player owner;
    private final RoomLink roomLink;
    private AccessConfig accessConfig;
    private List<Player> players;
    private List<PlayerId> playersToChoice;
    private List<Card> cardsToChoice;
    private Status status;

    private Room(
            final RoomId roomId,
            final Player owner,
            final RoomLink roomLink,
            final AccessConfig accessConfig,
            final List<Player> players) {

        this.roomId = roomId;
        this.owner = owner;
        this.roomLink = roomLink;
        this.accessConfig = accessConfig;
        this.players = players;
        this.cardsToChoice = new ArrayList<>();

        this.playersToChoice = new ArrayList<>(
                players.stream().map(p -> p.getPlayerId()).toList()
        );

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

        if (players.size() < accessConfig.getMinPlayers()) {
            throw new RuntimeException("'min players' can't be less than four!");
        }

        for (final var cardValue : CardValue.values()) {
            cardsToChoice.add(Card.of(cardValue, Suit.CLUBS));
        }

        Collections.shuffle(cardsToChoice);

    }

    public static Room of(final Player owner, final AccessConfig accessConfig) {

        final var roomId = RoomId.of();
        final var roomLink = RoomLink.of();
        final var players = new ArrayList<Player>();
        players.add(owner);

        return new Room(
                roomId,
                owner,
                roomLink,
                accessConfig,
                players
        );

    }

    public Card choiceCard(final Player player) {

        if (status != Status.IN_SORTING) {
            throw new IllegalStateException("Room is not in sorting");
        }

        if (!playersToChoice.contains(player.getPlayerId())) {
            throw new IllegalArgumentException(
                    "Can't choice card for player " + player.getPlayerId().getValue()
            );
        }

        final var card = cardsToChoice.get(0);
        player.choiceCard(card);
        cardsToChoice.remove(card);
        playersToChoice.remove(player.getPlayerId());

        if (playersToChoice.isEmpty()) {
            toInGame();
        }

        return card;

    }

    public void sortPlayers() {
        players.sort(Player::compareTo);
    }

    public void toInGame() {

        if (status != Status.IN_SORTING && status != Status.ROUND_FINISHED) {
            throw new IllegalStateException("Room is not in sorting or round finished");
        }

        status = Status.IN_GAME;

    }

    public void addPlayer(final Player player) {

        if (players.size() >= accessConfig.getMaxPlayers()) {
            throw new RuntimeException("This room is full!");
        }

        this.players.add(player);
        this.playersToChoice.add(player.getPlayerId());

    }

    public RoomId getRoomId() {
        return roomId;
    }

    public Player getOwner() {
        return owner;
    }

    public RoomLink getRoomLink() {
        return roomLink;
    }

    public AccessConfig getAccessConfig() {
        return accessConfig;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        WAITING, IN_SORTING, IN_GAME, ROUND, ROUND_FINISHED, FINISHED

    }
}
