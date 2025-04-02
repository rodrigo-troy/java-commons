package com.rodrigotroy.commons.core.model.list;

import com.rodrigotroy.commons.core.model.datatable.IListHolder;
import com.rodrigotroy.commons.core.model.datatable.filter.DefaultDataTableFilterFunction;
import com.rodrigotroy.commons.core.model.datatable.filter.IDataTableFilterFunction;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 15-10-19
 * Time: 11:16
 */
public class List<E extends IListHolder> implements IList<E> {
    private java.util.List<E> rows;
    private java.util.List<E> filteredRows;
    private E selectedRow;
    private IDataTableFilterFunction dataTableFilterFunction;

    public void setDataTableFilterFunction(IDataTableFilterFunction dataTableFilterFunction) {
        this.dataTableFilterFunction = dataTableFilterFunction;
    }

    private @NotNull IDataTableFilterFunction getTableFilterFunction() {
        if (dataTableFilterFunction == null) {
            dataTableFilterFunction = new DefaultDataTableFilterFunction();
        }

        return dataTableFilterFunction;
    }

    @Override
    public void addRowElement(E element) {
        this.getRows().add(element);
    }

    @Override
    public java.util.@NotNull List<E> getRows() {
        if (rows == null) {
            rows = new ArrayList<>();
        }

        return rows;
    }

    @Override
    public void setRows(java.util.List<E> rows) {
        this.rows = rows;
    }

    @Override
    public E getSelectedRow() {
        return this.selectedRow;
    }

    @Override
    public void setSelectedRow(E selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public Boolean isEmpty() {
        return Validator.isEmpty(this.rows);
    }

    @Override
    public java.util.@NotNull List<E> getFilteredRows() {
        if (filteredRows == null) {
            filteredRows = new ArrayList<>();
        }

        return this.filteredRows;
    }

    @Override
    public void setFilteredRows(java.util.List<E> filteredRows) {
        this.filteredRows = filteredRows;
    }

    @Override
    public final boolean filterBy(Object value,
                                  Object filter,
                                  Locale locale) {
        return this.getTableFilterFunction().globalFilterFunction(value,
                                                                  filter,
                                                                  locale);
    }
}
