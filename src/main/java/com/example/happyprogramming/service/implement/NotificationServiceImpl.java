package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.NotificationEntity;
import com.example.happyprogramming.repository.NotificationRepository;
import com.example.happyprogramming.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public int getUncheckedNoti() {
        return notificationRepository.findByStatus(0).size();
    }

    @Override
    public String getNotification() {
        ArrayList<NotificationEntity> notifiList =  notificationRepository.findTop5ByOrderByCreatedDateDesc();
        String result = "";
        for (NotificationEntity n: notifiList) {
            result += "<div class=\"detail-notification\" >\n" +
                    "                <p>"+n.getContent()+"</p>\n" +
                    "            </div>";
            n.setStatus(1);
            notificationRepository.save(n);
        }
        return result;
    }
}
