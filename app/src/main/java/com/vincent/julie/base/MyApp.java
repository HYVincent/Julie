package com.vincent.julie.base;

import android.app.Application;

import com.vincent.julie.bean.User;
import com.vincent.mylibrary.MyLibrary;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.base
 * @class describe
 * @date 2018/2/7 0:33
 */

public class MyApp extends Application{

    private static User user;

    public static User getUser() {
        return user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyLibrary.init(this.getApplicationContext(),"Julie");
        user = new User();
        user.user_phone="18696855784";
        user.user_name="vincent";
    }
}
