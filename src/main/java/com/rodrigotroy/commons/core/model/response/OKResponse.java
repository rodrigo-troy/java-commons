package com.rodrigotroy.commons.core.model.response;

import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 23-05-16
 * Time: 18:17
 */
public class OKResponse implements IResponse {
    private String message;
    private String detail;

    public OKResponse() {
    }

    public OKResponse(String message) {
        this.message = message;
    }

    public OKResponse(String message,
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
        return Outcome.OK;
    }

    @Override
    public @NotNull String toString() {
        return "OKResponse{" +
               "message='" + message + '\'' +
               ", detail='" + detail + '\'' +
               '}';
    }

    public static OKResponse fromMessage(String message) {
        return new OKResponse(message);
    }
}
