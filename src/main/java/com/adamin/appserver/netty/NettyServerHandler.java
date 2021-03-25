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

import com.adamin.appserver.netty.bean.SocketBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/3/13 15:38
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        LOGGER.error("发生异常:");
        LOGGER.error(cause.getMessage(), cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("连接成功 = " + ctx);
//        ctx.channel().close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("连接丢失 = " + ctx);
        DeviceGroup.getInstance().unregister(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Gson gson = new Gson();
        String unescapeStr = StringEscapeUtils.unescapeJson(msg);
        unescapeStr = unescapeStr.substring(1, unescapeStr.length() - 1);
        LOGGER.info("客户端返回信息：" + unescapeStr);
        SocketBean socketBean = gson.fromJson(unescapeStr, new TypeToken<SocketBean>() {
        }.getType());
        LOGGER.info("设备id：" + socketBean.getSn());
        switch (socketBean.getCmdType()) {
            case CmdType.TYPE_AUTH: //注册鉴权
                DeviceGroup.getInstance().register(socketBean.getSn(),ctx.channel());
                break;
            case CmdType.TYPE_HEART_BEAT:  //心跳
                break;
            case CmdType.TYPE_HOOK:  //hook
                handleHookResPonse(socketBean,ctx.channel());
                break;
            case CmdType.TYPE_PROXY: //代理
                break;
            default:
                LOGGER.info("类型错误");
                break;
        }
    }

    private void handleHookResPonse(SocketBean socketBean, Channel channel) {
        String sn = channel.attr(Constants.SN).get();
        LOGGER.info("处理hook数据sn："+sn);
        if(StringUtils.isEmpty(sn)){
            LOGGER.info("处理hooksn为空");
            return;
        }
        TaskRepro.getInstance().transferResponse(sn,socketBean.getSerialNumber(),socketBean);

    }
}
