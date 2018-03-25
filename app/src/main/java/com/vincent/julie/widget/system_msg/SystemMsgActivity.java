package com.vincent.julie.widget.system_msg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.view.EmptyRecyclerView;
import com.vincent.mylibrary.more_language.AppTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.system_msg
 * @class describe
 * @date 2018/3/26 1:44
 */

public class SystemMsgActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.system_msg_rlv)
    EmptyRecyclerView recycleView;

    public static void actionStart(Activity activity){
        Intent intent =new Intent(activity,SystemMsgActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_msg);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.system_msg_text_title));
    }

    @OnClick(R.id.include_titie_ll_left)
    public void onViewClicked() {
        finish();
    }
}
