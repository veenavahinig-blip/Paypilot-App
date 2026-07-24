package com.hcl.paypilot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.entity.PaymentEntity;

import com.hcl.paypilot.service.PaymentService;


/**

* ============================================================================

* Payment Controller

* ============================================================================

*

* This controller provides REST APIs for bill payment management

* within the PayPilot Application.

*

* Features:

* - Pay a bill

* - View paid bills

* - View unpaid bills

* - View payment history

*

* Base URL:

* http://localhost:8086/api/payments

*

* Author: PayPilot Team

* ============================================================================

*/


@RestController

@RequestMapping("/api/payments")

@CrossOrigin(origins = "*")

public class PaymentController {


    /**

     * Service layer dependency responsible for

     * payment-related business operations.

     */

    @Autowired

    private PaymentService paymentService;


    /**

     * =========================================================================

     * Pay Bill

     * =========================================================================

     *

     * Endpoint:

     * POST /api/payments/pay/{userId}/{billId}

     *

     * Processes payment for the specified bill.

     *

     * Business Flow:

     * - Validate bill existence

     * - Verify bill payment status

     * - Create payment record

     * - Mark bill as PAID

     * - Disable reminders

     *

     * Example:

     * POST /api/payments/pay/USER001/1001

     *

     * @param userId User identifier

     * @param billId Bill identifier

     * @return Payment status message

     */

    @PostMapping("/pay/{userId}/{billId}")

    public String payBill(

            @PathVariable String userId,

            @PathVariable Long billId) {


        return paymentService.payBill(

                userId,

                billId);


    }


    /**

     * =========================================================================

     * Get Paid Bills

     * =========================================================================

     *

     * Endpoint:

     * GET /api/payments/paid-bills/{userId}

     *

     * Retrieves all bills that have already been paid

     * by the specified user.

     *

     * Example:

     * GET /api/payments/paid-bills/USER001

     *

     * @param userId User identifier

     * @return List of paid bills

     */

    @GetMapping("/paid-bills/{userId}")

    public List<BillEntity> getPaidBills(

            @PathVariable String userId) {


        return paymentService.getPaidBills(userId);


    }


    /**

     * =========================================================================

     * Get Unpaid Bills

     * =========================================================================

     *

     * Endpoint:

     * GET /api/payments/unpaid-bills/{userId}

     *

     * Retrieves all unpaid bills belonging to the user.

     * Bills with statuses other than PAID are returned.

     *

     * Example:

     * GET /api/payments/unpaid-bills/USER001

     *

     * @param userId User identifier

     * @return List of unpaid bills

     */

    @GetMapping("/unpaid-bills/{userId}")

    public List<BillEntity> getUnpaidBills(

            @PathVariable String userId) {


        return paymentService.getUnpaidBills(userId);


    }


    /**

     * =========================================================================

     * Get Payment History

     * =========================================================================

     *

     * Endpoint:

     * GET /api/payments/history/{userId}

     *

     * Retrieves complete payment transaction history

     * for the specified user.

     *

     * Example:

     * GET /api/payments/history/USER001

     *

     * @param userId User identifier

     * @return List of payment transactions

     */

    @GetMapping("/history/{userId}")

    public List<PaymentEntity> getPaymentHistory(

            @PathVariable String userId) {


        return paymentService.getPaymentHistory(userId);


    }


}
 