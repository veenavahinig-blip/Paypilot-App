package com.hcl.paypilot.service;


import java.util.List;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.entity.PaymentEntity;


public interface PaymentService {


    String payBill(

            String userId,

            Long billId);


    List<BillEntity> getPaidBills(

            String userId);


    List<BillEntity> getUnpaidBills(

            String userId);


    List<PaymentEntity> getPaymentHistory(

            String userId);


}
 