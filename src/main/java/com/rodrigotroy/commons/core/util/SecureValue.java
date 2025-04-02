package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 4/5/17
 * Time: 18:20
 */
public final class SecureValue {
    private SecureValue() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts an object to a Boolean value.
     * <p>
     * If the object is not null, the method checks if it is an instance of Boolean.
     * If it is, it returns the object casted to Boolean.
     * If the object's string representation is "true" or "1" (after trimming), it returns Boolean.TRUE.
     * Otherwise, it returns Boolean.FALSE.
     * </p>
     *
     * @param obj the object to convert
     * @return the converted Boolean value
     */
    public static @NotNull Boolean objectToBoolean(@Nullable Object obj) {
        if (obj == null) {
            return Boolean.FALSE;
        }

        if (obj instanceof Optional<?> optional) {
            return optional.map(SecureValue::objectToBoolean).orElse(Boolean.FALSE);
        }

        if (obj instanceof Boolean boolean1) {
            return boolean1;
        }

        if (obj.toString().trim().equals("true")) {
            return Boolean.TRUE;
        }

        if (obj.toString().trim().equals("1")) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    /**
     * Converts an object to a string.
     * <p>
     * If the object is null, the method returns an empty string.
     * Otherwise, it returns the object's string representation.
     * </p>
     *
     * @param o the object to convert
     * @return the converted string value
     */
    public static @NotNull String objectToNotTrimmedString(Object o) {
        if (o == null) {
            return "";
        }

        if (o instanceof Enum<?> enum1) {
            return enum1.name();
        }

        if (o instanceof List<?> list) {
            return list.stream().map(SecureValue::objectToNotTrimmedString).collect(Collectors.joining(","));
        }

        if (o instanceof Object[] array) {
            return Arrays.stream(array).map(SecureValue::objectToNotTrimmedString).collect(Collectors.joining(","));
        }

        if (o instanceof Optional<?> optional) {
            return optional.map(SecureValue::objectToNotTrimmedString).orElse("");
        }

        if (o instanceof String) {
            return (String) o;
        }

        return Optional.of(o).map(Object::toString).orElse("");
    }

    /**
     * Converts the given object to a trimmed string representation.
     * If the object is null, the method returns an empty string.
     * Specific handling is provided for Enum, List, Object array, and Optional types:
     * - Enum: returns the enum's name.
     * - List: returns a comma-separated string of each element converted individually.
     * - Object array: returns a comma-separated string of each element converted individually.
     * - Optional: if a value is present, returns the converted value; otherwise, returns an empty string.
     * Otherwise, the method returns the trimmed string representation of the object.
     *
     * @param o the object to convert
     * @return the trimmed string representation of the object
     */
    public static @NotNull String objectToTrimmedString(Object o) {
        if (o == null) {
            return "";
        }

        if (o instanceof Enum) {
            return ((Enum<?>) o).name();
        }

        if (o instanceof List<?> list) {
            return list.stream().map(SecureValue::objectToTrimmedString).collect(Collectors.joining(","));
        }

        if (o instanceof Object[] array) {
            return Arrays.stream(array).map(SecureValue::objectToTrimmedString).collect(Collectors.joining(","));
        }

        if (o instanceof Optional<?> optional) {
            return optional.map(SecureValue::objectToTrimmedString).orElse("").trim();
        }

        return Optional.of(o).map(Object::toString).orElse("").trim();
    }

    public static Boolean cellRowToBoolean(List<Object> row,
                                           Integer cellIndex) {
        if (!isRowAccessible(row,
                             cellIndex)) {
            return Boolean.FALSE;
        }

        return SecureValue.objectToBoolean(row.get(cellIndex));
    }

    public static String cellRowToString(List<Object> row,
                                         Integer cellIndex) {
        return cellRowToString(row,
                               cellIndex,
                               "");
    }

    public static String cellRowToString(List<Object> row,
                                         Integer cellIndex,
                                         String defaultValue) {
        Optional<Object> o = SecureValue.getCellValue(row,
                                                      cellIndex);

        return o.map(value -> value.toString().trim()).orElse(defaultValue);
    }

    /**
     * Returns the value in the specified cell of a row as an object.
     *
     * @param <T>       the type of objects in the row
     * @param row       the row containing the value
     * @param cellIndex the index of the cell to convert
     * @return an Optional containing the converted object if successful, otherwise an empty Optional
     */
    public static <T> @NotNull Optional<T> getCellValue(List<T> row,
                                                        Integer cellIndex) {
        return Optional.ofNullable(row)
                       .filter(Validator::isNotEmpty)
                       .filter(r -> r.size() > cellIndex)
                       .map(r -> r.get(cellIndex));
    }

    /**
     * Converts the value in the specified cell of a row to an instance of the specified class if possible.
     *
     * @param <T>       the type of objects in the row
     * @param <E>       the type of the class
     * @param row       the row containing the value
     * @param cellIndex the index of the cell to convert
     * @param clazz     the class to convert to
     * @return an Optional containing the converted object if successful, otherwise an empty Optional
     */
    public static <T, E> Optional<E> cellRowToClass(List<T> row,
                                                    @Nullable Integer cellIndex,
                                                    @Nullable Class<? extends E> clazz) {
        if (row == null) {
            return Optional.empty();
        }

        if (cellIndex == null) {
            return Optional.empty();
        }

        if (clazz == null) {
            return Optional.empty();
        }

        if (!isRowAccessible(row,
                             cellIndex)) {
            return Optional.empty();
        }

        return objectToClass(row.get(cellIndex),
                             clazz);
    }

    /**
     * Converts an object to the specified class if possible.
     * <p>
     * If the object is not null and is an instance of the specified class, the method returns an Optional containing
     * the object converted to the specified class.
     * If the object is null or is not an instance of the specified class, the method returns an empty Optional.
     * </p>
     *
     * @param o     the object to convert
     * @param clazz the class to convert to
     * @param <E>   the type of the class or its subclasses
     * @return an Optional containing the converted object if successful, otherwise an empty Optional
     */
    public static <E> @NotNull Optional<E> objectToClass(@Nullable Object o,
                                                         @NotNull Class<? extends E> clazz) {
        if (o == null) {
            return Optional.empty();
        }

        if (o instanceof Optional<?> optional) {
            return optional.flatMap(value -> objectToClass(value,
                                                           clazz));
        }

        if (clazz.isInstance(o) || o.getClass().equals(clazz)) {
            return Optional.of(clazz.cast(o));
        }

        return Optional.empty();
    }

    /**
     * Checks if a row is accessible based on the cell index.
     *
     * @param row       the row to check
     * @param cellIndex the index of the cell to check
     * @param <T>       the type of objects in the row
     * @return {@code true} if the row is accessible, {@code false} otherwise
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static <T> boolean isRowAccessible(List<T> row,
                                               @Nullable Integer cellIndex) {
        if (row == null) {
            return false;
        }
        if (cellIndex == null) {
            return false;
        }

        return Validator.isNotEmpty(row) && (row.size() > cellIndex);
    }

    /**
     * Converts the value in the specified cell of a row to a BigDecimal.
     * <p>
     * If the cell is null or empty, the method returns BigDecimal.ZERO.
     * If the cell contains a valid number, the method returns the BigDecimal value of the cell.
     * If the cell does not contain a valid number, the method returns BigDecimal.ZERO.
     * </p>
     *
     * @param row       the row containing the value
     * @param cellIndex the index of the cell to convert
     * @return the BigDecimal value of the cell, or BigDecimal.ZERO if the cell is null or empty
     */
    public static BigDecimal cellRowToBigDecimal(List<Object> row,
                                                 Integer cellIndex) {
        return SecureValue.cellRowToBigDecimal(row,
                                               cellIndex,
                                               BigDecimal.ZERO);
    }

    /**
     * Converts the value in the specified cell of a row to a BigDecimal.
     * <p>
     * If the cell is null or empty, the method returns the specified defaultValue.
     * If the cell contains a valid number, the method returns the BigDecimal value of the cell.
     * If the cell does not contain a valid number, the method returns the specified defaultValue.
     * </p>
     *
     * @param row          the row containing the value
     * @param cellIndex    the index of the cell to convert
     * @param defaultValue the default value to return if the cell is null or empty
     * @return the BigDecimal value of the cell, or the defaultValue if the cell is null or empty
     */
    public static BigDecimal cellRowToBigDecimal(List<Object> row,
                                                 @Nullable Integer cellIndex,
                                                 BigDecimal defaultValue) {
        if (cellIndex == null) {
            return defaultValue;
        }

        Optional<BigDecimal> bigDecimal = NumberUtil.cellRowToBigDecimal(row,
                                                                         cellIndex);

        return bigDecimal.orElse(defaultValue);
    }

    /**
     * Converts the specified cell in an array to a string.
     *
     * @param parameters the array containing the values
     * @param x          the index of the cell to convert
     * @return the string representation of the cell value if valid; otherwise, an empty string
     */
    public static String cellRowToString(Object[] parameters,
                                         Integer x) {
        return (parameters != null && x != null && x >= 0 && x < parameters.length)
                ? parameters[x].toString().trim()
                : "";
    }

    public static <T> Optional<T> cellRowToObject(List<T> row,
                                                  Integer cellIndex) {
        return Optional.ofNullable(row)
                       .filter(Validator::isNotEmpty)
                       .filter(r -> r.size() > cellIndex)
                       .map(r -> r.get(cellIndex));
    }
}
