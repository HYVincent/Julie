package com.vincent.julie.netty.msg;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.netty.msg
 * @class describe
 * @date 2018/2/7 0:54
 */

public class PingMsg extends BaseMsg {
    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}

