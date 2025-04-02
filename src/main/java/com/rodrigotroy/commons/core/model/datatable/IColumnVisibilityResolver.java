package com.rodrigotroy.commons.core.model.datatable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 15:39
 */
public interface IColumnVisibilityResolver {
    Boolean isVisible(List<Object> list);

    Boolean isDetailVisible(List<Object> list);
}
