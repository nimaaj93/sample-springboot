package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByUser_Id(String userId);

}
