/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 1453829511997616548L;


    public <T> T getClaimFromToken(final String token, final String secret, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token, secret);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(final String token, final String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(final String userEmail, final String secret, final int jwtExpirationMs) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(final String token, final String secret, final UserDetails userDetails) {
        final String username = getClaimFromToken(token, secret, Claims::getSubject);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, secret));
    }

    private Boolean isTokenExpired(final String token, final String secret) {
        final Date expiration = getClaimFromToken(token, secret, Claims::getExpiration);
        return expiration.before(new Date());
    }

}
