package com.rodrigotroy.commons.core.model.response;

import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 27-04-16
 * Time: 16:48
 */
public class ErrorResponse implements IResponse {
    private final String message;
    private String detail;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message,
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
        return Outcome.ERROR;
    }

    @Override
    public @NotNull String toString() {
        return "ErrorResponse{" +
               "message='" + message + '\'' +
               ", detail='" + detail + '\'' +
               '}';
    }

    public static ErrorResponse fromMessage(String message) {
        return new ErrorResponse(message);
    }
}
