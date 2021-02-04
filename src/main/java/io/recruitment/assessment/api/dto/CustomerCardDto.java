package io.recruitment.assessment.api.dto;


;

public class CustomerCardDto {

    private String id;
    private ProductDto product;
    private Integer quantity;
    private String userId;

    public String getId() {
        return id;
    }

    public CustomerCardDto setId(String id) {
        this.id = id;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public CustomerCardDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CustomerCardDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public CustomerCardDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
