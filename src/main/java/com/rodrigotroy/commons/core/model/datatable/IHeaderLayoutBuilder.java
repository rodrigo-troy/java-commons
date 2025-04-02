package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:51
 */
public interface IHeaderLayoutBuilder {
    @Nullable HeaderLayout createHeaderLayout(List<Object> list);
}
