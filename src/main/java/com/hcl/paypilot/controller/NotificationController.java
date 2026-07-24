package com.hcl.paypilot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.hcl.paypilot.entity.NotificationEntity;

import com.hcl.paypilot.service.NotificationService;


/**

* ============================================================================

* Notification Controller

* ============================================================================

*

* This controller provides REST APIs for managing user notifications

* within the PayPilot Application.

*

* Features:

* - Retrieve notifications for a specific user

* - Mark notifications as read

*

* Base URL:

* http://localhost:8086/notification

*

* Author: PayPilot Team

* ============================================================================

*/


@RestController

@RequestMapping("/notification")

@CrossOrigin("*")

public class NotificationController {


    /**

     * Service layer dependency responsible for

     * notification-related business operations.

     */

    @Autowired

    private NotificationService notificationService;


    /**

     * =========================================================================

     * Get Notifications By User Id

     * =========================================================================

     *

     * Endpoint:

     * GET /notification/{userId}

     *

     * Retrieves all notifications belonging to a user.

     * Notifications are returned in descending order

     * based on creation date.

     *

     * Example:

     * GET /notification/USER001

     *

     * @param userId Unique identifier of the user

     * @return List of notifications associated with the user

     */

    @GetMapping("/{userId}")

    public List<NotificationEntity> getNotifications(

            @PathVariable String userId) {


        return notificationService

                .getNotificationsByUserId(userId);

    }


    /**

     * =========================================================================

     * Mark Notification As Read

     * =========================================================================

     *

     * Endpoint:

     * PUT /notification/read/{notificationId}

     *

     * Updates the notification status and marks

     * the notification as read.

     *

     * Example:

     * PUT /notification/read/101

     *

     * @param notificationId Unique notification identifier

     * @return Success response message

     */

    @PutMapping("/read/{notificationId}")

    public String markAsRead(

            @PathVariable Long notificationId) {


        notificationService.markAsRead(notificationId);


        return "Notification marked as read";

    }


}
 