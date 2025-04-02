package com.rodrigotroy.commons.core.model.datatable;

import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 04-03-20
 * Time: 15:42
 */
public class DefaultColumnVisibilityResolver implements IColumnVisibilityResolver {
    @Override
    public @NotNull Boolean isVisible(@NotNull List<Object> list) {
        return Validator.validateCellNumber(list,
                                            4) && !Integer.valueOf(list.get(4).toString()).equals(0);
    }

    @Override
    public @NotNull Boolean isDetailVisible(@NotNull List<Object> list) {
        return Validator.validateCellNumber(list,
                                            4) && !Integer.valueOf(list.get(4).toString()).equals(0);
    }
}
