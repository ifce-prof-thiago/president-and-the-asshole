package president.domain.valueobjects.identifier;

import java.util.UUID;

public class PlayerId extends  BaseId {

    private PlayerId(final UUID value) {
        super(value);
    }

    public static PlayerId of() {
        return new PlayerId(UUID.randomUUID());
    }

    public static PlayerId of(final UUID value) {
        return new PlayerId(value);
    }

}
