package com.hcl.paypilot.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.hcl.paypilot.entity.BillEntity;


@Repository

public interface BillRepository extends JpaRepository<BillEntity, Long> {


    List<BillEntity> findByUserId(String userId);
    
    List<BillEntity> findByUserIdAndBillStatus(

            String userId,

            String billStatus);


    List<BillEntity> findByBillStatusAndShedulePayment(

            String billStatus,

            boolean shedulePayment);
     

}
 