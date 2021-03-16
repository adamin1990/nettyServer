/**
 * Copyright (C), 2020-2021
 * FileName: NettyInitialzer
 * Date:     2021/3/13 14:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.adamin.appserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/13 14:45
 */
public class NettyInitialzer  extends ChannelInitializer<SocketChannel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyInitialzer.class);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new IdleStateHandler(60,0,0, TimeUnit.MINUTES));
        ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(4096*100,delimiter));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new NettyServerHandler());

    }
}
