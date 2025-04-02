package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 11/14/18
 * Time: 18:47
 */
public enum ColumnAlignment {
    LEFT(-1),
    RIGHT(1),
    CENTER(0),
    UNDEFINED(-999);

    private final Integer value;

    ColumnAlignment(Integer value) {
        this.value = value;
    }

    public static @NotNull ColumnAlignment fromValue(@Nullable Integer value) {
        if (value == null) {
            return UNDEFINED;
        }

        for (ColumnAlignment columnAlignment : ColumnAlignment.values()) {
            if (columnAlignment.getValue().equals(value)) {
                return columnAlignment;
            }
        }

        return UNDEFINED;
    }

    public static @NotNull ColumnAlignment fromString(@Nullable String value) {
        if (value == null || value.isEmpty()) {
            return UNDEFINED;
        }

        for (ColumnAlignment columnAlignment : ColumnAlignment.values()) {
            if (columnAlignment.name().equalsIgnoreCase(value)) {
                return columnAlignment;
            }
        }

        return UNDEFINED;
    }

    public Integer getValue() {
        return value;
    }
}
