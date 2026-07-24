package com.hcl.paypilot.service;


import java.util.List;


import com.hcl.paypilot.entity.NotificationEntity;


/**

* ============================================================================

* Notification Service

* ============================================================================

*

* Service interface responsible for defining business operations

* related to user notifications within the PayPilot Application.

*

* This interface acts as a contract between the Controller Layer

* and the Service Implementation Layer.

*

* Features Supported:

* - Create Notifications

* - Retrieve User Notifications

* - Mark Notifications As Read

* - Notification History Management

*

* Common Notification Types:

* - Bill Payment Success

* - Bill Reminder Alerts

* - Due Date Notifications

* - Scheduled Payment Updates

* - Account Activity Notifications

*

* Author: PayPilot Team

* ============================================================================

*/

public interface NotificationService {


    /**

     * =========================================================================

     * Save Notification

     * =========================================================================

     *

     * Creates and stores a new notification

     * for the specified user.

     *

     * Example:

     * - Bill Payment Successful

     * - Reminder Enabled Successfully

     * - Scheduled Payment Activated

     *

     * @param userId User Identifier

     * @param message Notification Message

     * @return Saved Notification Entity

     */

    NotificationEntity saveNotification(

            String userId,

            String message);


    /**

     * =========================================================================

     * Get Notifications By User Id

     * =========================================================================

     *

     * Retrieves all notifications belonging

     * to a specific user.

     *

     * Notifications are generally returned

     * in descending order based on creation date.

     *

     * Example:

     * USER001

     * →

     * Latest Notification First

     *

     * @param userId User Identifier

     * @return List of user notifications

     */

    List<NotificationEntity> getNotificationsByUserId(

            String userId);


    /**

     * =========================================================================

     * Mark Notification As Read

     * =========================================================================

     *

     * Updates the notification status

     * and marks it as read.

     *

     * This operation helps track whether

     * the user has viewed a notification.

     *

     * @param notificationId Notification Identifier

     */

    void markAsRead(Long notificationId);


}
 