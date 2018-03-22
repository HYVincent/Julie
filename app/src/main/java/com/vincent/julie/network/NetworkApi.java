package com.vincent.julie.network;

import android.support.annotation.Keep;

import com.vincent.julie.bean.ResponseEntity;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.network
 * @class describe
 * @date 2018/3/11 10:24
 */

public interface NetworkApi {

//    http://restapi.amap.com/v3/weather/weatherInfo?key=feb5202d4a21db8dc083257856a4ed34&output=JSON&city=440306
//    http://restapi.amap.com/julie/weather/weatherInfo?key=feb5202d4a21db8dc083257856a4ed34&output=JSON&ciry=440306
    /**
     * 获取高德地图天气预报信息
     * @param key
     * @param output
     * @param city
     * @return
     */
    @Keep
    @Headers({"url_tag:weather"})
    @GET("weather/weatherInfo")
    Observable<String> getCurrentAreaWeather(
        @Query("key")String key,
        @Query("output")String output,
        @Query("ciry")String city
    );

    /**
     * 登陆
     * @param user_phone
     * @param user_password
     * @return
     */
    @Keep
    @POST("user/login")
    Observable<ResponseEntity> login(
            @Query("user_phone") String user_phone,
            @Query("user_password") String user_password
    );

    /**
     * 注册接口
     * @param user_phone
     * @param user_password
     * @return
     */
    @Keep
    @POST("user/register")
    Observable<ResponseEntity> register(
            @Query("user_phone") String user_phone,
            @Query("user_password") String user_password
    );

    /**
     * 判断某个用户是否存在
     * @param user_phone
     * @return
     */
    @Keep
    @POST("user/checkPhoneIsExist")
    Observable<ResponseEntity> checkUserIsExist(
            @Query("user_phone") String user_phone
    );

    /**
     * 重置密码
     * @param user_phone
     * @param user_password
     * @return
     */
    @Keep
    @POST("user/resetPassword")
    Observable<ResponseEntity> resetPassword(
        @Query("user_phone")String user_phone,
        @Query("user_password")String user_password
    );

    @Keep
    @GET("version/checkNewVersion")
    Observable<ResponseEntity> checkNewVersion(
            @Query("version")int version
    );



}
