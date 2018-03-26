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
            //检查用户账号
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_is_null));
            return;
        }
        if(phone.length() != 11){
            //检查账号长度
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_length_error));
            return;
        }
        //检查用户密码
        if(TextUtils.isEmpty(password)){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_is_null));
            return;
        }
        //判断密码范围
        if(password.length()>16 || password.length()<6){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_not_standard));
            return;
        }
        //使用MD5对用户密码进行加密处理
        password = MD5Utils.md5Code(password);
        //提示用户正在登录..
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_login));
        //调用model的login函数并传递参数
        model.login(mContext, phone, password, new INetworkResponseListener() {
            /**
             * 登录结果处理
             * @param resultEntity
             */
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

            /**
             * 登录失败
             * @param throwable
             */
            @Override
            public void responseError(Throwable throwable) {
                ResultUtil.error(view);
            }

            /**
             * 服务器没有响应任何结果
             */
            @Override
            public void responseIsNull() {
                ResultUtil.error(view);
            }
        });
    }
}
