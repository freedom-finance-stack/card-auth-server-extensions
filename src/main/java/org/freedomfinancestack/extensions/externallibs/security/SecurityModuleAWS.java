package org.freedomfinancestack.extensions.externallibs.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("securityModuleAWS")
@ConditionalOnProperty(
        name = "external-libs.security.SecurityModuleAWS.enabled",
        havingValue = "true")
public class SecurityModuleAWS {

    public boolean verifyRequest(HttpServletRequest httpServletRequest) {
        log.info("verifyRequest() httpServletRequest: {}", httpServletRequest);
        /** Custom Business Logic can be implemented here. */
        return true;
    }
}
