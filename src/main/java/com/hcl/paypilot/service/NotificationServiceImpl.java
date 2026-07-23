package com.hcl.paypilot.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.paypilot.entity.NotificationEntity;
import com.hcl.paypilot.repository.NotificationRepository;
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public NotificationEntity saveNotification(String userId, String message) {
        NotificationEntity notification = new NotificationEntity();
        notification.setUserId(userId);
        notification.setNotificationMessage(message);
        notification.setIsRead(false);
        notification.setCreatedDate(LocalDateTime.now());;
        return notificationRepository.save(notification);
    }
    @Override
    public List<NotificationEntity> getNotificationsByUserId(String userId) {
        return notificationRepository.findByUserIdOrderByCreatedDateDesc(userId);
    }
    @Override
    public void markAsRead(Long notificationId) {
        NotificationEntity notification =
                notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification Not Found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
}
