package com.example.happyprogramming.service;


import com.example.happyprogramming.Entity.NotificationEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface NotificationService {

    int getUncheckedNoti();

    String getNotification();
}
