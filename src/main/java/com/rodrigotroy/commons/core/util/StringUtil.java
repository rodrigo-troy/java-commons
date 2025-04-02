package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 19-07-2024
 * Time: 13:23
 */
public final class StringUtil {
    private static final String THREE_NUMBERS_AFTER_DOT = "^[+-]?[0-9]{1,3}(\\.[0-9]{3})*(,[0-9]+)?";
    private static final String THREE_NUMBERS_AFTER_COMMA = "^[+-]?[0-9]{1,3}(,[0-9]{3})*(\\.[0-9]+)?";

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getValueAsString(Object value,
                                          Boolean stripTrailingZeros,
                                          Locale locale,
                                          String defaultValue) {
        String val = StringUtil.getValueAsString(value,
                                                 stripTrailingZeros,
                                                 locale);

        return Validator.isStringNotEmpty(val) ? val : defaultValue;
    }

    public static @NotNull String getValueAsString(Object value,
                                                   Boolean stripTrailingZeros,
                                                   Boolean grouping,
                                                   Locale locale) {
        return StringUtil.getValueAsString(value,
                                           stripTrailingZeros,
                                           grouping,
                                           null,
                                           locale);
    }

    public static @NotNull String getValueAsString(Object value,
                                                   Boolean stripTrailingZeros,
                                                   Boolean grouping,
                                                   Integer fractionDigits,
                                                   Locale locale) {
        return StringUtil.getValueAsString(value,
                                           stripTrailingZeros,
                                           grouping,
                                           fractionDigits,
                                           fractionDigits,
                                           locale);
    }

    public static @NotNull String getValueAsString(@Nullable Object value,
                                                   Boolean stripTrailingZeros,
                                                   Boolean grouping,
                                                   @Nullable Integer minimumFractionDigits,
                                                   @Nullable Integer maximumFractionDigits,
                                                   Locale locale) {
        if (value == null) {
            return "";
        }

        if (!Validator.isNotEmpty(value)) {
            return "";
        }

        if (Validator.isStringNotEmpty(value)) {
            long comasCount = SecureValue.objectToTrimmedString(value).chars().filter(ch -> ch == ',').count();
            long pointsCount = SecureValue.objectToTrimmedString(value).chars().filter(ch -> ch == '.').count();
            boolean threeNumbersAfterDot = SecureValue.objectToTrimmedString(value).matches(THREE_NUMBERS_AFTER_DOT);
            boolean threeNumbersAfterComma = SecureValue.objectToTrimmedString(value).matches(THREE_NUMBERS_AFTER_COMMA);

            if (pointsCount == 0 && comasCount == 1) {
                value = value.toString().replace(",",
                                                 ".");
            } else if (pointsCount == 0 && comasCount > 1 && threeNumbersAfterComma) {
                value = value.toString().replace(",",
                                                 "");
            } else if (pointsCount == 1 && comasCount > 1 && threeNumbersAfterComma) {
                value = value.toString().replace(",",
                                                 "");
            } else if (pointsCount > 1 && comasCount <= 2 && threeNumbersAfterDot) {
                value = value.toString().replace(".",
                                                 "");
                value = value.toString().replace(",",
                                                 ".");
            } else if (pointsCount == 1 && comasCount == 1 && threeNumbersAfterComma) {
                value = value.toString().replace(",",
                                                 "");
            } else if (pointsCount == 1 && comasCount == 1 && threeNumbersAfterDot) {
                value = value.toString().replace(".",
                                                 "");
                value = value.toString().replace(",",
                                                 ".");
            } else {
                try {
                    Double.parseDouble(SecureValue.objectToTrimmedString(value));
                } catch (NumberFormatException e) {
                    return "";
                }
            }
        }

        BigDecimal bigDecimal;

        try {
            bigDecimal = new BigDecimal(SecureValue.objectToTrimmedString(value));
        } catch (Exception e) {
            return "";
        }

        if (stripTrailingZeros) {
            bigDecimal = bigDecimal.stripTrailingZeros();
        }

        int decimals = bigDecimal.scale();

        NumberFormat nf = NumberFormat.getInstance(locale);

        if (minimumFractionDigits == null || maximumFractionDigits == null) {
            nf.setMinimumFractionDigits(decimals);
            nf.setMaximumFractionDigits(decimals);
        } else {
            nf.setMinimumFractionDigits(minimumFractionDigits);
            nf.setMaximumFractionDigits(maximumFractionDigits);
        }

        nf.setGroupingUsed(grouping);

        return nf.format(bigDecimal);
    }

    public static @NotNull String getValueAsString(Object value,
                                                   Boolean stripTrailingZeros,
                                                   Locale locale) {
        return StringUtil.getValueAsString(value,
                                           stripTrailingZeros,
                                           false,
                                           locale);
    }

    public static String capitalize(String phrase) {
        if (phrase == null) {
            return null;
        }

        if (phrase.isEmpty()) {
            return "";
        }

        String[] words = phrase.split("\\s+");
        StringBuilder capitalizedPhrase = new StringBuilder();

        for (String word : words) {
            if (Validator.isStringNotEmpty(word)) {
                capitalizedPhrase.append(word.substring(0,
                                                        1).toUpperCase())
                                 .append(word.substring(1).toLowerCase())
                                 .append(" ");
            }
        }

        return capitalizedPhrase.toString().trim();
    }
}
