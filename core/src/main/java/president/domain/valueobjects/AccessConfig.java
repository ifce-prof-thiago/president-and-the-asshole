package president.domain.valueobjects;

public class AccessConfig implements ValueObject {

    private final int minPlayers;
    private final int maxPlayers;
    private final int timeOfNextPlayer;
    private final Visibility visibility;

    private AccessConfig(
            final int minPlayers,
            final int maxPlayers,
            final int timeOfNextPlayer,
            final Visibility visibility) {

        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.timeOfNextPlayer = timeOfNextPlayer;
        this.visibility = visibility;

    }

    public static AccessConfig of(
            final int minPlayers,
            final int maxPlayers) {

        return AccessConfig.of(
                minPlayers,
                maxPlayers,
                Visibility.PUBLIC
        );
    }

    public static AccessConfig of(
            final int minPlayers,
            final int maxPlayers,
            final Visibility visibility) {

        if (minPlayers < 4) {
            throw new RuntimeException(
                    "'min players' can't be less than 4"
            );
        }

        if (minPlayers > 13) {
            throw new RuntimeException(
                    "'min players' can't be greater than " + 13
            );
        }

        if (maxPlayers > 13) {
            throw new RuntimeException(
                    "'max players' can't be greater than 13"
            );
        }

        if (maxPlayers < minPlayers) {
            throw new RuntimeException(
                    "'max players' can't be less than " + minPlayers
            );
        }

        return new AccessConfig(
                minPlayers,
                maxPlayers,
                15,
                visibility
        );

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

    public Visibility getVisibility() {
        return visibility;
    }

    public enum Visibility {
        PUBLIC, PRIVATE
    }

}
