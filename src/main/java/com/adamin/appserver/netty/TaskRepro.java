/**
 * Copyright (C), 2020-2021
 * FileName: TaskRepro
 * Date:     2021/3/25 10:04
 * Description:
 */
package com.adamin.appserver.netty;

import com.adamin.appserver.netty.bean.SocketBean;
import com.adamin.appserver.netty.bean.TaskRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/25 10:04
 */
public class TaskRepro {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRepro.class);
    private Map<String, TaskRecord> tasks=new ConcurrentHashMap<>();

    private TaskRepro() {
    }

    private static TaskRepro instance=new TaskRepro();

    public static TaskRepro getInstance(){
        return  instance;
    }

    /**
     * 获取任务的key
     * @param sn
     * @param flowId
     * @return
     */
    private String getTaskKey(String sn,long flowId){
        return "adamin_"+sn+"_"+flowId;
    }

    /**
     * 加入任务
     * @param record
     */
    public synchronized void putTask(TaskRecord record){
        tasks.put(getTaskKey(record.getSn(),record.getTaskId()),record);
    }

    /**
     * 检测是否包含
     * @param sn
     * @param flowId
     * @return
     */
    public boolean checkContainsTask(String sn,long flowId){
        return  tasks.containsKey(getTaskKey(sn,flowId));
    }


    public void transferResponse(String sn, long flowId, SocketBean socketBean){
        TaskRecord taskRecord = tasks.remove(getTaskKey(sn, flowId));
        if(taskRecord==null){
            LOGGER.info("没有设备号:{},流水号：{}的任务",sn,flowId);
            return;
        }
        taskRecord.notifyCaller(socketBean);

    }

    /**
     * 移除任务
     * @param record
     */
    public void remove(TaskRecord record){
        TaskRecord remove = tasks.remove(getTaskKey(record.getSn(), record.getTaskId()));
        if(remove==null){
            return;
        }
        remove.notifyCaller(null);

    }
}
