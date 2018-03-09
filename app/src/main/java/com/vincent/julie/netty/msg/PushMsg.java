package com.vincent.julie.netty.msg;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.netty.msg
 * @class describe
 * @date 2018/2/7 0:55
 */

public class PushMsg extends BaseMsg {

    private String account;

    private String content;

    public PushMsg() {
        super();
        setType(MsgType.PUSH);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}