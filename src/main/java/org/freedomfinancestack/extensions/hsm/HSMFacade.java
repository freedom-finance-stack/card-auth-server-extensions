package org.freedomfinancestack.extensions.hsm;

import lombok.NonNull;

public interface HSMFacade {
    String generateCVVFromHsmMessage(@NonNull final String hsmMessage) throws Exception;
}
