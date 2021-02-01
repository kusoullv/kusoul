package com.example.kusoul.config;

import com.example.kusoul.bean.Menu;
import com.example.kusoul.bean.Role;
import com.example.kusoul.service.IMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 请求路径
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 获取db所有menu菜单
        List<Menu> allMenus = menuService.list();
        allMenus.forEach(item -> {
            if(antPathMatcher.match(item.getPattern(),requestUrl)) {
                List<Role> roles = item.getRoles();
                String[] rolesArr = new String[roles.size()];
                for (int i = 0; i < rolesArr.length; i ++) {
                    rolesArr[i] = roles.get(i).getName();
                }
                SecurityConfig.createList(rolesArr);
                return;
            }
        });
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
