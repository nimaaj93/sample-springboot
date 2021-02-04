package io.recruitment.assessment.api.service.order;

import io.recruitment.assessment.api.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto submitOrder();

    List<OrderDto> getUserOrders();

}
