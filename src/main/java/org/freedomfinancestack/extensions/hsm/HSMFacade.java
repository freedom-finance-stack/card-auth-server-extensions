package org.freedomfinancestack.extensions.hsm;

import lombok.NonNull;

// todo - Remove this file before mergeing.. Not required.
public interface HSMFacade {
    String generateCVV(@NonNull final String hsmMessage) throws Exception;
}
