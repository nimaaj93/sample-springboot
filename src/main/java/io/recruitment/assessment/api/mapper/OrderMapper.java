package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.domain.Order;
import io.recruitment.assessment.api.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface OrderMapper extends BaseMapper<Order, OrderDto> {

    @Override
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order entity);

}
