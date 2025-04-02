package com.rodrigotroy.commons.core.model.value;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 10-07-2024
 * Time: 15:39
 */
public interface IValue {
    Integer getCellIndex();

    Boolean isPresent();

    Object getValue();

    String getValueAsString();
}
