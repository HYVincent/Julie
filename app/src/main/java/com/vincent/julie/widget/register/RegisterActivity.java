package com.vincent.julie.widget.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.widget.login.LoginActivity;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.IntentUtils;
import com.vincent.mylibrary.util.TimeCount;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.register
 * @class describe
 * @date 2018/3/11 0:20
 */

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.register_et_phone)
    EditText registerEtPhone;
    @BindView(R.id.register_atv_authcode)
    AppTextView registerAtvAuthcode;
    @BindView(R.id.register_et_password)
    EditText registerEtPassword;
    @BindView(R.id.register_atv_gvrp)
    AppTextView registerAtvGvrp;
    @BindView(R.id.register_btn_action)
    Button registerBtnAction;
    @BindView(R.id.register_et_authcode)
    EditText registerEtAuthcode;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        IntentUtils.startNewActivity(activity, intent);
    }

    private IRegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this);
        includeLayoutTitle.setText(getString(R.string.register_text_action));
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void registerSuccess() {
        LoginActivity.actionStart(RegisterActivity.this);
    }

    @Override
    public void authcode() {
        presenter.register(RegisterActivity.this,
                registerEtPhone.getText().toString(),
                registerEtPassword.getText().toString());
    }

    @Override
    public void sendAuthcodeSuccess() {
        timeCount.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeCount.cancel();
    }

    TimeCount timeCount = new TimeCount(60, new TimeCount.TimeOnListener() {
        @Override
        public void finishAction() {
            registerAtvAuthcode.setText(getString(R.string.register_text_authcode));
            registerAtvAuthcode.setClickable(true);
            registerAtvAuthcode.setTextColor(ContextCompat.getColor(RegisterActivity.this,R.color.color_white_ffffff));
            registerAtvAuthcode.setBackground(ContextCompat.getDrawable(RegisterActivity.this,R.drawable.shape_register_authcode_blue));
        }

        @Override
        public void everyAction(int s) {
            registerAtvAuthcode.setText(String.valueOf(s));
            registerAtvAuthcode.setClickable(false);
            registerAtvAuthcode.setTextColor(ContextCompat.getColor(RegisterActivity.this,R.color.color_black_333333));
            registerAtvAuthcode.setBackground(ContextCompat.getDrawable(RegisterActivity.this,R.drawable.shape_register_authcode_gray));
        }
    });

    @Override
    public void sendAuthcodeFail() {
        //发送失败
    }

    @Override
    public void userIsExist() {
        presenter.sendAuthcode(RegisterActivity.this,registerEtPhone.getText().toString());
    }

    @OnClick({R.id.include_titie_ll_left, R.id.register_atv_authcode, R.id.register_atv_gvrp, R.id.register_btn_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_titie_ll_left:
                finish();
                break;
            case R.id.register_atv_authcode:
                presenter.checkUserIsExist(RegisterActivity.this,registerEtPhone.getText().toString());
                break;
            case R.id.register_atv_gvrp:
                toastMsg("注册协议");
                break;
            case R.id.register_btn_action:
                presenter.checkAuthcode(RegisterActivity.this,
                        registerEtPhone.getText().toString(),
                        registerEtAuthcode.getText().toString(),
                        registerEtPassword.getText().toString());
                break;
            default:break;
        }
    }
}
