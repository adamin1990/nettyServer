/**
 * Copyright (C), 2020-2021
 * FileName: NettyServer
 * Date:     2021/3/13 14:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.adamin.appserver.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/13 14:28
 */
@Component
public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    @Value("${tcpServerPort}")
    private Integer tcpServerPort;
    private static class SingleTonNettyServer {
        static final NettyServer instance=new NettyServer();
    }

    public static  NettyServer getInstance(){
        return  SingleTonNettyServer.instance;
    }

    private EventLoopGroup bossGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public NettyServer() {
        bossGroup=new NioEventLoopGroup();
        subGroup=new NioEventLoopGroup();
        server=new ServerBootstrap();
        server.group(bossGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,10240)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
//                .childOption(ChannelOption.AUTO_READ,true)
                .childHandler(new NettyInitialzer());

    }

    /**
     * 启动netty
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        InetSocketAddress address = new InetSocketAddress("0.0.0.0", tcpServerPort);
        future=server.bind(address).sync();
        if (future.isSuccess()) {
           LOGGER.info("netty 启动成功，端口"+tcpServerPort);
        }
    }
}

