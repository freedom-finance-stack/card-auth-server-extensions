package org.freedomfinancestack.extensions.externallibs.config;

import lombok.RequiredArgsConstructor;

import org.freedomfinancestack.extensions.externallibs.security.SecurityModuleAWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestInterceptorConfig {

    private final Optional<SecurityModuleAWS> securityModuleAWSLib;

    /**
     * This method is called before any request is processed and optionally call custom module as
     * configured in properties file
     *
     * <p>This function can be modified as per use cases of Custom Module
     *
     * @param httpServletRequest
     */
    public void processRequest(HttpServletRequest httpServletRequest) {
        if (securityModuleAWSLib.isPresent()) {
            final SecurityModuleAWS securityModuleAWS = securityModuleAWSLib.get();
            boolean isVerified = securityModuleAWS.verifyRequest(httpServletRequest);
            /* Business logic for what to do if request is not verified. */
        }
    }
}
