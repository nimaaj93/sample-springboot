package io.recruitment.assessment.api.dto;

import java.math.BigDecimal;
import java.util.List;
;

public class OrderDto {

    private String id;
    private String userId;
    private BigDecimal totalAmount;
    private List<OrderItemDto> items;

    public String getId() {
        return id;
    }

    public OrderDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public OrderDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderDto setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public OrderDto setItems(List<OrderItemDto> items) {
        this.items = items;
        return this;
    }
}
