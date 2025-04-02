package com.rodrigotroy.commons.core.domain.selectable;


import com.rodrigotroy.commons.core.util.SecureValue;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 06-10-2022
 * Time: 15:39
 */
public class DefaultSelectable implements Selectable {
    private List<Object> list;
    private String clave;
    private String label;

    public DefaultSelectable() {
    }

    public DefaultSelectable(String clave,
                             String label) {
        this.clave = clave;
        this.label = label;
        this.list = Arrays.asList(clave,
                                  label);
    }

    @Override
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public List<Object> getList() {
        return this.list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Selectable selectable) {
            return SecureValue.objectToTrimmedString(selectable.getClave()).equals(SecureValue.objectToTrimmedString(this.getClave()));
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return SecureValue.objectToTrimmedString(this.getClave()).hashCode();
    }

    @Override
    public @NotNull String toString() {
        return "DefaultSelectable{" +
               "clave=" + clave +
               ", label='" + label + '\'' +
               '}';
    }
}
