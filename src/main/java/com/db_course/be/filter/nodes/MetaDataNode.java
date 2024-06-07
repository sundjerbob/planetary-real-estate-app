package com.db_course.be.filter.nodes;

import com.db_course.be.filter.defs.FilterOperation;

public interface MetaDataNode {

    void setFilterValues(Object... vals);

    void setOperation(FilterOperation operation);

    FilterOperation getOperation();

    Object[] getFilterValues();

    String mapToQuery();




}
