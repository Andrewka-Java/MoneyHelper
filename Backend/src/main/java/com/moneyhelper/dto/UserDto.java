/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class UserDto {

    @NotBlank(message = "User name is blank")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "User email is blank")
    @Email(message = "User email is not valid")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "User password is blank")
    @JsonProperty("password")
    private String password;

    @JsonProperty("cash")
    private BigDecimal cash;

    public UserDto() {
    }

    public UserDto(
            final String name,
            final String email,
            final String password,
            final BigDecimal cash
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(final BigDecimal cash) {
        this.cash = cash;
    }
}
