package org.freedomfinancestack.extensions.hsm.noop;

import org.freedomfinancestack.extensions.hsm.command.HSMCommand;
import org.freedomfinancestack.extensions.hsm.command.enums.HSMCommandType;
import org.freedomfinancestack.extensions.hsm.exception.HSMException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(HSMCommandType.HSMCommandTypeConstants.NO_OP_HSM)
@ConditionalOnProperty(
        name = "hsm.enabled_gateway",
        havingValue = HSMCommandType.HSMCommandTypeConstants.NO_OP_HSM)
public class NoOpHSMCommandImpl extends HSMCommand {

    public static final String NO_OP_CVV_OUTPUT = "123";

    @Override
    public void initialize() {}

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public void processResponse(byte[] responseMessage) {
        hsmMessage.setHsmResponse(NO_OP_CVV_OUTPUT);
    }

    @Override
    public byte[] sendRequest(byte[] requestMessage) throws HSMException {
        return new byte[0];
    }
}
