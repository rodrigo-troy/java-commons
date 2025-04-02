package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:52
 */
public class DefaultDomainObjectMapper implements IDomainObjectMapper<IListHolder> {
    @Override
    public @NotNull IListHolder createObject(List<Object> list) {
        return new DefaultListHolder(list);
    }
}
