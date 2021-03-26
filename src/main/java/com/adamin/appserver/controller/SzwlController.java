/**
 * Copyright (C), 2020-2021
 * FileName: SzwlController
 * Date:     2021/3/18 17:37
 * Description:
 */
package com.adamin.appserver.controller;

import com.adamin.appserver.bean.HttpResponse;
import com.adamin.appserver.netty.DeviceGroup;
import com.adamin.appserver.netty.bean.DeviceSessionBean;
import com.adamin.appserver.util.ResPonseUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/18 17:37
 */
@RestController
public class SzwlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SzwlController.class);
    @Autowired
    private ResPonseUtil resPonseUtil;

    /**
     * 调用远程app服务获取数据，请求数据必须携带参数 appname app名称
     * @param request
     * @param response
     */
   @RequestMapping(path = "/hook",method = {RequestMethod.GET})
    public void hook(HttpServletRequest request, HttpServletResponse response){
       HttpResponse httpResponse=new HttpResponse();
       Map<String, String[]> parameterMap = request.getParameterMap();
       JsonObject jsonObject=new JsonObject();
       for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
           String[] value = stringEntry.getValue();
           if (value == null || value.length == 0) {
               continue;
           }
           jsonObject.add(stringEntry.getKey(), new JsonPrimitive(value[0]));
       }
       JsonElement snElement = jsonObject.get("sn");
//       if(snElement==null){
//        commonResponseToClient(response,0,"请提供设备sn","");
//        return;
//
//       }
       DeviceSessionBean deviceSession;
       if(snElement!=null&&!StringUtils.isEmpty(snElement.getAsString())){  //指定了设备号，就从map中找到该设备执行任务
           deviceSession= DeviceGroup.getInstance().queryBySn(snElement.getAsString());
           if(deviceSession==null||!deviceSession.getChannel().isActive()){
               resPonseUtil.commonResponseToClient(response,0,"设备未注册或长链接已断开","");
               return;
           }

       }else{ //未指定设备号，则从map中拉一个出来执行任务
           deviceSession = DeviceGroup.getInstance().pollDevice();
           if(deviceSession==null){
               resPonseUtil.commonResponseToClient(response,0,"没有找到在线设备哦","");
               return;
           }else{
               LOGGER.info("获取设备信息：{}",new Gson().toJson(jsonObject));
               deviceSession.transfer(jsonObject,response);
           }

       }

    }

}
