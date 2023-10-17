package org.freedomfinancestack.extensions.externallibs.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ComponentScan(value = {"org.freedomfinancestack.extensions"})
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
