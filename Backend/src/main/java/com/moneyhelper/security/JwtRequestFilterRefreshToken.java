/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.moneyhelper.util.Constant.*;
import static io.micrometer.core.instrument.util.StringUtils.isBlank;
import static io.micrometer.core.instrument.util.StringUtils.isNotBlank;

@Component
public class JwtRequestFilterRefreshToken extends AbstractRequestFilterTokenHelper {

    private static final String CLAIMS_ATTRIBUTE = "claims";
    private static final Set<String> RESOLVED_URLS_FOR_REFRESH_TOKEN =
            Collections.unmodifiableSet(new HashSet<String>() {{
                add(REFRESH_TOKEN_CONTEXT_PATH);
                add(REFRESH_TOKEN_REVOKE_CONTEXT_PATH);
            }});

    @Autowired
    public JwtRequestFilterRefreshToken(
            final UserDetailsService userDetailsService,
            final JwtTokenUtil jwtTokenUtil,
            @Value("${jwt.refresh.secret}") final String secret,
            @Value("${jwt.refreshExpirationMs}") final int jwtExpirationMs
    ) {
        super(userDetailsService, jwtTokenUtil, secret, jwtExpirationMs);
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        final String token = request.getHeader(REFRESH_TOKEN_HEADER);
        final String url = request.getRequestURI();
        final String method = request.getMethod();

        //Authenticate anonymous user for login
        if ( isBlank(token) && url.contains(LOGIN_CONTEXT_PATH) && method.equals(HttpMethod.POST.name()) ) {
            generateTokenFunction(REFRESH_TOKEN_FUNCTION, request);
            filterChain.doFilter(request, response);
            return;
        }

        //Authenticate user by refreshToken
        if ( isNotBlank(token) && isUrlForRefreshTokenAllowed(url) ) {
            authenticate(token, request);
            final Claims claims = jwtTokenUtil.getAllClaimsFromToken(token, secret);
            request.setAttribute(CLAIMS_ATTRIBUTE, claims);
            request.setAttribute(REFRESH_TOKEN_FUNCTION, tokenFunction);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isUrlForRefreshTokenAllowed(final String url) {
        return RESOLVED_URLS_FOR_REFRESH_TOKEN.stream()
                .anyMatch(url::contains);
    }

}
