package president.domain.valueobjects;

public class AccessConfig implements ValueObject {

    private final int minPlayers;
    private final int maxPlayers;
    private final int timeOfNextPlayer;

    private AccessConfig(
            final int minPlayers,
            final int maxPlayers,
            final int timeOfNextPlayer) {

        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.timeOfNextPlayer = timeOfNextPlayer;

    }

    public static AccessConfig of(
            final int minPlayers,
            final int maxPlayers) {

        if (minPlayers < 4) {
            throw new RuntimeException("'min players' can't be less than 4");
        }

        if (minPlayers > 13) {
            throw new RuntimeException("'min players' can't be greater than " + 13);
        }

        if (maxPlayers > 13) {
            throw new RuntimeException("'max players' can't be greater than 13");
        }

        if (maxPlayers < minPlayers) {
            throw new RuntimeException("'max players' can't be less than " + minPlayers);
        }

        return new AccessConfig(minPlayers, maxPlayers, 15);
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getTimeOfNextPlayer() {
        return timeOfNextPlayer;
    }

}
