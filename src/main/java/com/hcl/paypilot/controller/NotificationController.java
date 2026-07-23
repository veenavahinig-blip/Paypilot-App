package com.hcl.paypilot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hcl.paypilot.entity.NotificationEntity;
import com.hcl.paypilot.service.NotificationService;
@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/{userId}")
    public List<NotificationEntity> getNotifications(
            @PathVariable String userId) {
        return notificationService.getNotificationsByUserId(userId);
    }
    @PutMapping("/read/{notificationId}")
    public String markAsRead(
            @PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return "Notification marked as read";
    }
}
