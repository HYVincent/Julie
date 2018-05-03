package com.vincent.julie.network;

import android.support.annotation.Keep;

import com.vincent.julie.bean.ResponseEntity;
import com.vincent.julie.bean.WeatherBean;

import retrofit2.Call;
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
    @GET("v3/weather/weatherInfo")
    Observable<WeatherBean> getCurrentAreaWeather(
        @Query("key")String key,
        @Query("output")String output,
        @Query("city")String city
    );


    /**
     * 登陆
     * @param user_phone
     * @param user_password
     * @return
     */
    @Keep//保持接口不被混淆
    @POST("MyWeb/user/login")//定义登录接口的路径
    Observable<ResponseEntity> login(//函数名
            @Query("user_phone") String user_phone,//登录账号 参数名称和参数类型
            @Query("user_password") String user_password//密码
    );

    /**
     * 注册接口
     * @param user_phone
     * @param user_password
     * @return
     */
    @Keep
    @POST("MyWeb/user/register")
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
    @POST("MyWeb/user/checkPhoneIsExist")
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
    @POST("MyWeb/user/resetPassword")
    Observable<ResponseEntity> resetPassword(
        @Query("user_phone")String user_phone,
        @Query("user_password")String user_password
    );

    /**
     * 检查新版本
     * @param version
     * @return
     */
    @Keep
    @GET("MyWeb/version/checkNewVersion")
    Observable<ResponseEntity> checkNewVersion(
            @Query("version")int version
    );

    /**
     * 添加备忘录
     * @param user_id
     * @param memo_title
     * @param memo_content
     * @return
     */
    @Keep
    @POST("MyWeb/user/memo/addMemo")
    Observable<ResponseEntity> addMemo(
            @Query("user_id")String user_id,
            @Query("memo_title")String memo_title,
            @Query("memo_content")String memo_content
    );

    /**
     * 提交反馈
     * @param user_id
     * @param feedback_title
     * @param feedback_type 1 反馈  2 bug
     * @param feedback_content
     * @return
     */
    @Keep
    @POST("MyWeb/user/feedback/addFeedback")
    Observable<ResponseEntity> commitFeedback(
            @Query("user_id")int user_id,
            @Query("feedback_title")String feedback_title,
            @Query("feedback_type")int feedback_type,
            @Query("feedback_content")String feedback_content
    );

}
