package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 9/10/18
 * Time: 18:48
 */
public class ComplexHeaderDataTable<E extends IListHolder> extends DataTable<E> {
    private List<HeaderLayout> headerLayouts;

    public @NotNull List<HeaderLayout> getHeaderLayouts() {
        if (headerLayouts == null) {
            headerLayouts = new ArrayList<>();
        }

        return headerLayouts;
    }

    public void setHeaderLayouts(List<HeaderLayout> headerLayouts) {
        this.headerLayouts = headerLayouts;
    }
}
