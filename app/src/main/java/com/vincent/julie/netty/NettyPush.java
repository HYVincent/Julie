package com.vincent.julie.netty;

import com.vincent.julie.netty.msg.BaseMsg;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.netty
 * @class describe
 * @date 2018/3/14 22:11
 */

public class NettyPush {

    private static NettyPush instance;

    public static NettyPush getInstance() {
        if(instance == null){
            instance = new NettyPush();
        }
        return instance;
    }

    /**
     * 发送消息给服务器
     * @param baseMsg
     */
    public void senMsg(BaseMsg baseMsg){
        NettyClientBootstrap.getInstance().sendMsg(baseMsg);
    }

}
