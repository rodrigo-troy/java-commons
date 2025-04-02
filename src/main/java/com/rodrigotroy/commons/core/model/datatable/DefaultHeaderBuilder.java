package com.rodrigotroy.commons.core.model.datatable;

import com.rodrigotroy.commons.core.util.SecureValue;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 8/16/18
 * Time: 19:36
 */
public class DefaultHeaderBuilder implements IHeaderBuilder {
    private final IColumnVisibilityResolver columnVisibilityResolver;

    public DefaultHeaderBuilder() {
        this.columnVisibilityResolver = new DefaultColumnVisibilityResolver();
    }

    public DefaultHeaderBuilder(IColumnVisibilityResolver columnVisibilityResolver) {
        this.columnVisibilityResolver = columnVisibilityResolver;
    }

    @Override
    public @Nullable Header createHeader(@NotNull List<Object> list) {
        Header header = null;

        if (Validator.isNotEmpty(list) && list.size() >= 6) {
            DataType dataType = this.resolveDatatype(list.get(1));

            header = new Header(SecureValue.cellRowToString(list,
                                                            0),
                                dataType,
                                Validator.validateCellNumber(list,
                                                             2) ? Integer.parseInt(list.get(2).toString()) : 0,
                                Validator.validateCellNumber(list,
                                                             3) && Integer.valueOf(list.get(3).toString()).equals(1),
                                this.columnVisibilityResolver.isVisible(list),
                                this.columnVisibilityResolver.isDetailVisible(list),
                                Validator.validateCellNumber(list,
                                                             5) ? Double.parseDouble(list.get(4).toString()) : 1,
                                this.resolveAlignment(dataType),
                                list);
        }

        return header;
    }

    private @NotNull DataType resolveDatatype(@NotNull Object rawDataType) {
        if (Validator.isNumber(rawDataType)) {
            Integer dataTypeNumber = Integer.valueOf(rawDataType.toString());

            for (DataType dataType : DataType.values()) {
                if (dataType.getValue().equals(dataTypeNumber)) {
                    return dataType;
                }
            }
        }

        return DataType.UNKNOWN;
    }

    private @NotNull ColumnAlignment resolveAlignment(@NotNull DataType dataType) {
        return switch (dataType) {
            case NUMBER, PERCENTAGE -> ColumnAlignment.RIGHT;
            case DATE -> ColumnAlignment.CENTER;
            default -> ColumnAlignment.LEFT;
        };

    }
}
