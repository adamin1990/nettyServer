/**
 * Copyright (C), 2020-2021
 * FileName: DeviceSessionBean
 * Date:     2021/3/16 18:17
 * Description: 客户端
 */
package com.adamin.appserver.netty.bean;

import com.adamin.appserver.netty.CmdType;
import com.adamin.appserver.netty.Constants;
import com.adamin.appserver.netty.TaskRepro;
import com.adamin.appserver.util.ResPonseUtil;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/16 18:17
 */
public class DeviceSessionBean {
    private final Logger LOGGER = LoggerFactory.getLogger(DeviceSessionBean.class);
    @Expose
    private String sn;
    private Channel channel=null;
    @Expose
    private long lastHeartBeatTimeStamp;
    @Expose
    private AtomicLong flowId=new AtomicLong(1l);//流水id


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


    public void transfer(Object content, HttpServletResponse response){

        long taskId=flowId.incrementAndGet();
        TaskRecord record=new TaskRecord();
        record.setSn(sn);
        record.setInvokeData(content);
        record.setTaskId(taskId);
        TaskRepro.getInstance().putTask(record); //注册任务
        //发送任务给客户端
        SocketBean socketBean=new SocketBean();
        socketBean.setCmdType(CmdType.TYPE_HOOK);
        socketBean.setSn(sn);
        socketBean.setSerialNumber(taskId);
        socketBean.setData(content);
        socketBean.setMessagge("指令下发");
        LOGGER.info("指令下发"+channel.isActive());
        channel.writeAndFlush(new Gson().toJson(socketBean)+Constants.SP);
        record.waitFor(15000); //等待15秒 阻塞内下面程序不会执行
        TaskRepro.getInstance().remove(record);
        SocketBean result = record.getResult();
        if(result==null){
            new ResPonseUtil().commonResponseToClient(response,0,"获取数据超时","");
            return;
        }
         new ResPonseUtil().commonResponseToClient(response,1,"获取数据成功",result.getData());

    }

    @Override
    public String toString() {
        return "DeviceSessionBean{" +
                "sn='" + sn + '\'' +
                ", channel=" + channel +
                ", lastHeartBeatTimeStamp=" + lastHeartBeatTimeStamp +
                ", flowId=" + flowId +
                '}';
    }
}
