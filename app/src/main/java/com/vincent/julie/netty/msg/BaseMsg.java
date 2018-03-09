package com.vincent.julie.netty.msg;

import java.io.Serializable;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.netty.msg
 * @class describe
 * @date 2018/2/7 0:56
 */

public abstract class BaseMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private MsgType type;
    //必须唯一，否者会出现channel调用混乱
    private String phoneNum;

    //初始化客户端id
    public BaseMsg() {
        this.phoneNum = Constants.getPhoneNum();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }
}