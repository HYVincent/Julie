package com.vincent.julie.widget.check_phone;

import android.content.Context;

import com.vincent.mylibrary.IBaseView;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.check_phone
 * @class describe
 * @date 2018/3/12 0:18
 */

public interface ICheckPhoneView extends IBaseView {

   void sendAuthcodeSuccess();
   void sendAuthcodeFail();

   void senAuthcode();
}
