package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.domain.CustomerCard;
import io.recruitment.assessment.api.dto.CustomerCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerCardMapper extends BaseMapper<CustomerCard, CustomerCardDto> {

    @Override
    @Mapping(source = "user.id", target = "userId")
    CustomerCardDto toDto(CustomerCard entity);

}
