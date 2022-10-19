package president.domain.valueobjects;

public class AccessConfig implements ValueObject {

    private final static int MIN_PLAYERS = 4;
    private final int maxPlayers;
    private final int timeOfNextPlayer;
    private final Visibility visibility;

    private AccessConfig(
            final int maxPlayers,
            final int timeOfNextPlayer,
            final Visibility visibility) {

        this.maxPlayers = maxPlayers;
        this.timeOfNextPlayer = timeOfNextPlayer;
        this.visibility = visibility;

    }

    public static AccessConfig ofPublic(
            final int maxPlayers) {

        return AccessConfig.of(
                maxPlayers,
                Visibility.PUBLIC
        );
    }

    public static AccessConfig ofPrivate(
            final int maxPlayers) {

        return AccessConfig.of(
                maxPlayers,
                Visibility.PRIVATE
        );
    }

    public static AccessConfig of(
            final int maxPlayers,
            final Visibility visibility) {

        if (maxPlayers < AccessConfig.MIN_PLAYERS) {
            throw new RuntimeException("'max players' can't be less than four");
        }

        if (maxPlayers > 13) {
            throw new RuntimeException(
                    "'max players' can't be greater than 13"
            );
        }

        return new AccessConfig(
                maxPlayers,
                15,
                visibility
        );

    }

    public int getMinPlayers() {
        return MIN_PLAYERS;
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
