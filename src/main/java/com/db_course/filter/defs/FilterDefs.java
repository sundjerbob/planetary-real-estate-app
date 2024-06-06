package com.db_course.filter.defs;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.db_course.filter.defs.FilterOperation.*;

public class FilterDefs {


    /******************************************************************************************************************/
    @FunctionalInterface
    public interface OperationExpressionBuilder {
        String buildOprExp(Object arg);
    }

    public static final int SUPPORTED_OPR_NB = 13;
    public static final OperationExpressionBuilder[] stringifyOperations = new OperationExpressionBuilder[SUPPORTED_OPR_NB];
    public static final Class<?>[] DATA_TYPES = {Integer.class, int.class, BigDecimal.class, String.class, LocalDate.class, Boolean.class};


    /******************************************************************************************************************/
    static {
        stringifyOperations[EQUAL.ordinal()] = arg -> " = ?";
        stringifyOperations[NOT_EQUAL.ordinal()] = arg -> " != ?";
        stringifyOperations[LESS_THAN.ordinal()] = arg -> " < ?";
        stringifyOperations[LESS_THAN_OR_EQUALS.ordinal()] = arg -> " <= ?";
        stringifyOperations[GREATER_THAN.ordinal()] = arg -> " > ?";
        stringifyOperations[GREATER_THAN_OR_EQUALS.ordinal()] = arg -> " >= ?";
        stringifyOperations[STARTS_WITH.ordinal()] = arg -> "LIKE ?";
        stringifyOperations[CONTAINS.ordinal()] = arg -> "LIKE ?";
        stringifyOperations[BETWEEN.ordinal()] = arg -> "BETWEEN ? AND ?";
        stringifyOperations[IS_NULL.ordinal()] = arg -> "IS NULL";
        stringifyOperations[IS_NOT_NULL.ordinal()] = arg -> "IS NOT NULL";
        stringifyOperations[IS_TRUE.ordinal()] = arg -> "IS TRUE";
        stringifyOperations[IS_FALSE.ordinal()] = arg -> "IS FALSE";
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
