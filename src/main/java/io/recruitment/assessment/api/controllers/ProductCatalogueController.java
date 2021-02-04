package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.dto.ProductDto;
import io.recruitment.assessment.api.service.productcatalogue.ProductCatalogueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
;

@RestController
@RequestMapping("/api/v1/product-catalogue")
public class ProductCatalogueController {

    private final ProductCatalogueService productCatalogueService;

    public ProductCatalogueController(ProductCatalogueService productCatalogueService) {
        this.productCatalogueService = productCatalogueService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<Page<ProductDto>> searchProducts(Pageable pageable,
                                                           @RequestParam(value = "q", required = false) String q) {
        return ResponseEntity.ok(productCatalogueService.searchProducts(pageable, q));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productCatalogueService.create(productDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        productCatalogueService.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
