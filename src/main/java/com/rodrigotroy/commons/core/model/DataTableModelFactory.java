package com.rodrigotroy.commons.core.model;

import com.rodrigotroy.commons.core.model.datatable.DataTable;
import com.rodrigotroy.commons.core.model.datatable.IDataTable;
import com.rodrigotroy.commons.core.model.datatable.IListHolder;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 28-08-2024
 * Time: 18:11
 */
public final class DataTableModelFactory {
    private DataTableModelFactory() {
    }

    public static <E extends IListHolder> IDataTable<E> createDataTable() {
        return new DataTable<>();
    }
}
