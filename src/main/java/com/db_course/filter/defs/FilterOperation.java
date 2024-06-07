package com.db_course.filter.defs;

public enum FilterOperation {

    EQUAL("="),
    NOT_EQUAL("!="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUALS("<="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUALS(">="),
    STARTS_WITH("starts with: "),
    CONTAINS("contains: "),
    BETWEEN(" between "),
    IS_NULL(" is null"),
    IS_NOT_NULL(" is not null"),
    IS_TRUE(" is true"),
    IS_FALSE(" is false");

    public final String string;

    FilterOperation(String string) {
        this.string = string;
    }

}
