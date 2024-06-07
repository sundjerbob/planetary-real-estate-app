package com.db_course.filter.nodes;

import com.db_course.filter.defs.FilterDefs;
import com.db_course.filter.defs.FilterOperation;
import lombok.Data;

@Data
public class ColumnFilter implements MetaDataNode {


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
        return filterVals;
    }


    @Override
    public String mapToQuery() {

        StringBuilder query = new StringBuilder();

        query.append(tableName).append(".").append(filterByCol);

        if (operation == FilterOperation.BETWEEN && filterVals != null && filterVals.length == 2)
            query.append(" ").append(FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp());

        else if (filterVals != null && filterVals.length > 0)
            query.append(" ").append(FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp());

        else
            query.append(" ").append(FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp());


        return query.toString();
    }
}
