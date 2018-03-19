package com.vincent.julie.widget.reset_password;

import android.content.Context;
import android.util.Log;

import com.elvishew.xlog.XLog;
import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NovateUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.reset_password
 * @class describe
 * @date 2018/3/13 22:15
 */

public class ResetPasswordModelImpl implements IResetPasswordModel {

    private static final String TAG = ResetPasswordModelImpl.class.getSimpleName();
    private boolean isRequest = false;

    @Override
    public void resetPassword(Context mContext, String phone, String s, final INetworkResponseListener iNetworkResponseListener) {
        if(isRequest){
            Log.d(TAG, "resetPassword: 上次请求未结束..");
            isRequest = true;
            return;
        }
        NovateUtils.getNovate().call(NovateUtils.getMyApi().resetPassword(phone, s), new MySubscriber<ResponseEntity>(mContext) {
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
                iNetworkResponseListener.responseError(e);
                isRequest = false;
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                isRequest = false;
                try {
                    if(responseEntity != null){
                        iNetworkResponseListener.responseResult(responseEntity);
                    }else {
                        iNetworkResponseListener.responseIsNull();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    iNetworkResponseListener.responseError(e.getCause());
                }
            }
        });

    }
}
