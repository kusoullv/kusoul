package com.example.kusoul.tools;


import com.alibaba.fastjson.JSON;
import com.example.kusoul.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Lan
 * @date: 2019/4/8 14:19
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtTokenUtil {

    /**
     * header名称
     */
    private String tokenHeader;

    /**
     * token前缀
     */
    private String tokenPrefix;

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 过期时间
     */
    private Long expiration;

    /**
     * 选择记住后过期时间
     */
    private Long rememberExpiration;

    /**
     * 生成token
     *
     * @param user
     * @return
     */
//    public String createToken(User user) {
//        Long time = User.getRemember() ? this.rememberExpiration : this.expiration;
//        Map<String, Object> map = new HashMap<>(1);
//        map.put("user", User);
//        return Jwts.builder()
//                .setClaims(map)
//                .setSubject(User.getUsername())
//                .setExpiration(new Date(System.currentTimeMillis() + time * 1000))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        return generateToken(token).getSubject();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims generateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取user
     *
     * @param token
     * @return
     */
    public User getUser(String token) {
        Claims claims = generateToken(token);
        Map<String, String> map = claims.get("user", Map.class);
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        return user;
    }
}