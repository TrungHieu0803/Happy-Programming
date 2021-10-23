package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.repository.NotificationRepository;
import com.example.happyprogramming.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public int getUncheckedNoti() {
        return notificationRepository.findByStatus(0).size();
    }
}
