package com.vincent.julie.widget.check_phone;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.check_phone
 * @class describe
 * @date 2018/3/12 0:14
 */

public class CheckPhoneActivity extends BaseActivity implements ICheckPhoneView{

    private ICheckPhonePresenter presenter;
//    private

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_phone);
        presenter = new CheckPhonePresenterImpl(this);
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void sendAuthcodeSuccess() {
        toastMsg(getString(R.string.toast_msg_send_authcode_success));
    }

    @Override
    public void sendAuthcodeFail() {
        toastMsg(getString(R.string.toast_msg_send_authcode_fail));
    }

    @Override
    public void senAuthcode() {
//        presenter.sendAuthcode(CheckPhoneActivity.this,);
    }
}
