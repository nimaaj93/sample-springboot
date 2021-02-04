package io.recruitment.assessment.api.domain;

import io.recruitment.assessment.api.enumeration.Currency;
import io.recruitment.assessment.api.enumeration.ProductStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;
;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currency;
    @PositiveOrZero
    @NotNull
    private Integer stock;
    @NotNull
    private ProductStatus status;

    @PrePersist
    void preSave() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Product setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public Product setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public Product setStatus(ProductStatus status) {
        this.status = status;
        return this;
    }
}
