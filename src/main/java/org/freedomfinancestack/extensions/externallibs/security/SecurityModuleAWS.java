package org.freedomfinancestack.extensions.externallibs.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityModuleAWS {

    public boolean verifyRequest(HttpServletRequest httpServletRequest) {
        log.info("httpServletRequest: {}", httpServletRequest);
        /** Custom Business Logic can be implemented here. */
        return true;
    }
}
