package com.example.kusoul.config;

import com.example.kusoul.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl UserService;

    @Autowired
    private UrlAccessDeniedHandler urlAccessDeniedHandler;

    @Autowired
    private UrlAuthenticationEntryPoint urlAuthenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf()// 由于使用的是JWT，我们这里不需要csrf
            .disable()
            .sessionManagement() // 基于token，所以不需要session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,
                    "/",
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/swagger-resources/**",
                    "/v2/api-docs/**") // 允许对于网站静态资源的无授权访问
//            .permitAll()
//            .antMatchers("/**") // 测试时全部允许访问
            .permitAll()
            .anyRequest() // 除上面外的所有请求全部需要鉴权认证
            .authenticated();

        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(new JwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
            //  当访问接口没有权限时，自定义的返回结果
            .accessDeniedHandler(urlAccessDeniedHandler)
            //  当未登录或者token失效访问接口时，自定义的返回结果
            .authenticationEntryPoint(urlAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public UserDetailsService userDetailsService() {
        // 获取用户登录信息
        return username -> {
            UserDetails user = UserService.loadUserByUsername(username);
            if(user != null) {
                return user;
            }
            throw new UsernameNotFoundException("用户名或者密码错误");
        };
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
