package com.example.happyprogramming.service;

import com.example.happyprogramming.Entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public interface UserService {
    void register(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendVerificationEmail(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    boolean verify(String verificationCode);

    boolean checkEmail(String email);

    void changePassword(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void sendEmailChangePassword(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException;

    void doChangePassword(String email,String newPassword);
}
