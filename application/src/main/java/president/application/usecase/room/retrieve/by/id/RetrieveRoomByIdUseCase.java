package president.application.usecase.room.retrieve.by.id;

import president.domain.valueobjects.identifier.RoomId;

public interface RetrieveRoomByIdUseCase {

    RetrieveRoomByIdOut execute(RoomId anIn);

}
