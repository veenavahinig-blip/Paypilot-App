package com.hcl.paypilot.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.hcl.paypilot.entity.PaymentEntity;


/**

* ============================================================================

* Payment Repository

* ============================================================================

*

* Repository interface responsible for performing database operations

* related to payment transactions in the PayPilot Application.

*

* This repository extends JpaRepository and provides:

* - Standard CRUD Operations

* - Payment History Retrieval

* - User-wise Payment Search

* - Bill-wise Payment Search

*

* Entity:

* PaymentEntity

*

* Primary Key:

* Long (paymentId)

*

* Features Supported:

* - Save payment transactions

* - Retrieve payment history by user

* - Retrieve payment details by bill

* - Payment reporting and tracking

*

* Author: PayPilot Team

* ============================================================================

*/

@Repository

public interface PaymentRepository

        extends JpaRepository<PaymentEntity, Long> {


    /**

     * =========================================================================

     * Find Payments By User Id

     * =========================================================================

     *

     * Retrieves all payment transactions associated

     * with a specific user.

     *

     * Example:

     * USER001 →

     * - Electricity Bill Payment

     * - Internet Bill Payment

     * - Water Bill Payment

     *

     * Common Usage:

     * - Payment History

     * - Transaction Dashboard

     * - User Activity Tracking

     *

     * @param userId User Identifier

     * @return List of payment transactions

     */

    List<PaymentEntity> findByUserId(String userId);


    /**

     * =========================================================================

     * Find Payments By Bill Id

     * =========================================================================

     *

     * Retrieves all payment transactions associated

     * with a specific bill.

     *

     * This method can be used to track payment history

     * of a bill and verify transaction records.

     *

     * Example:

     * Bill ID = 1001

     *

     * Returns all payment transactions related

     * to bill 1001.

     *

     * @param billId Bill Identifier

     * @return List of payment records for the bill

     */

    List<PaymentEntity> findByBillId(Long billId);


}
 