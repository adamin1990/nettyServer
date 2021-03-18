/**
 * Copyright (C), 2020-2021
 * FileName: SzwlController
 * Date:     2021/3/18 17:37
 * Description:
 */
package com.adamin.appserver.controller;

import com.adamin.appserver.bean.HttpResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/18 17:37
 */
@RestController
public class SzwlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SzwlController.class);

    /**
     * 调用远程app服务获取数据，请求数据必须携带参数 appname app名称
     * @param request
     * @param response
     */
   @RequestMapping(path = "/hook",method = {RequestMethod.GET})
    public void hook(HttpServletRequest request, HttpServletResponse response){
       HttpResponse httpResponse=new HttpResponse();


       response.setContentType(request.getMethod());
       LOGGER.info("------content -----------"+response.getContentType());
       ServletOutputStream outputStream=null;
       try {
         outputStream = response.getOutputStream();
          httpResponse.setCode(1);
          httpResponse.setData("adam");
          httpResponse.setMessage("this is message");
         outputStream.write(new Gson().toJson(httpResponse).getBytes());

       } catch (IOException e) {
           e.printStackTrace();
           httpResponse.setCode(0);
           httpResponse.setData("adam");
           httpResponse.setMessage(e.getMessage());
           try {
               outputStream.write(new Gson().toJson(httpResponse).getBytes());
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
