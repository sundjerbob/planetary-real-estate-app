package com.db_course.be.entity_model;

public enum RadiationLevel {


    LOW("LOW (0-10 mSv/year)"),

    MID("MEDIUM (10-100 mSv/year)"),

    HI("HIGH (>100 mSv/year)");


    public final String val;

    public static RadiationLevel parse(String str) {

        if (str.equals(LOW.val))
            return LOW;

        if (str.equals(MID.val))
            return MID;

        if (str.equals(HI.val))
            return HI;

        throw new RuntimeException("RadiationLevel.parse() says: parsing: " + str + " radiation level failed!\nParsable values:\nLOW (0-10 mSv/year)\nMEDIUM (10-100 mSv/year)\nHIGH (>100 mSv/year)");
    }


    RadiationLevel(String val) {
        this.val = val;
    }
}
