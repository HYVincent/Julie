package com.vincent.julie.widget.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.mylibrary.more_language.AppTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.setting
 * @class describe
 * @date 2018/3/24 20:20
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.setting_rl_upgrade)
    RelativeLayout settingRlUpgrade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.setting_text_title));
    }

    @OnClick({R.id.include_titie_ll_left, R.id.setting_rl_upgrade})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_titie_ll_left:
                finish();
                break;
            case R.id.setting_rl_upgrade:
                break;
            default:break;
        }
    }

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,SettingActivity.class);
        activity.startActivity(intent);
    }

}
