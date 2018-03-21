package com.vincent.julie.network;

import android.support.annotation.Keep;

import com.vincent.julie.bean.ResponseEntity;

import retrofit2.http.GET;
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
