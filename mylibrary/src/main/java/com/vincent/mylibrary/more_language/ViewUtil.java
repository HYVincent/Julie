package com.vincent.mylibrary.more_language;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.more_language
 * @class describe
 * @date 2018/3/5 10:55
 */

public class ViewUtil {

    /**
     * 调用更新语言
     * @param view
     */
    public static void updateViewLanguage(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            int count = vg.getChildCount();
            for (int i = 0; i < count; i++) {
                updateViewLanguage(vg.getChildAt(i));
            }
        } else if (view instanceof LanguageChangableView) {
            LanguageChangableView tv = (LanguageChangableView) view;
            tv.reLoadLanguage();
        }
    }

}
