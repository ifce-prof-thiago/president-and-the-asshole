package president.infrastructure.rooms.repository;

import president.domain.entity.Room;
import president.domain.repository.RoomRepository;
import president.domain.valueobjects.identifier.RoomId;

import java.util.HashMap;
import java.util.Map;

public class RoomInMemoryRepository implements RoomRepository {

    private final static Map<String, Room> ROOMS = new HashMap<>();

    @Override
    public Room save(Room aRoom) {

        ROOMS.put(aRoom.getRoomId().getValue(), aRoom);

        return aRoom;

    }

    @Override
    public void delete(RoomId anId) {
        ROOMS.remove(anId.getValue());
    }

    @Override
    public Room getById(RoomId anId) {
        return ROOMS.get(anId.getValue());
    }

    @Override
    public Room getByLink(String aLink) {
        return null;
    }

}
