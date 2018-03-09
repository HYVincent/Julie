package com.vincent.mylibrary.listener;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.elvishew.xlog.XLog;


/**
 * @name KangDaEr
 * @class name：com.vincent.library.listener
 * @class describe 用来判断APP是否进入了后台  使用方式：在MyApplication的onCreate()中注册  registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
 * @author Vincent QQ:1032006226
 * @time 2017/10/24 9:25
 * @change
 * @chang time
 * @class describe
 */

public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {

    private int refCount = 0;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        refCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
//        XLogs.getLogger().d("恢复:"+activity.getPackageName());
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        refCount--;
        if(refCount == 0){
//            setAppGoBackGround(true);
            //表示APP进入后台了
            XLog.d("APP已经进入后台了");
        }
    }



    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
