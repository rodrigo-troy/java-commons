package com.rodrigotroy.commons.core.domain.selectable;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 06-10-2022
 * Time: 14:38
 */
public class NullSelectable implements Selectable {
    @Override
    public @NotNull String getClave() {
        return "-9999";
    }

    @Override
    public @NotNull String getLabel() {
        return "";
    }

    @Override
    public @NotNull List<Object> getList() {
        return Collections.emptyList();
    }
}
