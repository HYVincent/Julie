package com.vincent.mylibrary.entity;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.mylibrary.entity
 * @class describe
 * @date 2018/3/1 22:52
 */

public class NetworkResponseEntity<T> {

    //true 成功 false 失败
    private boolean success;
    //错误码
    private int errorCode;
    //服务器返回的消息
    private String msg;
    //服务器返回的数据
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
