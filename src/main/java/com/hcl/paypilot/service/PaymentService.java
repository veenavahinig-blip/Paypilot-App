package com.hcl.paypilot.service;


import java.util.List;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.entity.PaymentEntity;


/**

* ============================================================================

* Payment Service

* ============================================================================

*

* Service interface responsible for defining business operations

* related to bill payments within the PayPilot Application.

*

* This interface acts as a contract between the Controller Layer

* and the Service Implementation Layer.

*

* Features Supported:

* - Bill Payment Processing

* - Paid Bills Retrieval

* - Unpaid Bills Retrieval

* - Payment History Management

* - Transaction Tracking

*

* Common Operations:

* - Pay a bill

* - View paid bills

* - View unpaid bills

* - View transaction history

*

* Author: PayPilot Team

* ============================================================================

*/

public interface PaymentService {


    /**

     * =========================================================================

     * Pay Bill

     * =========================================================================

     *

     * Processes payment for the specified bill.

     *

     * Business Flow:

     * - Validate bill existence

     * - Verify bill payment status

     * - Create payment transaction

     * - Update bill status to PAID

     * - Disable active reminders

     *

     * @param userId User Identifier

     * @param billId Bill Identifier

     * @return Payment status message

     */

    String payBill(

            String userId,

            Long billId);


    /**

     * =========================================================================

     * Get Paid Bills

     * =========================================================================

     *

     * Retrieves all bills that have been successfully paid

     * by the specified user.

     *

     * A bill is considered paid when:

     * Bill Status = PAID

     *

     * @param userId User Identifier

     * @return List of paid bills

     */

    List<BillEntity> getPaidBills(

            String userId);


    /**

     * =========================================================================

     * Get Unpaid Bills

     * =========================================================================

     *

     * Retrieves all unpaid bills belonging to a user.

     *

     * Typically includes bills with statuses:

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

    List<BillEntity> getUnpaidBills(

            String userId);


    /**

     * =========================================================================

     * Get Payment History

     * =========================================================================

     *

     * Retrieves complete payment transaction history

     * for the specified user.

     *

     * Transaction details may include:

     * - Payment Amount

     * - Bill Identifier

     * - Payment Date & Time

     * - Payment Status

     * - Transaction Message

     *

     * @param userId User Identifier

     * @return List of payment transactions

     */

    List<PaymentEntity> getPaymentHistory(

            String userId);


}
 