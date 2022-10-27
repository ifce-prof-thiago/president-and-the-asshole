package president.domain.valueobjects;

import java.util.Comparator;

public enum CardValue implements ValueObject {

    ACE(12),
    TWO(13),
    THREE(1),
    FOUR(2),
    FIVE(3),
    SIX(4),
    SEVEN(5),
    EIGHT(6),
    NINE(7),
    TEN(8),
    JACK(9),
    QUEEN(10),
    KING(11);

    private final int value;

    CardValue(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compare(final CardValue c) {
        return this.getValue() - c.getValue();
    }
}
