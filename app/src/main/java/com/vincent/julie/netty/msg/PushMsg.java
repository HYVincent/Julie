package com.vincent.julie.netty.msg;

/**
 * @Project: schoolmallapi
 * @ClassName: PushMsg
 * @Description: 鎺ㄩ?佹秷鎭被鍨?
 * @author:	chenpy
 * @date:	2016骞?11鏈?1鏃?
 * @version 1.0.0
 */
public class PushMsg<T> extends BaseMsg {


    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据类型 比如推送给客户端的数据类型
     */
    private int dataType;

    /**
     * 泛型的数据
     */
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public PushMsg() {
        super();
        setType(MsgType.PUSH);
    }


}
