package io.recruitment.assessment.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
;

public class AddCardDto {

    @NotNull
    private String productId;
    @Positive
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public AddCardDto setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AddCardDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
