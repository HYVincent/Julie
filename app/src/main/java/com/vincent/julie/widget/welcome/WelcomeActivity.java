package com.vincent.julie.widget.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vincent.julie.base.AppConfig;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.widget.login.LoginActivity;
import com.vincent.mylibrary.MyLibrary;
import com.vincent.mylibrary.util.IntentUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.welcome
 * @class describe
 * @date 2018/3/11 0:17
 */

public class WelcomeActivity extends BaseActivity {

    private boolean isFirstApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //避免重复启动app
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }
        isFirstApp = MyLibrary.getSpUtil().getBoolean(AppConfig.FIRST_START_APP,false);
        if(!isFirstApp){
            //第一次启动
            IntentUtils.startImplicitActivity(WelcomeActivity.this,"GuideActivity");
            MyLibrary.getSpUtil().putBoolean(AppConfig.FIRST_START_APP,true);
        }else {
            //不是第一次启动
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
