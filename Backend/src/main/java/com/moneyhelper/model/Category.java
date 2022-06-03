/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public Category() {
    }

    public Category(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
