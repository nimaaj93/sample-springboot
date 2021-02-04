package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.domain.OrderItem;
import io.recruitment.assessment.api.dto.OrderItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemDto> {
}
