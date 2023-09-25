package org.freedomfinancestack.extensions.hsm.luna.gateway.impl;

import org.freedomfinancestack.extensions.hsm.exception.HSMException;
import org.freedomfinancestack.extensions.hsm.luna.gateway.HSMGateway;
import org.freedomfinancestack.extensions.hsm.luna.gateway.HSMGatewayAsyncReply;
import org.freedomfinancestack.extensions.hsm.luna.gateway.HSMGatewayService;
import org.freedomfinancestack.extensions.hsm.luna.message.HSMTransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.freedomfinancestack.extensions.hsm.exception.HSMErrorCode.HSM_FETCH_RESPONSE_ERROR;
import static org.freedomfinancestack.extensions.hsm.exception.HSMErrorCode.HSM_SEND_REQUEST_ERROR;
import static org.freedomfinancestack.extensions.hsm.luna.utils.LunaConstants.setHSMEchoTime;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HSMGatewayServiceImpl implements HSMGatewayService<HSMGateway, HSMTransactionMessage> {

    private final HSMGatewayAsyncReply<Object, Message<?>> hsmgatewayAsyncReply;

    @Override
    public void sendRequest(HSMGateway gatewayHandler, HSMTransactionMessage message)
            throws HSMException {

        try {

            TcpSendingMessageHandler sendHandler = gatewayHandler.getTcpSendingMessageHandler();

            if (null != sendHandler) {
                sendHandler.handleMessage(message);
                setHSMEchoTime();
            } else {
                log.debug("Send Message Handler is Null");
            }

        } catch (Exception e) {
            log.error("sendRequest() Exception in sending transaction to HSM", e);
            throw new HSMException(HSM_SEND_REQUEST_ERROR, e);
        }
    }

    @Override
    public byte[] fetchResponse(Object correlationKey) throws HSMException {
        byte[] byteArray = null;

        Message<?> message = hsmgatewayAsyncReply.get(correlationKey);
        if (null != message) {
            byteArray = (byte[]) message.getPayload();
        } else {
            log.error("fetchResponse() Exception in receiving transaction");
            throw new HSMException(HSM_FETCH_RESPONSE_ERROR);
        }
        return byteArray;
    }
}
