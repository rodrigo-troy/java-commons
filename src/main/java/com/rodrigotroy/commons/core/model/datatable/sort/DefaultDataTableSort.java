package com.rodrigotroy.commons.core.model.datatable.sort;

import com.rodrigotroy.commons.core.util.SecureValue;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 14-10-22
 * Time: 14:40
 */
public class DefaultDataTableSort implements IDataTableSort {
    @Override
    public int sortBy(Object holder1,
                      Object holder2) {
        return SecureValue.objectToNotTrimmedString(holder1).compareTo(SecureValue.objectToNotTrimmedString(holder2));
    }
}
