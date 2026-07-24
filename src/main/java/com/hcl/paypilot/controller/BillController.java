package com.hcl.paypilot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.service.BillService;


/**

* ============================================================================

* Bill Controller

* ============================================================================

*

* This controller exposes REST APIs for managing bills within the

* PayPilot Application.

*

* Functionalities:

* - Add a new bill

* - Update an existing bill

* - Delete a bill

* - Set reminder for bill payment

* - Snooze and Unsnooze bills

* - Fetch bills by user

* - Fetch bill details by bill ID

* - View pending bills

* - Enable scheduled payments

* - Disable scheduled payments

* - Trigger auto-payment process

*

* Base URL:

* http://localhost:8086/api/bills

*

* Author: PayPilot Team

* ============================================================================

*/


@RestController

@RequestMapping("/api/bills")

@CrossOrigin(origins = "*")

public class BillController {


    /**

     * Service layer dependency for bill business operations.

     */

    @Autowired

    private BillService billService;


    /**

     * =========================================================================

     * Add New Bill

     * =========================================================================

     *

     * Endpoint:

     * POST /api/bills/add

     *

     * Creates a new bill for a user.

     *

     * @param bill Bill details received from request body

     * @return Success message

     */

    @PostMapping("/add")

    public String addBill(@RequestBody BillEntity bill) {


        return billService.addBill(bill);


    }


    /**

     * =========================================================================

     * Update Existing Bill

     * =========================================================================

     *

     * Endpoint:

     * PUT /api/bills/update/{billId}

     *

     * Updates bill information for an existing bill.

     *

     * @param billId Unique bill identifier

     * @param bill Updated bill details

     * @return Success message

     */

    @PutMapping("/update/{billId}")

    public String updateBill(

            @PathVariable Long billId,

            @RequestBody BillEntity bill) {


        return billService.updateBill(billId, bill);


    }


    /**

     * =========================================================================

     * Delete Bill

     * =========================================================================

     *

     * Endpoint:

     * DELETE /api/bills/delete/{billId}

     *

     * Deletes a bill from the system.

     *

     * @param billId Unique bill identifier

     * @return Success message

     */

    @DeleteMapping("/delete/{billId}")

    public String deleteBill(@PathVariable Long billId) {


        return billService.deleteBill(billId);


    }


    /**

     * =========================================================================

     * Set Reminder

     * =========================================================================

     *

     * Endpoint:

     * PUT /api/bills/set-reminder/{billId}

     *

     * Enables reminder notification for a bill.

     *

     * @param billId Unique bill identifier

     * @return Success message

     */

    @PutMapping("/set-reminder/{billId}")

    public String setReminder(@PathVariable Long billId) {


        return billService.setReminder(billId);


    }


    /**

     * =========================================================================

     * Snooze Bill

     * =========================================================================

     *

     * Endpoint:

     * PUT /api/bills/snooze/{billId}

     *

     * Snoozes a bill for a specified period.

     * During snooze:

     * - Bill status becomes SNOOZED

     * - Reminder is temporarily disabled

     *

     * @param billId Unique bill identifier

     * @return Success message

     */

    @PutMapping("/snooze/{billId}")

    public String snoozeBill(@PathVariable Long billId) {


        return billService.snoozeBill(billId);


    }


    /**

     * =========================================================================

     * Get All Bills of User

     * =========================================================================

     *

     * Endpoint:

     * GET /api/bills/user/{userId}

     *

     * Fetches all bills belonging to a specific user.

     *

     * @param userId User identifier

     * @return List of bills

     */

    @GetMapping("/user/{userId}")

    public List<BillEntity> getUserBills(

            @PathVariable String userId) {


        return billService.getUserBills(userId);


    }


    /**

     * =========================================================================

     * Unsnooze Bill

     * =========================================================================

     *

     * Endpoint:

     * PUT /api/bills/unsnooze/{billId}

     *

     * Restores bill from snoozed state.

     * - Status becomes PENDING

     * - Previous reminder settings restored

     *

     * @param billId Unique bill identifier

     * @return Success message

     */

    @PutMapping("/unsnooze/{billId}")

    public String unSnoozeBill(

            @PathVariable Long billId) {


        return billService.unSnoozeBill(billId);


    }


    /**

     * =========================================================================

     * Get Bill By Id

     * =========================================================================

     *

     * Endpoint:

     * GET /api/bills/{billId}

     *

     * Retrieves bill details using bill identifier.

     *

     * @param billId Unique bill identifier

     * @return Bill details

     */

    @GetMapping("/{billId}")

    public BillEntity getBillById(

            @PathVariable Long billId) {


        return billService.getBillById(billId);


    }


    /**

     * =========================================================================

     * Get Pending Bills

     * =========================================================================

     *

     * Endpoint:

     * GET /api/bills/pending/{userId}

     *

     * Returns only pending bills of a user.

     *

     * @param userId User identifier

     * @return List of pending bills

     */

    @GetMapping("/pending/{userId}")

    public List<BillEntity> getPendingBills(

            @PathVariable String userId) {


        return billService.getPendingBills(userId);


    }


    /**

     * =========================================================================

     * Enable Scheduled Payment

     * =========================================================================

     *

     * Endpoint:

     * PUT /api/bills/schedule/{billId}

     *

     * Enables automatic payment for the specified bill.

     *

     * @param billId Unique bill identifier

     * @return Success message

     */

    @PutMapping("/schedule/{billId}")

    public String enableSchedulePayment(

            @PathVariable Long billId) {


        return billService.enableSchedulePayment(billId);


    }


    /**

     * =========================================================================

     * Auto Pay Bills

     * =========================================================================

     *

     * Endpoint:

     * POST /api/bills/autopay

     *

     * Processes all eligible bills that have:

     * - Status = PENDING

     * - Scheduled payment enabled

     * - Due date equals current date

     *

     * Eligible bills are automatically marked as PAID.

     *

     * @return Auto payment process result

     */

    @PostMapping("/autopay")

    public String autoPayBills() {


        return billService.autoPayBills();


    }


    /**

     * =========================================================================

     * Disable Scheduled Payment

     * =========================================================================

     *

     * Endpoint:

     * PUT /api/bills/unschedule/{billId}

     *

     * Disables automatic payment functionality

     * for the specified bill.

     *

     * @param billId Unique bill identifier

     * @return Success message

     */

    @PutMapping("/unschedule/{billId}")

    public String disableSchedulePayment(

            @PathVariable Long billId) {


        return billService.disableSchedulePayment(billId);


    }


}
 