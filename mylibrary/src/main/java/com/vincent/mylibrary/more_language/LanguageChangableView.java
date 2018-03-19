package com.vincent.mylibrary.more_language;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name StartKangMedical_Android
 * @page com.vincent.mylibrary.more_language
 *  欢迎访问我的博客:http://georgeyang.cn
 * @class describe
 * @date 2018/3/5 10:54
 */

public interface LanguageChangableView {
    String ANDROIDXML = "http://schemas.android.com/apk/res/android";

    //由于setText无法被重写，需要添加以下三个必要的方法，如果你的app不需要对多语言的textview修改值(只是xml写死就够了)，那就不需要实现他们
    void setTextById(@StringRes int id);//手动设置textId
    void setTextWithString(String text);//手动去掉textId,不然重新加载语言的时候会被重置掉
    void setTextByArrayAndIndex(@ArrayRes int arrId, @StringRes int arrIndex);//手动通过TextArray设置语言

    void reLoadLanguage();//修改语言时主要调用的方法
}
