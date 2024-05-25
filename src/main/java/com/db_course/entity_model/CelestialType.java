package com.db_course.entity_model;

import lombok.Getter;

@Getter
public enum CelestialType {

    PLANET("A celestial body that orbits a star and is massive enough to be rounded by its own gravity."),
    SATELLITE("A celestial body that orbits a planet or another body larger than itself."),
    STAR("A massive, luminous sphere of plasma held together by gravity."),
    DWARF_PLANET("A celestial body that orbits the sun and is spherical in shape but has not cleared its orbit of other debris."),
    ASTEROID("A small rocky body orbiting the sun, often found in the asteroid belt between Mars and Jupiter.");

    private final String description;

    CelestialType(String description) {
        this.description = description;
    }

}
