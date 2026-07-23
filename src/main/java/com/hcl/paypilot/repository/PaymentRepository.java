package com.hcl.paypilot.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.hcl.paypilot.entity.PaymentEntity;


@Repository

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {


    List<PaymentEntity> findByUserId(String userId);


    List<PaymentEntity> findByBillId(Long billId);


}
 