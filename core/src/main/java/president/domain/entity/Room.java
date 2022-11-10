package president.domain.entity;

import president.domain.valueobjects.*;
import president.domain.valueobjects.identifier.PlayerId;
import president.domain.valueobjects.identifier.RoomId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Room {

    private final static int N_DECKS = 2;
    private final RoomId roomId;
    private Player owner;
    private final RoomLink roomLink;
    private AccessConfig accessConfig;
    private List<Player> players;
    private List<PlayerId> playersToChoice;
    private List<Card> cardsToChoice;

    private List<Card> cardsToDeal;
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
        this.cardsToDeal = new ArrayList<>();

        initializePlayers(players);

        this.status = Status.WAITING;

    }

    public void dealCards() {

        var qtyCardsOfRemove = (N_DECKS * 52) % players.size();

        for (int i = 0; i < N_DECKS; i++) {
            for (final var card : Deck.of().getCards()) {
                if (card.getCardValue().equals(CardValue.THREE) && qtyCardsOfRemove > 0) {
                    qtyCardsOfRemove--;
                    continue;
                }
                cardsToDeal.add(card);
            }
        }

        Collections.shuffle(cardsToDeal);

        var currentPlayer = 0;

        for (final var card : cardsToDeal) {
            players.get(currentPlayer).addCard(card);
            currentPlayer = (currentPlayer + 1) % players.size();
        }

    }

    private void initializePlayers(List<Player> players) {
        this.playersToChoice = new ArrayList<>(
                players.stream().map(p -> p.getPlayerId()).toList()
        );
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

        return card;

    }

    public void sortPlayers() {
        if (playersToChoice.isEmpty()) {
            players.sort(Player::compareTo);
            toThrowing();
        }
    }

    void toThrowing() {

        if (status != Status.IN_SORTING && status != Status.ROUND_FINISHED) {
            throw new IllegalStateException("Room is not in sorting or round finished");
        }

        this.status = Status.THROWING_CARDS;
    }

    public void toInGame() {

        if (status != Status.THROWING_CARDS) {
            throw new IllegalStateException("Room is not throwing cards");
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

    public List<Card> getCardsToDeal() {
        return Collections.unmodifiableList(cardsToDeal);
    }

    public enum Status {
        WAITING, IN_SORTING, THROWING_CARDS, IN_GAME, ROUND_FINISHED, GAME_FINISHED

    }
}
