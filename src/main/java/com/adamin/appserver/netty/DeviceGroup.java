/**
 * Copyright (C), 2020-2021
 * FileName: DeviceGroup
 * Date:     2021/3/18 18:04
 * Description: 设备维护列表
 */
package com.adamin.appserver.netty;

import com.adamin.appserver.netty.bean.DeviceSessionBean;
import io.netty.channel.Channel;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
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
   //sn
    private Map<String, DeviceSessionBean> devices=new ConcurrentHashMap<>();
    private LinkedList<String> poolQueue=new LinkedList<>();

    private DeviceGroup() {
    }
    private static DeviceGroup instance=new DeviceGroup();


    public static DeviceGroup getInstance(){
        return  instance;
    }

    /**
     * 注册设备
     * @param sn
     * @param channel
     */
    public synchronized void register(String sn, Channel channel){
        if(StringUtil.isNullOrEmpty(sn)){
           LOGGER.info("请提供注册设备号");
           return;
        }
        if(!channel.isActive()){
            LOGGER.info("设备"+sn+"channel 离线");
            return;
        }
        if(devices.get(sn)!=null){
            LOGGER.info("设别"+sn+"已注册");
            if(channel!=devices.get(sn)){
                LOGGER.info("channel 不一致,更新channel");
                devices.get(sn).setChannel(channel);
            }
            return;
        }
        DeviceSessionBean sessionBean=new DeviceSessionBean();
        sessionBean.setSn(sn);
        sessionBean.setChannel(channel);
        sessionBean.setLastHeartBeatTimeStamp(0);
        devices.put(sn,sessionBean);
        removeQueue(sn);
         poolQueue.add(sn);
    }

    /**
     * 解注册
     * @param sn
     */
    public void unregister(String sn){
        devices.remove(sn);
        removeQueue(sn);

    }

    /**
     * 根据频道解注册
     * @param channel
     */
    public void unregister(Channel channel){
        if(channel!=null){
            LOGGER.info("注销频道");
            devices.forEach((sn, deviceSessionBean) -> {
                if(deviceSessionBean.getChannel()==channel){
                    devices.remove(sn);
                    removeQueue(sn);
                }

            });
        }

    }

    /**
     * 设置上次心跳
     * @param sn
     * @param time
     */
    public void setLastHeartBeat(String sn,long time){
        if(devices.get(sn)!=null){
            devices.get(sn).setLastHeartBeatTimeStamp(time);
        }
    }

    /**
     * 根据设备号查看sn
     * @param sn
     * @return
     */
    public DeviceSessionBean queryBySn(String sn){
        return devices.get(sn);
    }

    private void removeQueue(String sn) {
        while (poolQueue.remove(sn)) {
        }
    }

    /**
     * 抽取设备
     * @return
     */
    public synchronized DeviceSessionBean pollDevice(){
        while (true) {
            String sn = poolQueue.poll();
            if (sn == null) {
                LOGGER.info("设备抽取失败:{}", sn);
                return null;
            }

            DeviceSessionBean deviceSessionBean = devices.get(sn);
            if (deviceSessionBean == null) {
                continue;
            }
            if (deviceSessionBean.getChannel() == null) {
                devices.remove(sn);
                continue;
            }
            if (!deviceSessionBean.getChannel().isActive()) {
                devices.remove(sn);
                continue;
            }
            poolQueue.add(sn);
            return deviceSessionBean;
        }
    }

}
