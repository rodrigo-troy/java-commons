package com.rodrigotroy.commons.core.model.datatable;

import com.rodrigotroy.commons.core.model.IComplexHeaderDataTableModel;
import com.rodrigotroy.commons.core.model.IDataTableModel;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 13:13
 */
public class DataTableFactory {
    private DataTableFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends IListHolder> @NotNull IDataTable<T> createEmptyDatatable() {
        return new DataTable<>();
    }

    public static <T extends IListHolder> @NotNull ComplexHeaderDataTable<T> createComplexHeaderDatatable(@NotNull IComplexHeaderDataTableModel dataTableModel,
                                                                                                          @NotNull IDomainObjectMapper<T> domainObjectMapper) {
        return DataTableFactory.createComplexHeaderDatatable(dataTableModel,
                                                             new DefaultHeaderBuilder(),
                                                             new HeaderLayoutBuilder(),
                                                             domainObjectMapper);
    }

    public static <T extends IListHolder> @NotNull ComplexHeaderDataTable<T> createComplexHeaderDatatable(@NotNull IComplexHeaderDataTableModel dataTableModel,
                                                                                                          @NotNull IHeaderBuilder headerBuilder,
                                                                                                          @NotNull IHeaderLayoutBuilder headerLayoutBuilder,
                                                                                                          @NotNull IDomainObjectMapper<T> domainObjectMapper) {
        ComplexHeaderDataTable<T> dataTable = new ComplexHeaderDataTable<>();

        if (Validator.isNotEmpty(dataTableModel.getTitles())) {
            dataTable.setTitles(dataTableModel.getTitles().get(0));
        }

        if (Validator.isNotEmpty(dataTableModel.getSelectedRow())) {
            dataTable.setSelectedRow(domainObjectMapper.createObject(dataTableModel.getSelectedRow()));
        }

        for (List<Object> header : dataTableModel.getHeaders()) {
            dataTable.getHeaders().add(headerBuilder.createHeader(header));
        }

        for (List<Object> row : dataTableModel.getRows()) {
            dataTable.getRows().add(domainObjectMapper.createObject(row));
        }

        for (List<Object> header : dataTableModel.getHeaderLayout()) {
            dataTable.getHeaderLayouts().add(headerLayoutBuilder.createHeaderLayout(header));
        }

        return dataTable;
    }

    public static <T extends IListHolder> @NotNull IDataTable<T> createDatatable(@NotNull IDataTableModel dataTableModel,
                                                                                 @NotNull IDomainObjectMapper<T> domainObjectMapper) {
        return DataTableFactory.createDatatable(dataTableModel,
                                                new DefaultHeaderBuilder(),
                                                domainObjectMapper);
    }

    public static <T extends IListHolder> @NotNull IDataTable<T> createDatatable(@NotNull IDataTableModel dataTableModel,
                                                                                 @NotNull IHeaderBuilder headerBuilder,
                                                                                 @NotNull IDomainObjectMapper<T> domainObjectMapper) {
        DataTable<T> dataTable = new DataTable<>();

        if (Validator.isNotEmpty(dataTableModel.getTitles())) {
            dataTable.setTitles(dataTableModel.getTitles().get(0));
        }

        if (Validator.isNotEmpty(dataTableModel.getSelectedRow())) {
            dataTable.setSelectedRow(domainObjectMapper.createObject(dataTableModel.getSelectedRow()));
        }

        for (List<Object> header : dataTableModel.getHeaders()) {
            dataTable.getHeaders().add(headerBuilder.createHeader(header));
        }

        for (List<Object> row : dataTableModel.getRows()) {
            dataTable.getRows().add(domainObjectMapper.createObject(row));
        }

        return dataTable;
    }
}
