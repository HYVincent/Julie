package com.vincent.julie.network;

import com.vincent.julie.base.AppConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.network
 * @class describe
 * @date 2018/3/23 0:46
 */

public class UrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("url_tag");
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_tag");

            //从request中获取原有的HttpUrl实例oldHttpUrl
            HttpUrl oldHttpUrl = request.url();

            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if ("weather".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(AppConfig.GAODE_WEATHER_SERVICE_ADDRESS);
            }  else{
                newBaseUrl = oldHttpUrl;
            }
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
//                    .scheme(newBaseUrl.scheme())
                    .host(newBaseUrl.host())
                    .port(newBaseUrl.port())
                    .build();

            //重建这个request，通过builder.url(newFullUrl).build()；
            //然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        } else {
            return chain.proceed(request);
        }
    }
}
