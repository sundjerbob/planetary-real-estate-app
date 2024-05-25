package com.db_course.entity_model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SpaceshipRoom {
    private int roomId;
    private int spaceshipId;
    private String perks;
    private int numHibernationCapsules;
    private int numBeds;
}
