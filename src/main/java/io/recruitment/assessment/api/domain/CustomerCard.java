package io.recruitment.assessment.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
;import java.util.UUID;

@Entity
@Table(name = "customer_card")
public class CustomerCard extends BaseEntity {

    @Id
    private String id;
    @ManyToOne
    @NotNull
    private Product product;
    @Positive
    private Integer quantity;
    @ManyToOne
    @NotNull
    private User user;

    @PrePersist
    void preSave() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public CustomerCard setId(String id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public CustomerCard setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CustomerCard setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public User getUser() {
        return user;
    }

    public CustomerCard setUser(User user) {
        this.user = user;
        return this;
    }
}
