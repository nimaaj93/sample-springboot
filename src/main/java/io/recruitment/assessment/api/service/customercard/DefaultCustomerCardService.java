package io.recruitment.assessment.api.service.customercard;

import io.recruitment.assessment.api.domain.CustomerCard;
import io.recruitment.assessment.api.domain.Product;
import io.recruitment.assessment.api.domain.User;
import io.recruitment.assessment.api.dto.AddCardDto;
import io.recruitment.assessment.api.dto.CheckoutSummaryDto;
import io.recruitment.assessment.api.dto.CustomerCardDto;
import io.recruitment.assessment.api.exception.ProductNotFoundException;
import io.recruitment.assessment.api.mapper.CustomerCardMapper;
import io.recruitment.assessment.api.repository.CustomerCardRepository;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.repository.UserRepository;
import io.recruitment.assessment.api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
;
import java.util.stream.Collectors;

@Service
public class DefaultCustomerCardService implements CustomerCardService {

    private final CustomerCardRepository customerCardRepository;
    private final CustomerCardMapper customerCardMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DefaultCustomerCardService(CustomerCardRepository customerCardRepository,
                                      CustomerCardMapper customerCardMapper,
                                      ProductRepository productRepository,
                                      UserRepository userRepository) {
        this.customerCardRepository = customerCardRepository;
        this.customerCardMapper = customerCardMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<CustomerCardDto> addToCard(AddCardDto addCardDto) {
        
        String currentUserId = SecurityUtil.getCurrentUserId();
        List<CustomerCard> customerCardItems = customerCardRepository.findAllByUser_Id(currentUserId);

        CustomerCard customerCard = customerCardItems.stream()
                .filter(existingCustomerCard -> existingCustomerCard.getProduct().getId().equals(addCardDto.getProductId()))
                .map(existingCustomerCard ->
                        existingCustomerCard.setQuantity(existingCustomerCard.getQuantity() + addCardDto.getQuantity()))
                .findAny()
                .orElseGet(() -> {
                    Product product = productRepository.findById(addCardDto.getProductId())
                            .orElseThrow(ProductNotFoundException::new);
                    User user = userRepository.findById(currentUserId).orElseThrow();
                    return new CustomerCard()
                            .setQuantity(addCardDto.getQuantity())
                            .setProduct(product)
                            .setUser(user);
                });

        customerCardRepository.save(customerCard);
        return getCustomerCard();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerCardDto> getCustomerCard() {
        return customerCardRepository.findAllByUser_Id(SecurityUtil.getCurrentUserId())
                .stream()
                .map(customerCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CheckoutSummaryDto getCheckoutSummary() {
        List<CustomerCardDto> customerCards = getCustomerCard();
        return new CheckoutSummaryDto()
                .setItems(customerCards)
                .setTotalAmount(
                        customerCards.stream()
                                .map(customerCardDto -> customerCardDto.getProduct().getPrice()
                                        .multiply(new BigDecimal(customerCardDto.getQuantity())))
                                .reduce(BigDecimal::add).orElse(new BigDecimal(0)));
    }

}
