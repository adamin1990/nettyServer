/**
 * Copyright (C), 2020-2021
 * FileName: TaskRecord
 * Date:     2021/3/22 10:17
 * Description:
 */
package com.adamin.appserver.netty.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/22 10:17
 */
public class TaskRecord {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRecord.class);
    private String sn;
    private long taskId;
    private String invokeData;
    private SocketBean socketBean;
    private TaskListener taskListener;
    private Object lock=new Object();
    private boolean callbacked=false;

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getInvokeData() {
        return invokeData;
    }

    public void setInvokeData(String invokeData) {
        this.invokeData = invokeData;
    }

    public SocketBean getSocketBean() {
        return socketBean;
    }

    public void setSocketBean(SocketBean socketBean) {
        this.socketBean = socketBean;
    }

    /**
     * 使调用线程阻塞
     * @param time
     */
    public void waitFor(long time){
        if(callbacked){
            return;
        }
        synchronized (lock){
            if(callbacked){
                return;
            }
            try {
                lock.wait(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 唤醒调用线程
     * @param socketBean
     */
    public void notifyCaller(SocketBean socketBean){
        this.socketBean=socketBean;
        if(taskListener!=null){
            taskListener.onReceive(socketBean);
        }else{
            synchronized (lock){
                callbacked=true;
                lock.notify();

            }
        }



    }


    public interface  TaskListener{
        /**
         * 回调信息
         * @param data
         */
        void onReceive(SocketBean data);
    }
}
