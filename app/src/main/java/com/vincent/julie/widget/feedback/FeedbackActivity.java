package com.vincent.julie.widget.feedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.mylibrary.dialog.OrdinaryMsgDialog;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;
import com.vincent.mylibrary.util.MyToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.feedback
 * @class describe
 * @date 2018/3/18 18:58
 */

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.root_ll_left)
    LinearLayout rootLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.include_layout_right_tv)
    AppTextView includeLayoutRightTv;
    @BindView(R.id.include_layout_right_ll)
    LinearLayout includeLayoutRightLl;
    @BindView(R.id.feedback_et_content)
    EditText feedbackEtContent;

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,FeedbackActivity.class);
        IntentUtils.startNewActivity(activity,intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.feedback_text_title));
        includeLayoutRightTv.setText(getString(R.string.feedback_text_commit));
    }

    @OnClick({R.id.root_ll_left, R.id.include_layout_right_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.root_ll_left:
                finish();
                break;
            case R.id.include_layout_right_ll:
                OrdinaryMsgDialog ordinaryMsgDialog = new OrdinaryMsgDialog(FeedbackActivity.this);
                ordinaryMsgDialog.setMargin(30);
                ordinaryMsgDialog.setStrContent("是否确认提交?")
                        .setStrTitle("提示")
                        .setOnActionClickListener(new OrdinaryMsgDialog.OnActionClickListener() {
                            @Override
                            public void doAction() {
                                MyToastUtil.showMsg("提交！");
                            }
                        })
                        .show();
                break;
            default:break;
        }
    }
}
