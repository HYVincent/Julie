package com.vincent.julie.base;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.base
 * @class describe 家电设备
 * @date 2018/3/24 19:20
 */

public class ElectricDeviceBean  {
    //电器设备名称
    private String title;
    //电器设备所在房间
    private String roomName;
    // 0 未连接  1 已连接
    private String status;
    // 设备的地址
    private String address;
    // 这个设备的图标
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
