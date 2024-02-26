package com.gussoft.farmaciabe.core.business.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        token = authHeader.substring(7);
        System.err.println(token);
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
    }
}
