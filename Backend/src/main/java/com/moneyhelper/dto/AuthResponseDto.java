/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseDto {

    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("refreshToken")
    private String refreshToken;

    public AuthResponseDto() {
    }

    public AuthResponseDto(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
