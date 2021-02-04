package com.example.kusoul.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            Object principal = authentication.getPrincipal();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = response.getWriter();
            response.setStatus(200);
            Map<String, Object> map = new HashMap<>();
            map.put("status",200);
            map.put("msg",principal);
            ObjectMapper om = new ObjectMapper();
            pw.write(om.writeValueAsString(map));
            pw.flush();
            pw.close();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }
}
