/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.function.Function;

abstract class AbstractRequestFilterTokenHelper extends OncePerRequestFilter {

    protected final UserDetailsService userDetailsService;
    protected final JwtTokenUtil jwtTokenUtil;
    protected final Function<String, String> tokenFunction;
    protected final String secret;
    protected final int jwtExpirationMs;

    public AbstractRequestFilterTokenHelper(
            final UserDetailsService userDetailsService,
            final JwtTokenUtil jwtTokenUtil,
            final String secret,
            final int jwtExpirationMs
    ) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenFunction = username -> jwtTokenUtil.generateToken(username, secret, jwtExpirationMs);
        this.secret = secret;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    protected void authenticate(final String token, final HttpServletRequest request) {
        final String userEmail = jwtTokenUtil.getClaimFromToken(token, secret, Claims::getSubject);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtTokenUtil.validateToken(token, secret, userDetails)) {
                authenticate(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities(), request);
            }
        }
    }

    protected void authenticate(
            final Object principal,
            final Object credentials,
            final Collection<? extends GrantedAuthority> authorities,
            final HttpServletRequest request
    ) {
        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                principal,
                credentials,
                authorities
        );

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    protected void generateTokenFunction(final String functionName, final HttpServletRequest request) {
        final Function<String, String> tokenFunction = username ->
                jwtTokenUtil.generateToken(username, secret, jwtExpirationMs);

        request.setAttribute(functionName, tokenFunction);
    }

}
