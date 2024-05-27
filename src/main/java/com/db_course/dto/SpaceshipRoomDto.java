package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SpaceshipRoomDto {


    private int roomId;
    private int spaceshipId;
    private int roomNumber;
    private String perks;
    private int numHibernationCapsules;


}
