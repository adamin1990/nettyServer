/**
 * Copyright (C), 2020-2021
 * FileName: DeviceSessionBean
 * Date:     2021/3/16 18:17
 * Description: 客户端
 */
package com.adamin.appserver.netty.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.Channel;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/16 18:17
 */
public class DeviceSessionBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceSessionBean.class);
    private String sn;
    private Channel channel=null;
    private long lastHeartBeatTimeStamp;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public long getLastHeartBeatTimeStamp() {
        return lastHeartBeatTimeStamp;
    }

    public void setLastHeartBeatTimeStamp(long lastHeartBeatTimeStamp) {
        this.lastHeartBeatTimeStamp = lastHeartBeatTimeStamp;
    }
}
