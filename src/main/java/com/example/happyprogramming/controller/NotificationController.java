package com.example.happyprogramming.controller;


import com.example.happyprogramming.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get-uncheck-notification")
    public void getNotification(HttpServletResponse response) throws IOException {
        response.getWriter().print(notificationService.getUncheckedNoti());
    }
    @GetMapping("/get-notification-content")
    public void getNotificationContent(HttpServletResponse response)throws IOException{
        response.getWriter().print(notificationService.getNotification());
        System.out.print(notificationService.getNotification());
    }
}
