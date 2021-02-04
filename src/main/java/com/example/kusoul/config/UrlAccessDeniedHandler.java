package com.example.kusoul.config;

import com.alibaba.fastjson.JSONObject;
import com.example.kusoul.tools.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 自定义权限不足处理器：返回状态码403
        ResponseUtil responseUtil = new ResponseUtil();
        responseUtil.setSuccess(false);
        responseUtil.setCode("403");
        responseUtil.setMessage(accessDeniedException.getMessage());
        responseUtil.setData(null);
        // 返回json数据
        response.setContentType("application/json;charset-utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(403);
        PrintWriter pw = response.getWriter();
        pw.write(JSONObject.toJSONString(responseUtil));
        pw.flush();
        pw.close();

    }
}
