package org.freedomfinancestack.extensions.ext_modules.security;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityModuleAWS {

    public boolean verifyRequest(HttpServletRequest httpServletRequest) {
        log.info("httpServletRequest: {}", httpServletRequest);
        /** Business Logic written by AWS team or RPC call to AWS Server */
        return true;
    }
}
