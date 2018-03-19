package com.vincent.julie.widget.login;

import android.content.Context;
import android.util.Log;

import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NovateUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.login
 * @class describe
 * @date 2018/3/11 10:12
 */

public class LoginModelImpl implements ILoginModel {

    private int count = 0;

    /**
     * 登录接口
     * @param mContext
     * @param phone 手机号码
     * @param password 密码
     * @param iNetworkResponseListener
     */
    @Override
    public void login(final Context mContext, String phone, String password,
                      final INetworkResponseListener iNetworkResponseListener) {
        count ++;
        Log.d("请求次数", "login: "+count);
        NovateUtils.getNovate().call(NovateUtils.getMyApi().login(
                phone,
                password),
                new MySubscriber<ResponseEntity>(mContext) {
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
}
