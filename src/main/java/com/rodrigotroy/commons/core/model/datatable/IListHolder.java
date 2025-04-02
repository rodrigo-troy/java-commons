package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 8/17/18
 * Time: 13:46
 */
public interface IListHolder {
    List<Object> getList();

    default <T extends IListHolder> T getConversion(@NotNull IDomainObjectMapper<T> domainObjectMapper) {
        return domainObjectMapper.createObject(this.getList());
    }
}
