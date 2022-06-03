/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AuthRequestDto {

    @NotBlank(message = "Username is blank")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "Password is blank")
    @JsonProperty("password")
    private String password;

    public AuthRequestDto() {
    }

    public AuthRequestDto(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
