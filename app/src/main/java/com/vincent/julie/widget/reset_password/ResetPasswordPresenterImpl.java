package com.vincent.julie.widget.reset_password;

import android.content.Context;
import android.text.TextUtils;

import com.vincent.julie.R;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.util.ResultUtil;
import com.vincent.julie.widget.register.IRegisterModel;
import com.vincent.julie.widget.register.RegisterModelImpl;
import com.vincent.mylibrary.util.MD5Utils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.reset_password
 * @class describe
 * @date 2018/3/13 22:16
 */

public class ResetPasswordPresenterImpl implements IResetPasswordPresenter {

    private IResetPasswordView view;
    private IResetPasswordModel model;
    private IRegisterModel registerModel;

    public ResetPasswordPresenterImpl(IResetPasswordView view) {
        this.view = view;
        model = new ResetPasswordModelImpl();
        registerModel = new RegisterModelImpl();
    }


    @Override
    public void checkUserExist(final Context mContext, String phone) {
        if(TextUtils.isEmpty(phone)){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_is_null));
            return;
        }
        if(phone.length() != 11){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_length_error));
            return;
        }
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_check_phone));
        registerModel.checkUserIsExist(mContext, phone, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                view.hideLoadingDialog();
                if(!resultEntity.isSuccess()){
                    view.sendAuthcode();
                    view.toastMsg(mContext.getString(R.string.toast_msg_send_authcode_success));
                }else {
                    view.toastMsg(mContext.getString(R.string.toast_msg_user_unregister));
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

    @Override
    public void sendAuthcode(final Context mContext, String phone) {
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_request_authcode));
        registerModel.sendAuthcode(mContext, phone, new RegisterModelImpl.RegisterAuthcodeListener() {
            @Override
            public void sendSuccess() {
                view.sendAuthcodeSuccess();
                view.hideLoadingDialog();
            }

            @Override
            public void sendFail(int event, int result, Object data) {
                view.hideLoadingDialog();
                view.toastMsg(mContext.getString(R.string.toast_msg_send_authcode_fail));
            }
        });
    }

    @Override
    public void resetPassword(final Context mContext, final String phone, String authcode, final String password1, String password2) {
        if(TextUtils.isEmpty(phone)){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_is_null));
            return;
        }
        if(phone.length() != 11){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_length_error));
            return;
        }
        if(TextUtils.isEmpty(password1)){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_is_null));
            return;
        }
        if(password1.length()<6||password1.length()>16){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_not_standard));
            return;
        }
        if(!TextUtils.equals(password1,password2)){
            view.toastMsg(mContext.getString(R.string.toast_msg_password_diff));
            return;
        }
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_check_phone));
        registerModel.checkAuthcode(mContext, phone, authcode, new RegisterModelImpl.RegisterCheckResultListener() {
            @Override
            public void right() {
                view.hideLoadingDialog();
                resetPassword(mContext,phone, MD5Utils.md5Code(password1));
            }

            @Override
            public void error() {
                view.hideLoadingDialog();
                view.toastMsg(mContext.getString(R.string.toast_msg_authcode_error));
            }
        });
    }

    /**
     * 重置密码
     * @param mContext
     * @param phone
     * @param s
     */
    private void resetPassword(Context mContext, String phone, String s) {
        model.resetPassword(mContext, phone, s, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                if(ResultUtil.success(view,resultEntity)){
                    view.resetPasswordSuccess();
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
