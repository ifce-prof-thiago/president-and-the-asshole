package president.application.usecase.room.create;

import president.domain.entity.Player;
import president.domain.entity.Room;
import president.domain.repository.RoomRepository;
import president.domain.valueobjects.AccessConfig;

public class CreateRoomUseCaseImpl implements CreateRoomUseCase {

    private final RoomRepository repository;

    public CreateRoomUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateRoomOut execute(CreateRoomIn anIn) {

        return CreateRoomOut.of(
                repository.save(anIn.toRoom())
        );

    }

}
