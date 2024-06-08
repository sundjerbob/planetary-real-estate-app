package com.db_course.be.obj_mapper;

import com.db_course.be.entity_model.SpaceshipRoom;
import com.db_course.dto.SpaceshipRoomDto;

public class SpaceshipRoomMapper {

    public static SpaceshipRoomDto spaceshipRoomToDto(SpaceshipRoom room, String spaceship) {

        return new SpaceshipRoomDto(
                room.getSpaceshipId(),
                spaceship,
                room.getRoomNumber(),
                room.getPerks(),
                room.getNumHibernationCapsules()
        );
    }
}
