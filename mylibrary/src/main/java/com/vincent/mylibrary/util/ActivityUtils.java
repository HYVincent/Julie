package com.vincent.mylibrary.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/2/10 14:56
 */

public class ActivityUtils {

    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        if(activityList != null){
            activityList.add(activity);
        }
    }

    public static void removeActivity(Activity activity){
        if(activityList != null){
            activityList.remove(activity);
        }
    }

    public static void removeAllActivity(){
        if(activityList != null && activityList.size() >0){
            for (Activity activity: activityList){
                activity.finish();
            }
        }
    }

}
