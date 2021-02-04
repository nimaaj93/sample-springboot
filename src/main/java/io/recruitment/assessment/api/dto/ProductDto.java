package io.recruitment.assessment.api.dto;

import io.recruitment.assessment.api.enumeration.Currency;
import io.recruitment.assessment.api.enumeration.ProductStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
;

public class ProductDto {

    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Currency currency;
    @NotNull
    private ProductStatus status;
    @PositiveOrZero
    @NotNull
    private Integer stock;

    public String getId() {
        return id;
    }

    public ProductDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public ProductDto setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public ProductDto setStatus(ProductStatus status) {
        this.status = status;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public ProductDto setStock(Integer stock) {
        this.stock = stock;
        return this;
    }
}
