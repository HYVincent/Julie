package com.vincent.julie.widget.login;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.elvishew.xlog.XLog;
import com.vincent.julie.R;
import com.vincent.julie.base.MyApp;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.bean.User;
import com.vincent.julie.network.NovateUtils;
import com.vincent.julie.util.ResultUtil;
import com.vincent.mylibrary.util.MD5Utils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.login
 * @class describe
 * @date 2018/3/11 10:14
 */

public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginView view;
    private ILoginModel model;

    public LoginPresenterImpl(ILoginView view) {
        this.view = view;
        model = new LoginModelImpl();
    }

    @Override
    public void login(final Context mContext, String phone, String password) {
        if(TextUtils.isEmpty(phone)){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_is_null));
            return;
        }
        if(phone.length() != 11){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_length_error));
            return;
        }
        if(TextUtils.isEmpty(password)){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_is_null));
            return;
        }
        if(password.length()>16 || password.length()<6){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_not_standard));
            return;
        }
        password = MD5Utils.md5Code(password);
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_login));
        model.login(mContext, phone, password, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                if(ResultUtil.success(view,resultEntity)){
                    //登陆成功
                    MyApp.user = JSON.parseObject(JSON.toJSONString(resultEntity.getData()), User.class);
                    NovateUtils.setRequestHead("API_TOKEN",MyApp.user.getApi_token());
                    view.toastMsg(mContext.getString(R.string.toast_msg_login_success));
                    view.loginSuccess();
                }
            }

            @Override
            public void responseError(Throwable throwable) {
                ResultUtil.error(view);
            }

            @Override
            public void responseIsNull() {
                ResultUtil.error(view);
            }
        });
    }
}
