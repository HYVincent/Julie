package com.vincent.julie.widget.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.widget.main.MainActivity;
import com.vincent.julie.widget.register.RegisterActivity;
import com.vincent.julie.widget.reset_password.ResetPasswordActivity;
import com.vincent.julie.widget.welcome.WelcomeActivity;
import com.vincent.mylibrary.more_language.AppButton;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;
import com.vincent.mylibrary.util.NotificationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.login
 * @class describe
 * @date 2018/3/11 0:16
 */

public class LoginActivity extends BaseActivity implements ILoginView{

    @BindView(R.id.login_et_phone)
    EditText loginEtPhone;
    @BindView(R.id.login_et_password)
    EditText loginEtPassword;
    @BindView(R.id.login_tv_register)
    AppTextView loginTvRegister;
    @BindView(R.id.login_tv_reset_password)
    AppTextView loginTvResetPassword;
    @BindView(R.id.login_btn_action)
    AppButton loginBtnAction;

    private ILoginPresenter presenter;

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,LoginActivity.class);
        IntentUtils.startNewActivity(activity,intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginEtPhone.setText("18696855784");
        loginEtPhone.setSelection(loginEtPhone.getText().length());
        loginEtPassword.setText("555555");
        presenter = new LoginPresenterImpl(this);
    }

    @OnClick({R.id.login_tv_register, R.id.login_tv_reset_password, R.id.login_btn_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_register:
                RegisterActivity.actionStart(LoginActivity.this);
                break;
            case R.id.login_tv_reset_password:
//                NotificationUtil.sendNotification(LoginActivity.this, WelcomeActivity.class,R.mipmap.ic_launcher,"测试","消息");
                ResetPasswordActivity.actionStart(LoginActivity.this);
                break;
            case R.id.login_btn_action:
                presenter.login(LoginActivity.this,
                        loginEtPhone.getText().toString(),
                        loginEtPassword.getText().toString());
                break;
            default:break;
        }
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void loginSuccess() {
        MainActivity.actionStart(LoginActivity.this);
        finish();
    }
}
