package com.db_course.filter.defs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.db_course.filter.defs.FilterOperation.*;

public class FilterDefs {


    /******************************************************************************************************************/
    @FunctionalInterface
    public interface OperationExpressionBuilder {
        String buildOprExp();
    }


    public static final int SUPPORTED_OPR_NB = 13;
    public static final OperationExpressionBuilder[] stringifyOperations = new OperationExpressionBuilder[SUPPORTED_OPR_NB];


    public static final Class<?>[] DATA_TYPES = {
            Integer.class,
            int.class,
            BigDecimal.class,
            String.class,
            LocalDate.class,
            LocalDateTime.class,
            Boolean.class,
            boolean.class
    };


    /******************************************************************************************************************/
    static {
        stringifyOperations[EQUAL.ordinal()] = () -> " = ?";
        stringifyOperations[NOT_EQUAL.ordinal()] = () -> " != ?";
        stringifyOperations[LESS_THAN.ordinal()] = () -> " < ?";
        stringifyOperations[LESS_THAN_OR_EQUALS.ordinal()] = () -> " <= ?";
        stringifyOperations[GREATER_THAN.ordinal()] = () -> " > ?";
        stringifyOperations[GREATER_THAN_OR_EQUALS.ordinal()] = () -> " >= ?";
        stringifyOperations[STARTS_WITH.ordinal()] = () -> "LIKE ?";
        stringifyOperations[CONTAINS.ordinal()] = () -> "LIKE ?";
        stringifyOperations[BETWEEN.ordinal()] = () -> "BETWEEN ? AND ?";
        stringifyOperations[IS_NULL.ordinal()] = () -> "IS NULL";
        stringifyOperations[IS_NOT_NULL.ordinal()] = () -> "IS NOT NULL";
        stringifyOperations[IS_TRUE.ordinal()] = () -> "IS TRUE";
        stringifyOperations[IS_FALSE.ordinal()] = () -> "IS FALSE";
    }

    static {
        for (int i = 0; i < stringifyOperations.length; i++) {
            if (stringifyOperations[i] == null) {
                throw new IllegalStateException("FilterDefs initialization error: operation at index " + i + " is not defined.");
            }
        }
    }

    public static boolean isTypeSupported(Class<?> type) {
        for (Class<?> supportedType : DATA_TYPES) {
            if (supportedType == type) {
                return true;
            }
        }
        return false;
    }

}
