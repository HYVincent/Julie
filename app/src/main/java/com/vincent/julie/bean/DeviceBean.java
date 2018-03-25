package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe
 * @date 2018/3/25 0:43
 */

public class DeviceBean {
    //类型 0 照明灯  1 厨房灯光 2 客厅灯光  3 卧室灯光 4 厨房冰箱温度 5 走廊灯光 6 插座开关 7 电源开关状态
    private int type;
    //0 关闭  1 打开
    private int status;
    //包含电器名称和位置  比如  厨房灯光
    private String title;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
