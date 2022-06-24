/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemDto {

    @NotBlank(message = "Item name is blank")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Item price is null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Item price less then 0.0")
    @JsonProperty("price")
    private BigDecimal price;

    @NotBlank(message = "Item category name is blank")
    @JsonProperty("categoryName")
    private String categoryName;

    public ItemDto() {
    }

    public ItemDto(
            final String name,
            final BigDecimal price,
            final String categoryName
    ) {
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

}
