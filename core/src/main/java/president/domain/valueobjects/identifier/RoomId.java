package president.domain.valueobjects.identifier;

import java.util.UUID;

public class RoomId extends  BaseId {

    private RoomId(UUID value) {
        super(value);
    }

    public static RoomId of() {
        return new RoomId(UUID.randomUUID());
    }

    public static RoomId of(UUID value) {
        return new RoomId(value);
    }

}
