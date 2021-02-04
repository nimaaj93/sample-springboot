package io.recruitment.assessment.api.service.productcatalogue;

import io.recruitment.assessment.api.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

;

public interface ProductCatalogueService {

    Page<ProductDto> searchProducts(Pageable pageable, String query);

    ProductDto create(ProductDto productDto);

    void deleteProduct(String productId);

}
