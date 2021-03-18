/**
 * Copyright (C), 2020-2021
 * FileName: HttpResponse
 * Date:     2021/3/18 17:55
 * Description: http接口返回bean
 */
package com.adamin.appserver.bean;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/18 17:55
 */
public class HttpResponse {
    private int code;
    private String data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
