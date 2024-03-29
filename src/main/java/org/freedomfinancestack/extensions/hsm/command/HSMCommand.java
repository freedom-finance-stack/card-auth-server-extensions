package org.freedomfinancestack.extensions.hsm.command;

import org.freedomfinancestack.extensions.hsm.exception.HSMException;
import org.freedomfinancestack.extensions.hsm.message.HSMMessage;

import lombok.NonNull;

public abstract class HSMCommand {
    public HSMMessage hsmMessage;

    public abstract void initialize();

    public abstract byte[] serialize();

    public abstract byte[] sendRequest(byte[] requestMessage) throws HSMException;

    public abstract void processResponse(byte[] responseMessage);

    public void processHSMMessage(@NonNull final HSMMessage hsmMessage) throws HSMException {
        this.hsmMessage = hsmMessage;

        initialize();

        byte[] hsmRequest = serialize();

        byte[] hsmResponse = sendRequest(hsmRequest);

        processResponse(hsmResponse);
    }
}
