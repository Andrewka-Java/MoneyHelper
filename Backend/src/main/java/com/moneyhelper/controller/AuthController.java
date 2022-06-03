/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.controller;

import com.moneyhelper.dto.AuthRequestDto;
import com.moneyhelper.dto.AuthResponseDto;
import com.moneyhelper.security.annotation.AnonymousRole;
import com.moneyhelper.security.annotation.UserRole;
import com.moneyhelper.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.function.Function;

import static com.moneyhelper.util.Constant.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
class AuthController {

    private final UserService userService;

    @Autowired
    AuthController(final UserService userService) {
        this.userService = userService;
    }

    @AnonymousRole
    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> login(
            @Valid @RequestBody final AuthRequestDto authRequestDto,
            final HttpServletRequest request
    ) {
        final UserDetails userDetails = userService.loadUserByUsername(authRequestDto.getUsername());
        return generateResponse(request, userDetails.getUsername());
    }

    @AnonymousRole
    @GetMapping("/logout")
    ResponseEntity<String> logout(final HttpServletRequest request) {
        final String userEmail = request.getUserPrincipal().getName();
        userService.logoutUser(userEmail);
        return ok("You are successfully logged out");
    }

    @UserRole
    @GetMapping("/refresh-token")
    ResponseEntity<AuthResponseDto> getRefreshToken(final HttpServletRequest request) {
        final DefaultClaims claims = (DefaultClaims) request.getAttribute(CLAIMS_ATTRIBUTE);
        final String username = claims.getSubject();
        final UserDetails userDetails = userService.loadUserByUsername(username);
        return generateResponse(request, userDetails.getUsername());
    }


    @SuppressWarnings("unchecked")
    private ResponseEntity<AuthResponseDto> generateResponse(final HttpServletRequest request, final String userEmail) {
        final Function<String, String> accessTokenFunction = (Function<String, String>) request.getAttribute(ACCESS_TOKEN_FUNCTION);
        final Function<String, String> refreshTokenFunction = (Function<String, String>) request.getAttribute(REFRESH_TOKEN_FUNCTION);

        final String accessToken = accessTokenFunction.apply(userEmail);
        final String refreshToken = refreshTokenFunction.apply(userEmail);

        userService.editAuth(userEmail, accessToken, refreshToken);

        return ok(new AuthResponseDto(accessToken, refreshToken));
    }

}
