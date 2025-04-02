package com.rodrigotroy.commons.core.model.value;

import com.rodrigotroy.commons.core.util.SecureValue;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class Value implements IValue {
    private final List<Object> data;
    private final Integer cellIndex;

    public Value(List<Object> data,
                 Integer cellIndex) {
        this.data = data;
        this.cellIndex = cellIndex;
    }

    @Override
    public Integer getCellIndex() {
        return cellIndex;
    }

    @Override
    public @NotNull Boolean isPresent() {
        return Validator.isValueAccessible(data,
                                           this.getCellIndex());
    }

    @Override
    public Optional<Object> getValue() {
        return SecureValue.getCellValue(data,
                                        this.getCellIndex());
    }

    @Override
    public String getValueAsString() {
        return SecureValue.cellRowToString(data,
                                           this.getCellIndex());
    }
}
