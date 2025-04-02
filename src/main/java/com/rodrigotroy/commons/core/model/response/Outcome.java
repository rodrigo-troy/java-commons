package com.rodrigotroy.commons.core.model.response;

import com.rodrigotroy.commons.core.util.SecureValue;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:49
 */
public enum Outcome {
    OK(0),
    ERROR(1),
    WARN(2),
    NONE(-1);

    private final Integer value;

    Outcome(Integer value) {
        this.value = value;
    }

    public static @NotNull Outcome fromValue(Integer value) {
        for (Outcome outcome : Outcome.values()) {
            if (outcome.getValue().equals(value)) {
                return outcome;
            }
        }

        return NONE;
    }

    public static @NotNull Outcome fromValue(String value) {
        for (Outcome outcome : Outcome.values()) {
            if (SecureValue.objectToTrimmedString(outcome.name()).equalsIgnoreCase(SecureValue.objectToTrimmedString(value))) {
                return outcome;
            }
        }

        return NONE;
    }

    public Integer getValue() {
        return value;
    }
}
