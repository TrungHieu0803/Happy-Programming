package com.example.happyprogramming.service.implement;

import com.example.happyprogramming.Entity.RoleEntity;
import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.RoleRepository;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    public UserServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void register(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        HashSet<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MENTEE"));
        user.setRoles(roles);
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userRepo.save(user);
        sendVerificationEmail(user, siteURL);
    }

    @Override
    public void sendVerificationEmail(UserEntity user, String siteURL) throws UnsupportedEncodingException, MessagingException {
        String toAddress = user.getEmail();
        String fromAddress = "hieunthe150001@fpt.edu.vn";
        String senderName = "Happry-Programming";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Happy Programming.";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public boolean verify(String verificationCode) {
        UserEntity user = userRepo.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepo.save(user);
            return true;
        }
    }
}
