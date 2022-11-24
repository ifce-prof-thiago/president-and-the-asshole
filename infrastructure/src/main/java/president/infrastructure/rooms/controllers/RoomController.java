package president.infrastructure.rooms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import president.application.usecase.room.create.CreateRoomIn;
import president.application.usecase.room.create.CreateRoomOut;
import president.application.usecase.room.create.CreateRoomUseCaseImpl;
import president.application.usecase.room.retrieve.by.id.RetrieveRoomByIdOut;
import president.application.usecase.room.retrieve.by.id.RetrieveRoomByIdUseCaseImpl;
import president.domain.valueobjects.identifier.RoomId;
import president.infrastructure.rooms.repository.RoomInMemoryRepository;

@RestController
public class RoomController {

    @PostMapping("rooms")
    @ResponseStatus(HttpStatus.CREATED)
    CreateRoomOut createRoom(@RequestBody CreateRoomIn anIn) {

        final var useCase = new CreateRoomUseCaseImpl(
                new RoomInMemoryRepository()
        );

        return useCase.execute(anIn);

    }

    @GetMapping("rooms/{roomId}")
    RetrieveRoomByIdOut retrieveRoomById(@PathVariable String roomId) {

        final var useCase = new RetrieveRoomByIdUseCaseImpl(
                new RoomInMemoryRepository()
        );

        return useCase.execute(RoomId.of(roomId));

    }

}
