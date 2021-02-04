package com.example.kusoul.config;

import com.alibaba.fastjson.JSONObject;
import com.example.kusoul.bean.User;
import com.example.kusoul.tools.ResponseUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

    // token 请求头Key
    @Value("${jwt.header}")
    private String tokenHeader;
    // token 前缀
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;
    // token秘钥
    @Value("${jwt.secret}")
    private String tokenSecret;
    // jwt 过期时间
    @Value("${jwt.expiration}")
    private String tokenExpiration;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setSuccess(true);
        responseUtil.setCode("200");
        responseUtil.setMessage("Login Success!");
        // 生成token并设置响应头
        String username = ((User) principal).getUsername(); //表单输入的用户名
        Claims claims = Jwts.claims();
        responseUtil.setData(claims);

        claims.put("role", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username) //设置用户名
//                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)) //设置token过期时间
                .signWith(SignatureAlgorithm.HS512, tokenSecret).compact(); //设置token签名算法及秘钥

        PrintWriter pw = response.getWriter();
        response.addHeader(tokenHeader, tokenPrefix + " " + token); //设置token响应头
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        pw.write(JSONObject.toJSONString(responseUtil));
        pw.flush();
        pw.close();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }
}
