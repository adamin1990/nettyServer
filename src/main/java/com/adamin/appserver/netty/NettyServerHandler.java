/**
 * Copyright (C), 2020-2021
 * FileName: NettyServerHandler
 * Date:     2021/3/13 15:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.adamin.appserver.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/13 15:38
 */
@ChannelHandler.Sharable
@Service
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
      LOGGER.info("-------------服务端读取--------"+msg+"-------"+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LOGGER.error("发生异常:");
        LOGGER.error(cause.getMessage(), cause);
    }
}
