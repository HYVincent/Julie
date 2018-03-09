package com.vincent.mylibrary.util;

import android.os.Handler;
import android.os.Looper;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe  使用
 *
 *  MainHandler.getInstance().post(new Runnable() {
        @Override
        public void run() {
        //TODO run your code
    }
});
 *
 * @date 2018/1/16 11:11
 */
public class MainHandler extends Handler {
    private static volatile MainHandler instance;

    public static MainHandler getInstance() {
        if (null == instance) {
            synchronized (MainHandler.class) {
                if (null == instance) {
                    instance = new MainHandler();
                }
            }
        }
        return instance;
    }
    private MainHandler() {
        super(Looper.getMainLooper());
    }
}