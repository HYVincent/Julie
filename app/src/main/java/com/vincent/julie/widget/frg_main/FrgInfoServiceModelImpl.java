package com.vincent.julie.widget.frg_main;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.elvishew.xlog.XLog;
import com.tamic.novate.Throwable;
import com.vincent.julie.bean.INetworkResponseStringListener;
import com.vincent.julie.bean.WeatherBean;
import com.vincent.julie.network.MySubscriber;
import com.vincent.julie.network.NetworkApi;
import com.vincent.julie.network.NovateUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        NovateUtils.getNovate().call(NovateUtils.getMyApi().getCurrentAreaWeather(key, output, city), new MySubscriber<WeatherBean>(mContext) {
            @Override
            public void onError(Throwable e) {
//                iNetworkResponseStringListener.responseError(e);
            }

            @Override
            public void onNext(WeatherBean weatherBean) {
                Log.d("获取天气", "onNext: 结果-->"+JSON.toJSONString(weatherBean));
                if(weatherBean != null){
                    iNetworkResponseStringListener.responseResult(JSON.toJSONString(weatherBean));
                }else {
                    iNetworkResponseStringListener.responseIsNull();
                }
            }
        });
    }
}
