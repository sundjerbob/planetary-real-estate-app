package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SpaceshipRoom {


    private int roomId;
    private int spaceshipId;
    private String roomNumber;
    private String perks;
    private int numHibernationCapsules;


}
