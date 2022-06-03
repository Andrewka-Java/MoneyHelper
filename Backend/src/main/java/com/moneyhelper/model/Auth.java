/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auth")
public class Auth extends BaseEntity {

    private String refreshToken;
    private String accessToken;

    public Auth() {
    }

    public Auth(final String refreshToken, final String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }
}
