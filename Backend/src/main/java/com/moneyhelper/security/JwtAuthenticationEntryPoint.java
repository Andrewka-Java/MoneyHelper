/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final String UNAUTHORIZED_ERROR_MESSAGE = "Unauthorized";

    @Override
    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException authException
    ) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_ERROR_MESSAGE);
    }

}
