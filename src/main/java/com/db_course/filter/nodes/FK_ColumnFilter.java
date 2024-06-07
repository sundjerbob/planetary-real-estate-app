package com.db_course.filter.nodes;

import com.db_course.filter.defs.FilterDefs;
import com.db_course.filter.defs.FilterOperation;
import lombok.Getter;

@SuppressWarnings("ALL")
@Getter
public class FK_ColumnFilter implements MetaDataNode {

    private final String tableName;
    private final String referenceTable;
    private final String fkColName;
    private final String refPkColName;
    private final String filterByCol;
    private final Class<?> dataType;

    private Object[] filterValues;
    private FilterOperation operation;

    public FK_ColumnFilter(String tableName, String referenceTable, String fkColName, String refPkColName, String filterByCol, Class<?> dataType) {
        this.tableName = tableName;
        this.referenceTable = referenceTable;
        this.fkColName = fkColName;
        this.refPkColName = refPkColName;
        this.filterByCol = filterByCol;
        this.dataType = dataType;
    }

    @Override
    public void setFilterValues(Object... values) {
        this.filterValues = values;
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
        return filterValues;
    }

    @Override
    public String mapToQuery() {

        StringBuilder query = new StringBuilder();

        query.append(tableName).append(".").append(fkColName)
                .append(" IN (SELECT ").append(refPkColName)
                .append(" FROM ").append(referenceTable)
                .append(" WHERE ").append(referenceTable).append(".").append(filterByCol);

        if (operation == FilterOperation.BETWEEN && filterValues != null && filterValues.length == 2)
            query.append(" ").append(FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp());

        else if (filterValues != null && filterValues.length > 0)
            query.append(" ").append(FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp());

        else
            query.append(" ").append(FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp());

        query.append(")");

        return query.toString();
    }

}