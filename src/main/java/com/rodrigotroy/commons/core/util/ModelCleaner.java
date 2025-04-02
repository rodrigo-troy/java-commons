package com.rodrigotroy.commons.core.util;

import com.rodrigotroy.commons.core.model.IComboBoxModel;
import com.rodrigotroy.commons.core.model.IDataTableModel;
import com.rodrigotroy.commons.core.model.IListModel;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 3/26/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public final class ModelCleaner {
    private ModelCleaner() {
        throw new IllegalStateException("Utility class");
    }

    public static void cleanModel(@Nullable IDataTableModel model) {
        if (model == null) {
            return;
        }

        if (model.getTitles() != null) {
            model.getTitles().clear();
            model.setTitles(null);
        }

        if (model.getHeaders() != null) {
            model.getHeaders().clear();
            model.setHeaders(null);
        }

        if (model.getRows() != null) {
            model.getRows().clear();
            model.setRows(null);
        }

        if (model.getSelectedRow() != null) {
            model.getSelectedRow().clear();
            model.setSelectedRow(null);
        }
    }

    public static void cleanModel(@Nullable IListModel model) {
        if (model == null) {
            return;
        }

        if (model.getRows() != null) {
            model.getRows().clear();
            model.setRows(null);
        }

        model.setSelectedRow(null);
    }

    public static void cleanModel(@Nullable List<?> model) {
        if (model == null) {
            return;
        }

        model.clear();
    }

    public static void cleanModel(@Nullable IComboBoxModel model) {
        if (model == null) {
            return;
        }

        if (model.getOptions() != null) {
            model.getOptions().clear();
            model.setOptions(null);
        }

        Object selectedOption = model.getSelectedOption();
        if (selectedOption == null) {
            return;
        }

        if (selectedOption instanceof List<?> list) {
            list.clear();
        }

        model.setSelectedOption(null);
    }
}
