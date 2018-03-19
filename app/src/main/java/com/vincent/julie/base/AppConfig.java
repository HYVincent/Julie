package com.vincent.julie.base;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.base
 * @class describe http://127.0.0.1:8080/MyWeb/user/login?user_password=555555&user_phone=18696855784
 * @date 2018/3/11 10:39
 */

public class AppConfig {

    /**
     * 服务器地址 注意 如果是Eclipse启动的本地Web 服务器地址需要用ip
     * 如果是本地Tomcat启动的服务器地址则为:127.0.0.1
     */
//    public static final String SERVICE_ADDRESS = "http://192.168.3.3:8080/MyWeb/";
    public static final String SERVICE_ADDRESS = "http:123.207.47.61:8080/MyWeb/";
    /**
     * 服务器文件地址  例如apk版本 http://123.207.47.61:8080/Julie/Apk/zhijia_10000.apk
     */
    public static final String SERVICE_FILE_ADDRESS = "http://123.207.47.61:8080/Julie/";
    /**
     * 是否是第一次启动APP
     */
    public static final String FIRST_START_APP = "first_start_app";

}