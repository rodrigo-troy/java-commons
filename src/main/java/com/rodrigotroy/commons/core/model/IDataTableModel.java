package com.rodrigotroy.commons.core.model;

import java.util.List;

public interface IDataTableModel extends IListModel {
    List<List<Object>> getHeaders();

    void setHeaders(List<List<Object>> header);

    List<List<Object>> getTitles();

    void setTitles(List<List<Object>> titles);
}
