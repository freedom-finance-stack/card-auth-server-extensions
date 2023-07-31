package org.ffs.extensions.hsm.cvv;

import org.ffs.extensions.hsm.message.HSMMessage;

import lombok.NonNull;

public interface CVVFacade {

    String generateCVV(@NonNull final HSMMessage hsmMessage) throws Exception;
}
