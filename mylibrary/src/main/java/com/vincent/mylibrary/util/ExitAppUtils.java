package com.vincent.mylibrary.util;

import android.content.Context;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/2/10 14:40
 */

public class ExitAppUtils {

    private static boolean isQuit=false;
    /**
     * 在onBackPressed()方法中调用
     *true 退出
     *@author Vincent QQ1032006226
     *created at 2016/9/27 11:03
     */
    public static boolean isQuit(Context context, String exitMsg,ExitAppListener exitAppListener){
        if (!isQuit) {
            isQuit = true;
            MyToastUtil.showMsg(exitMsg);
            TimerTask task = null;
            task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 2000);
            return true;
        } else {
            exitAppListener.removeAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            Log.d("app is exit", "app is exit");
            return false;
        }
    }

    public interface ExitAppListener{
        void removeAllActivity();
    }

}
