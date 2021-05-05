package com.example.kusoul.config;

import com.example.kusoul.tools.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    // token 请求头Key
    @Value("${jwt.header}")
    private String tokenHeader;

    // token 前缀
    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    // token秘钥
    @Value("${jwt.secret}")
    private String tokenSecret;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(tokenHeader);
         logger.info(String.format("JwtAuthorizationTokenFilter >> token:{}",token));
        if(token==null || !token.startsWith(tokenPrefix + " ")) {
            filterChain.doFilter(request,response);
            return;
        }
//        Claims claims;
//        try {
//            // claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token.replace(tokenPrefix + " ", "")).getBody();
//
//            claims = jwtTokenUtils
//        } catch (Exception e) {
//            logger.error(String.format("JwtToken validity! error=[0]",e.getMessage()));
//            filterChain.doFilter(request, response);
//            return;
//        }

        //         List<String> roles = claims.get("role", List.class);
        String userName = jwtTokenUtils.getUsername(token);
        // List<String> roles = jwtTokenUtils.getUserRole(token);
        String roles11 = jwtTokenUtils.getUserRole(token);
        List<String> roles= null;
        List<SimpleGrantedAuthority> authorities =
                roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        if (null != userName) {
            // 生成authentication身份信息
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
