package org.freedomfinancestack.extensions.hsm;

import lombok.NonNull;

public interface HSMFacade {
    String generateCVV(@NonNull final String hsmMessage) throws Exception;
}
