package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.bean
 * @class describe
 * @date 2018/3/11 10:10
 */

public interface INetworkResponseListener {

    /**
     * 服务器结果监听
     * @param resultEntity
     */
    void responseResult(ResponseEntity resultEntity);

    /**
     * 服务器响应错误
     */
    void responseError(Throwable throwable);

    /**
     * 服务器响应为空
     */
    void responseIsNull();

}
