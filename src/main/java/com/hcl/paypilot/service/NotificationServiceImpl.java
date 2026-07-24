package com.hcl.paypilot.service;


import java.time.LocalDateTime;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.hcl.paypilot.entity.NotificationEntity;

import com.hcl.paypilot.repository.NotificationRepository;


/**

* ============================================================================

* Notification Service Implementation

* ============================================================================

*

* This service implementation contains all business logic related to

* notification management within the PayPilot Application.

*

* Responsibilities:

* - Create Notifications

* - Save Notifications

* - Retrieve User Notifications

* - Mark Notifications As Read

* - Manage Notification History

*

* This class acts as the bridge between:

* Controller Layer and Repository Layer.

*

* Author: PayPilot Team

* ============================================================================

*/

@Service

public class NotificationServiceImpl implements NotificationService {


    /**

     * Repository dependency used for performing

     * database operations related to notifications.

     */

    @Autowired

    private NotificationRepository notificationRepository;


    /**

     * =========================================================================

     * Save Notification

     * =========================================================================

     *

     * Creates and stores a notification for the specified user.

     *

     * Default Values:

     * - isRead = false

     * - createdDate = Current Date & Time

     *

     * Example Notifications:

     * - Bill Payment Successful

     * - Reminder Enabled Successfully

     * - Scheduled Payment Activated

     *

     * @param userId User Identifier

     * @param message Notification Message

     * @return Saved Notification Entity

     */

    @Override

    public NotificationEntity saveNotification(

            String userId,

            String message) {


        NotificationEntity notification =

                new NotificationEntity();


        notification.setUserId(userId);


        notification.setNotificationMessage(message);


        notification.setIsRead(false);


        notification.setCreatedDate(

                LocalDateTime.now());


        return notificationRepository.save(notification);

    }


    /**

     * =========================================================================

     * Get Notifications By User Id

     * =========================================================================

     *

     * Retrieves all notifications belonging to a user.

     *

     * Notifications are returned in descending order

     * based on their creation date.

     *

     * Latest notifications appear first.

     *

     * Example:

     * USER001

     * →

     * Bill Payment Successful

     * →

     * Auto Payment Enabled

     * →

     * Reminder Set Successfully

     *

     * @param userId User Identifier

     * @return List of user notifications

     */

    @Override

    public List<NotificationEntity> getNotificationsByUserId(

            String userId) {


        return notificationRepository

                .findByUserIdOrderByCreatedDateDesc(

                        userId);

    }


    /**

     * =========================================================================

     * Mark Notification As Read

     * =========================================================================

     *

     * Updates notification status and marks

     * the notification as read.

     *

     * If the notification does not exist,

     * a RuntimeException is thrown.

     *

     * Example:

     * Notification Status

     * false → true

     *

     * @param notificationId Notification Identifier

     */

    @Override

    public void markAsRead(Long notificationId) {


        NotificationEntity notification =

                notificationRepository.findById(

                        notificationId)

                .orElseThrow(() ->

                        new RuntimeException(

                                "Notification Not Found"));


        notification.setIsRead(true);


        notificationRepository.save(notification);

    }


}
 