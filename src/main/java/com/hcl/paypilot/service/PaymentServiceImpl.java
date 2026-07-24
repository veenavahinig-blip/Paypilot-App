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


/**

* ============================================================================

* Payment Service Implementation

* ============================================================================

*

* This service implementation contains all business logic related to

* payment processing within the PayPilot Application.

*

* Responsibilities:

* - Bill Payment Processing

* - Payment Transaction Creation

* - Payment History Retrieval

* - Paid Bills Retrieval

* - Unpaid Bills Retrieval

* - Bill Status Management

*

* This class acts as the bridge between:

* Controller Layer and Repository Layer.

*

* Business Operations:

* - Validate bill availability

* - Prevent duplicate payments

* - Record successful transactions

* - Update bill payment status

* - Disable reminders after payment

*

* Author: PayPilot Team

* ============================================================================

*/

@Service

public class PaymentServiceImpl implements PaymentService {


    /**

     * Repository dependency used for performing

     * payment transaction database operations.

     */

    @Autowired

    private PaymentRepository paymentRepository;


    /**

     * Repository dependency used for performing

     * bill-related database operations.

     */

    @Autowired

    private BillRepository billRepository;


    /**

     * =========================================================================

     * Pay Bill

     * =========================================================================

     *

     * Processes payment for a specified bill.

     *

     * Business Flow:

     * - Validate bill existence

     * - Prevent duplicate payments

     * - Create payment transaction

     * - Mark bill as PAID

     * - Disable reminders

     * - Remove reminder date

     *

     * Payment Status:

     * SUCCESS

     *

     * @param userId User Identifier

     * @param billId Bill Identifier

     * @return Payment status message

     */

    @Override

    public String payBill(

            String userId,

            Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

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


    /**

     * =========================================================================

     * Get Paid Bills

     * =========================================================================

     *

     * Retrieves all bills that have been paid

     * by the specified user.

     *

     * A bill is considered paid when:

     * Bill Status = PAID

     *

     * @param userId User Identifier

     * @return List of paid bills

     */

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


    /**

     * =========================================================================

     * Get Unpaid Bills

     * =========================================================================

     *

     * Retrieves all unpaid bills belonging

     * to the specified user.

     *

     * Includes statuses such as:

     * - PENDING

     * - OVERDUE

     * - SNOOZED

     *

     * Excludes:

     * - PAID

     *

     * @param userId User Identifier

     * @return List of unpaid bills

     */

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


    /**

     * =========================================================================

     * Get Payment History

     * =========================================================================

     *

     * Retrieves complete payment transaction history

     * for the specified user.

     *

     * Transaction Information Includes:

     * - Payment Identifier

     * - Bill Identifier

     * - Payment Amount

     * - Payment Date & Time

     * - Payment Status

     * - Payment Message

     *

     * Common Usage:

     * - Payment History Screen

     * - Dashboard Reporting

     * - Transaction Auditing

     *

     * @param userId User Identifier

     * @return List of payment transactions

     */

    @Override

    public List<PaymentEntity> getPaymentHistory(

            String userId) {


        return paymentRepository

                .findByUserId(userId);

    }


}
 