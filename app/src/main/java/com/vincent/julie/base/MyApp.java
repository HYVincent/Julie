package com.vincent.julie.base;

import android.app.Application;

import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
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

    public static User user;
    private static MyApp myApp;

    public static MyApp getMyApp() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        MyLibrary.init(this.getApplicationContext(),"Julie");
        user = new User();
        MobSDK.init(this);
        CrashReport.initCrashReport(getApplicationContext(), "9589575c85", false);
    }
}
