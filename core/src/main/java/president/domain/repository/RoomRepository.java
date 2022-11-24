package president.domain.repository;

import president.domain.entity.Room;
import president.domain.valueobjects.identifier.RoomId;

public interface RoomRepository {

    Room save(Room aRoom);

    void delete(RoomId anId);

    Room getById(RoomId anId);

    Room getByLink(String aLink);

}
