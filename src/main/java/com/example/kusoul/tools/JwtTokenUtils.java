package com.example.kusoul.tools;

import com.example.kusoul.bean.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class JwtTokenUtils {
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
    // 记住我 过期时间
    @Value("${jwt.remeber.expiration}")
    private String emeberExpiration;
    // 角色key
    @Value("${jwt.role}")
    private String roleKey;

    // 创建token
    public String createToken(String username, List<Role> role, boolean isRememberMe) {
        long expiration = isRememberMe ? Long.parseLong(emeberExpiration) : Long.parseLong(tokenExpiration);
        HashMap<String, Object> map = new HashMap<>();
        map.put(roleKey, role);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, tokenSecret) // 签证(加密方式，我加密秘钥)
                .setClaims(map) // 签发声明
                .setIssuer("kusoul")// 签发者
                .setIssuedAt(new Date()) // 签发时间
                .setSubject(username) // 代表这个JWT的主体，即它的所有人
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) // jwt 过期时间
                .compact();
    }

    // 从token中获取用户名
    public String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // 获取用户角色
    public String getUserRole(String token){
        return (String) getTokenBody(token).get(roleKey);
    }

    // 是否已过期
    public boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}

