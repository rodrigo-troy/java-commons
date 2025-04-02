package com.rodrigotroy.commons.core.domain.selectable;


import com.rodrigotroy.commons.core.model.datatable.IListHolder;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 11-11-2020
 * Time: 12:30
 */
public interface Selectable extends IListHolder,
                                    Serializable {
    String getClave();

    String getLabel();
}
