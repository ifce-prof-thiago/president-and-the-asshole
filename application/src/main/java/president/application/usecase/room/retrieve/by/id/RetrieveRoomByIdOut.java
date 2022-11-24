package president.application.usecase.room.retrieve.by.id;

import president.domain.entity.Room;
import president.domain.valueobjects.AccessConfig;

public record RetrieveRoomByIdOut(String link, String status, AccessConfig.Visibility visibility) {

    static RetrieveRoomByIdOut of(Room aRoom) {
        return new RetrieveRoomByIdOut(
                aRoom.getRoomLink().getValue(),
                aRoom.getStatus().name(),
                aRoom.getAccessConfig().getVisibility()
        );
    }

}
