package president.domain.entity;

import president.domain.valueobjects.AccessConfig;
import president.domain.valueobjects.RoomLink;
import president.domain.valueobjects.identifier.PlayerId;
import president.domain.valueobjects.identifier.RoomId;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final RoomId roomId;
    private PlayerId ownerId;
    private final RoomLink roomLink;
    private AccessConfig accessConfig;

    private List<PlayerId> players;

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

    public void addPlayer(final PlayerId playerId) {

        if (players.size() >= accessConfig.getMaxPlayers()) {
            throw new RuntimeException("This room is full!");
        }

        this.players.add(playerId);

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

    public enum status {

        IN_LOBBY, SORTING, READY, ROUND, ROUND_FINISHED, FINISHED

    }
}
