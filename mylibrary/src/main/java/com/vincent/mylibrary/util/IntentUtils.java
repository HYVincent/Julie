package com.vincent.mylibrary.util;

import android.app.Activity;
import android.content.Intent;

import com.vincent.mylibrary.R;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2017/12/8 15:22
 */


public class IntentUtils {

    /**
     * 跳转到新的Activity并配置同意的跳转动画
     * @param activity
     * @param c
     */
    public static void startNewActivity(Activity activity,Class c){
        Intent intent = new Intent(activity,c);
        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.activity_join,R.anim.activity_exit);
    }

    /**
     * 跳转到新的Activity并配置同意的跳转动画
     * @param activity
     * @param c
     */
    public static void startNewActivity(Activity activity,Class c,String params){
        Intent intent = new Intent(activity,c);
        intent.putExtra("params",params);
        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.activity_join,R.anim.activity_exit);
    }



    /**
     * 隐式启动Activity
     * @param activity
     * @param tag 目标Activity的action配置
     */
    public static void startImplicitActivity(Activity activity,String tag){
        Intent intent = new Intent();
        //设置intent的Action属性值
        intent.setAction( tag);
        //不加这行也行，因为这个值默认就是Intent.CATEGORY_DEFAULT
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        activity.startActivity( intent );
    }

}
