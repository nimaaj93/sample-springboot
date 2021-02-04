package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
;

@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, String> {

    List<CustomerCard> findAllByUser_Id(String userId);

}
