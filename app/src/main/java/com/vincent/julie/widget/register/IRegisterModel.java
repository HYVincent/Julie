package com.vincent.julie.widget.register;

import android.content.Context;

import com.vincent.julie.bean.INetworkResponseListener;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.register
 * @class describe
 * @date 2018/3/11 11:16
 */

public interface IRegisterModel {

    /**
     * 注册
     * @param mContext
     * @param phone
     * @param password
     * @param iNetworkResponseListener
     */
    void register(Context mContext, String phone, String password, INetworkResponseListener iNetworkResponseListener);

    /**
     * 发送验证码
     * @param mContext
     * @param phone
     */
    void sendAuthcode(Context mContext, String phone, RegisterModelImpl.RegisterAuthcodeListener registerAuthcodeListener);

    /**
     * 检查验证码
     * @param mContext
     * @param authCode
     */
    void checkAuthcode(Context mContext, String phone,String authCode, RegisterModelImpl.RegisterCheckResultListener registerCheckResultListener);

    /**
     * 查询用户是否存在
     * @param mContext
     * @param phone
     */
    void checkUserIsExist(Context mContext,String phone,INetworkResponseListener iNetworkResponseListener);
}
