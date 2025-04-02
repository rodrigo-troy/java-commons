package com.rodrigotroy.commons.core.model.response;

import com.rodrigotroy.commons.core.util.ExceptionInfoCollector;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 27-04-16
 * Time: 16:48
 */
public class ExceptionResponse implements IResponse {
    private final String message;
    private final @NotNull String detail;

    public ExceptionResponse(@NotNull Exception e) {
        this.message = e.getMessage();
        this.detail = new ExceptionInfoCollector().collectExceptionDetails(e);
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

    public static ExceptionResponse fromException(Exception e) {
        return new ExceptionResponse(e);
    }
}
