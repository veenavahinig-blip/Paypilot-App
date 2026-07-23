package com.hcl.paypilot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity

@Table(name = "notification_tab")

public class NotificationEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "notification_id")

    private Long notificationId;

    @Column(name = "user_id", nullable = false)

    private String userId;

    @Column(name = "notification_message", nullable = false)

    private String notificationMessage;

    @Column(name = "is_read")

    private Boolean isRead = false;

    @Column(name = "created_date")

    private LocalDateTime createdDate = LocalDateTime.now();

    public NotificationEntity() {

    }

    public Long getNotificationId() {

        return notificationId;

    }

    public void setNotificationId(Long notificationId) {

        this.notificationId = notificationId;

    }

    public String getUserId() {

        return userId;

    }

    public void setUserId(String userId) {

        this.userId = userId;

    }

    public String getNotificationMessage() {

        return notificationMessage;

    }

    public void setNotificationMessage(String notificationMessage) {

        this.notificationMessage = notificationMessage;

    }

    public Boolean getIsRead() {

        return isRead;

    }

    public void setIsRead(Boolean isRead) {

        this.isRead = isRead;

    }

    public LocalDateTime getCreatedDate() {

        return createdDate;

    }

    public void setCreatedDate(LocalDateTime createdDate) {

        this.createdDate = createdDate;

    }

}
 
