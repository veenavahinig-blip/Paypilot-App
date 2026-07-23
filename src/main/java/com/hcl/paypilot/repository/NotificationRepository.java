package com.hcl.paypilot.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hcl.paypilot.entity.NotificationEntity;
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByUserIdOrderByCreatedDateDesc(String userId);
}