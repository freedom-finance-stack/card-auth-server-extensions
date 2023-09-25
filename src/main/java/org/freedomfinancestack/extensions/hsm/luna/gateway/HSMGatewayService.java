package org.freedomfinancestack.extensions.hsm.luna.gateway;

import org.freedomfinancestack.extensions.hsm.exception.HSMException;

public interface HSMGatewayService<H, M> {

    public void sendRequest(H gatewayHandler, M message) throws HSMException;

    public byte[] fetchResponse(Object correlationKey) throws HSMException;
}
