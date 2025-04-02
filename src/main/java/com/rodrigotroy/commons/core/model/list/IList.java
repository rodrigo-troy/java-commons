package com.rodrigotroy.commons.core.model.list;

import com.rodrigotroy.commons.core.model.datatable.IListHolder;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 15-10-19
 * Time: 11:17
 */
public interface IList<E extends IListHolder> extends Serializable {
    List<E> getRows();

    void setRows(List<E> rows);

    List<E> getFilteredRows();

    void setFilteredRows(List<E> filteredRows);

    E getSelectedRow();

    void setSelectedRow(E selectedRow);

    void addRowElement(E element);

    Boolean isEmpty();

    boolean filterBy(Object value,
                     Object filter,
                     Locale locale);
}
