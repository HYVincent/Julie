package com.vincent.julie.widget.check_phone;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vincent.julie.R;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.util.ResultUtil;
import com.vincent.julie.widget.register.IRegisterModel;
import com.vincent.julie.widget.register.RegisterModelImpl;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.check_phone
 * @class describe
 * @date 2018/3/12 0:20
 */

public class CheckPhonePresenterImpl implements ICheckPhonePresenter {
    private ICheckPhoneView view;
    private IRegisterModel model;

    public CheckPhonePresenterImpl(ICheckPhoneView view) {
        this.view = view;
        model = new RegisterModelImpl();
    }

    @Override
    public void sendAuthcode(final Context mContext, String phone) {
        if(TextUtils.isEmpty(phone)){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_is_null));
            return;
        }
        if(phone.length() != 11){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_length_error));
            return;
        }
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_request_authcode));
        model.sendAuthcode(mContext, phone, new RegisterModelImpl.RegisterAuthcodeListener() {
            @Override
            public void sendSuccess() {
                view.sendAuthcodeSuccess();
            }

            @Override
            public void sendFail(int event, int result, Object data) {
                view.hideLoadingDialog();
                try {
                    if(event == 2){
                        String resultStr = String.valueOf(data);
                        //http://www.lpnote.com/2017/05/12/autotype-not-support-with-fastjson/
                        //这里为了避免异常直接把@符号替换掉
                        String resultStr1 = resultStr.substring(21);
                        Log.d("222", "error1:"+resultStr1);
                        JSONObject jsonObject = JSON.parseObject(resultStr1);
                        String message = jsonObject.getString("detail");
                        if(TextUtils.isEmpty(message)){
                            view.sendAuthcodeFail();
                        }else {
                            view.toastMsg(message);
                        }
                    }else {
                        view.toastMsg(mContext.getString(R.string.toast_msg_send_authcode_fail));
                    }
                    view.hideLoadingDialog();
                }catch (Exception e){
                    e.printStackTrace();
                    view.toastMsg(mContext.getString(R.string.toast_msg_send_authcode_fail));
                    view.hideLoadingDialog();
                }
            }
        });
    }

    @Override
    public void checkUserIsExist(Context mContext, String phone) {
        if(TextUtils.isEmpty(phone)){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_is_null));
            return;
        }
        if(phone.length() != 11){
            view.toastMsg(mContext.getString(R.string.toast_msg_phone_length_error));
            return;
        }
        view.showLoadingDialog(mContext.getString(R.string.loading_msg_check_phone));
        model.checkUserIsExist(mContext, phone, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                if(ResultUtil.success(view,resultEntity)){
                    view.senAuthcode();
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
