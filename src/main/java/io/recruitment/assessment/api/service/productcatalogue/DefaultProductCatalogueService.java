package io.recruitment.assessment.api.service.productcatalogue;

import io.recruitment.assessment.api.domain.Product;
import io.recruitment.assessment.api.dto.ProductDto;
import io.recruitment.assessment.api.enumeration.ProductStatus;
import io.recruitment.assessment.api.mapper.ProductMapper;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.util.SpecificationFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

;

@Service
public class DefaultProductCatalogueService implements ProductCatalogueService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public DefaultProductCatalogueService(ProductRepository productRepository,
                                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> searchProducts(Pageable pageable, String query) {
        Specification<Product> productSpecification = SpecificationFactory.getProductSpecification(query);
        return productRepository.findAll(productSpecification, pageable)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteProduct(String productId) {
        productRepository.findById(productId)
                .ifPresent(product -> {
                    product.setStatus(ProductStatus.DELETED);
                    productRepository.save(product);
                });
    }
}