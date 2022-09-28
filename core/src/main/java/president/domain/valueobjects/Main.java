package president.domain.valueobjects;

import president.domain.entity.Room;
import president.domain.valueobjects.identifier.PlayerId;

public class Main {

    public static void main(String[] args) {

        final var playerId = PlayerId.of();

        final var room = Room.of(playerId, AccessConfig.of(5, 9));

        System.out.println("Room id: " + room.getRoomId().getValue());
        System.out.println("Owner id: " + room.getOwnerId().getValue());
        System.out.println("Link value: " + room.getRoomLink().getValue());
        System.out.println("Min players: " + room.getAccessConfig().getMinPlayers());
        System.out.println("Max players: " + room.getAccessConfig().getMaxPlayers());

    }
}
