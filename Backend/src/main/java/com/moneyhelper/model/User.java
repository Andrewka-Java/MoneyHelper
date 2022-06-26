/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
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

    @ManyToMany
    @JoinTable(
            name = "user_item",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "id")
    private Set<Auth> authes = new HashSet<>();

    public User() {
    }

    public User(
            final String name,
            final String email,
            final String password,
            final Role role,
            final BigDecimal cash
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        if (items != null && !items.isEmpty()) {
            items.forEach(item -> item.addUser(this));
        }
        this.items = items;
    }

    public Set<Auth> getAuthes() {
        return authes;
    }

    public void setAuthes(final Set<Auth> authSet) {
        if (authSet != null && !authSet.isEmpty()) {
            authSet.forEach(auth -> auth.setUser(this));
        }
        this.authes = authSet;
    }

    public void addAuth(final Auth auth) {
        if (auth == null) {
            return;
        }
        this.authes.add(auth);
    }

    public void addItem(final Item item) {
        if (item == null) {
            return;
        }
        this.items.add(item);
    }

}
