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
 * Date: 9/10/18
 * Time: 18:58
 */
public class HeaderLayoutBuilder implements IHeaderLayoutBuilder {
    @Override
    public @Nullable HeaderLayout createHeaderLayout(@NotNull List<Object> list) {
        HeaderLayout header = null;
        if (Validator.isNotEmpty(list) && list.size() >= 3) {
            header = new HeaderLayout(Validator.isNumber(list.get(0)) ? Integer.parseInt(list.get(0).toString()) : 1,
                                      Validator.isNumber(list.get(1)) ? Integer.parseInt(list.get(1).toString()) : 1,
                                      SecureValue.cellRowToString(list,
                                                                  2));
        }

        return header;
    }
}
