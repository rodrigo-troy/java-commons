package com.rodrigotroy.commons.core.domain;


import com.rodrigotroy.commons.core.model.IEnum;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 2019-05-09
 * Time: 19:51
 */
public enum Opcion implements IEnum {
    SI(1,
       "Si"),
    NO(0,
       "No");

    private final Integer id;
    private final String label;

    Opcion(Integer id,
           String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public @NotNull String toString() {
        return "Opcion{" +
               "id=" + id +
               ", label='" + label + '\'' +
               '}';
    }
}
