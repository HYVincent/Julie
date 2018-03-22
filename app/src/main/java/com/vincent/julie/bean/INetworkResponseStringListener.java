package com.vincent.julie.bean;

/**
 * Created by Administrator on 2018/3/23.
 */

public interface INetworkResponseStringListener {

    /**
     * 服务器结果监听
     * @param response
     */
    void responseResult(String response);

    /**
     * 服务器响应错误
     */
    void responseError(Throwable throwable);

    /**
     * 服务器响应为空
     */
    void responseIsNull();
}
