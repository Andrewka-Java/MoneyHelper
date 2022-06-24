/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import javax.persistence.*;

@Entity
@Table(name = "auth")
public class Auth extends BaseEntity {

    @Column(name = "revoked_refresh_token", nullable = false)
    private String revokedRefreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_auth_user_id"))
    private User user;

    public Auth() {
    }

    public Auth(final String revokedRefreshToken, final User user) {
        this.revokedRefreshToken = revokedRefreshToken;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
