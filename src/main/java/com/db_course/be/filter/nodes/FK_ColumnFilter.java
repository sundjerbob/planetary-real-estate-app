package com.db_course.be.filter.nodes;

import com.db_course.be.filter.defs.FilterDefs;
import com.db_course.be.filter.defs.FilterOperation;
import lombok.Getter;

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
        if (operation == FilterOperation.BEGINS_WITH) {
            return new Object[]{filterValues[0] + "%"};
        } else if (operation == FilterOperation.CONTAINS) {
            return new Object[]{"%" + filterValues[0] + "%"};
        }
        return filterValues;
    }

    @Override
    public String mapToQuery() {
        return tableName + "." + fkColName + " IN (SELECT " + refPkColName + " FROM " + referenceTable + " WHERE " + referenceTable + "." + filterByCol + " " + FilterDefs.stringifyOperations[operation.ordinal()].buildOprExp("?") + ")";
    }
}
