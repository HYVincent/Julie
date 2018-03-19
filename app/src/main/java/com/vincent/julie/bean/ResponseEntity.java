package com.vincent.julie.bean;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.toncentsoft.starkangmedical.entity
 * @class describe
 * @date 2017/12/8 16:09
 */


public class ResponseEntity<T> {

    private boolean success;//true或者false
    private String errorCode;//错误码
    private String msg;//错误消息
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String errorMsg) {
        this.msg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
