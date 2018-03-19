package com.vincent.julie.widget.register;

import android.content.Context;

import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NovateUtils;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.register
 * @class describe
 * @date 2018/3/11 11:17
 */

public class RegisterModelImpl implements IRegisterModel {

    private boolean isRequest = false;

    @Override
    public void register(Context mContext, String phone, String password, final INetworkResponseListener iNetworkResponseListener) {
        if(isRequest){
            return;
        }
        NovateUtils.getNovate().call(NovateUtils.getMyApi().register(phone, password), new MySubscriber<ResponseEntity>(mContext) {
            @Override
            public void onError(Throwable e) {
                isRequest = false;
                iNetworkResponseListener.responseError(e);
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                isRequest = false;
                if(responseEntity != null){
                    iNetworkResponseListener.responseResult(responseEntity);
                }else {
                    iNetworkResponseListener.responseIsNull();
                }
            }
        });
    }

    @Override
    public void sendAuthcode(Context mContext, String phone, final RegisterAuthcodeListener listener) {
        // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
        //        public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    listener.sendSuccess();
                } else{
                    listener.sendFail(event,result,data);
                }
                // 用完回调要注销，否则会造成泄露
                SMSSDK.unregisterEventHandler(this);
            }
        });
        // 触发操作
        SMSSDK.getVerificationCode("86", phone);
//        }
    }

    @Override
    public void checkAuthcode(Context mContext,String phone, String authCode, final RegisterCheckResultListener registerCheckResultListener) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    registerCheckResultListener.right();
                } else{
                    registerCheckResultListener.error();
                }
                // 用完回调要注销，否则会造成泄露
                SMSSDK.unregisterEventHandler(this);
            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode("86", phone, authCode);
    }

    @Override
    public void checkUserIsExist(Context mContext, String phone, final INetworkResponseListener iNetworkResponseListener) {
        NovateUtils.getNovate().call(NovateUtils.getMyApi().checkUserIsExist(phone), new MySubscriber<ResponseEntity>(mContext) {
            @Override
            public void onError(Throwable e) {
                iNetworkResponseListener.responseError(e);
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                if(responseEntity != null){
                    iNetworkResponseListener.responseResult(responseEntity);
                }else {
                    iNetworkResponseListener.responseIsNull();
                }
            }
        });
    }


    public interface RegisterAuthcodeListener{
        void sendSuccess();

        void sendFail(int event, int result, Object data);
    }

    public interface RegisterCheckResultListener{
        void right();

        void error();
    }

}
