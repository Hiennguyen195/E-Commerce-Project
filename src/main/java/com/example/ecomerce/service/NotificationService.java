package com.example.ecomerce.service;

import com.example.ecomerce.entity.Notification;
import com.example.ecomerce.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    // This service will handle the logic for notifications
    // For example, sending notifications to users when a product is updated or a new product is added
    // You can use libraries like JavaMailSender for email notifications or WebSocket for real-time notifications
    // Implement the methods as per your requirements
    @Autowired
    NotificationRepository notificationRepository;
    // Example method to send a notification
    public void sendNotification(String message) {
        // Logic to send notification
        System.out.println("Notification sent: " + message);
    }

    // Example method to mark a notification as read
    public void markAsRead(Long notificationId) {
        // Logic to mark notification as read
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.get().setRead(true);

        notificationRepository.save(notification.get());
    }

    public List<Notification> getAllNotifications() {
        // Logic to get all notifications
        return notificationRepository.findAll();
    }

    public void markAsReadAll() {
        // Logic to mark all notifications as read
        List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    public void getUnreadNotifications() {
        // Logic to get unread notifications
        List<Notification> notifications = notificationRepository.findByReadFalseOrderByCreatedAtDesc();
        for (Notification notification : notifications) {
            System.out.println(notification.getMessage());
        }
    }
}
