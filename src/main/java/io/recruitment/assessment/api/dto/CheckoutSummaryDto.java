package io.recruitment.assessment.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutSummaryDto {

    private List<CustomerCardDto> items;
    private BigDecimal totalAmount;

    public List<CustomerCardDto> getItems() {
        return items;
    }

    public CheckoutSummaryDto setItems(List<CustomerCardDto> items) {
        this.items = items;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public CheckoutSummaryDto setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }
}
