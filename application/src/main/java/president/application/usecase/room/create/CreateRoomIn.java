package president.application.usecase.room.create;

import president.domain.entity.Player;
import president.domain.entity.Room;
import president.domain.valueobjects.AccessConfig;

public record CreateRoomIn(
        String nickName,
        AccessConfig.Visibility visibility,
        int maxPlayers) {


    public Room toRoom() {
        return Room.of(
                Player.of(nickName),
                AccessConfig.of(maxPlayers, visibility)
        );
    }

}
