package com.example.kusoul.config;

import com.alibaba.fastjson.JSONObject;
import com.example.kusoul.tools.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 */
@SuppressWarnings("Duplicates")
@Component
public class UrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setSuccess(false);
        responseUtil.setCode("401");
        responseUtil.setMessage(authException.getMessage());
        responseUtil.setData(null);
        // 返回json数据
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter pw = response.getWriter();
        pw.write(JSONObject.toJSONString(responseUtil));
        pw.flush();
        pw.close();
    }
}
