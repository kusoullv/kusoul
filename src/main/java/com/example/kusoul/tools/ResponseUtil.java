package com.example.kusoul.tools;

import java.io.Serializable;

public class ResponseUtil implements Serializable {
    // 请求成功与否
    private boolean success;
    // 请求状态嘛
    private String code;
    // 信息说明
    private String message;
    // 数据结果集
    private Object data;
    // 总条数
    private int total;

    public ResponseUtil() {
    }

    public ResponseUtil(boolean success, String code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseUtil(boolean success, String code, String message, Object data, int total) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
