package com.rodrigotroy.commons.core.model;


import com.rodrigotroy.commons.core.model.datatable.*;
import com.rodrigotroy.commons.core.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 21-10-21
 * Time: 11:23
 */
public class DataTableFactoryTest {
    private static final Logger LOG = LogManager.getLogger(DataTableFactoryTest.class);

    @Test
    public void shouldCreateEmptyDataTable() {
        IDataTable<IListHolder> dataTable = DataTableFactory.createEmptyDatatable();
        assertNotNull(dataTable);
        assertTrue(dataTable.getHeaders().isEmpty());
        assertTrue(dataTable.getRows().isEmpty());
    }

    @Test
    public void shouldThrowExceptionWhenDomainObjectMapperIsNull() {
        Exception exception = assertThrows(Exception.class,
                                           () -> {
                                               IDataTableModel dataTableModel = DataTableModelBuilder.createDatatable().build();
                                               DataTableFactory.createDatatable(dataTableModel,
                                                                                null);
                                           });

        assertEquals("DomainObjectMapper cannot be null",
                     exception.getMessage());
    }

    @Test
    public void shouldCreateDataTableWithHeadersAndRows() throws Exception {
        IDataTableModel dataTableModel = DataTableModelBuilder.createDatatable().setTitles(new Object[]{100, "Titulo", "Subtitulo"}).addColumn("Column1",
                                                                                                                                               DataType.STRING,
                                                                                                                                               0,
                                                                                                                                               false,
                                                                                                                                               true,
                                                                                                                                               true,
                                                                                                                                               0.0).addRandomRows(10).build();

        IDomainObjectMapper<IListHolder> domainObjectMapper = new DefaultDomainObjectMapper();
        IDataTable<IListHolder> dataTable = DataTableFactory.createDatatable(dataTableModel,
                                                                             domainObjectMapper);

        assertNotNull(dataTable);
        assertEquals(dataTable.getHeaders().size(),
                     1);
        assertEquals(dataTable.getRows().size(),
                     10);
    }

    @Test
    public void shouldCreateDataTableWithHeadersAndRows2() throws Exception {
        IDataTableModel dataTableModel = DataTableModelBuilder.createDatatable().setTitles(new Object[]{100, "Titulo", "Subtitulo"}).addColumn("Oculta",
                                                                                                                                               DataType.NUMBER,
                                                                                                                                               2,
                                                                                                                                               false,
                                                                                                                                               false,
                                                                                                                                               true,
                                                                                                                                               0.0).addColumn("Foto",
                                                                                                                                                              DataType.IMAGE,
                                                                                                                                                              0,
                                                                                                                                                              false,
                                                                                                                                                              true,
                                                                                                                                                              true,
                                                                                                                                                              0.0).addColumn("UF",
                                                                                                                                                                             DataType.NUMBER,
                                                                                                                                                                             2,
                                                                                                                                                                             false,
                                                                                                                                                                             true,
                                                                                                                                                                             true,
                                                                                                                                                                             0.0).addColumn("Nombre",
                                                                                                                                                                                            DataType.STRING,
                                                                                                                                                                                            0,
                                                                                                                                                                                            false,
                                                                                                                                                                                            true,
                                                                                                                                                                                            true,
                                                                                                                                                                                            0.0).addColumn("Monto",
                                                                                                                                                                                                           DataType.NUMBER,
                                                                                                                                                                                                           0,
                                                                                                                                                                                                           false,
                                                                                                                                                                                                           true,
                                                                                                                                                                                                           false,
                                                                                                                                                                                                           0.0).addColumn("Porcentaje",
                                                                                                                                                                                                                          DataType.PERCENTAGE,
                                                                                                                                                                                                                          0,
                                                                                                                                                                                                                          false,
                                                                                                                                                                                                                          true,
                                                                                                                                                                                                                          false,
                                                                                                                                                                                                                          0.0).addRandomRows(10)
                                                              .build();

        IDataTable<IListHolder> datatable = DataTableFactory.createDatatable(dataTableModel,
                                                                             new DefaultHeaderBuilder(new DefaultColumnVisibilityResolver()),
                                                                             new DefaultDomainObjectMapper());

        for (Header header : datatable.getHeaders()) {
            if (header.isVisible()) {
                LOG.info(header.getHeaderText());
            }
        }

        Assert.assertTrue(Validator.isNotEmpty(dataTableModel.getRows()));
    }
}
