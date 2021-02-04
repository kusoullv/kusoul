package com.example.kusoul.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter pw =  response.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("status", 401);
        if (exception instanceof UsernameNotFoundException) {
            map.put("msg", "当前用户不存在!");
        }
        else if (exception instanceof LockedException) {
            map.put("msg", "用户被锁定, 登录失败!");
        }
        else if  (exception instanceof BadCredentialsException) {
            map.put("msg", "用户名或密码输入错误，登录失败!");
        }
        else if (exception instanceof DisabledException) {
            map.put("msg", "用户被禁用，登录失败!");
        }
        else if (exception instanceof AccountExpiredException) {
            map.put("msg", "用户已过期，登录失败!");
        }
        else if (exception instanceof CredentialsExpiredException) {
            map.put("msg", "密码已过期，登录失败!");
        } else {
            map.put("msg", "登录失败！");
        }

        ObjectMapper om = new ObjectMapper();
        pw.write(om.writeValueAsString(map));
        pw.flush();
        pw.close();
    }
}
