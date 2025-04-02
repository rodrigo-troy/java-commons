package com.rodrigotroy.commons.core.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultDataTableModel implements IDataTableModel,
                                              Serializable {
    private List<Object> selectedRow;

    private List<List<Object>> titles;
    private List<List<Object>> headers;
    private List<List<Object>> rows;

    public DefaultDataTableModel() {
        super();
    }

    public DefaultDataTableModel(List<List<Object>> titles,
                                 List<List<Object>> headers,
                                 List<List<Object>> rows) {
        super();
        this.titles = titles;
        this.headers = headers;
        this.rows = rows;
    }

    @Override
    public @NotNull List<List<Object>> getHeaders() {
        if (this.headers == null) {
            this.headers = new ArrayList<>();
        }

        return headers;
    }

    @Override
    public void setHeaders(List<List<Object>> headers) {
        this.headers = headers;
    }

    @Override
    public @NotNull List<List<Object>> getRows() {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }

        return rows;
    }

    @Override
    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    @Override
    public @NotNull List<List<Object>> getTitles() {
        if (this.titles == null) {
            this.titles = new ArrayList<>();
        }

        return titles;
    }

    @Override
    public void setTitles(List<List<Object>> titles) {
        this.titles = titles;
    }

    @Override
    public List<Object> getSelectedRow() {
        return this.selectedRow;
    }

    @Override
    public void setSelectedRow(List<Object> row) {
        this.selectedRow = row;
    }
}
