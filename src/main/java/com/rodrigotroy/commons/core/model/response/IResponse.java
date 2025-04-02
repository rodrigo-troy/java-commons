package com.rodrigotroy.commons.core.model.response;

import com.rodrigotroy.commons.core.model.datatable.IListHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:49
 */
public interface IResponse extends IListHolder {
    String getMessage();

    String getDetail();

    Outcome getOutcome();

    @Override
    default @NotNull List<Object> getList() {
        return Arrays.asList(this.getOutcome().getValue(),
                             this.getMessage(),
                             this.getDetail());
    }

    default @NotNull Boolean isError() {
        if (this.isUndefined()) {
            return false;
        }

        return this.getOutcome() == Outcome.ERROR;
    }

    default @NotNull Boolean isOK() {
        if (this.isUndefined()) {
            return false;
        }

        return this.getOutcome() == Outcome.OK;
    }

    default @NotNull Boolean isWarning() {
        if (this.isUndefined()) {
            return false;
        }

        return this.getOutcome() == Outcome.WARN;
    }

    default @NotNull Boolean isUndefined() {
        if (this.getOutcome() == null) {
            return true;
        }

        return this.getOutcome() == Outcome.NONE;
    }
}
