package com.vincent.julie.widget.reset_password;

import android.content.Context;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.reset_password
 * @class describe
 * @date 2018/3/13 22:15
 */

public interface IResetPasswordPresenter {
    /**
     * 检查用户是否注册
     * @param mContext
     * @param phone
     */
    void checkUserExist(Context mContext, String phone);

    /**
     * 发送验证码
     * @param mContext
     * @param phone
     */
    void sendAuthcode( Context mContext, String phone);

    /**
     * 重置密码
     * @param mContext
     * @param phone
     * @param authcode
     * @param password1
     * @param password2
     */
    void resetPassword(Context mContext,String phone,String authcode,String password1,String password2);

}
