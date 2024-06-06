package com.db_course.filter.entity_filters.api;

import com.db_course.db_config.DB_Client;
import com.db_course.filter.defs.FilterOperation;
import com.db_course.filter.nodes.MetaDataNode;
import com.db_course.filter.nodes.MetaNodeConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EntityFilter {


    protected MetaNodeConstructor[] constructors;
    protected MetaDataNode[] filters;
    protected final Connection connection;
    private final String table;


    protected EntityFilter(final String table, final int colNb) {
        this.connection = DB_Client.getInstance().getConnection();
        this.table = table;
        constructors = new MetaNodeConstructor[colNb]; // initialize values to null;
        filters = new MetaDataNode[colNb];
    }


    public void setFilter(int columnKey, FilterOperation operation, Object... vals) {
        MetaDataNode filter = initFilter(columnKey);
        filters[columnKey] = filter;
        filter.setOperation(operation);
        filter.setFilterValues(vals);
    }


    private String generateQuery() {
        StringBuilder query = new StringBuilder("SELECT * FROM ").append(table).append(" WHERE ");
        boolean first = true;

        for (MetaDataNode filter : filters) {
            if (filter != null) {
                if (!first)
                    query.append(" AND ");

                query.append(filter.mapToQuery());
                first = false;
            }
        }

        /** DEBUG **/
        System.out.println("Generated query (before parameterization): " + query.toString());
        /** DEBUG **/

        return query.toString();
    }


    public PreparedStatement generatePreparedStatement() throws SQLException {
        String query = generateQuery();
        List<Object> values = new ArrayList<>();

        for (MetaDataNode filter : filters) {
            if (filter != null) {
                Collections.addAll(values, filter.getFilterValues());
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


    private MetaDataNode initFilter(int columnKey) {
        return filters[columnKey] == null ? filters[columnKey] = constructors[columnKey].construct() : filters[columnKey];
    }


}
