package com.vincent.julie.widget.frg_main;

import android.content.Context;
import android.util.Log;

import com.vincent.julie.base.AppConfig;
import com.vincent.julie.bean.INetworkResponseListener;
import com.vincent.julie.bean.INetworkResponseStringListener;
import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.widget.frg_mine.FrgMineModelImpl;
import com.vincent.julie.widget.frg_mine.IFrgMineModel;
import com.vincent.mylibrary.MyLibrary;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_info_service
 * @class describe
 * @date 2018/3/23 0:41
 */

public class FrgInfoServicePresenterImpl implements IFrgInfoServicePresenter {
    private static final String TAG = FrgInfoServicePresenterImpl.class.getSimpleName();
    private IFrgInfoServiceView view;
    private IFrgInfoServiceModel model;
    private IFrgMineModel mineModel;

    public FrgInfoServicePresenterImpl(IFrgInfoServiceView view) {
        this.view = view;
        model = new FrgInfoServiceModelImpl();
        mineModel = new FrgMineModelImpl();
    }

    @Override
    public void getCurrentCityWeather(Context mContext, String key, String output, String city) {
        model.getCurrentCityWeather(mContext, key, output, city, new INetworkResponseStringListener() {
            @Override
            public void responseResult(String response) {
                Log.d(TAG, "responseResult: 天气结果-->"+response);
            }

            @Override
            public void responseError(Throwable throwable) {
                Log.d(TAG, "responseError: 天气信息获取失败..");
            }

            @Override
            public void responseIsNull() {
                Log.d(TAG, "responseError: 天气信息获取失败..");
            }
        });
    }

    @Override
    public void checkNewVersion(Context mContext, int version) {
        mineModel.checkAppUpgrade(mContext, version, new INetworkResponseListener() {
            @Override
            public void responseResult(ResponseEntity resultEntity) {
                if(resultEntity.isSuccess()){
                    MyLibrary.getSpUtil().putBoolean(AppConfig.EVENTMSG_NEW_VERSION,true);
                }else {
                    MyLibrary.getSpUtil().putBoolean(AppConfig.EVENTMSG_NEW_VERSION,false);
                }
            }

            @Override
            public void responseError(Throwable throwable) {

            }

            @Override
            public void responseIsNull() {

            }
        });
    }

    @Override
    public void getDeviceStatus() {

    }
}
