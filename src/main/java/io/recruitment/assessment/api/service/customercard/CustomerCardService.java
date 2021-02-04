package io.recruitment.assessment.api.service.customercard;

import io.recruitment.assessment.api.dto.AddCardDto;
import io.recruitment.assessment.api.dto.CheckoutSummaryDto;
import io.recruitment.assessment.api.dto.CustomerCardDto;

import java.util.List;

public interface CustomerCardService {

    List<CustomerCardDto> addToCard(AddCardDto addCardDto);

    List<CustomerCardDto> getCustomerCard();

    CheckoutSummaryDto getCheckoutSummary();

}
