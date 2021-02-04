package io.recruitment.assessment.api.util;

import io.recruitment.assessment.api.domain.Product;
import io.recruitment.assessment.api.enumeration.ProductStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SpecificationFactory {

    private SpecificationFactory() {
    }

    public static Specification<Product> getProductSpecification(String query) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("status"), ProductStatus.AVAILABLE));
            if (StringUtils.hasText(query)) {
                predicateList.add(
                        criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + query + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + query + "%"))
                );
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(predicates));
        };
    }

}
