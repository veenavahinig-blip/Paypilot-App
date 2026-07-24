package com.hcl.paypilot.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.hcl.paypilot.entity.NotificationEntity;


/**

* ============================================================================

* Notification Repository

* ============================================================================

*

* Repository interface responsible for performing database operations

* related to user notifications in the PayPilot Application.

*

* This repository extends JpaRepository and provides:

* - Standard CRUD Operations

* - Notification Retrieval

* - Notification Status Updates

* - User-specific Notification Queries

*

* Entity:

* NotificationEntity

*

* Primary Key:

* Long (notificationId)

*

* Features Supported:

* - Store notifications

* - Retrieve notifications by user

* - Mark notifications as read

* - Notification history management

*

* Author: PayPilot Team

* ============================================================================

*/

public interface NotificationRepository

        extends JpaRepository<NotificationEntity, Long> {


    /**

     * =========================================================================

     * Find Notifications By User Id

     * =========================================================================

     *

     * Retrieves all notifications belonging to a specific user.

     *

     * Notifications are returned in descending order based on

     * creation date so that the most recent notifications appear first.

     *

     * Example:

     * USER001 →

     * - Bill Payment Successful

     * - Reminder Set Successfully

     * - Auto Payment Enabled

     *

     * @param userId User Identifier

     * @return List of notifications ordered by created date descending

     */

    List<NotificationEntity> findByUserIdOrderByCreatedDateDesc(

            String userId);


}
 