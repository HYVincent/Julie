package com.vincent.julie.widget.feedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.base.MyApp;
import com.vincent.mylibrary.dialog.OrdinaryMsgDialog;
import com.vincent.mylibrary.dialog.SingleSelectDialog;
import com.vincent.mylibrary.entity.SelectEntity;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;
import com.vincent.mylibrary.util.MyToastUtil;

import java.util.ArrayList;
import java.util.List;

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

public class FeedbackActivity extends BaseActivity implements IFeedbackView{

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
    @BindView(R.id.feedback_tv_type)
    TextView feedbackTvType;
    @BindView(R.id.feedback_rl_type)
    RelativeLayout feedbackRlType;
    @BindView(R.id.feedback_et_title)
    EditText feedbackEtTitle;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, FeedbackActivity.class);
        IntentUtils.startNewActivity(activity, intent);
    }

    private IFeedbackPresenter presenter;
    private List<SelectEntity> data = new ArrayList<>();
    // 1 建议  2 bug
    private int type = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.feedback_text_title));
        includeLayoutRightTv.setText(getString(R.string.feedback_text_commit));
        presenter = new FeedbackPresenterImpl(this);
        SelectEntity selectEntity = new SelectEntity();
        selectEntity.setItemContent(getString(R.string.feedback_text_type_feedback));
        selectEntity.setSelect(true);
        SelectEntity bug = new SelectEntity();
        bug.setItemContent(getString(R.string.feedback_text_type_bug));
        bug.setSelect(false);
        data.add(selectEntity);
        data.add(bug);
    }

    @OnClick({R.id.root_ll_left, R.id.include_layout_right_ll,R.id.feedback_rl_type})
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
                                presenter.commitFeedback(FeedbackActivity.this, MyApp.user.getUser_id(),
                                        feedbackEtTitle.getText().toString(),type,feedbackEtContent.getText().toString());
                            }
                        })
                        .show();
                break;
            case R.id.feedback_rl_type:
                SingleSelectDialog singleSelectDialog = new SingleSelectDialog(FeedbackActivity.this);
                singleSelectDialog.setTitleText(getString(R.string.feedback_text_feedback_type))
                        .setData(data)
                        .setSelectResultOnListener(new SingleSelectDialog.SelectResultOnListener() {
                            @Override
                            public void onResult(String result) {
                                feedbackTvType.setText(result);
                                if(TextUtils.equals(result,data.get(0).getItemContent())){
                                    type = 1;
                                }else {
                                    type = 2;
                                }
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    @Override
    public void commitSuccess() {
        toastMsg(getString(R.string.toast_msg_feedback_commit_success));
        try {
            Thread.sleep(500);
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFail() {

    }
}
