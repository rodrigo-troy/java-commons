package com.rodrigotroy.commons.core.model.datatable.filter;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 02-08-2022
 * Time: 15:47
 */
public interface IDataTableFilterFunction {
    boolean globalFilterFunction(Object value,
                                 Object filter,
                                 Locale locale);
}
