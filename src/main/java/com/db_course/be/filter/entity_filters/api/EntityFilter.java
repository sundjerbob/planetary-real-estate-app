package com.db_course.be.filter.entity_filters.api;

import com.db_course.be.db_config.DB_Client;
import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.be.filter.nodes.MetaDataNode;
import com.db_course.be.filter.nodes.MetaNodeConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class EntityFilter {

    protected MetaNodeConstructor[] constructors;
    protected List<MetaDataNode>[] filters;
    protected MetaDataNode[] sortColumns;
    protected boolean[] sortOrder; // true for ASC, false for DESC
    protected final Connection connection;
    private final String table;

    protected EntityFilter(final String table, final int colNb) {

        constructors = new MetaNodeConstructor[colNb]; // initialize values to null;
        filters = new List[colNb];

        /** get repo **/
        this.connection = DB_Client.getInstance().getConnection();
        this.table = table;

        sortColumns = new MetaDataNode[colNb];
        sortOrder = new boolean[colNb];
    }

    public void addFilter(int columnKey, FilterOperation operation, Object... vals) {
        MetaDataNode newFilter = constructors[columnKey].construct();
        newFilter.setOperation(operation);
        newFilter.setFilterValues(vals);
        initFilter(columnKey).add(newFilter);
    }

    public void addSortColumn(int columnKey, boolean ascendingOrder) {
        MetaDataNode sortColumn = constructors[columnKey].construct();
        sortColumns[columnKey] = sortColumn;
        sortOrder[columnKey] = ascendingOrder;
    }

    public void removeFilter(int columnKey, int filterIndex) {
        if (filters[columnKey] != null && filterIndex >= 0 && filterIndex < filters[columnKey].size()) {
            filters[columnKey].remove(filterIndex);
        }
    }

    public void removeSortColumn(int columnKey) {
        sortColumns[columnKey] = null;
    }

    public void clearFilters() {
        Arrays.fill(filters, null);
    }

    public void clearSortColumns() {
        for (int i = 0; i < sortColumns.length; i++) {
            sortColumns[i] = null;
            sortOrder[i] = false;
        }
    }

    private List<MetaDataNode> initFilter(int columnKey) {
        return filters[columnKey] == null ? filters[columnKey] = new ArrayList<>() : filters[columnKey];
    }

    private String generateQuery() {
        StringBuilder query = new StringBuilder("SELECT * FROM ").append(table).append(" WHERE ");
        boolean first = true;

        for (List<MetaDataNode> filterList : filters) {
            if (filterList != null && !filterList.isEmpty()) {
                for (MetaDataNode filter : filterList) {
                    if (!first) {
                        query.append(" AND ");
                    }
                    query.append(filter.mapToQuery());
                    first = false;
                }
            }
        }

        if (first) {
            // If no filters are present, remove the WHERE clause
            query.setLength(query.length() - 7);
        }

        List<String> orderClauses = new ArrayList<>();
        for (int i = 0; i < sortColumns.length; i++) {
            if (sortColumns[i] != null) {
                orderClauses.add(sortColumns[i].mapToQuery().split(" ")[0] + (sortOrder[i] ? " ASC" : " DESC"));
            }
        }

        if (!orderClauses.isEmpty()) {
            query.append(" ORDER BY ").append(String.join(", ", orderClauses));
        }

        /** DEBUG **/
        System.out.println("Generated query (before parameterization): " + query.toString());
        /** DEBUG **/

        return query.toString();
    }

    public PreparedStatement generatePreparedStatement() throws SQLException {
        String query = generateQuery();
        List<Object> values = new ArrayList<>();

        for (List<MetaDataNode> filterList : filters) {
            if (filterList != null) {
                for (MetaDataNode filter : filterList) {
                    Collections.addAll(values, filter.getFilterValues());
                }
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < values.size(); i++) {
            preparedStatement.setObject(i + 1, values.get(i));
        }

        /** DEBUG **/
        System.out.println("Generated PreparedStatement (after parameterization): " + preparedStatement.toString());
        /** DEBUG **/

        return preparedStatement;
    }
}
