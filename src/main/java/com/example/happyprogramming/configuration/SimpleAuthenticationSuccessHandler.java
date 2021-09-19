package com.example.happyprogramming.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            // nếu quyền có vai trò user, chuyển đến trang "/" nếu login thành công
            if (authority.getAuthority().equals("ROLE_MENTOR") && authority.getAuthority().equals("ROLE_MENTEE")) {
                try {
                    String sessionRole = "mentorAndMentee";
                    session.setAttribute("role",sessionRole);
                    redirectStrategy.sendRedirect(request, response, "/home");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(authority.getAuthority().equals("ROLE_MENTEE")){
                try {
                    String sessionRole = "mentee";
                    session.setAttribute("role",sessionRole);
                    redirectStrategy.sendRedirect(request, response, "/home");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else if (authority.getAuthority().contains("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }  else {
                throw new IllegalStateException();
            }
        });

    }

}
