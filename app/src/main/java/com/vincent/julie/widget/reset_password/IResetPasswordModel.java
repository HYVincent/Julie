package com.vincent.julie.widget.reset_password;

import android.content.Context;

import com.vincent.julie.bean.INetworkResponseListener;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.reset_password
 * @class describe
 * @date 2018/3/13 22:14
 */

public interface IResetPasswordModel {

    /**
     * 重置密码
     * @param mContext
     * @param phone
     * @param s
     * @param iNetworkResponseListener
     */
    void resetPassword(Context mContext, String phone, String s, INetworkResponseListener iNetworkResponseListener);

}
