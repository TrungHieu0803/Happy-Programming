package com.example.happyprogramming.controller;


import com.example.happyprogramming.Entity.*;
import com.example.happyprogramming.repository.UserRepository;
import com.example.happyprogramming.service.MentorService;
import com.example.happyprogramming.service.SkillService;
import com.example.happyprogramming.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillService skillService;

    @GetMapping({"/", "/home"})
    public String home(Model model,@RequestParam(value = "pageNumber",required = false,defaultValue = "1")int pageNumber) {
        Pagination<CVEntity> page = mentorService.getPaginatedMentors(pageNumber);
        model.addAttribute("popularSkill",skillService.getPopularSkill());
        model.addAttribute("listMentor",page.getPaginatedList());
        model.addAttribute("pageNumbers", page.getPageNumbers());
        model.addAttribute("listSkill",skillService.getAllSkill());
        model.addAttribute("listSkillForSearch",new SkillEntity());
        model.addAttribute("currentPage",pageNumber);
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
        model.addAttribute("alert", true);
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

    @PostMapping("/reset-password")
    public void processResetPassword(HttpServletRequest request, Model model, HttpServletResponse response)
            throws IOException, MessagingException {
        String email = request.getParameter("email");
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            response.getWriter().print("<p class=\"text-danger\">This email address is not registered!</p>");
        } else {
            userService.sendEmailChangePassword(user, getSiteURL(request));
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
    public String doResetPassword(HttpServletRequest request) {
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
    public String changePassword(Model model) {
        model.addAttribute("isChanged",null);
        return "client/change-password";
    }

    @PostMapping("/change-password")
    public String doChangePassword(HttpServletRequest request,Model model) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        model.addAttribute("alert", true);
        if (userService.doChangePassword(newPassword, oldPassword, user)) {
            model.addAttribute("isChanged",true);
            model.addAttribute("mess","Your password has been changed.");
            return "client/change-password";
        } else
            model.addAttribute("isChanged",false);
            model.addAttribute("mess","Password is wrong! Please enter again.");
            return "client/change-password";
    }

    @PostMapping("/upload-avatar")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadImage(@RequestParam("avatar") MultipartFile avatar, HttpServletRequest request) throws IOException{
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        session.setAttribute("userInformation",userService.saveAvatar(avatar,user.getEmail()));
        return "client/user-profile";
    }

    @PostMapping("/update-profile")
    public void profileUpdate(HttpServletRequest request) {
        String fullName = request.getParameter("fullName");
        String DoB = request.getParameter("DoB");
        String phone = request.getParameter("phone");
        UserEntity user = (UserEntity) session.getAttribute("userInformation");
        user.setFullName(fullName);
        user.setDoB(DoB);
        user.setPhone(phone);
        userRepository.save(user);
    }
}

