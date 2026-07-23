package com.hcl.paypilot.service;

import java.util.List;
import com.hcl.paypilot.entity.NotificationEntity;
public interface NotificationService {
    NotificationEntity saveNotification(String userId, String message);
    List<NotificationEntity> getNotificationsByUserId(String userId);
    void markAsRead(Long notificationId);
}