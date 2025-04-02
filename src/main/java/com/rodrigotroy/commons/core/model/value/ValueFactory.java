package com.rodrigotroy.commons.core.model.value;

import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 10-07-2024
 * Time: 15:38
 */
public final class ValueFactory {
    private ValueFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static @NotNull IValue createHeader(List<List<Object>> data,
                                               Integer rowIndex) {
        return Optional.ofNullable(data)
                       .filter(d -> Validator.isValueAccessible(d,
                                                                rowIndex))
                       .map(d -> new Value(d.get(rowIndex),
                                           0))
                       .map(d -> (IValue) d)
                       .orElse(ValueFactory.createEmptyValue());
    }

    public static @NotNull IValue createEmptyValue() {
        return new Value(null,
                         null);
    }

    public static @NotNull IValue createValue(List<List<Object>> data,
                                              Integer rowIndex,
                                              Integer cellIndex) {
        return Optional.ofNullable(data)
                       .filter(d -> Validator.isValueAccessible(d,
                                                                rowIndex))
                       .map(d -> new Value(d.get(rowIndex),
                                           cellIndex))
                       .map(d -> (IValue) d)
                       .orElse(ValueFactory.createEmptyValue());
    }
}
