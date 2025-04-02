package com.rodrigotroy.commons.core.model.response;

import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 7/3/17
 * Time: 16:57
 */
public class WarnResponse implements IResponse {
    private final String message;
    private String detail;

    public WarnResponse(String message) {
        this.message = message;
    }

    public WarnResponse(String message,
                        String detail) {
        this.message = message;
        this.detail = detail;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetail() {
        return this.detail;
    }

    @Override
    public @NotNull Outcome getOutcome() {
        return Outcome.WARN;
    }

    @Override
    public @NotNull String toString() {
        return "WarnResponse{" +
               "message='" + message + '\'' +
               ", detail='" + detail + '\'' +
               '}';
    }

    public static WarnResponse fromMessage(String message) {
        return new WarnResponse(message);
    }
}
