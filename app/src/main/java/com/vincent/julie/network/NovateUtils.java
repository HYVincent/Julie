package com.vincent.julie.network;

import com.tamic.novate.Novate;
import com.vincent.julie.base.AppConfig;
import com.vincent.mylibrary.MyLibrary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @name KangDaEr
 * @class name：com.toncentsoft.kangdaer.utils
 * @class describe Novate网络框架
 * @author Vincent QQ:1032006226
 * @time 2017/10/17 9:39
 * @change
 * @chang time
 * @class describe
 */

public class NovateUtils {

    private static Novate novate;
    private static Map<String, String> headers = new HashMap<>();
    private static NetworkApi myApi;
    private static OkHttpClient okHttpClient;
    private static OkHttpClient.Builder builder;
    private static final int NETWORK_CONNECT_TIME_OUT = 10 * 1000;
    private static final int NETWORK_READ_TIME_OUT = 10 * 1000;
    private static final int NETWORK_WRITE_TIME_OUT = 10 * 1000;
//    private static final String NETWORK_BASE_URL = "http://mp.toncentsoft.com:8089/StarCare-app/";

    public static String getToken(){
        return headers.get("API_TOKEN");
    }

    /**
     * 添加请求头
     * @param key
     * @param values
     */
    public static void setRequestHead(String key,String values){
        if(headers != null){
            headers.clear();
            headers.put(key,values);
//            XLogs.getLogger().d("request head:"+headers.get("token"));
        }
    }

    /**
     * 清空请求头
     */
    public static void clearRequestHead(){
        if(headers != null){
            headers.clear();
        }
    }

    public static OkHttpClient getOkHttpClient() {
        if(builder == null){
            builder = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
//                                    .addHeader("token",NovateUtils.getToken())
                                    .addHeader("Accept-Encoding", "identity")
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    });
        }
        return builder.build();
    }

    /**
     * 获取Novate对象
     * @return
     */
    public static Novate getNovate(){
        if(novate == null){
            novate = new Novate.Builder(MyLibrary.getmContext())
                    .client(getOkHttpClient())
                    .connectTimeout(NETWORK_CONNECT_TIME_OUT)
                    .readTimeout(NETWORK_READ_TIME_OUT)
                    .writeTimeout(NETWORK_WRITE_TIME_OUT)
                    .baseUrl(AppConfig.SERVICE_ADDRESS)
                    .addInterceptor(new MyNetworkInterceptor())
                    .addHeader(headers)
                    .addCache(false)
                    .addLog(true)
                    .build();
        }
        return novate;
    }


    /**
     * 获取API对象
     * @return
     */
    public static NetworkApi getMyApi() {
        if(myApi == null){
            myApi = getNovate().create(NetworkApi.class);
        }
        return myApi;
    }
}
