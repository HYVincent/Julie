package com.vincent.julie.widget.login;

import android.content.Context;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.login
 * @class describe
 * @date 2018/3/11 10:13
 */

public interface ILoginPresenter  {

    /**
     * 登录接口
     * @param mContext
     * @param phone 手机号码
     * @param password 密码
     */
    void login(Context mContext, String phone, String password);

}
