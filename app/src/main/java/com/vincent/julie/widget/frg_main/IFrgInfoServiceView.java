package com.vincent.julie.widget.frg_main;

import com.vincent.julie.bean.WeatherBean;
import com.vincent.mylibrary.IBaseView;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_info_service
 * @class describe
 * @date 2018/3/23 0:40
 */

public interface IFrgInfoServiceView extends IBaseView {

    /**
     * 刷新天气
     */
    void refreshWeather(WeatherBean weatherBean);

    /**
     * 获取天气失败
     */
    void refreshWeatherFail();

}
