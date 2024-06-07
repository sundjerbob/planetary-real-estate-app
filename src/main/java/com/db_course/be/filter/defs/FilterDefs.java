package com.db_course.be.filter.defs;

public class FilterDefs {

    @FunctionalInterface
    public interface OperationExpressionBuilder {
        String buildOprExp(String placeholder);
    }

    public static final OperationExpressionBuilder[] stringifyOperations = new OperationExpressionBuilder[FilterOperation.values().length];

    static {
        stringifyOperations[FilterOperation.EQUAL.ordinal()] = placeholder -> "= " + placeholder;
        stringifyOperations[FilterOperation.NOT_EQUAL.ordinal()] = placeholder -> "!= " + placeholder;
        stringifyOperations[FilterOperation.LESS_THAN.ordinal()] = placeholder -> "< " + placeholder;
        stringifyOperations[FilterOperation.LESS_THAN_OR_EQUAL.ordinal()] = placeholder -> "<= " + placeholder;
        stringifyOperations[FilterOperation.GREATER_THAN.ordinal()] = placeholder -> "> " + placeholder;
        stringifyOperations[FilterOperation.GREATER_THAN_OR_EQUAL.ordinal()] = placeholder -> ">= " + placeholder;
        stringifyOperations[FilterOperation.BEGINS_WITH.ordinal()] = placeholder -> "LIKE " + placeholder;
        stringifyOperations[FilterOperation.CONTAINS.ordinal()] = placeholder -> "LIKE " + placeholder;
        stringifyOperations[FilterOperation.BETWEEN.ordinal()] = placeholder -> "BETWEEN ? AND ?";
        stringifyOperations[FilterOperation.IS_NULL.ordinal()] = placeholder -> "IS NULL";
        stringifyOperations[FilterOperation.IS_NOT_NULL.ordinal()] = placeholder -> "IS NOT NULL";
        stringifyOperations[FilterOperation.IS_TRUE.ordinal()] = placeholder -> "IS TRUE";
        stringifyOperations[FilterOperation.IS_FALSE.ordinal()] = placeholder -> "IS FALSE";
    }
}
