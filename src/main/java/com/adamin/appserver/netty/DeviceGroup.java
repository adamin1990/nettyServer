/**
 * Copyright (C), 2020-2021
 * FileName: DeviceGroup
 * Date:     2021/3/18 18:04
 * Description: 设备维护列表
 */
package com.adamin.appserver.netty;

import com.adamin.appserver.netty.bean.DeviceSessionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/18 18:04
 */
public class DeviceGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceGroup.class);
   //app名称
    private Map<String, DeviceSessionBean> devices=new ConcurrentHashMap<>();

    private DeviceGroup() {
    }
    private static DeviceGroup instance=new DeviceGroup();


    public static DeviceGroup getInstance(){
        return  instance;
    }

}
