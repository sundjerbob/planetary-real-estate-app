package com.db_course.filter.nodes;

import com.db_course.filter.defs.FilterDefs;
import com.db_course.filter.defs.FilterOperation;

public interface MetaDataNode {

    void setFilterValues(Object... vals);

    void setOperation(FilterOperation operation);

    FilterOperation getOperation();

    Object[] getFilterValues();

    String mapToQuery();




}
