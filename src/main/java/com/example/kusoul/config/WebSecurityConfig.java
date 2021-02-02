package com.example.kusoul.config;

import com.example.kusoul.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserServiceImpl userService;

    @Bean
    RoleHierarchy roleHierarchy() {
        // 角色等级
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierachy = "ROLE_dba > ROLE_admin ROLE_admin > ROLE_user";
        // 设置角色等级大小
        roleHierarchy.setHierarchy(hierachy);
        return roleHierarchy;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 密码加密
        return new BCryptPasswordEncoder();
    }
// loadUserByUsername
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode("123");
        System.out.println(encodedPassword);

        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(cfisms());
                        object.setAccessDecisionManager(cadm());
                        return object;
                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Authentication authentication) throws IOException, ServletException {
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
                })
                .failureHandler(new AuthenticationFailureHandler() {
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
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login_page");
                    }
                })
                .and()
                .csrf().
                disable();
    }
    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms() {
        return new CustomFilterInvocationSecurityMetadataSource();
    }

    @Bean
    CustomAccessDecisionManager cadm() {
        return  new CustomAccessDecisionManager();
    }

}
