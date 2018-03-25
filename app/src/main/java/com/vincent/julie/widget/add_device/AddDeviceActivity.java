package com.vincent.julie.widget.add_device;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.mylibrary.more_language.AppTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.add_device
 * @class describe
 * @date 2018/3/24 18:56
 */

public class AddDeviceActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.add_device_text_title));
    }


    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AddDeviceActivity.class);
        activity.startActivity(intent);
    }

}
