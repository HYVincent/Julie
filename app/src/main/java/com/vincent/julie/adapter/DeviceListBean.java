package com.vincent.julie.adapter;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.adapter
 * @class describe
 * @date 2018/3/26 1:04
 */

public class DeviceListBean {

    private String name;
    private int  totalNum;
    private int  runNum;
    //类型  1 灯  2 电源  3 窗帘  4 影音  5 空调  6 遥控器  7 安防
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
