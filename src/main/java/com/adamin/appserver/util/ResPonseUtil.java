/**
 * Copyright (C), 2020-2021
 * FileName: ResPonseUtil
 * Date:     2021/3/26 14:44
 * Description:
 */
package com.adamin.appserver.util;

import com.adamin.appserver.bean.HttpResponse;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/26 14:44
 */
@Component
public class ResPonseUtil {


    public  void commonResponseToClient(HttpServletResponse servletResponse, int code, String message
            , Object data){
        HttpResponse response=new HttpResponse();
        response.setStatus(code);
        response.setMsg(message);
        response.setDataReturn(data);
        ServletOutputStream outputStream=null;
        servletResponse.setContentType("application/json;charset=utf8");
        try {
            outputStream = servletResponse.getOutputStream();
            byte[] bytes =new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(response).getBytes("UTF-8");

            outputStream.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(0);
            response.setDataReturn("adamin");
            response.setMsg(e.getMessage());
            try {
                outputStream.write(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(response).getBytes("UTF-8"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
