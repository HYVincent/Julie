package com.vincent.julie.widget.register;

import android.content.Context;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.register
 * @class describe
 * @date 2018/3/11 11:20
 */

public interface IRegisterPresenter {

    /**
     * 注册
     * @param mContext
     * @param phone
     * @param password
     */
    void register(Context mContext, String phone, String password);

    /**
     * 检查验证码
     * @param mContext
     * @param authcode
     */
    void checkAuthcode(Context mContext,String phone,String authcode,String password);

    /**
     * 发送验证码
     * @param mContext
     * @param phone
     */
    void sendAuthcode(Context mContext,String phone);

    /**
     * 查询用户是否存在
     * @param mContext
     * @param phone
     */
    void checkUserIsExist(Context mContext,String phone);
}
