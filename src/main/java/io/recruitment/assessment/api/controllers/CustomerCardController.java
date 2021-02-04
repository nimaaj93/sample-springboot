package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.dto.AddCardDto;
import io.recruitment.assessment.api.dto.CustomerCardDto;
import io.recruitment.assessment.api.service.customercard.CustomerCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer-card")
@PreAuthorize("hasAuthority('CUSTOMER')")
public class CustomerCardController {

    private final CustomerCardService customerCardService;

    public CustomerCardController(CustomerCardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @PostMapping
    public ResponseEntity<List<CustomerCardDto>> addToCard(@Valid @RequestBody AddCardDto addCard) {
        return ResponseEntity.ok(customerCardService.addToCard(addCard));
    }

    @GetMapping
    public ResponseEntity<List<CustomerCardDto>> getCustomerCard() {
        return ResponseEntity.ok(customerCardService.getCustomerCard());
    }

}
