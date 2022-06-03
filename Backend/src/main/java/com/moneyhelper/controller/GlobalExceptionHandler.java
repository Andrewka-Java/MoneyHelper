/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = {ExpiredJwtException.class})
    ResponseEntity<String> handleRuntimeException(final Exception exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Token is expired");
    }

}
