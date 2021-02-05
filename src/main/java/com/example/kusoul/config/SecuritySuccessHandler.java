package com.example.kusoul.config;

import com.alibaba.fastjson.JSONObject;
import com.example.kusoul.bean.User;
import com.example.kusoul.tools.JwtTokenUtils;
import com.example.kusoul.tools.ResponseUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {
    // token 请求头Key
    @Value("${jwt.header}")
    private String tokenHeader;
    // token 前缀
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setSuccess(true);
        responseUtil.setCode(String.valueOf(HttpStatus.OK.value()));
        responseUtil.setMessage("Login Success!");
        // 生成token并设置响应头
        String username = ((User) principal).getUsername(); //表单输入的用户名
        // 用户角色
        String role = JSONObject.toJSONString(((User) principal).getRoles());
        Claims claims = Jwts.claims();
        claims.put("role", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        // 记住我
        boolean isRememberMe = false;
        // 取得token
        String token = jwtTokenUtils.createToken(username, claims, isRememberMe);
        Map<String,Object> map = new HashMap<>();
        map.put("token", token);
        map.put("role",claims);
        responseUtil.setData(map);
        response.addHeader(tokenHeader, tokenPrefix + " " + token); //设置token响应头
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter pw = response.getWriter();
        pw.write(JSONObject.toJSONString(responseUtil));
        pw.flush();
        pw.close();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }
}
