package com.db_course.be.filter.nodes;

import com.db_course.be.filter.defs.FilterDefs;
import com.db_course.be.filter.defs.FilterOperation;
import lombok.Data;

@Data
public class    ColumnFilter implements MetaDataNode {

    private final String tableName;
    private final String filterByCol;
    private final Class<?> dataType;

    private Object[] filterVals;
    private FilterOperation operation;

    public ColumnFilter(String tableName, String filterByCol, Class<?> dataType) {
        this.tableName = tableName;
        this.filterByCol = filterByCol;
        this.dataType = dataType;
    }

    @Override
    public void setFilterValues(Object... vals) {
        this.filterVals = vals;
    }

    @Override
    public void setOperation(FilterOperation operation) {
        this.operation = operation;
    }

    @Override
    public FilterOperation getOperation() {
        return operation;
    }

    @Override
    public Object[] getFilterValues() {
        if (operation == FilterOperation.BEGINS_WITH) {
            return new Object[]{filterVals[0] + "%"};
        } else if (operation == FilterOperation.CONTAINS) {
            return new Object[]{"%" + filterVals[0] + "%"};
        }
        return filterVals;
    }

    @Override
    public String mapToQuery() {
        return tableName + "." + filterByCol + " " + FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp("?");
    }
}
