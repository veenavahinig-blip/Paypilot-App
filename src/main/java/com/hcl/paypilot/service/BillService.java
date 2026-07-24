package com.hcl.paypilot.service;


import java.util.List;


import com.hcl.paypilot.entity.BillEntity;


/**

* ============================================================================

* Bill Service

* ============================================================================

*

* Service interface responsible for defining business operations

* related to bill management within the PayPilot Application.

*

* This interface acts as a contract between the Controller Layer

* and Service Implementation Layer.

*

* Features Supported:

* - Add Bill

* - Update Bill

* - Delete Bill

* - Set Reminder

* - Snooze Bill

* - Unsnooze Bill

* - Retrieve User Bills

* - Retrieve Bill By Id

* - Retrieve Pending Bills

* - Enable Scheduled Payment

* - Disable Scheduled Payment

* - Automatic Bill Payment Processing

*

* Author: PayPilot Team

* ============================================================================

*/

public interface BillService {


    /**

     * =========================================================================

     * Add Bill

     * =========================================================================

     *

     * Creates a new bill for the user.

     *

     * @param bill Bill details

     * @return Status message indicating whether

     *         bill creation was successful

     */

    String addBill(BillEntity bill);


    /**

     * =========================================================================

     * Update Bill

     * =========================================================================

     *

     * Updates an existing bill using the provided bill identifier.

     *

     * @param billId Bill Identifier

     * @param bill Updated bill information

     * @return Status message indicating whether

     *         update was successful

     */

    String updateBill(

            Long billId,

            BillEntity bill);


    /**

     * =========================================================================

     * Delete Bill

     * =========================================================================

     *

     * Removes a bill from the system.

     *

     * @param billId Bill Identifier

     * @return Status message indicating whether

     *         deletion was successful

     */

    String deleteBill(Long billId);


    /**

     * =========================================================================

     * Set Reminder

     * =========================================================================

     *

     * Enables reminder functionality for a bill.

     *

     * Reminder date is typically calculated

     * based on the bill due date.

     *

     * @param billId Bill Identifier

     * @return Status message indicating whether

     *         reminder was configured successfully

     */

    String setReminder(Long billId);


    /**

     * =========================================================================

     * Snooze Bill

     * =========================================================================

     *

     * Temporarily postpones a bill.

     *

     * During snooze:

     * - Bill status becomes SNOOZED

     * - Reminder is disabled temporarily

     * - Previous reminder settings are preserved

     *

     * @param billId Bill Identifier

     * @return Status message indicating whether

     *         snooze operation was successful

     */

    String snoozeBill(Long billId);


    /**

     * =========================================================================

     * Get User Bills

     * =========================================================================

     *

     * Retrieves all bills associated with a user.

     *

     * @param userId User Identifier

     * @return List of user bills

     */

    List<BillEntity> getUserBills(String userId);


    /**

     * =========================================================================

     * Get Bill By Id

     * =========================================================================

     *

     * Retrieves bill details using bill identifier.

     *

     * @param billId Bill Identifier

     * @return Bill Details

     */

    BillEntity getBillById(Long billId);


    /**

     * =========================================================================

     * Unsnooze Bill

     * =========================================================================

     *

     * Restores a snoozed bill back to active status.

     *

     * Actions Performed:

     * - Status changed to PENDING

     * - Snooze date removed

     * - Previous reminder settings restored

     *

     * @param billId Bill Identifier

     * @return Status message indicating whether

     *         unsnooze operation was successful

     */

    String unSnoozeBill(Long billId);


    /**

     * =========================================================================

     * Get Pending Bills

     * =========================================================================

     *

     * Retrieves all pending bills for a user.

     *

     * A pending bill is one that has not yet been paid.

     *

     * @param userId User Identifier

     * @return List of pending bills

     */

    List<BillEntity> getPendingBills(String userId);


    /**

     * =========================================================================

     * Enable Scheduled Payment

     * =========================================================================

     *

     * Enables automatic payment functionality

     * for a specific bill.

     *

     * Once enabled, the bill becomes eligible

     * for auto payment processing on the due date.

     *

     * @param billId Bill Identifier

     * @return Status message indicating whether

     *         scheduled payment was enabled

     */

    String enableSchedulePayment(Long billId);


    /**

     * =========================================================================

     * Auto Pay Bills

     * =========================================================================

     *

     * Processes all bills that satisfy:

     * - Status = PENDING

     * - Scheduled Payment Enabled

     * - Due Date equals Current Date

     *

     * Eligible bills are automatically marked as PAID.

     *

     * @return Auto payment process completion message

     */

    String autoPayBills();


    /**

     * =========================================================================

     * Disable Scheduled Payment

     * =========================================================================

     *

     * Disables automatic payment functionality

     * for a specific bill.

     *

     * @param billId Bill Identifier

     * @return Status message indicating whether

     *         scheduled payment was disabled

     */

    String disableSchedulePayment(Long billId);


}
 