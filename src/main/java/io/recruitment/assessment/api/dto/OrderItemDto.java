package io.recruitment.assessment.api.dto;

;

public class OrderItemDto {

    private String id;
    private ProductDto product;
    private Integer quantity;
    private String orderId;

    public String getId() {
        return id;
    }

    public OrderItemDto setId(String id) {
        this.id = id;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public OrderItemDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderItemDto setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
}
