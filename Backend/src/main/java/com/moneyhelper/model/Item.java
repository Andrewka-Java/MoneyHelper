/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

import com.moneyhelper.dto.ItemDto;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@NamedNativeQuery(name = "Item.findAllAndCompressItem",
        query = "SELECT i.name AS name, c.name AS categoryName, SUM(i.price) AS price FROM item i " +
                "INNER JOIN category c ON i.category_id = c.id " +
                "WHERE i.user_id = :userId " +
                "GROUP BY i.name, c.name;",
        resultSetMapping = "Mapping.ItemDto"
)
@SqlResultSetMapping(name = "Mapping.ItemDto",
        classes = @ConstructorResult(targetClass = ItemDto.class,
                columns = {
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "price"),
                        @ColumnResult(name = "categoryName")
                })
)
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @DecimalMin(value = "0", message = "Price less than 0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_item_category_id"))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_item_user_id"))
    private User user;

    public Item() {
    }

    public Item(
            final String name,
            final BigDecimal price,
            final Category category,
            final User user
    ) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
