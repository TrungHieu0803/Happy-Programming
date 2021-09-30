package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.UserEntity;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping({"/", "/home"})
    public String home() {
        return "client/index";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "client/my-account";
    }


    @PostMapping("/process_register")
    public String processRegister(UserEntity user, HttpServletRequest request, Model model)
            throws UnsupportedEncodingException, MessagingException {
        userService.register(user, getSiteURL(request));
        boolean alert = true;
        model.addAttribute("alert", alert);
        return "client/my-account";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "client/components/verify-success";
        } else {
            return "client/components/verify-fail";
        }
    }

    @GetMapping("/check_email")
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        if (userService.checkEmail(email)) {
            response.getWriter().print("<p class=\"text-success\">Available</p>");
        } else {
            response.getWriter().print("<p class=\"text-danger\">This email address is already being used</p>");
        }
    }

    @GetMapping("/reset-password")
    public void processChangePassword(HttpServletRequest request, Model model, HttpServletResponse response)
            throws IOException, MessagingException {
        String email = request.getParameter("email");
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            response.getWriter().print("<p class=\"text-danger\">This email address is not registered!</p>");
        } else {
            userService.changePassword(user, getSiteURL(request));
            response.getWriter().print("<p class=\"text-success\">Check your email for change the password</p>");

        }
    }

    @GetMapping("/do-reset-password")
    public String resetPassword(@Param("code") String code, @Param("email") String email, Model model) {
        if (userService.verify(code)) {
            model.addAttribute("email", email);
            return "client/components/reset-password";
        } else {
            return "client/components/verify-fail";
        }
    }

    @PostMapping("/do-reset-password")
    public String resetPassword(HttpServletRequest request) {
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        userService.doResetPassword(email, newPassword);
        return "client/my-account";
    }

    @GetMapping("/user-profile")
    public String UserProfile(Model model) {
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        model.addAttribute("userinfo", user);
        return "client/user-profile";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "client/change-password";
    }

    @PostMapping("/change-password")
    public String doChangePassword(HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        if (userService.doChangePassword(newPassword, oldPassword, user)) {
            return "client/index";
        } else
            return "client/change-password";
    }
}

