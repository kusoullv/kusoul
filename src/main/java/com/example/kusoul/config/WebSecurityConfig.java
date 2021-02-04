package com.example.kusoul.config;

import com.example.kusoul.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserServiceImpl userService;
    @Resource
    private JwtAuthorizationTokenFilter authorizationTokenFilter; //JwtToken解析并生成authentication身份信息过滤器
    @Resource
    private SecuritySuccessHandler securitySuccessHandler;
    @Resource
    private SecurityFailureHandler securityFailureHandler;

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
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode("123");
//        System.out.println(encodedPassword);

        // JwtToken解析并生成authentication身份信息过滤器
        http.addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 未登录时：返回状态码401
//        http.exceptionHandling().authenticationEntryPoint(new UrlAuthenticationEntryPoint());

        // 无权访问时：返回状态码403
        http.exceptionHandling().accessDeniedHandler(new UrlAccessDeniedHandler());

        // 将session策略设置为无状态的,通过token进行登录认证
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


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
                .successHandler(securitySuccessHandler)
                .failureHandler(securityFailureHandler)
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
