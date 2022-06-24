/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.security;

import com.moneyhelper.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.moneyhelper.util.Constant.*;
import static com.moneyhelper.util.MoneyHelperUtil.getGrantedAuthorities;
import static io.micrometer.core.instrument.util.StringUtils.isBlank;
import static io.micrometer.core.instrument.util.StringUtils.isNotBlank;

@Component
public class JwtRequestFilterToken extends AbstractRequestFilterTokenHelper {

    @Autowired
    public JwtRequestFilterToken(
            final UserDetailsService userDetailsService,
            final JwtTokenUtil jwtTokenUtil,
            @Value("${jwt.secret}") final String secret,
            @Value("${jwt.tokenExpirationMs}") final int jwtTokenExpirationMs
    ) {
        super(userDetailsService, jwtTokenUtil, secret, jwtTokenExpirationMs);
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        final String url = request.getRequestURI();
        final String method = request.getMethod();
        final String token = request.getHeader(ACCESS_TOKEN_HEADER);
        final String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);

        //Authenticate anonymous user for login or refresh-token urls
        final boolean isLoginAllowed = url.contains(LOGIN_CONTEXT_PATH) && method.equals("POST");

        if ( isBlank(token) && (isNotBlank(refreshToken) || isLoginAllowed) ) {
            generateTokenFunction(ACCESS_TOKEN_FUNCTION, request);
            filterChain.doFilter(request, response);
            return;
        }

        //Authenticate anonymous user for any another request
        if ( isBlank(token) && isBlank(refreshToken) ) {
            authenticate(
                    null,
                    null,
                    getGrantedAuthorities(Collections.singleton(Role.ANONYMOUS)),
                    request
            );
            filterChain.doFilter(request, response);
            return;
        }

        //Authenticate actual user
        authenticate(token, request);
        filterChain.doFilter(request, response);
    }

}
