/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.moneyhelper.dto.ItemDto;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Item.findAllAndCompressItem",
        query = "SELECT i.name AS name, c.name AS categoryName, COUNT(i.name) AS number, SUM(i.price) AS price FROM user_item ui " +
                "INNER JOIN item i ON ui.item_id = i.id " +
                "INNER JOIN category c ON i.category_id = c.id " +
                "WHERE ui.user_id = :userId " +
                "GROUP BY i.name, c.name;",
        resultSetMapping = "Mapping.ItemDto"
)
@SqlResultSetMapping(name = "Mapping.ItemDto",
        classes = @ConstructorResult(targetClass = ItemDto.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "categoryName", type = String.class),
                        @ColumnResult(name = "number", type = Integer.class),
                        @ColumnResult(name = "price", type = BigDecimal.class)
                })
)
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @DecimalMin(value = "0", message = "Price less than 0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_item_category_id")
    )
    private Category category;

    @JsonBackReference
    @ManyToMany(mappedBy = "items")
    private List<User> users = new ArrayList<>();

    public Item() {
    }

    public Item(
            final String name,
            final BigDecimal price,
            final Category category
    ) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        if (users != null && !users.isEmpty()) {
            users.forEach(user -> user.addItem(this));
        }
        this.users = users;
    }

    public void addUser(final User user) {
        if (user == null) {
            return;
        }
        this.users.add(user);
    }
}

