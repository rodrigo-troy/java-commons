package com.rodrigotroy.commons.core.domain;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 29-07-2024
 * Time: 11:34
 */
public enum DataSourceType implements Serializable {
    UNKNOWN(-999),
    WEB_SERVICE(1),
    DATABASE(2);

    private final Integer codigo;

    DataSourceType(Integer codigo) {
        this.codigo = codigo;
    }

    public static DataSourceType fromCodigo(@Nullable Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (DataSourceType origenError : DataSourceType.values()) {
            if (origenError.getCodigo().equals(codigo)) {
                return origenError;
            }
        }

        return null;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
