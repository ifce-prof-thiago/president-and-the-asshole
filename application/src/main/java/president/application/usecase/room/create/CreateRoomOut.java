package president.application.usecase.room.create;

import president.domain.entity.Room;

public record CreateRoomOut(String roomId, String link, String status) {

    static CreateRoomOut of(Room aRoom) {
        return new CreateRoomOut(
                aRoom.getRoomId().getValue(),
                aRoom.getRoomLink().getValue(),
                aRoom.getStatus().name()
        );
    }
}
