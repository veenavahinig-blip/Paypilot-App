package com.hcl.paypilot;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.never;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Arrays;

import java.util.Collections;

import java.util.List;

import java.util.Optional;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.entity.PaymentEntity;

import com.hcl.paypilot.repository.BillRepository;

import com.hcl.paypilot.repository.PaymentRepository;
import com.hcl.paypilot.service.PaymentServiceImpl;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)

class PaymentServiceImplTest {


    @Mock

    private PaymentRepository paymentRepository;


    @Mock

    private BillRepository billRepository;


    @InjectMocks

    private PaymentServiceImpl paymentService;


    private BillEntity billEntity;


    @BeforeEach

    void setUp() {


        billEntity = new BillEntity();


        billEntity.setBillId(101L);

        billEntity.setUserId("USER001");

        billEntity.setBillAmount(2500.0);

        billEntity.setBillStatus("UNPAID");

        billEntity.setReminderEnabled("YES");

        billEntity.setReminderDate(LocalDate.now().plusDays(2));

    }


    @Test

    void testPayBill_Success() {


        when(billRepository.findById(101L))

                .thenReturn(Optional.of(billEntity));


        String result =

                paymentService.payBill("USER001", 101L);


        assertEquals(

                "Bill payment completed successfully",

                result);


        ArgumentCaptor<PaymentEntity> paymentCaptor =

                ArgumentCaptor.forClass(PaymentEntity.class);


        verify(paymentRepository, times(1))

                .save(paymentCaptor.capture());


        PaymentEntity savedPayment =

                paymentCaptor.getValue();


        assertEquals(

                "USER001",

                savedPayment.getUserId());


        assertEquals(

                101L,

                savedPayment.getBillId());


        assertEquals(

                2500.0,

                savedPayment.getPaidAmount());


        assertEquals(

                "SUCCESS",

                savedPayment.getPaymentStatus());


        assertEquals(

                "Payment Successful",

                savedPayment.getMessage());


        verify(billRepository, times(1))

                .save(any(BillEntity.class));


        assertEquals(

                "PAID",

                billEntity.getBillStatus());


        assertEquals(

                "NO",

                billEntity.getReminderEnabled());


        assertNull(

                billEntity.getReminderDate());

    }


    @Test

    void testPayBill_WhenBillAlreadyPaid() {


        billEntity.setBillStatus("PAID");


        when(billRepository.findById(101L))

                .thenReturn(Optional.of(billEntity));


        String result =

                paymentService.payBill("USER001", 101L);


        assertEquals(

                "Bill is already paid",

                result);


        verify(paymentRepository, never())

                .save(any(PaymentEntity.class));


        verify(billRepository, never())

                .save(any(BillEntity.class));

    }


    @Test

    void testPayBill_BillNotFound() {


        when(billRepository.findById(101L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> paymentService.payBill(

                                "USER001",

                                101L));


        assertEquals(

                "Bill not found with Id : 101",

                exception.getMessage());


        verify(paymentRepository, never())

                .save(any(PaymentEntity.class));

    }


    @Test

    void testGetPaidBills() {


        BillEntity paidBill1 = new BillEntity();

        paidBill1.setBillId(1L);

        paidBill1.setBillStatus("PAID");


        BillEntity paidBill2 = new BillEntity();

        paidBill2.setBillId(2L);

        paidBill2.setBillStatus("PAID");


        BillEntity unpaidBill = new BillEntity();

        unpaidBill.setBillId(3L);

        unpaidBill.setBillStatus("UNPAID");


        when(billRepository.findByUserId("USER001"))

                .thenReturn(

                        Arrays.asList(

                                paidBill1,

                                paidBill2,

                                unpaidBill));


        List<BillEntity> result =

                paymentService.getPaidBills("USER001");


        assertNotNull(result);

        assertEquals(2, result.size());


        result.forEach(

                bill ->

                        assertEquals(

                                "PAID",

                                bill.getBillStatus()));

    }


    @Test

    void testGetPaidBills_EmptyList() {


        when(billRepository.findByUserId("USER001"))

                .thenReturn(Collections.emptyList());


        List<BillEntity> result =

                paymentService.getPaidBills("USER001");


        assertNotNull(result);

        assertEquals(0, result.size());

    }


    @Test

    void testGetUnpaidBills() {


        BillEntity bill1 = new BillEntity();

        bill1.setBillId(1L);

        bill1.setBillStatus("UNPAID");


        BillEntity bill2 = new BillEntity();

        bill2.setBillId(2L);

        bill2.setBillStatus("PENDING");


        BillEntity bill3 = new BillEntity();

        bill3.setBillId(3L);

        bill3.setBillStatus("PAID");


        when(billRepository.findByUserId("USER001"))

                .thenReturn(

                        Arrays.asList(

                                bill1,

                                bill2,

                                bill3));


        List<BillEntity> result =

                paymentService.getUnpaidBills("USER001");


        assertNotNull(result);

        assertEquals(2, result.size());

    }


    @Test

    void testGetUnpaidBills_EmptyList() {


        when(billRepository.findByUserId("USER001"))

                .thenReturn(Collections.emptyList());


        List<BillEntity> result =

                paymentService.getUnpaidBills("USER001");


        assertNotNull(result);

        assertEquals(0, result.size());

    }


    @Test

    void testGetPaymentHistory() {


        PaymentEntity payment1 = new PaymentEntity();

        payment1.setPaymentId(1L);

        payment1.setUserId("USER001");


        PaymentEntity payment2 = new PaymentEntity();

        payment2.setPaymentId(2L);

        payment2.setUserId("USER001");


        List<PaymentEntity> paymentList =

                Arrays.asList(payment1, payment2);


        when(paymentRepository.findByUserId("USER001"))

                .thenReturn(paymentList);


        List<PaymentEntity> result =

                paymentService.getPaymentHistory("USER001");


        assertNotNull(result);

        assertEquals(2, result.size());


        verify(paymentRepository, times(1))

                .findByUserId("USER001");

    }


    @Test

    void testGetPaymentHistory_EmptyList() {


        when(paymentRepository.findByUserId("USER001"))

                .thenReturn(Collections.emptyList());


        List<PaymentEntity> result =

                paymentService.getPaymentHistory("USER001");


        assertNotNull(result);

        assertEquals(0, result.size());

    }

}
 