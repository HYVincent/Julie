package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe
 * @date 2018/5/3 23:14
 */

public class ManagerNotifyBean {
    //通知时间戳
    private long notifyTime;
    //通知类型
    private long notifyType;
    //通知标题
    private String title;
    //具体描述
    private String desc;

    public long getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(long notifyTime) {
        this.notifyTime = notifyTime;
    }

    public long getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(long notifyType) {
        this.notifyType = notifyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
