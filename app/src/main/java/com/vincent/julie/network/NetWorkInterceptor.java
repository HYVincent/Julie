package com.vincent.julie.network;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.elvishew.xlog.XLog;
import com.vincent.julie.base.AppConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vincent on 2018/3/21.
 */

public class NetWorkInterceptor implements Interceptor{


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Headers headers = chain.proceed(originalRequest).headers();
        List<String> cookies = headers.values("Set-Cookie");
        String session = cookies.get(0);
        String sessionId = session.substring(0,session.indexOf(";"));
        XLog.d("Response Cookies:"+ JSONArray.toJSONString(cookies)+" \nsession ="+session+"\nsessionId="+sessionId);

        Headers headersRequest = originalRequest.headers();
        List<String> cookiesRequest = headersRequest.values("Set-Cookie");
        String sessionRequest = cookies.get(0);
        String sessionIdRequest = session.substring(0,sessionRequest.indexOf(";"));
        XLog.d("Request Cookies:"+ JSONArray.toJSONString(cookiesRequest)+" \nsession ="+sessionRequest+"\nsessionId="+sessionIdRequest);

        //获取老的url
        HttpUrl oldUrl = originalRequest.url();
        //获取originalRequest的创建者builder
        Request.Builder builder = originalRequest.newBuilder();
        //把sessionId放到请求头里面
        builder.addHeader("sessionId",sessionIdRequest);


        /*//获取头信息的集合如：manage,mdffx
        List<String> urlnameList = originalRequest.headers("urlname");
        if (urlnameList != null && urlnameList.size() > 0) {
            //删除原有配置中的值,就是namesAndValues集合里的值
            builder.removeHeader("urlname");
            //获取头信息中配置的value,如：manage或者mdffx
            String urlname = urlnameList.get(0);
            Log.d("拦截器", "intercept: "+urlname);
            HttpUrl baseURL=null;
            //根据头信息中配置的value,来匹配新的base_url地址
            if (TextUtils.equals("supplierLogin",urlname)) {
                baseURL = HttpUrl.parse(AppConfig.BASE_SERVICE_ADDRESS_SUPPLIER_LOGIN);
                //重建新的HttpUrl，需要重新设置的url部分
                HttpUrl newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        .build();
                //获取处理后的新newRequest
                Request newRequest = builder.url(newHttpUrl).build();
                return  chain.proceed(newRequest);
            }else {
                return chain.proceed(originalRequest);
            }
        }*/
        return chain.proceed(originalRequest);
    }
}
