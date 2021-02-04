package io.recruitment.assessment.api.service.order;

import io.recruitment.assessment.api.domain.*;
import io.recruitment.assessment.api.dto.OrderDto;
import io.recruitment.assessment.api.exception.EmptyCardException;
import io.recruitment.assessment.api.exception.ItemInsufficientStockException;
import io.recruitment.assessment.api.exception.UserNotFoundException;
import io.recruitment.assessment.api.mapper.OrderMapper;
import io.recruitment.assessment.api.repository.*;
import io.recruitment.assessment.api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
;
import java.util.stream.Collectors;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final CustomerCardRepository customerCardRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public DefaultOrderService(OrderRepository orderRepository,
                               OrderItemRepository orderItemRepository,
                               OrderMapper orderMapper,
                               CustomerCardRepository customerCardRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.customerCardRepository = customerCardRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public OrderDto submitOrder() {
        String currentUserId = SecurityUtil.getCurrentUserId();
        List<CustomerCard> userCardList = customerCardRepository.findAllByUser_Id(currentUserId);
        checkPreconditions(userCardList);
        Order savedOrder = saveOrder(currentUserId, userCardList);
        afterOrderSave(savedOrder, userCardList);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getUserOrders() {
        return orderRepository.findAllByUser_Id(SecurityUtil.getCurrentUserId())
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    private void checkPreconditions(List<CustomerCard> userCardList) {
        if (userCardList.isEmpty()) {
            throw new EmptyCardException();
        }
        userCardList.stream()
                .filter(customerCard -> customerCard.getQuantity() > customerCard.getProduct().getStock())
                .findAny()
                .ifPresent(__ -> {
                    throw new ItemInsufficientStockException();
                });
    }

    private List<OrderItem> convertToOrderItems(List<CustomerCard> userCardList) {
        return userCardList.stream()
                .map(customerCard ->
                        new OrderItem()
                            .setProduct(customerCard.getProduct())
                            .setQuantity(customerCard.getQuantity())
                )
                .collect(Collectors.toList());
    }

    private Order saveOrder(String currentUserId, List<CustomerCard> userCardList) {
        List<OrderItem> orderItems = convertToOrderItems(userCardList);
        BigDecimal totalAmount = orderItems
                .stream()
                .map(orderItem -> orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        User user = userRepository.findById(currentUserId)
                .orElseThrow(UserNotFoundException::new);

        Order order = new Order()
                .setUser(user)
                .setItems(orderItems)
                .setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        savedOrder.getItems()
                .stream()
                .map(orderItem -> orderItem.setOrder(savedOrder))
                .forEach(orderItemRepository::save);
        return savedOrder;
    }

    private void afterOrderSave(Order savedOrder, List<CustomerCard> userCardList) {
        customerCardRepository.deleteAll(userCardList);
        savedOrder.getItems()
                .forEach(orderItem -> {
                    Product product = orderItem.getProduct();
                    Integer stock = product.getStock();
                    stock = stock - orderItem.getQuantity();
                    product.setStock(stock);
                    productRepository.save(product);
                });
    }

}
