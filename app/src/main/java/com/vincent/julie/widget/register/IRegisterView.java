package com.vincent.julie.widget.register;

import com.vincent.mylibrary.IBaseView;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.register
 * @class describe
 * @date 2018/3/11 11:21
 */

public interface IRegisterView extends IBaseView{

    void registerSuccess();

    void authcode();

    void sendAuthcodeSuccess();

    void sendAuthcodeFail();

    /**
     * 用户不存在，可以注册了
     */
    void userIsExist();

}
