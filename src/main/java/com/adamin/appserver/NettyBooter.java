/**
 * Copyright (C), 2020-2021
 * FileName: NettyBooter
 * Date:     2021/3/13 15:56
 * Description: 启动
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.adamin.appserver;

import com.adamin.appserver.netty.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/13 15:56
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyBooter.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                NettyServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
