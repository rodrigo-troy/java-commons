package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


/**
 * A utility class providing methods for converting row cell values or objects to BigDecimal.
 */
public final class NumberUtil {
    private NumberUtil() {
        throw new IllegalStateException("Utility class");
    }


    /**
     * Converts a cell value from a row to a BigDecimal using default locale.
     *
     * @param row       the row containing the cell value
     * @param cellIndex the index of the cell
     * @return an Optional containing the converted BigDecimal if the cell value is a valid number,
     * or an empty Optional if the cell value is not a valid number
     */
    public static Optional<BigDecimal> cellRowToBigDecimal(List<Object> row,
                                                           Integer cellIndex) {
        return NumberUtil.cellRowToBigDecimal(row,
                                              cellIndex,
                                              null,
                                              null,
                                              Locale.US);
    }

    /**
     * Converts a cell value from a row to a BigDecimal.
     * <p>
     * The resulting BigDecimal will have the following default values:
     * <ul>
     * <li>locale: Locale.US</li>
     * </ul>
     * </p>
     *
     * @param row                   the row containing the cell value
     * @param cellIndex             the index of the cell
     * @param minimumFractionDigits the minimum number of fraction digits in the resulting BigDecimal
     * @param maximumFractionDigits the maximum number of fraction digits in the resulting BigDecimal
     * @return an Optional containing the converted BigDecimal if the cell value is a valid number,
     * or an empty Optional if the cell value is not a valid number
     */
    public static Optional<BigDecimal> cellRowToBigDecimal(List<Object> row,
                                                           Integer cellIndex,
                                                           Integer minimumFractionDigits,
                                                           Integer maximumFractionDigits) {
        return NumberUtil.cellRowToBigDecimal(row,
                                              cellIndex,
                                              minimumFractionDigits,
                                              maximumFractionDigits,
                                              Locale.US);
    }

    /**
     * Converts a cell value from a row to a BigDecimal.
     *
     * @param row                   the row containing the cell value
     * @param cellIndex             the index of the cell
     * @param minimumFractionDigits the minimum number of fraction digits in the resulting BigDecimal
     * @param maximumFractionDigits the maximum number of fraction digits in the resulting BigDecimal
     * @param locale                the locale to use for formatting the resulting BigDecimal
     * @return an Optional containing the converted BigDecimal if the cell value is a valid number,
     * or an empty Optional if the cell value is not a valid number
     */
    public static Optional<BigDecimal> cellRowToBigDecimal(List<Object> row,
                                                           Integer cellIndex,
                                                           Integer minimumFractionDigits,
                                                           Integer maximumFractionDigits,
                                                           Locale locale) {
        Optional<Object> cellValue = SecureValue.getCellValue(row,
                                                              cellIndex);

        if (Validator.isEmpty(cellValue)) {
            return Optional.empty();
        }

        return NumberUtil.objectToBigDecimal(cellValue.get(),
                                             minimumFractionDigits,
                                             maximumFractionDigits,
                                             locale);
    }

    /**
     * Converts an Object to a BigDecimal.
     *
     * @param object                the object to convert
     * @param minimumFractionDigits the minimum number of fraction digits in the resulting BigDecimal
     * @param maximumFractionDigits the maximum number of fraction digits in the resulting BigDecimal
     * @param locale                the locale to use for formatting the resulting BigDecimal
     * @return an Optional containing the converted BigDecimal if the object is a valid number,
     * or an empty Optional if the object is not a valid number
     */
    public static @NotNull Optional<BigDecimal> objectToBigDecimal(Object object,
                                                                   Integer minimumFractionDigits,
                                                                   Integer maximumFractionDigits,
                                                                   Locale locale) {
        if (Validator.isEmpty(object)) {
            return Optional.empty();
        }

        if (object instanceof Optional<?> optional) {

            if (optional.isPresent()) {
                object = optional.get();
            } else {
                return Optional.empty();
            }
        }

        String valueAsString = StringUtil.getValueAsString(object,
                                                           false,
                                                           false,
                                                           minimumFractionDigits,
                                                           maximumFractionDigits,
                                                           locale);

        try {
            return Optional.of(new BigDecimal(valueAsString));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<BigDecimal> objectToBigDecimal(Object object,
                                                          Locale locale) {
        return NumberUtil.objectToBigDecimal(object,
                                             null,
                                             null,
                                             locale);
    }
}
