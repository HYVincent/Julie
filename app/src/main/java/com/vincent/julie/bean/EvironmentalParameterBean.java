package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe 环境参数实体类
 * @date 2018/3/31 13:06
 */
public class EvironmentalParameterBean {

    private String roomName;
    // 温度 0  湿度 2 光照 3 pm25 4 噪音 5
    private int type;
    //温度
    private int temperature;
    //湿度
    private int humidity;
    //光照
    private int illumination;
    //pm 2.5
    private int pm25;
    //噪音
    private int noise;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }
}
