package com.vincent.mylibrary.util;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.util
 * @class describe
 * @date 2018/3/7 15:39
 */

public class TextViewUtils {

    private static  TextViewUtils instance;

    public static TextViewUtils getInstance() {
        if(instance == null){
            instance = new TextViewUtils();
        }
        return instance;
    }

    /**
     * 设置一个Text View不同部分不同颜色
     * @param textView
     * @param start 开始变色的下标
     * @param end 结束下标
     */
    public void toProtocol(TextView textView, int start, int end,int commonColorId,int specialColorId) {
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(specialColorId);
        UnderlineSpan lineSpan = new UnderlineSpan();
//    builder.setSpan(lineSpan,8,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //下划线
//        builder.setSpan(blueSpan,start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //字体颜色
        builder.setSpan(blueSpan,start,end, commonColorId);  //字体颜色
        textView.setText(builder);
    }

}
