package com.hcl.paypilot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.entity.PaymentEntity;

import com.hcl.paypilot.service.PaymentService;


@RestController

@RequestMapping("/api/payments")

@CrossOrigin(origins = "*")

public class PaymentController {


    @Autowired

    private PaymentService paymentService;


    @PostMapping("/pay/{userId}/{billId}")

    public String payBill(

            @PathVariable String userId,

            @PathVariable Long billId) {


        return paymentService.payBill(

                userId,

                billId);

    }


    @GetMapping("/paid-bills/{userId}")

    public List<BillEntity> getPaidBills(

            @PathVariable String userId) {


        return paymentService.getPaidBills(userId);

    }


    @GetMapping("/unpaid-bills/{userId}")

    public List<BillEntity> getUnpaidBills(

            @PathVariable String userId) {


        return paymentService.getUnpaidBills(userId);

    }


    @GetMapping("/history/{userId}")

    public List<PaymentEntity> getPaymentHistory(

            @PathVariable String userId) {


        return paymentService.getPaymentHistory(userId);

    }


}
 