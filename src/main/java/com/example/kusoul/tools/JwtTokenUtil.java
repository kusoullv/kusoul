package com.example.kusoul.tools;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
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
     * 根据负责生成JWT的token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String,Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.PS512,secret)
                .compact();
    }

    /**
     * 从 token 中获取JWT中的负载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败：{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    /**
     * 从token 中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String userName= "";
        try {
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败：{}", token);
        }
        return userName;
    }

    /**
     * 验证token是否还有效
     * @param token 客户端传入的token
     * @param userDetails  从数据库中查询出来的用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
     String username = getUserNameFromToken(token);
     return username.equals(userDetails.getUsername()) && ! isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token 中获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}