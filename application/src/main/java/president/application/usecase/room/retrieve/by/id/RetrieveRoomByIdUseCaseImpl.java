package president.application.usecase.room.retrieve.by.id;

import president.domain.repository.RoomRepository;
import president.domain.valueobjects.identifier.RoomId;

public class RetrieveRoomByIdUseCaseImpl implements RetrieveRoomByIdUseCase {

    private final RoomRepository repository;

    public RetrieveRoomByIdUseCaseImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrieveRoomByIdOut execute(RoomId anIn) {

        return RetrieveRoomByIdOut.of(
                repository.getById(anIn)
        );

    }

}
