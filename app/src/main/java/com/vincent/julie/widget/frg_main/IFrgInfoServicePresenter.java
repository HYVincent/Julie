package com.vincent.julie.widget.frg_main;

import android.content.Context;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_info_service
 * @class describe
 * @date 2018/3/23 0:39
 */

public interface IFrgInfoServicePresenter {

    void getCurrentCityWeather(Context mContext, String key, String output, String city);

    void checkNewVersion(Context mContext, int version);

    void getDeviceStatus();

}
