/**
 * Copyright (C), 2020-2021
 * FileName: HttpResponse
 * Date:     2021/3/18 17:55
 * Description: http接口返回bean
 */
package com.adamin.appserver.bean;

import com.google.gson.annotations.Expose;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/18 17:55
 */
public class HttpResponse {
    @Expose
    private int status;
    @Expose
    private Object dataReturn;
    @Expose
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getDataReturn() {
        return dataReturn;
    }

    public void setDataReturn(Object dataReturn) {
        this.dataReturn = dataReturn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
