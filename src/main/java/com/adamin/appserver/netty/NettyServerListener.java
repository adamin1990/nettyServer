package com.adamin.appserver.netty;

import io.netty.channel.Channel;

/*
    ___        __                   _        ____   ____
   /   |  ____/ /____ _ ____ ___   (_)____  / __ \ / __ \
  / /| | / __  // __ `// __ `__ \ / // __ \/ /_/ // / / /
 / ___ |/ /_/ // /_/ // / / / / // // / / /\__, // /_/ /
/_/  |_|\__,_/ \__,_//_/ /_/ /_//_//_/ /_//____/ \____/

^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 做一款产品       愉悦自己
****************Powered by Adamin90********************
* @email: 14846869@qq.com
* Date: 2020/3/20
* Time: 11:21
* Desc:
* @link: https://www.lixiaopeng.top
*******************************************************
*/
public interface NettyServerListener<T> {
    public final static byte STATUS_CONNECT_SUCCESS = 1;

    public final static byte STATUS_CONNECT_CLOSED = 0;

    public final static byte STATUS_CONNECT_ERROR = 0;

    /**
     *
     * @param msg
     * @param ChannelId unique id
     */
    void onMessageResponseServer(T msg,String ChannelId);

    /**
     * server开启成功
     */
    void onStartServer();

    /**
     * server关闭
     */
    void onStopServer();

    /**
     * 与客户端建立连接
     *
     * @param channel
     */
    void onChannelConnect(Channel channel);

    /**
     * 与客户端断开连接
     * @param
     */
    void onChannelDisConnect(Channel channel);
}
