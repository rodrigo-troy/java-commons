package com.rodrigotroy.commons.core.model.messenger;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigotroy
 * Date: 9/9/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManualMessenger implements IMessenger {
    private final MessageType messageType;
    private final String message;

    public ManualMessenger(MessageType messageType,
                           String message) {
        this.messageType = messageType;
        this.message = message;
    }

    @Override
    public MessageType getType() {
        return this.messageType;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
