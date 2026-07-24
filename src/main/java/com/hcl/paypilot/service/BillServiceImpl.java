package com.hcl.paypilot.service;


import java.time.LocalDate;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.repository.BillRepository;


/**

* ============================================================================

* Bill Service Implementation

* ============================================================================

*

* This service implementation contains all business logic related to

* bill management within the PayPilot Application.

*

* Responsibilities:

* - Add Bills

* - Update Bills

* - Delete Bills

* - Set Bill Reminders

* - Snooze Bills

* - Unsnooze Bills

* - Retrieve User Bills

* - Retrieve Bill Details

* - Retrieve Pending Bills

* - Enable Scheduled Payments

* - Disable Scheduled Payments

* - Process Automatic Bill Payments

*

* This class acts as the bridge between:

* Controller Layer and Repository Layer.

*

* Author: PayPilot Team

* ============================================================================

*/

@Service

public class BillServiceImpl implements BillService {


    /**

     * Repository dependency used for performing

     * database operations related to bills.

     */

    @Autowired

    private BillRepository billRepository;


    /**

     * =========================================================================

     * Add Bill

     * =========================================================================

     *

     * Saves a new bill into the system.

     *

     * @param bill Bill details

     * @return Success message

     */

    @Override

    public String addBill(BillEntity bill) {


        billRepository.save(bill);


        return "Bill added successfully";

    }


    /**

     * =========================================================================

     * Update Bill

     * =========================================================================

     *

     * Updates an existing bill using the provided bill identifier.

     *

     * @param billId Bill Identifier

     * @param bill Updated bill details

     * @return Success message

     */

    @Override

    public String updateBill(

            Long billId,

            BillEntity bill) {


        BillEntity existingBill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found with ID : "

                                                + billId));


        existingBill.setUserId(bill.getUserId());

        existingBill.setBillName(bill.getBillName());

        existingBill.setBillCategory(bill.getBillCategory());

        existingBill.setBillAmount(bill.getBillAmount());

        existingBill.setBillDueDate(bill.getBillDueDate());

        existingBill.setReminderEnabled(bill.getReminderEnabled());

        existingBill.setReminderDate(bill.getReminderDate());

        existingBill.setBillStatus(bill.getBillStatus());

        existingBill.setSnoozeDate(bill.getSnoozeDate());


        billRepository.save(existingBill);


        return "Bill updated successfully";

    }


    /**

     * =========================================================================

     * Delete Bill

     * =========================================================================

     *

     * Removes a bill from the system.

     *

     * @param billId Bill Identifier

     * @return Success message

     */

    @Override

    public String deleteBill(Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found with ID : "

                                                + billId));


        billRepository.delete(bill);


        return "Bill deleted successfully";

    }


    /**

     * =========================================================================

     * Set Reminder

     * =========================================================================

     *

     * Enables reminder functionality for a bill.

     *

     * Reminder date is automatically calculated

     * as 3 days before the due date.

     *

     * @param billId Bill Identifier

     * @return Success message

     */

    @Override

    public String setReminder(Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found with ID : "

                                                + billId));


        bill.setReminderEnabled("YES");


        if (bill.getBillDueDate() != null) {


            bill.setReminderDate(

                    bill.getBillDueDate().minusDays(3));

        }


        billRepository.save(bill);


        return "Reminder set successfully";

    }


    /**

     * =========================================================================

     * Snooze Bill

     * =========================================================================

     *

     * Temporarily postpones the bill.

     *

     * Actions Performed:

     * - Stores current reminder status

     * - Sets bill status to SNOOZED

     * - Sets snooze date to current date + 3 days

     * - Disables reminders

     *

     * @param billId Bill Identifier

     * @return Success message

     */

    @Override

    public String snoozeBill(Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found with ID : "

                                                + billId));


        bill.setPreviousReminderStatus(

                bill.getReminderEnabled());


        bill.setBillStatus("SNOOZED");


        bill.setSnoozeDate(

                LocalDate.now().plusDays(3));


        bill.setReminderEnabled("NO");


        billRepository.save(bill);


        return "Bill Snoozed Successfully";

    }


    /**

     * =========================================================================

     * Unsnooze Bill

     * =========================================================================

     *

     * Restores a snoozed bill back to active state.

     *

     * Actions Performed:

     * - Status changed to PENDING

     * - Snooze date removed

     * - Previous reminder setting restored

     *

     * @param billId Bill Identifier

     * @return Success message

     */

    @Override

    public String unSnoozeBill(Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found with ID : "

                                                + billId));


        bill.setBillStatus("PENDING");


        bill.setSnoozeDate(null);


        if (bill.getPreviousReminderStatus() != null) {


            bill.setReminderEnabled(

                    bill.getPreviousReminderStatus());


        } else {


            bill.setReminderEnabled("YES");

        }


        billRepository.save(bill);


        return "Bill Unsnoozed Successfully";

    }


    /**

     * =========================================================================

     * Get User Bills

     * =========================================================================

     *

     * Retrieves all bills belonging to a user.

     *

     * @param userId User Identifier

     * @return List of Bills

     */

    @Override

    public List<BillEntity> getUserBills(String userId) {


        return billRepository.findByUserId(

                String.valueOf(userId));

    }


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

    @Override

    public BillEntity getBillById(Long billId) {


        return billRepository.findById(billId)

                .orElseThrow(() ->

                        new RuntimeException(

                                "Bill not found with ID : "

                                        + billId));

    }


    /**

     * =========================================================================

     * Get Pending Bills

     * =========================================================================

     *

     * Retrieves all pending bills for a user.

     *

     * @param userId User Identifier

     * @return List of Pending Bills

     */

    @Override

    public List<BillEntity> getPendingBills(

            String userId) {


        return billRepository

                .findByUserIdAndBillStatus(

                        userId,

                        "PENDING");

    }


    /**

     * =========================================================================

     * Enable Scheduled Payment

     * =========================================================================

     *

     * Enables automatic payment functionality

     * for the specified bill.

     *

     * @param billId Bill Identifier

     * @return Success message

     */

    @Override

    public String enableSchedulePayment(

            Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found"));


        bill.setShedulePayment(true);


        billRepository.save(bill);


        return "Schedule Payment Enabled";

    }


    /**

     * =========================================================================

     * Auto Pay Bills

     * =========================================================================

     *

     * Processes all eligible bills for automatic payment.

     *

     * Eligibility Criteria:

     * - Bill Status = PENDING

     * - Scheduled Payment Enabled

     * - Due Date equals Current Date

     *

     * Eligible bills are automatically updated

     * to PAID status.

     *

     * @return Process completion message

     */

    @Override

    public String autoPayBills() {


        List<BillEntity> bills =

                billRepository

                        .findByBillStatusAndShedulePayment(

                                "PENDING",

                                true);


        LocalDate today = LocalDate.now();


        for (BillEntity bill : bills) {


            if (bill.getBillDueDate() != null

                    && bill.getBillDueDate()

                    .equals(today)) {


                bill.setBillStatus("PAID");


                billRepository.save(bill);

            }

        }


        return "Auto Payment Process Completed";

    }


    /**

     * =========================================================================

     * Disable Scheduled Payment

     * =========================================================================

     *

     * Disables automatic payment functionality

     * for the specified bill.

     *

     * @param billId Bill Identifier

     * @return Success message

     */

    @Override

    public String disableSchedulePayment(

            Long billId) {


        BillEntity bill =

                billRepository.findById(billId)

                        .orElseThrow(() ->

                                new RuntimeException(

                                        "Bill not found"));


        bill.setShedulePayment(false);


        billRepository.save(bill);


        return "Auto Payment Disabled Successfully";

    }


}
 