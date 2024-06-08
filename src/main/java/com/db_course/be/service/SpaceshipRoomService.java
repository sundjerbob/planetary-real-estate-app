package com.db_course.be.service;

import com.db_course.be.dao.SpaceshipRoomDao;
import com.db_course.be.entity_model.SpaceshipRoom;
import com.db_course.be.filter.entity_filters.impl.SpaceShipRoomFilter;
import com.db_course.dto.SpaceshipDto;
import com.db_course.dto.SpaceshipRoomDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.SpaceshipRoomMapper.spaceshipRoomToDto;

public class SpaceshipRoomService {
    private static volatile SpaceshipRoomService instance;
    private static final Object mutex = new Object();
    private final SpaceshipRoomDao celestialPathDao;


    private SpaceshipRoomService() {
        celestialPathDao = new SpaceshipRoomDao();
    }


    public static SpaceshipRoomService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new SpaceshipRoomService();
                }
            }
        }
        return instance;
    }

    public void processAllSpaceshipRooms(Consumer<SpaceshipRoomDto> consumer) {

        celestialPathDao.processAllSpaceshipRooms(
                spaceshipRoom -> {
                    SpaceshipDto spaceship = SpaceshipService.getInstance().getSpaceshipById(spaceshipRoom.getSpaceshipId());
                    consumer.accept(spaceshipRoomToDto(spaceshipRoom, spaceship.getName()));
                }
        );


    }

    public void processFilteredSpaceshipRooms(Consumer<SpaceshipRoomDto> consumer, SpaceShipRoomFilter filter) {

        celestialPathDao.processFilteredSpaceshipRooms(
                spaceshipRoom -> {
                    SpaceshipDto spaceship = SpaceshipService.getInstance().getSpaceshipById(spaceshipRoom.getSpaceshipId());
                    consumer.accept(spaceshipRoomToDto(spaceshipRoom, spaceship.getName()));
                },
                filter
        );


    }


    public SpaceshipRoomDto getRoomById(int roomId) {

        SpaceshipRoom room = celestialPathDao.getRoomById(roomId);
        return spaceshipRoomToDto(
                room,
                SpaceshipService.getInstance().getSpaceshipById(room.getSpaceshipId()).getName()
        );


    }
}
