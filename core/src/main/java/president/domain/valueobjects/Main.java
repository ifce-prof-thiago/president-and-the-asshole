package president.domain.valueobjects;

import president.domain.entity.Card;
import president.domain.entity.Room;
import president.domain.valueobjects.identifier.CardId;
import president.domain.valueobjects.identifier.PlayerId;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        final var room = Room.of(PlayerId.of(), AccessConfig.of(5, 9));
        room.addPlayer(PlayerId.of());
        room.addPlayer(PlayerId.of());
        room.addPlayer(PlayerId.of());
        room.addPlayer(PlayerId.of());

        final var cardsValues = CardValue.values();
        final var cardsClub = new ArrayList<Card>();

        for (final var cardValue : cardsValues) {
            cardsClub.add(Card.of(cardValue, Suit.CLUBS));
        }

        Collections.shuffle(cardsClub);

        final var lara = cardsClub.get(12);
        final var gabriel = cardsClub.get(11);

        System.out.println(lara.getCardValue());
        System.out.println(gabriel.getCardValue());

//        print(room);
    }

    private static void print(final Room room) {
        System.out.println("Room id: " + room.getRoomId().getValue());
        System.out.println("Owner id: " + room.getOwnerId().getValue());
        room.getPlayers().forEach(p -> {
            System.out.println("Player: " + p.getValue());
        });
        System.out.println("Link value: " + room.getRoomLink().getValue());
        System.out.println("Min players: " + room.getAccessConfig().getMinPlayers());
        System.out.println("Max players: " + room.getAccessConfig().getMaxPlayers());
        System.out.println("Time of next player: " + room.getAccessConfig().getTimeOfNextPlayer());
        System.out.println("Visibility: " + room.getAccessConfig().getVisibility());
    }
}
