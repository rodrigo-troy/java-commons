package com.rodrigotroy.commons.core.util;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 15-09-2023
 * Time: 17:05
 */
public class ExceptionInfoCollector {
    private final @NotNull StringBuilder info;

    public ExceptionInfoCollector() {
        this.info = new StringBuilder(500);
    }

    public @NotNull String collectExceptionInfo(Exception exception) {
        return collectExceptionInfo(exception,
                                    false);
    }

    public @NotNull String collectExceptionDetails(Exception exception) {
        return collectExceptionInfo(exception,
                                    true);
    }

    private @NotNull String collectExceptionInfo(Exception exception,
                                                 boolean detailed) {
        info.setLength(0);

        if (exception instanceof SQLException lException) {
            collectSQLExceptionInfo(lException,
                                    detailed);
        } else if (exception.getCause() instanceof SQLException) {
            collectSQLExceptionInfo((SQLException) exception.getCause(),
                                    detailed);
        } else {
            collectGeneralExceptionInfo(exception,
                                        detailed);
        }

        if (detailed) {
            collectStackTraceInfo(exception);
        }

        return info.toString();
    }

    private void appendInfo(String label,
                            Object value) {
        info.append(label).append(value).append("\n");
    }

    private void collectSQLExceptionInfo(@NotNull SQLException sqlException,
                                         boolean detailed) {
        appendInfo("SQL State: ",
                   sqlException.getSQLState());
        appendInfo("Error Code: ",
                   sqlException.getErrorCode());
        appendInfo("SQL Exception Message: ",
                   sqlException.getMessage());

        if (detailed) {
            appendInfo("SQL Exception Cause: ",
                       sqlException.getCause());
            appendInfo("SQL Exception Next Exception: ",
                       sqlException.getNextException());
        }
    }

    private void collectGeneralExceptionInfo(@NotNull Exception exception,
                                             boolean detailed) {
        appendInfo("Message: ",
                   exception.getMessage());
    }

    private void collectStackTraceInfo(@NotNull Exception exception) {
        appendInfo("Stack Trace: ",
                   "");
        for (StackTraceElement element : exception.getStackTrace()) {
            appendInfo("",
                       element.toString());
        }
    }
}
