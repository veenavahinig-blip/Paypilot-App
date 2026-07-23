package com.hcl.paypilot.service;


import java.time.LocalDateTime;

import java.util.List;

import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.entity.PaymentEntity;

import com.hcl.paypilot.repository.BillRepository;

import com.hcl.paypilot.repository.PaymentRepository;


@Service

public class PaymentServiceImpl implements PaymentService {


    @Autowired

    private PaymentRepository paymentRepository;


    @Autowired

    private BillRepository billRepository;


    @Override

    public String payBill(String userId, Long billId) {


        BillEntity bill = billRepository.findById(billId)

                .orElseThrow(() ->

                        new RuntimeException(

                                "Bill not found with Id : "

                                        + billId));


        if ("PAID".equalsIgnoreCase(

                bill.getBillStatus())) {


            return "Bill is already paid";

        }


        PaymentEntity payment =

                new PaymentEntity();


        payment.setUserId(userId);


        payment.setBillId(billId);


        payment.setPaidAmount(

                bill.getBillAmount());


        payment.setPaymentDate(

                LocalDateTime.now());


        payment.setPaymentStatus(

                "SUCCESS");


        payment.setMessage(

                "Payment Successful");


        paymentRepository.save(payment);


        bill.setBillStatus("PAID");


        bill.setReminderEnabled("NO");


        bill.setReminderDate(null);


        billRepository.save(bill);


        return "Bill payment completed successfully";

    }


    @Override

    public List<BillEntity> getPaidBills(

            String userId) {

        List<BillEntity> bills =

                billRepository.findByUserId(

                        userId);


        return bills.stream()

                .filter(

                        bill ->

                                "PAID".equalsIgnoreCase(

                                        bill.getBillStatus()))

                .collect(Collectors.toList());

    }


    @Override

    public List<BillEntity> getUnpaidBills(

            String userId) {


        List<BillEntity> bills =

                billRepository.findByUserId(

                        userId);


        return bills.stream()

                .filter(

                        bill ->

                                !"PAID".equalsIgnoreCase(

                                        bill.getBillStatus()))

                .collect(Collectors.toList());

    }


    @Override

    public List<PaymentEntity> getPaymentHistory(

            String userId) {


        return paymentRepository

                .findByUserId(userId);

    }


}
 