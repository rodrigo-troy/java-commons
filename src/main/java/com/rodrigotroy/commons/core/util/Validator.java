package com.rodrigotroy.commons.core.util;

import com.rodrigotroy.commons.core.model.IDataTableModel;
import com.rodrigotroy.commons.core.model.IListModel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator is a utility class for validating different types of data.
 */
public final class Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+&]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Validates an email address.
     *
     * @param mail the email address to validate
     * @return true if the email is valid, false otherwise
     */
    public static @NotNull Boolean validateMail(@Nullable String mail) {
        if (Validator.isEmpty(mail)) {
            return false;
        }

        Pattern emailCompiledPattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = emailCompiledPattern.matcher(SecureValue.objectToTrimmedString(mail));

        return matcher.matches();
    }

    public static @NotNull Boolean validateMail(@Nullable String mail,
                                                @Nullable String domain) {
        if (mail == null || mail.isEmpty() || domain == null || domain.isEmpty()) {
            return false;
        }

        return Validator.validateMail(mail) && mail.endsWith(domain);
    }

    /**
     * Checks if the given object can be parsed as a number or is already a Number instance.
     * <p>
     * This method is used to check if an object is a number or can be parsed as a number.
     * The object can be an instance of Number or a String that can be parsed as a number.
     * The method will return true if the object is a number or can be parsed as a number, false otherwise.
     * If the object is null, the method will return false.
     * If the object is a String, the method will try to parse it as a number and return true if the parsing is successful.
     * If the object is a Number, the method will return true.
     * If the object is not a number and cannot be parsed as a number, the method will return false.
     * </p>
     *
     * @param o the object to check
     * @return true if the object is a number or can be parsed as a number, false otherwise
     */
    public static @NotNull Boolean isNumber(@Nullable Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Number) {
            return true;
        }

        if (o instanceof String) {
            Optional<BigDecimal> bigDecimal = NumberUtil.objectToBigDecimal(o,
                                                                            0,
                                                                            4,
                                                                            Locale.US);
            return bigDecimal.isPresent();
        }

        try {
            Double.parseDouble(o.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given object is zero or can be considered as zero.
     * <p>
     * This method checks if the object is null, a number, a string representing a number,
     * or one of the supported zero values: 0, 0.0, 0L, 0F, BigDecimal.ZERO, BigInteger.ZERO.
     * </p>
     *
     * @param value the object to check for zero value
     * @return true if the object is zero or can be considered as zero, false otherwise
     */
    @Contract("null -> false")
    public static boolean isZero(@Nullable Object value) {
        if (value == null) {
            return false;
        }

        if (value instanceof Optional<?> optional && optional.isPresent()) {
            return Validator.isZero(optional.get());
        } else if (value instanceof Number number) {
            return number.doubleValue() == 0;
        } else if (value instanceof String) {
            boolean isDigits = Validator.isNumber(value.toString());

            if (isDigits) {
                Optional<BigDecimal> bigDecimal = NumberUtil.objectToBigDecimal(value,
                                                                                4,
                                                                                4,
                                                                                Locale.US);
                return Validator.isZero(bigDecimal);
            }
        } else {
            return value.equals(0.0) || value.equals(0) || value.equals(0L) || value.equals(0F) || value.equals(BigDecimal.ZERO) || value.equals(BigInteger.ZERO);
        }

        return false;
    }

    /**
     * Validates whether the given object is a valid date.
     *
     * @param o the object to be validated
     * @return {@code true} if the object is an instance of {@code java.sql.Date}
     * or {@code java.util.Date} or {@code java.time.LocalDate} or {@code java.time.LocalDateTime},
     * @deprecated use {@link Validator#isDate(Object)} instead
     */
    @Deprecated
    @Contract(value = "null -> !null", pure = true)
    public static @NotNull Boolean validateDate(@Nullable Object o) {
        return isDate(o);
    }

    /**
     * Checks if the given object is of type Date or java.time.LocalDate.
     *
     * @param o the object to be checked, which could be null
     * @return {@code true} if the object is an instance of Date or java.time.LocalDate, otherwise {@code false}
     */
    public static @NotNull Boolean isDate(@Nullable Object o) {
        if (Validator.isEmpty(o)) {
            return false;
        }

        return o instanceof Date || o instanceof java.time.LocalDate;
    }

    /**
     * Validates a List object by checking if it is not null, is an instance of List, and is not empty.
     *
     * @param o the List object to validate
     * @return true if the List object is not null, is an instance of List, and is not empty, false otherwise
     * @deprecated use {@link Validator#isNotEmpty(Object)} instead
     */
    @Contract(pure = true)
    @Deprecated
    public static @NotNull Boolean validateList(Object o) {
        return o instanceof List<?> l && !l.isEmpty();
    }

    /**
     * Checks if the object is not null and is not empty.
     * <ul>
     * <li>If the object is a List, the method will return true if the list is not empty. </li>
     * <li>If the object is an {@link IListModel}, the method will return true if the model's rows are not empty.</li>
     * <li>If the object is an {@link IDataTableModel}, the method will return true if the model's rows are not empty or the headers are not empty.</li>
     * <li>If the object is a String, the method will return true if the string is not empty. </li>
     * <li>If the object is a Map, the method will return true if the map is not empty.</li>
     * <li>If the object is null or empty, the method will return false.</li>
     * <li>If the object is an Optional, the method will return true if the Optional is present.</li>
     * <li>If the object is a byte array, the method will return true if the array length is greater than 0.</li>
     * <li>If the object is a Character, the method will return true if the character is not the null character or a space.</li>
     * </ul>
     *
     * @param object the object to check for emptiness
     * @return true if the object is not empty, false otherwise
     */
    @Contract("null -> !null")
    public static @NotNull Boolean isNotEmpty(Object object) {
        return !Validator.isEmpty(object);
    }

    /**
     * Checks if the given object is empty.
     *
     * <ul>
     * <li>If the object is null or empty, the method will return true.</li>
     * <li>If the object is a List, the method will return true if the list is empty.</li>
     * <li>If the object is an IListModel, the method will return true if the model's rows are empty.</li>
     * <li>If the object is an IDataTableModel, the method will return true if the model's rows or headers are empty.</li>
     * <li>If the object is a String, the method will return true if the string is empty.</li>
     * <li>If the object is a Set, the method will return true if the set or empty.</li>
     * <li>If the object is a Map, the method will return true if the map or empty.</li>
     * <li>If the object is an Optional, the method will return true if the Optional is empty.</li>
     * <li>If the object is a byte array, the method will return true if the array length is 0.</li>
     * <li>If the object is a Character, the method will return true if the character is the null character or a space.</li>
     * </ul>
     *
     * @param object the object to check for emptiness
     * @return true if the object is empty, false otherwise
     */

    @Contract("null -> !null")
    public static @NotNull Boolean isEmpty(@Nullable Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof List<?> list) {
            return list.isEmpty();
        } else if (object instanceof IDataTableModel model) {
            return model.getRows().isEmpty() || model.getHeaders().isEmpty();
        } else if (object instanceof IListModel model) {
            return model.getRows().isEmpty();
        } else if (object instanceof String) {
            return !Validator.isStringNotEmpty(object);
        } else if (object instanceof Map<?, ?> map) {
            return map.isEmpty();
        } else if (object instanceof Set<?> set) {
            return set.isEmpty();
        } else if (object instanceof Optional<?> optional) {
            return optional.isEmpty();
        } else if (object instanceof byte[] bytes) {
            return bytes.length == 0;
        } else if (object instanceof char[] chars) {
            return chars.length == 0;
        } else if (object instanceof long[] longs) {
            return longs.length == 0;
        } else if (object instanceof int[] ints) {
            return ints.length == 0;
        } else if (object instanceof double[] doubles) {
            return doubles.length == 0;
        } else if (object instanceof float[] floats) {
            return floats.length == 0;
        } else if (object instanceof Character character) {
            return character == '\u0000' || character == ' ';
        }

        return false;
    }

    /**
     * Validates a List object by checking if it is not null, is an instance of List, and has a specific size.
     *
     * @param o    the List object to validate
     * @param size the expected size of the List
     * @return true if the List object is not null, is an instance of List, and has the expected size, false otherwise
     */
    public static @NotNull Boolean validateList(Object o,
                                                Integer size) {
        if (o instanceof List<?> list && !list.isEmpty()) {
            return list.size() == size;
        }

        return false;
    }

    /**
     * Checks if the value at the specified cell index is accessible in the given row.
     *
     * @param row       the row containing the values
     * @param cellIndex the index of the cell to check
     * @param <T>       the type of the values in the row
     * @return true if the cell index is not null, the row is valid and the cell index is within the row's bounds; false otherwise
     */
    @Contract("_, null -> false")
    public static <T> boolean isValueAccessible(@Nullable List<T> row,
                                                @Nullable Integer cellIndex) {
        if (cellIndex == null || row == null) {
            return false;
        }

        return Validator.isNotEmpty(row) && (row.size() > cellIndex);
    }

    /**
     * Checks if the element at the given index exists in the list.
     *
     * @param list  the list to check
     * @param index the index to check
     * @return true if the element at the given index exists in the list, false otherwise
     * @deprecated use {@link Validator#isValueAccessible(List, Integer)} instead
     */
    @Deprecated
    public static @NotNull Boolean exist(@Nullable List<Object> list,
                                         @Nullable Integer index) {
        if (index == null) {
            return false;
        }

        return list != null && list.size() > index && list.get(index) != null;
    }

    public static Boolean isValidKeyInMap(@Nullable String value,
                                          @Nullable Map<?, ?> map) {
        if (value == null || map == null || map.isEmpty()) {
            return false;
        }

        return map.containsKey(value);
    }

    public static @NotNull Boolean isStringNotEmpty(Object o) {
        return o instanceof String && !SecureValue.objectToTrimmedString(o).isEmpty();
    }

    /**
     * Determines if the given object is an instance of String.
     *
     * @param o the object to be checked
     * @return true if the object is a String, false otherwise
     */
    public static @NotNull Boolean isString(@Nullable Object o) {
        return o instanceof String;
    }

    /**
     * Validates an object by checking if it is an instance of String and has the expected size.
     * The method will return true if the object is an instance of String and has the expected size, false otherwise.
     *
     * @param o    the string object to validate
     * @param size the expected size of the string
     * @return true if the string object is not null and has the expected size, false otherwise
     */
    public static @NotNull Boolean validateString(@Nullable Object o,
                                                  @Nullable Integer size) {
        if (size == null || o == null) {
            return false;
        }

        return Validator.isStringNotEmpty(o) && ((String) o).length() == size;
    }

    public static @NotNull Boolean validateString(@Nullable Object o,
                                                  @Nullable String txt) {
        return Validator.isStringNotEmpty(o) && Objects.equals(o,
                                                               txt);
    }

    /**
     * Validates an integer object. The method will return true if the object is an instance of Integer, false otherwise.
     *
     * @param o the object to validate
     * @return true if the object is an instance of Integer, false otherwise
     */
    @Contract(value = "null -> !null", pure = true)
    public static @NotNull Boolean validateInteger(@Nullable Object o) {
        return o instanceof Integer;
    }

    /**
     * Validates if the given object is an instance of Integer and is equal to the specified integer value.
     *
     * @param o The object to be validated.
     * @param x The integer value to compare with the object.
     * @return Returns true if the object is equal to the specified integer, false otherwise.
     * @deprecated use {@link Validator#isLong(Object)} instead
     */
    @Deprecated
    public static @NotNull Boolean validateInteger(@Nullable Object o,
                                                   @Nullable Integer x) {
        if (o == null || x == null) {
            return false;
        }

        return Validator.validateInteger(o) && o.equals(x);
    }

    /**
     * Validates a number object. The object can be an instance of Number or a String that can be parsed as a number.
     * The method will return true if the object is a number or can be parsed as a number, false otherwise.
     *
     * @param o the object to check
     * @return true if the object is a number or can be parsed as a number, false otherwise
     * @deprecated This method is deprecated and should no longer be used. It may be removed in future versions. Use {@link Validator#isNumber(Object)} instead.
     */
    @Deprecated
    public static @NotNull Boolean validateNumber(@Nullable Object o) {
        return Validator.isNumber(o);
    }

    public static @NotNull Boolean validateCellNumber(@Nullable List<Object> row,
                                                      @Nullable Integer cellIndex) {
        Optional<Object> object = SecureValue.getCellValue(row,
                                                           cellIndex);

        return object.isPresent() && Validator.isNumber(object.get());
    }

    /**
     * Checks if the specified object is of type Double.
     *
     * @param o the object to be validated
     * @return true if the object is an instance of Double, false otherwise
     * @deprecated This method is deprecated and should no longer be used. It may be removed in future versions. Use {@link Validator#isDouble(Object)} instead.
     */
    @Deprecated
    public static @NotNull Boolean validateDouble(@Nullable Object o) {
        return Validator.isDouble(o);
    }

    /**
     * Checks if the given object is of type Double.
     *
     * @param o the object to be checked
     * @return true if the object is of type Double, false otherwise
     */
    @Contract(value = "null -> !null", pure = true)
    public static Boolean isDouble(@Nullable Object o) {
        return o instanceof Double;
    }

    /**
     * Validates whether the provided object is a BigDecimal.
     *
     * @param o the object to be validated
     * @return true if the object is a BigDecimal, false otherwise; non-null
     * @deprecated This method is deprecated and should no longer be used. It may be removed in future versions. Use {@link Validator#isBigDecimal(Object)} instead.
     */
    @Contract(value = "null -> !null", pure = true)
    @Deprecated
    public static Boolean validateBigDecimal(@Nullable Object o) {
        return Validator.isBigDecimal(o);
    }

    /**
     * Checks if the given object is an instance of BigDecimal.
     *
     * @param o the object to be checked for being an instance of BigDecimal
     * @return true if the object is an instance of BigDecimal, false otherwise
     */
    @Contract(value = "null -> !null", pure = true)
    public static @NotNull Boolean isBigDecimal(@Nullable Object o) {
        return o instanceof BigDecimal;
    }

    /**
     * Validates if the given object is a valid Long value.
     * Returns a Boolean value indicating whether the object is a Long.
     * This method is deprecated. Use {@link Validator#isLong(Object)} instead.
     *
     * @param o the object to be validated
     * @return true if the object is a valid Long value, false otherwise
     */
    @Contract(value = "null -> !null", pure = true)
    @Deprecated
    public static Boolean validateLong(@Nullable Object o) {
        return Validator.isLong(o);
    }

    @Contract(value = "null -> !null", pure = true)
    public static @NotNull Boolean isLong(@Nullable Object o) {
        return o instanceof Long;
    }

    public static @NotNull Boolean validateLong(@Nullable Object o,
                                                @Nullable Integer x) {
        if (o == null || x == null) {
            return false;
        }
        if (o instanceof Long) {
            return o.equals(Long.valueOf(x));
        }

        return false;
    }

    public static @NotNull Boolean validateRUT(@NotNull String rut) {
        if (!Validator.isStringNotEmpty(rut)) {
            return false;
        }

        if (Validator.isStringNotEmpty(rut)) {
            boolean matches = Pattern.matches("^0*(\\d{1,3}(\\.?\\d{3})*)-([\\dkK])$",
                                              rut);

            if (matches) {
                String rutSinDV = rut.substring(0,
                                                rut.indexOf("-")).replace(".",
                                                                          "");
                String dv = rut.substring(rut.indexOf("-") + 1,
                                          rut.indexOf("-") + 2).toUpperCase();
                String dvCalculado = DigitoVerificador.calcular(Integer.parseInt(rutSinDV));

                return dv.equals(dvCalculado);
            }
        }

        return false;
    }

    /**
     * Validates an IListModel object.
     *
     * @param model the IListModel object to be validated.
     * @return true if the IListModel contains non-empty rows, false otherwise.
     * @see IListModel
     * @see IListModel#getRows()
     * @see Validator#isNotEmpty(Object)
     * @deprecated This method is deprecated and should not be used as it does not provide sufficient validation. Use {@link Validator#isNotEmpty(Object)} instead.
     */
    @Deprecated
    public static @NotNull Boolean validateIListModel(@Nullable IListModel model) {
        return Validator.isNotEmpty(model.getRows());
    }
}
