package io.recruitment.assessment.api.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
;import java.util.UUID;

@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @Id
    private String id;
    @ManyToOne
    @NotNull
    private Product product;
    @Positive
    private Integer quantity;
    @ManyToOne
    @NotNull
    private Order order;

    public String getId() {
        return id;
    }

    public OrderItem setId(String id) {
        this.id = id;
        return this;
    }

    @PrePersist
    void preSave() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public Product getProduct() {
        return product;
    }

    public OrderItem setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItem setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderItem setOrder(Order order) {
        this.order = order;
        return this;
    }
}
