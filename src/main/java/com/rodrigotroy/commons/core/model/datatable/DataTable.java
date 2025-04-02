package com.rodrigotroy.commons.core.model.datatable;

import com.rodrigotroy.commons.core.model.datatable.filter.DefaultDataTableFilterFunction;
import com.rodrigotroy.commons.core.model.datatable.filter.IDataTableFilterFunction;
import com.rodrigotroy.commons.core.model.datatable.sort.DefaultDataTableSort;
import com.rodrigotroy.commons.core.model.datatable.sort.IDataTableSort;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 1/30/18
 * Time: 11:02
 */
public class DataTable<E extends IListHolder> implements IDataTable<E> {
    private List<Object> titles;
    private List<Header> headers;
    private List<E> rows;
    private List<E> filteredRows;
    private E selectedRow;
    private transient IDataTableSort dataTableSort;
    private transient IDataTableFilterFunction dataTableFilterFunction;

    public DataTable() {
    }

    public @NotNull IDataTableSort getDataTableSort() {
        if (dataTableSort == null) {
            dataTableSort = new DefaultDataTableSort();
        }

        return dataTableSort;
    }

    public void setDataTableSort(IDataTableSort dataTableSort) {
        this.dataTableSort = dataTableSort;
    }

    private @NotNull IDataTableFilterFunction getDataTableFilterFunction() {
        if (dataTableFilterFunction == null) {
            dataTableFilterFunction = new DefaultDataTableFilterFunction();
        }

        return dataTableFilterFunction;
    }

    public void setDataTableFilterFunction(IDataTableFilterFunction dataTableFilterFunction) {
        this.dataTableFilterFunction = dataTableFilterFunction;
    }

    @Override
    public E getSelectedRow() {
        return selectedRow;
    }

    @Override
    public void setSelectedRow(E selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public void addRowElement(E element) {
        this.getRows().add(element);
    }

    @Override
    public @NotNull List<Object> getTitles() {
        if (titles == null) {
            titles = new ArrayList<>();
        }

        return titles;
    }

    @Override
    public void setTitles(List<Object> titles) {
        this.titles = titles;
    }

    @Override
    public @NotNull List<Header> getHeaders() {
        if (headers == null) {
            headers = new ArrayList<>();
        }

        return headers;
    }

    @Override
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    @Override
    public @NotNull List<E> getRows() {
        if (rows == null) {
            rows = new ArrayList<>();
        }

        return rows;
    }

    @Override
    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    @Override
    public @NotNull List<E> getFilteredRows() {
        if (filteredRows == null) {
            filteredRows = new ArrayList<>();
        }

        return this.filteredRows;
    }

    @Override
    public void setFilteredRows(List<E> filteredRows) {
        this.filteredRows = filteredRows;
    }

    @Override
    public Boolean isEmpty() {
        return Validator.isEmpty(this.rows);
    }

    @Override
    public boolean filterBy(Object value,
                            Object filter,
                            Locale locale) {
        return this.getDataTableFilterFunction().globalFilterFunction(value,
                                                                      filter,
                                                                      locale);
    }

    @Override
    public int sortBy(Object holder1,
                      Object holder2) {
        return this.getDataTableSort().sortBy(holder1,
                                              holder2);
    }
}
