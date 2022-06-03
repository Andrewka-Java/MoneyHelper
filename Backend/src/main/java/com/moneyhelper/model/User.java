/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @DecimalMin(value = "0", message = "Your cash less than 0")
    @Column(name = "cash", nullable = false)
    private BigDecimal cash = new BigDecimal(0);

    @OneToMany(mappedBy = "id")
    private List<Item> item;

    @OneToOne
    @JoinColumn(
            name = "auth_id",
            referencedColumnName = "id"
    )
    private Auth auth;

    public User() {
    }

    public User(
            final String name,
            final String email,
            final String password,
            final Role role,
            final BigDecimal cash,
            final List<Item> item
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.cash = cash;
        this.item = item;
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

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(final BigDecimal cash) {
        this.cash = cash;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(final List<Item> items) {
        if (items != null) {
            items.forEach(item -> item.setUser(this));
        }
        this.item = items;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(final Auth auth) {
        this.auth = auth;
    }
}
