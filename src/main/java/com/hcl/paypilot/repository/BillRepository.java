package com.hcl.paypilot.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.hcl.paypilot.entity.BillEntity;


/**

* ============================================================================

* Bill Repository

* ============================================================================

*

* Repository interface responsible for performing database operations

* related to bill management in the PayPilot Application.

*

* This repository extends JpaRepository and provides:

* - Standard CRUD Operations

* - Custom Finder Methods

* - Bill Search Operations

* - Pending Bill Retrieval

* - Auto Payment Processing Support

*

* Entity:

* BillEntity

*

* Primary Key:

* Long (billId)

*

* Features Supported:

* - Retrieve bills by user

* - Retrieve pending bills

* - Retrieve bills eligible for auto payment

* - Standard create, update, delete operations

*

* Author: PayPilot Team

* ============================================================================

*/

@Repository

public interface BillRepository

        extends JpaRepository<BillEntity, Long> {


    /**

     * =========================================================================

     * Find Bills By User Id

     * =========================================================================

     *

     * Retrieves all bills belonging to a specific user.

     *

     * Example:

     * USER001 → Electricity Bill, Water Bill, Internet Bill

     *

     * @param userId User Identifier

     * @return List of bills associated with the user

     */

    List<BillEntity> findByUserId(String userId);


    /**

     * =========================================================================

     * Find Bills By User Id And Bill Status

     * =========================================================================

     *

     * Retrieves bills based on user identifier

     * and current bill status.

     *

     * Example:

     * USER001 + PENDING

     *

     * Returns all pending bills for USER001.

     *

     * @param userId User Identifier

     * @param billStatus Bill Status

     * @return List of matching bills

     */

    List<BillEntity> findByUserIdAndBillStatus(

            String userId,

            String billStatus);


    /**

     * =========================================================================

     * Find Bills By Status And Scheduled Payment Flag

     * =========================================================================

     *

     * Retrieves bills eligible for automatic payment processing.

     *

     * Typically used by:

     * - Auto Payment Scheduler

     * - Scheduled Payment Service

     *

     * Example:

     * Status = PENDING

     * Schedule Payment = true

     *

     * Returns all pending bills configured for auto payment.

     *

     * @param billStatus Current bill status

     * @param shedulePayment Scheduled payment enabled flag

     * @return List of bills eligible for auto payment

     */

    List<BillEntity> findByBillStatusAndShedulePayment(

            String billStatus,

            boolean shedulePayment);


}
 