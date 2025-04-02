package com.rodrigotroy.commons.core.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:40
 */
public interface IComplexHeaderDataTableModel extends IDataTableModel {
    List<List<Object>> getHeaderLayout();

    void setHeaderLayout(List<List<Object>> headerLayout);
}
