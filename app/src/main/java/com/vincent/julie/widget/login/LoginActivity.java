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
import com.vincent.mylibrary.more_language.AppButton;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.login
 * @class describe 用户登录界面
 */
public class LoginActivity extends BaseActivity implements ILoginView{

    /**
     * 初始化Activity中的控件
     */
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
    /**
     * 初始化Presenter实例
     */
    private ILoginPresenter presenter;

    /**
     * 调用此函数跳转到登录页面
     * @param activity
     */
    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,LoginActivity.class);
        IntentUtils.startNewActivity(activity,intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //初始化presenter对象
        presenter = new LoginPresenterImpl(this);
    }

    @OnClick({R.id.login_tv_register, R.id.login_tv_reset_password, R.id.login_btn_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_register:
                //点击注册按钮跳转到注册页面
                RegisterActivity.actionStart(LoginActivity.this);
                break;
            case R.id.login_tv_reset_password:
                //点击跳转到忘记密码页面
                ResetPasswordActivity.actionStart(LoginActivity.this);
                break;
            case R.id.login_btn_action:
                //点击执行登录
                presenter.login(LoginActivity.this,
                        loginEtPhone.getText().toString(),
                        loginEtPassword.getText().toString());
                break;
            default:break;
        }
    }

    /**
     * 登录失败
     */
    @Override
    public void loadFail() {
        toastMsg("登录失败");
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {
        MainActivity.actionStart(LoginActivity.this);
        finish();
    }
}
