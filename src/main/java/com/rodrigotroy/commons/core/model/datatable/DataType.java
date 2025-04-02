package com.rodrigotroy.commons.core.model.datatable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 4/5/17
 * Time: 18:29
 */
public enum DataType {
    UNKNOWN(-9999),
    STRING(1),
    NUMBER(2),
    PERCENTAGE(3),
    DATE(4),
    IMAGE(5),
    BOOLEAN(6),
    DUMMY_RUT(100),
    DUMMY_NOMBRE_CLIENTE(101);

    private final Integer value;

    DataType(Integer value) {
        this.value = value;
    }

    public static @NotNull DataType fromValue(@Nullable Integer value) {
        if (value == null) {
            return UNKNOWN;
        }

        for (DataType dataType : DataType.values()) {
            if (dataType.getValue().equals(value)) {
                return dataType;
            }
        }

        return UNKNOWN;
    }

    public Integer getValue() {
        return value;
    }


}
