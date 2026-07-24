package com.hcl.paypilot;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.never;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;


import java.time.LocalDateTime;

import java.util.Arrays;

import java.util.Collections;

import java.util.List;

import java.util.Optional;


import com.hcl.paypilot.entity.NotificationEntity;

import com.hcl.paypilot.repository.NotificationRepository;
import com.hcl.paypilot.service.NotificationServiceImpl;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)

class NotificationServiceImplTest {


    @Mock

    private NotificationRepository notificationRepository;


    @InjectMocks

    private NotificationServiceImpl notificationService;


    private NotificationEntity notification;


    @BeforeEach

    void setUp() {


        notification = new NotificationEntity();


        notification.setNotificationId(1L);

        notification.setUserId("USER001");

        notification.setNotificationMessage(

                "Bill payment successful");

        notification.setIsRead(false);

        notification.setCreatedDate(

                LocalDateTime.now());

    }


    @Test

    void testSaveNotification() {


        when(notificationRepository.save(

                any(NotificationEntity.class)))

                .thenAnswer(

                        invocation -> invocation.getArgument(0));


        NotificationEntity result =

                notificationService.saveNotification(

                        "USER001",

                        "Payment completed successfully");


        assertNotNull(result);


        assertEquals(

                "USER001",

                result.getUserId());


        assertEquals(

                "Payment completed successfully",

                result.getNotificationMessage());


        assertFalse(result.getIsRead());


        assertNotNull(

                result.getCreatedDate());


        ArgumentCaptor<NotificationEntity> captor =

                ArgumentCaptor.forClass(

                        NotificationEntity.class);


        verify(notificationRepository)

                .save(captor.capture());


        NotificationEntity savedNotification =

                captor.getValue();


        assertEquals(

                "USER001",

                savedNotification.getUserId());


        assertEquals(

                "Payment completed successfully",

                savedNotification.getNotificationMessage());


        assertFalse(

                savedNotification.getIsRead());

    }


    @Test

    void testGetNotificationsByUserId() {


        NotificationEntity notification1 =

                new NotificationEntity();


        notification1.setNotificationId(1L);

        notification1.setUserId("USER001");

        notification1.setNotificationMessage(

                "Electricity bill paid");


        NotificationEntity notification2 =

                new NotificationEntity();


        notification2.setNotificationId(2L);

        notification2.setUserId("USER001");

        notification2.setNotificationMessage(

                "Water bill paid");


        List<NotificationEntity> notifications =

                Arrays.asList(

                        notification1,

                        notification2);


        when(notificationRepository

                .findByUserIdOrderByCreatedDateDesc(

                        "USER001"))

                .thenReturn(notifications);


        List<NotificationEntity> result =

                notificationService

                        .getNotificationsByUserId(

                                "USER001");


        assertNotNull(result);


        assertEquals(

                2,

                result.size());


        verify(notificationRepository,

                times(1))

                .findByUserIdOrderByCreatedDateDesc(

                        "USER001");

    }


    @Test

    void testGetNotificationsByUserId_EmptyList() {


        when(notificationRepository

                .findByUserIdOrderByCreatedDateDesc(

                        "USER001"))

                .thenReturn(

                        Collections.emptyList());


        List<NotificationEntity> result =

                notificationService

                        .getNotificationsByUserId(

                                "USER001");


        assertNotNull(result);


        assertEquals(

                0,

                result.size());

    }


    @Test

    void testMarkAsRead() {


        notification.setIsRead(false);


        when(notificationRepository.findById(1L))

                .thenReturn(

                        Optional.of(notification));


        notificationService.markAsRead(1L);


        assertEquals(

                true,

                notification.getIsRead());


        verify(notificationRepository,

                times(1))

                .save(notification);

    }


    @Test

    void testMarkAsRead_NotificationNotFound() {


        when(notificationRepository.findById(1L))

                .thenReturn(Optional.empty());


        RuntimeException exception =

                assertThrows(

                        RuntimeException.class,

                        () -> notificationService

                                .markAsRead(1L));


        assertEquals(

                "Notification Not Found",

                exception.getMessage());


        verify(notificationRepository,

                never())

                .save(any(NotificationEntity.class));

    }

}
 