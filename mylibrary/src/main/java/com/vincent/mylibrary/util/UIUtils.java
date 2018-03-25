package com.vincent.mylibrary.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name FoodApp
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/3/15 11:25
 */


public class UIUtils {

    private static UIUtils instance;

    public static UIUtils getInstance() {
        if(instance == null){
            instance = new UIUtils();
        }
        return instance;
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

}
