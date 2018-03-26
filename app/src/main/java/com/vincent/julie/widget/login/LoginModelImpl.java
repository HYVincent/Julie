package com.vincent.julie.widget.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.tamic.novate.Throwable;
import com.vincent.julie.base.AppConfig;
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



    /**
     * 登录接口
     * @param mContext
     * @param phone 手机号码
     * @param password 密码
     * @param iNetworkResponseListener 网络访问结果回调接口
     */
    @Override
    public void login(final Context mContext, String phone, String password,
                      final INetworkResponseListener iNetworkResponseListener) {

        NovateUtils.getNovate().call(NovateUtils.getMyApi().login(
                phone,
                password),
                new MySubscriber<ResponseEntity>(mContext) {
                    @Override
                    public void onError(Throwable e) {
                        //登录出错
                        iNetworkResponseListener.responseError(e);
                    }

                    @Override
                    public void onNext(ResponseEntity responseEntity) {
                        if(responseEntity != null){
                            //通过NetworkResponseListener接口把登录结果回调给Presenter
                            iNetworkResponseListener.responseResult(responseEntity);
                        }else {
                            //响应结果为空
                            iNetworkResponseListener.responseIsNull();
                        }
                    }
                });
    }
}
