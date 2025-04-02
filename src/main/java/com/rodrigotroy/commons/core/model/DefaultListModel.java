package com.rodrigotroy.commons.core.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultListModel implements IListModel,
                                         Serializable {
    private List<List<Object>> rows;
    private List<Object> selectedRow;

    public DefaultListModel() {
    }

    public DefaultListModel(List<List<Object>> rows) {
        this.rows = rows;
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
    public List<Object> getSelectedRow() {
        return this.selectedRow;
    }

    @Override
    public void setSelectedRow(List<Object> selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public @NotNull String toString() {
        return "DefaultListModel{" +
               "rows=" + rows +
               ", selectedRow=" + selectedRow +
               '}';
    }
}
