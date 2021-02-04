package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
