package com.vincent.mylibrary.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe EditText工具类
 * @date 2018/1/12 22:04
 */

public class EditTextUtils {

    /**
     * 设置EditText的可输入和不可输入状态
     * @param editText
     * @param editable
     */
    public static void editTextable(EditText editText, boolean editable) {
        if (!editable) { // disable editing password
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            editText.setClickable(false); // user navigates with wheel and selects widget
        } else { // enable editing of password
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.setClickable(true);
        }
    }

    /**
     * 点击打开键盘
     * @param context
     * @param editText
     */
    public static void openKeyboard(Context context,EditText editText){
        InputMethodManager inputMethodManager=(InputMethodManager)context. getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        editText.requestFocus();//setFocus方法无效 //addAddressRemarkInfo is EditText
    }

    /**
     * 关闭键盘
     * @param activity
     */
    public static void closeKeyboard(Activity activity){
        if(activity == null){
            return;
        }
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
