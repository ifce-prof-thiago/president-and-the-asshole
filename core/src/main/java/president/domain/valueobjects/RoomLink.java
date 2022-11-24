package president.domain.valueobjects;

import java.util.UUID;

public class RoomLink implements ValueObject {

    private final UUID value;

    private RoomLink(final UUID value) {
        this.value = value;
    }

    public static RoomLink of() {
        return new RoomLink(UUID.randomUUID());
    }

    public String getValue() {
        return value.toString();
    }
}
