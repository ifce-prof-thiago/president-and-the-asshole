package president.domain.entity;

import president.domain.valueobjects.AccessConfig;
import president.domain.valueobjects.RoomLink;
import president.domain.valueobjects.identifier.PlayerId;
import president.domain.valueobjects.identifier.RoomId;

public class Room {

    private final RoomId roomId;
    private PlayerId ownerId;
    private final RoomLink roomLink;
    private AccessConfig accessConfig;

    private Room(
            final RoomId roomId,
            final PlayerId ownerId,
            final RoomLink roomLink,
            final AccessConfig accessConfig) {

        this.roomId = roomId;
        this.ownerId = ownerId;
        this.roomLink = roomLink;
        this.accessConfig = accessConfig;

    }

    public static Room of(final PlayerId ownerId, final AccessConfig accessConfig) {

        final var roomId = RoomId.of();
        final var roomLink = RoomLink.of();

        return new Room(roomId, ownerId, roomLink, accessConfig);

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

}
