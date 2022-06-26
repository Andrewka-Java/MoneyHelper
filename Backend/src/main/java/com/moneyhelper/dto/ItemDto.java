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

    @NotBlank(message = "Item category name is blank")
    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("number")
    private Integer number;

    @NotNull(message = "Item price is null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Item price less then 0.0")
    @JsonProperty("price")
    private BigDecimal price;



    public ItemDto() {
    }

    public ItemDto(
            final String name,
            final String categoryName,
            final Integer number,
            final BigDecimal price
    ) {
        this.name = name;
        this.categoryName = categoryName;
        this.number = number;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
