package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.NotificationEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.NotificationRepository;
import com.example.happyprogramming.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public int getUncheckedNoti(UserEntity user) {
        return notificationRepository.findByStatusAndUsers(0,user).size();
    }

    @Override
    public String getNotification(UserEntity user) {
        ArrayList<NotificationEntity> notifiList =  notificationRepository.findTop5ByUsersOrderByCreatedDateDesc(user);
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

    @Override
    public void welcomeNotification(UserEntity user) {
        String content = "Welcome to Happy Programming\n Find the mentor that fits your request now! ";
        NotificationEntity notification = new NotificationEntity();
        notification.setUsers(user);
        notification.setCreatedDate(getCurrentDate());
        notification.setStatus(0);
        notification.setContent(content);
        notificationRepository.save(notification);
    }

    @Override
    public void menteeSendRequestNotification(UserEntity mentor, UserEntity mentee) {
        String content = "You just sent a request to "+mentor.getFullName()+". Waiting for the response!";
        NotificationEntity notification = new NotificationEntity();
        notification.setContent(content);
        notification.setUsers(mentee);
        notification.setStatus(0);
        notification.setCreatedDate(getCurrentDate());
        notificationRepository.save(notification);

    }

    @Override
    public void acceptedNotification(UserEntity mentor, UserEntity mentee) {
        String content = "Your request has been accepted by "+mentor.getFullName()+". You can now contact your mentor!";
        NotificationEntity notification = new NotificationEntity();
        notification.setContent(content);
        notification.setUsers(mentee);
        notification.setStatus(0);
        notification.setCreatedDate(getCurrentDate());
        notificationRepository.save(notification);
    }

    @Override
    public void rejectedNotification(UserEntity mentor, UserEntity mentee) {
        String content = "Your request has been denied by "+mentor.getFullName()+". Find other mentor now!";
        NotificationEntity notification = new NotificationEntity();
        notification.setContent(content);
        notification.setUsers(mentee);
        notification.setStatus(0);
        notification.setCreatedDate(getCurrentDate());
        notificationRepository.save(notification);
    }

    @Override
    public void receivedNotification(UserEntity mentor, UserEntity mentee) {
        String content = "You just received a request from "+mentee.getFullName()+". View now!";
        NotificationEntity notification = new NotificationEntity();
        notification.setContent(content);
        notification.setUsers(mentor);
        notification.setStatus(0);
        notification.setCreatedDate(getCurrentDate());
        notificationRepository.save(notification);
    }

    @Override
    public void ratedNotification(UserEntity mentor, UserEntity mentee) {
        String content = "You were just rated by "+mentee.getFullName()+" View now!";
        NotificationEntity notification = new NotificationEntity();
        notification.setContent(content);
        notification.setUsers(mentor);
        notification.setStatus(0);
        notification.setCreatedDate(getCurrentDate());
        notificationRepository.save(notification);
    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}