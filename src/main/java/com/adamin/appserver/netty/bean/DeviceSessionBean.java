/**
 * Copyright (C), 2020-2021
 * FileName: DeviceSessionBean
 * Date:     2021/3/16 18:17
 * Description: 客户端
 */
package com.adamin.appserver.netty.bean;

import com.adamin.appserver.netty.Constants;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;


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
    private AtomicLong flowId=new AtomicLong(1l);//流水id

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
        if(StringUtils.isEmpty(sn)){
            LOGGER.info("设备sn是空");
            try {
                throw new Exception("请先设置设备sn");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        channel.attr(Constants.SN).set(sn);
        this.channel = channel;
    }



    public long getLastHeartBeatTimeStamp() {
        return lastHeartBeatTimeStamp;
    }

    public void setLastHeartBeatTimeStamp(long lastHeartBeatTimeStamp) {
        this.lastHeartBeatTimeStamp = lastHeartBeatTimeStamp;
    }


    public void transfer(String content){
        long taskId=flowId.incrementAndGet();
        TaskRecord record=new TaskRecord();
        record.setSn(sn);
        record.setInvokeData(content);
        record.setTaskId(taskId);

    }
}
