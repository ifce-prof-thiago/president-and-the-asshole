package president.domain.valueobjects;

import president.domain.entity.Card;
import president.domain.entity.Room;
import president.domain.valueobjects.identifier.CardId;
import president.domain.valueobjects.identifier.PlayerId;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        final var p1 = PlayerId.of();
        final var p2 = PlayerId.of();
        final var p3 = PlayerId.of();
        final var p4 = PlayerId.of();

        final var room = Room.of(p1, AccessConfig.of(5, 9));
        room.addPlayer(p2);
        room.addPlayer(p3);
        room.addPlayer(p4);

        room.toSorting();

        final var card1 = room.choiceCard(p1);
        System.out.println(card1.getCardValue());
        final var card2 = room.choiceCard(p2);
        System.out.println(card2.getCardValue());


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
