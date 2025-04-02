package com.rodrigotroy.commons.core.model.response;

import com.rodrigotroy.commons.core.model.DefaultListModel;
import com.rodrigotroy.commons.core.model.IDataTableModel;
import com.rodrigotroy.commons.core.model.IListModel;
import com.rodrigotroy.commons.core.model.IMultiListModel;
import com.rodrigotroy.commons.core.util.SecureValue;
import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: java-commons
 * User: rodrigotroy
 * Date: 29-03-16
 * Time: 15:48
 */
public class DefaultResponse implements IResponse {
    private final IListModel model;

    public DefaultResponse() {
        this.model = new DefaultListModel();
    }

    public DefaultResponse(String message,
                           String detail,
                           Integer resultValue) {
        this.model = new DefaultListModel();
        this.model.getRows().add(Arrays.asList(resultValue,
                                               message,
                                               detail));
    }

    public DefaultResponse(IListModel model) {
        this.model = model;
    }

    public DefaultResponse(@NotNull IDataTableModel model) {
        this.model = new DefaultListModel(model.getTitles());
    }

    public DefaultResponse(@NotNull IMultiListModel model) {
        this.model = new DefaultListModel(model.getDataset(0));
    }

    @Override
    public String getMessage() {
        if (model != null &&
            Validator.isNotEmpty(model.getRows()) &&
            Validator.isNotEmpty(model.getRows().get(0))) {
            return SecureValue.cellRowToString(model.getRows().get(0),
                                               1);
        }

        return "";
    }

    @Override
    public String getDetail() {
        if (model != null &&
            Validator.isNotEmpty(model.getRows()) &&
            Validator.isNotEmpty(model.getRows().get(0))) {
            return SecureValue.cellRowToString(model.getRows().get(0),
                                               2);
        }

        return "";
    }

    @Override
    public @NotNull Outcome getOutcome() {
        if (model != null &&
            Validator.isNotEmpty(model.getRows()) &&
            Validator.isNotEmpty(model.getRows().get(0))) {
            if (Validator.isZero(model.getRows().get(0).get(0))) {
                return Outcome.OK;
            } else if (model.getRows().get(0).get(0).equals(2)) {
                return Outcome.WARN;
            }

            return Outcome.ERROR;
        }

        return Outcome.NONE;
    }

    @Override
    public @NotNull String toString() {
        return "DefaultResponse{" +
               "model=" + model +
               '}';
    }

    public static DefaultResponse fromMessage(String message,
                                              Outcome outcome) {
        return new DefaultResponse(message,
                                   "",
                                   outcome.getValue());
    }

    public static DefaultResponse fromMessage(List<List<Object>> lists) {
        return new DefaultResponse(new DefaultListModel(lists));
    }

    public static DefaultResponse fromMessage(IListModel model) {
        return new DefaultResponse(model);
    }

    public static DefaultResponse fromMessage(String message,
                                              String detail,
                                              Outcome outcome) {
        return new DefaultResponse(message,
                                   detail,
                                   outcome.getValue());
    }
}
