package president.domain;

import president.domain.entity.Player;
import president.domain.entity.Room;
import president.domain.valueobjects.AccessConfig;

public class Main {

    public static void main(String[] args) {

        final var p1 = Player.of("Lara");
        final var p2 = Player.of("Mel");
        final var p3 = Player.of("feericles");
        final var p4 = Player.of("doquinha-e-coxinha");

        final var room = Room.of(p1, AccessConfig.ofPrivate(4));

        room.addPlayer(p2);
        room.addPlayer(p3);
//        room.addPlayer(p4);
////
//        room.toSorting();
//
//        room.choiceCard(p1);
//        room.choiceCard(p2);
//        room.choiceCard(p3);
//        room.choiceCard(p4);
//
//        room.sortPlayers();

//        room.dealCards();

//        print(room);

        room.dealCards();

        System.out.println("-------------------------");
        p1.getCards().forEach(System.out::println);
        System.out.println(p1.getCards().size());

        System.out.println("-------------------------");
        p2.getCards().forEach(System.out::println);
        System.out.println(p2.getCards().size());

        System.out.println("-------------------------");
        p3.getCards().forEach(System.out::println);
        System.out.println(p3.getCards().size());

        System.out.println("-------------------------");
        p4.getCards().forEach(System.out::println);
        System.out.println(p4.getCards().size());

        System.out.println("-------------------------");

    }

    private static void print(final Room room) {
        System.out.println("Room id: " + room.getRoomId().getValue());
        System.out.println("Owner: " + room.getOwner().getNickName());
        System.out.println("Players: " + room.getPlayers().size());
        room.getPlayers().forEach((p) -> {
            if (p.getChoiceCard() != null)
                System.out.println("Player: %s  | Card: %s".formatted(p.getNickName(), p.getChoiceCard().getCardValue()));
        });
        System.out.println("Status: " + room.getStatus());
        System.out.println("Link value: " + room.getRoomLink().getValue());
        System.out.println("Min players: " + room.getAccessConfig().getMinPlayers());
        System.out.println("Max players: " + room.getAccessConfig().getMaxPlayers());
        System.out.println("Time of next player: " + room.getAccessConfig().getTimeOfNextPlayer());
        System.out.println("Visibility: " + room.getAccessConfig().getVisibility());
    }
}
