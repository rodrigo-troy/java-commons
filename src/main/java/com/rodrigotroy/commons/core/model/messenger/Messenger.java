package com.rodrigotroy.commons.core.model.messenger;

import com.rodrigotroy.commons.core.util.Validator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The Messenger class implements IMessenger interface and provides functionalities
 * to retrieve message type and message text from a list of objects.
 * The first element in the list represents the message type ID, and the second element
 * represents the message text.
 */
public final class Messenger implements IMessenger {
    private List<Object> row;

    public Messenger() {
    }

    public Messenger(List<Object> row) {
        this.row = row;
    }

    public @NotNull List<Object> getRow() {
        if (this.row == null) {
            this.row = new ArrayList<>();
        }

        return row;
    }

    public void setRow(List<Object> row) {
        this.row = row;
    }

    @Override
    public @NotNull MessageType getType() {
        List<Object> rows = this.getRow();

        if (Validator.isNotEmpty(rows)) {
            Object messageTypeID = rows.get(0);

            if (messageTypeID instanceof Number) {
                if (messageTypeID.equals(0)) {
                    return MessageType.INFO;
                }
            }
        }

        return MessageType.ERROR;
    }

    @Override
    public @NotNull String getMessage() {
        List<Object> row1 = this.getRow();

        if (Validator.isNotEmpty(row1)) {
            Object message = row1.get(1);

            if (message instanceof String) {
                return message.toString();
            }
        }

        return "";
    }
}
