/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
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

    @OneToMany(mappedBy = "id")
    private Set<Auth> authes;

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

    public Set<Auth> getAuthes() {
        return authes;
    }

    public void setAuthes(final Set<Auth> authSet) {
        if (authSet != null && !authSet.isEmpty()) {
            authes.forEach(auth -> auth.setUser(this));
        }
        this.authes = authSet;
    }

    public void addAuth(final Auth auth) {
        if (this.authes != null) {
            if (auth != null) {
                this.authes.add(auth);
                return;
            }
        }
        this.authes = Collections.singleton(auth);
    }

}
