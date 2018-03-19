package com.vincent.julie.widget.help;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.widget.feedback.FeedbackActivity;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.help
 * @class describe
 * @date 2018/3/18 18:49
 */

public class HelpFeedBackActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.help_feedback_tv_help)
    AppTextView helpFeedbackTvHelp;
    @BindView(R.id.help_feedback_tv_feedback)
    AppTextView helpFeedbackTvFeedback;

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,HelpFeedBackActivity.class);
        IntentUtils.startNewActivity(activity,intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_feedback);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.help_feedback_text_title));
    }

    @OnClick({R.id.include_titie_ll_left, R.id.help_feedback_tv_help, R.id.help_feedback_tv_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_titie_ll_left:
                finish();
                break;
            case R.id.help_feedback_tv_help:
                break;
            case R.id.help_feedback_tv_feedback:
                FeedbackActivity.actionStart(HelpFeedBackActivity.this);
                break;
                default:break;
        }
    }
}
