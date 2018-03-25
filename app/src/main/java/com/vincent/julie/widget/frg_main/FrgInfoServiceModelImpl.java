package com.vincent.julie.widget.frg_main;

import android.content.Context;
import android.text.TextUtils;

import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseStringListener;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NovateUtils;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_info_service
 * @class describe
 * @date 2018/3/23 0:40
 */

public class FrgInfoServiceModelImpl implements IFrgInfoServiceModel {
    @Override
    public void getCurrentCityWeather(Context mContext, String key, String output, String city, final INetworkResponseStringListener iNetworkResponseStringListener) {
        NovateUtils.getNovate().call(NovateUtils.getMyApi().getCurrentAreaWeather(key, output, city), new MySubscriber<String>(mContext) {
            @Override
            public void onError(Throwable e) {
                iNetworkResponseStringListener.responseError(e);
            }

            @Override
            public void onNext(String s) {
                if(TextUtils.isEmpty(s)){
                    iNetworkResponseStringListener.responseIsNull();
                }else {
                    iNetworkResponseStringListener.responseResult(s);
                }
            }
        });
    }
}
