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

import java.util.Arrays;

import java.util.Collections;

import java.util.List;

import java.util.Optional;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.repository.BillRepository;
import com.hcl.paypilot.service.BillServiceImpl;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)

class BillServiceImplTest {


    @Mock

    private BillRepository billRepository;


    @InjectMocks

    private BillServiceImpl billService;


    private BillEntity bill;


    @BeforeEach

    void setUp() {


        bill = new BillEntity();


        bill.setBillId(1L);

        bill.setUserId("USER001");

        bill.setBillName("Electricity");

        bill.setBillCategory("UTILITY");

        bill.setBillAmount(2500.00);

        bill.setBillDueDate(LocalDate.now().plusDays(5));

        bill.setReminderEnabled("NO");

        bill.setBillStatus("PENDING");

        bill.setShedulePayment(false);

    }


    @Test

    void testAddBill() {


        when(billRepository.save(any(BillEntity.class)))

                .thenReturn(bill);


        String result = billService.addBill(bill);


        assertEquals(

                "Bill added successfully",

                result);


        verify(billRepository, times(1))

                .save(bill);

    }


    @Test

    void testUpdateBill() {


        BillEntity updatedBill = new BillEntity();


        updatedBill.setUserId("USER002");

        updatedBill.setBillName("Internet");

        updatedBill.setBillCategory("BROADBAND");

        updatedBill.setBillAmount(1200.00);

        updatedBill.setBillDueDate(LocalDate.now().plusDays(10));

        updatedBill.setReminderEnabled("YES");

        updatedBill.setReminderDate(LocalDate.now().plusDays(7));

        updatedBill.setBillStatus("PENDING");


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.updateBill(

                        1L,

                        updatedBill);


        assertEquals(

                "Bill updated successfully",

                result);


        verify(billRepository)

                .save(bill);


        assertEquals(

                "Internet",

                bill.getBillName());


        assertEquals(

                "BROADBAND",

                bill.getBillCategory());

    }


    @Test

    void testUpdateBill_NotFound() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> billService.updateBill(

                                1L,

                                bill));


        assertEquals(

                "Bill not found with ID : 1",

                exception.getMessage());

    }


    @Test

    void testDeleteBill() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.deleteBill(1L);


        assertEquals(

                "Bill deleted successfully",

                result);


        verify(billRepository)

                .delete(bill);

    }


    @Test

    void testDeleteBill_NotFound() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> billService.deleteBill(1L));


        assertEquals(

                "Bill not found with ID : 1",

                exception.getMessage());

    }


    @Test

    void testSetReminder() {


        bill.setBillDueDate(

                LocalDate.now().plusDays(5));


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.setReminder(1L);


        assertEquals(

                "Reminder set successfully",

                result);


        assertEquals(

                "YES",

                bill.getReminderEnabled());


        assertEquals(

                bill.getBillDueDate().minusDays(3),

                bill.getReminderDate());


        verify(billRepository)

                .save(bill);

    }


    @Test

    void testSnoozeBill() {


        bill.setReminderEnabled("YES");


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.snoozeBill(1L);


        assertEquals(

                "Bill Snoozed Successfully",

                result);


        assertEquals(

                "SNOOZED",

                bill.getBillStatus());


        assertEquals(

                "NO",

                bill.getReminderEnabled());


        assertNotNull(

                bill.getSnoozeDate());


        assertEquals(

                "YES",

                bill.getPreviousReminderStatus());


        verify(billRepository)

                .save(bill);

    }


    @Test

    void testUnSnoozeBill_WithPreviousReminder() {


        bill.setPreviousReminderStatus("YES");

        bill.setBillStatus("SNOOZED");


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.unSnoozeBill(1L);


        assertEquals(

                "Bill Unsnoozed Successfully",

                result);


        assertEquals(

                "PENDING",

                bill.getBillStatus());


        assertNull(

                bill.getSnoozeDate());


        assertEquals(

                "YES",

                bill.getReminderEnabled());


        verify(billRepository)

                .save(bill);

    }


    @Test

    void testUnSnoozeBill_WithoutPreviousReminder() {


        bill.setPreviousReminderStatus(null);


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        billService.unSnoozeBill(1L);


        assertEquals(

                "YES",

                bill.getReminderEnabled());

    }


    @Test

    void testGetUserBills() {


        List<BillEntity> bills =

                Arrays.asList(bill);


        when(billRepository.findByUserId("USER001"))

                .thenReturn(bills);


        List<BillEntity> result =

                billService.getUserBills("USER001");


        assertEquals(1, result.size());


        verify(billRepository)

                .findByUserId("USER001");

    }


    @Test

    void testGetUserBills_EmptyList() {


        when(billRepository.findByUserId("USER001"))

                .thenReturn(Collections.emptyList());


        List<BillEntity> result =

                billService.getUserBills("USER001");


        assertEquals(0, result.size());

    }


    @Test

    void testGetBillById() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        BillEntity result =

                billService.getBillById(1L);


        assertNotNull(result);


        assertEquals(

                1L,

                result.getBillId());

    }


    @Test

    void testGetBillById_NotFound() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> billService.getBillById(1L));


        assertEquals(

                "Bill not found with ID : 1",

                exception.getMessage());

    }


    @Test

    void testGetPendingBills() {


        List<BillEntity> bills =

                Arrays.asList(bill);


        when(billRepository

                .findByUserIdAndBillStatus(

                        "USER001",

                        "PENDING"))

                .thenReturn(bills);


        List<BillEntity> result =

                billService.getPendingBills(

                        "USER001");


        assertEquals(1, result.size());


        verify(billRepository)

                .findByUserIdAndBillStatus(

                        "USER001",

                        "PENDING");

    }


    @Test

    void testEnableSchedulePayment() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.enableSchedulePayment(1L);


        assertEquals(

                "Schedule Payment Enabled",

                result);


        assertEquals(

                true,

                bill.isShedulePayment());


        verify(billRepository)

                .save(bill);

    }


    @Test

    void testEnableSchedulePayment_NotFound() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> billService

                                .enableSchedulePayment(1L));


        assertEquals(

                "Bill not found",

                exception.getMessage());

    }


    @Test

    void testDisableSchedulePayment() {


        bill.setShedulePayment(true);


        when(billRepository.findById(1L))

                .thenReturn(Optional.of(bill));


        String result =

                billService.disableSchedulePayment(1L);


        assertEquals(

                "Auto Payment Disabled Successfully",

                result);


        assertEquals(

                false,

                bill.isShedulePayment());


        verify(billRepository)

                .save(bill);

    }


    @Test

    void testDisableSchedulePayment_NotFound() {


        when(billRepository.findById(1L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> billService

                                .disableSchedulePayment(1L));


        assertEquals(

                "Bill not found",

                exception.getMessage());

    }


    @Test

    void testAutoPayBills() {


        bill.setBillDueDate(LocalDate.now());

        bill.setBillStatus("PENDING");

        bill.setShedulePayment(true);


        when(billRepository

                .findByBillStatusAndShedulePayment(

                        "PENDING",

                        true))

                .thenReturn(

                        Arrays.asList(bill));


        String result =

                billService.autoPayBills();


        assertEquals(

                "Auto Payment Process Completed",

                result);


        assertEquals(

                "PAID",

                bill.getBillStatus());


        verify(billRepository)

                .save(bill);

    }


    @Test

    void testAutoPayBills_NoMatchingBills() {


        bill.setBillDueDate(

                LocalDate.now().plusDays(3));


        when(billRepository

                .findByBillStatusAndShedulePayment(

                        "PENDING",

                        true))

                .thenReturn(

                        Arrays.asList(bill));


        String result =

                billService.autoPayBills();


        assertEquals(

                "Auto Payment Process Completed",

                result);


        verify(billRepository, never())

                .save(any(BillEntity.class));

    }


    @Test

    void testAutoPayBills_EmptyList() {


        when(billRepository

                .findByBillStatusAndShedulePayment(

                        "PENDING",

                        true))

                .thenReturn(

                        Collections.emptyList());


        String result =

                billService.autoPayBills();


        assertEquals(

                "Auto Payment Process Completed",

                result);

    }

}
 