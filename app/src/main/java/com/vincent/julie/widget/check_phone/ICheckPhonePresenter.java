package com.vincent.julie.widget.check_phone;

import android.content.Context;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.check_phone
 * @class describe
 * @date 2018/3/12 0:19
 */

public interface ICheckPhonePresenter {

    void sendAuthcode(Context mContext, String phone);

    void checkUserIsExist(Context mContext,String phone);

}
