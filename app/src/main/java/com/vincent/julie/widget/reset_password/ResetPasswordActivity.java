package com.vincent.julie.widget.reset_password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.widget.register.RegisterActivity;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;
import com.vincent.mylibrary.util.MainHandler;
import com.vincent.mylibrary.util.TimeCount;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.reset_password
 * @class describe
 * @date 2018/3/11 9:53
 */

public class ResetPasswordActivity extends BaseActivity implements IResetPasswordView{

    @BindView(R.id.root_ll_left)
    LinearLayout rootLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.include_layout_right_tv)
    AppTextView includeLayoutRightTv;
    @BindView(R.id.include_layout_right_ll)
    LinearLayout includeLayoutRightLl;
    @BindView(R.id.reset_password_et_phone)
    EditText resetPasswordEtPhone;
    @BindView(R.id.reset_passwordr_iv_1)
    ImageView resetPasswordrIv1;
    @BindView(R.id.reset_password_atv_authcode)
    AppTextView resetPasswordAtvAuthcode;
    @BindView(R.id.reset_password_et_authcode)
    EditText resetPasswordEtAuthcode;
    @BindView(R.id.reset_password_atv_authcode_et_password_1)
    EditText resetPasswordAtvAuthcodeEtPassword1;
    @BindView(R.id.reset_password_atv_authcode_et_password_2)
    EditText resetPasswordAtvAuthcodeEtPassword2;

    private IResetPasswordPresenter presenter;

    /**
     * 跳转
     *
     * @param activity
     */
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ResetPasswordActivity.class);
        IntentUtils.startNewActivity(activity,intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.reset_password_text_title));
        includeLayoutRightTv.setText(getString(R.string.common_text_finish));
        presenter = new ResetPasswordPresenterImpl(this);
    }

    @OnClick({R.id.root_ll_left, R.id.include_layout_right_ll, R.id.reset_password_atv_authcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.root_ll_left:
                finish();
                break;
            case R.id.include_layout_right_ll:
                presenter.resetPassword(ResetPasswordActivity.this,
                        resetPasswordEtPhone.getText().toString(),
                        resetPasswordEtAuthcode.getText().toString(),
                        resetPasswordAtvAuthcodeEtPassword1.getText().toString(),
                        resetPasswordAtvAuthcodeEtPassword2.getText().toString());
                break;
            case R.id.reset_password_atv_authcode:
                presenter.checkUserExist(ResetPasswordActivity.this,
                        resetPasswordEtPhone.getText().toString());
                break;
            default:break;
        }
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void resetPasswordSuccess() {
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                toastMsg(getString(R.string.toast_msg_reste_password_successfully));
                finish();
            }
        });
    }

    @Override
    public void sendAuthcode() {
        presenter.sendAuthcode(ResetPasswordActivity.this,
                resetPasswordEtPhone.getText().toString());
    }

    @Override
    public void sendAuthcodeSuccess() {
//        验证码发送成功
        timeCount.start();
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                resetPasswordAtvAuthcode.setClickable(false);
                resetPasswordAtvAuthcode.setBackground(ContextCompat.getDrawable(ResetPasswordActivity.this,R.drawable.shape_register_authcode_gray));
            }
        });
    }

    private TimeCount timeCount = new TimeCount(60, new TimeCount.TimeOnListener() {
        @Override
        public void finishAction() {
            MainHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    resetPasswordAtvAuthcode.setText(getString(R.string.register_text_authcode));
                    resetPasswordAtvAuthcode.setClickable(true);
                    resetPasswordAtvAuthcode.setBackground(ContextCompat.getDrawable(ResetPasswordActivity.this,R.drawable.shape_register_authcode_blue));
                }
            });
            timeCount.cancel();
        }

        @Override
        public void everyAction(int s) {
            resetPasswordAtvAuthcode.setText(String.valueOf(s));
        }
    });

}
