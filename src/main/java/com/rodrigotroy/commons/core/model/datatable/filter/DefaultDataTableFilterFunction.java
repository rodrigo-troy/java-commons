package com.rodrigotroy.commons.core.model.datatable.filter;

import com.rodrigotroy.commons.core.util.SecureValue;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 02-08-2022
 * Time: 15:48
 */
public class DefaultDataTableFilterFunction implements IDataTableFilterFunction {
    @Override
    public boolean globalFilterFunction(Object value,
                                        Object filter,
                                        @Nullable Locale locale) {
        if (!Validator.isStringNotEmpty(filter) ||
            !Validator.isStringNotEmpty(value) ||
            locale == null) {
            return false;
        }

        return SecureValue.objectToNotTrimmedString(value).toUpperCase(locale).equals(SecureValue.objectToNotTrimmedString(filter).toUpperCase(locale));
    }
}
