package com.rodrigotroy.commons.core.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 11/15/12
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAutocompleteModel {

    List<List<Object>> getHeaders();

    void setHeaders(List<List<Object>> headers);

    List<List<Object>> getRows();

    void setRows(List<List<Object>> rows);

    List<List<Object>> getTitles();

    void setTitles(List<List<Object>> titles);

    Object getSelectedObject();

    void setSelectedObject(Object selectedObject);

}
