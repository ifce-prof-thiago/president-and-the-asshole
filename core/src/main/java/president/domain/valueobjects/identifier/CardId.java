package president.domain.valueobjects.identifier;

import java.util.UUID;

public class CardId extends  BaseId {

    private CardId(final UUID value) {
        super(value);
    }

    public static CardId of() {
        return new CardId(UUID.randomUUID());
    }

    public static CardId of(final UUID value) {
        return new CardId(value);
    }

}
