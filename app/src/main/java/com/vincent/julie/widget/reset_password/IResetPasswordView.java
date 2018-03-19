package com.vincent.julie.widget.reset_password;

import com.vincent.mylibrary.IBaseView;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.reset_password
 * @class describe
 * @date 2018/3/13 22:15
 */

public interface IResetPasswordView extends IBaseView {

    void resetPasswordSuccess();

    /**
     * 发送验证码
     */
    void sendAuthcode();

    /**
     * 发送成功
     */
    void sendAuthcodeSuccess();

}
