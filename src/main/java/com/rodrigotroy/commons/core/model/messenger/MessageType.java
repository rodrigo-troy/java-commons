package com.rodrigotroy.commons.core.model.messenger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 5/14/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public enum MessageType {
    ERROR(1,
          "ERROR"),
    WARNING(2,
            "WARNING"),
    INFO(3,
         "INFO"),
    UNDEFINED(-1,
              "UNDEFINED");

    private final int id;
    private final String alias;

    MessageType(int id,
                String alias) {
        this.id = id;
        this.alias = alias;
    }

    public static @NotNull MessageType getById(@Nullable Integer id) {
        if (id == null) {
            return UNDEFINED;
        }

        for (MessageType messageType : MessageType.values()) {
            if (messageType.getId() == id) {
                return messageType;
            }
        }

        return UNDEFINED;
    }

    public static @NotNull MessageType getByAlias(@Nullable String alias) {
        if (alias == null || alias.isEmpty()) {
            return UNDEFINED;
        }

        for (MessageType messageType : MessageType.values()) {
            if (messageType.getAlias().equals(alias.trim().toUpperCase())) {
                return messageType;
            }
        }

        return UNDEFINED;
    }

    public int getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return alias;
    }
}
