package com.vincent.mylibrary.entity;

import java.io.Serializable;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary.entity
 * @class describe
 * @date 2017/12/12 10:55
 */

public class EventMsg<T> implements Serializable {

    public String msgType;

    public T msgContent;

    private int position;

    public EventMsg() {
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public T getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(T msgContent) {
        this.msgContent = msgContent;
    }

    public EventMsg(String msgType, T msgContent) {
        this.msgType = msgType;
        this.msgContent = msgContent;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}