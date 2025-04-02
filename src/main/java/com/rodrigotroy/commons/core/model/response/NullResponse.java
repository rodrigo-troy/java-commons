package com.rodrigotroy.commons.core.model.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 30-03-16
 * Time: 10:18
 */
public class NullResponse implements IResponse {
    @Override
    public @NotNull String getMessage() {
        return "Null";
    }

    @Override
    public @Nullable String getDetail() {
        return null;
    }

    @Override
    public @NotNull Outcome getOutcome() {
        return Outcome.NONE;
    }
}
