package io.recruitment.assessment.api.mapper;

import io.recruitment.assessment.api.domain.Product;
import io.recruitment.assessment.api.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductDto> {
}
