package com.vincent.julie.network;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elvishew.xlog.XLog;
import com.vincent.julie.bean.ResponseEntity;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary.network
 * @class describe 网络拦截器
 * @date 2017/12/12 10:36
 */
public class MyNetworkInterceptor implements Interceptor {

    private static final String TAG = MyNetworkInterceptor.class.getSimpleName();
    private ResponseEntity resultEntity;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        ResponseBody body = null;
        String strResponse = "";
        okhttp3.MediaType mediaType = null;
        try {
            response = chain.proceed(chain.request());
            body = response.body();
            strResponse = body.string();
            Log.d(TAG, "intercept: "+strResponse);
            if (!TextUtils.isEmpty(strResponse)) {
                resultEntity = JSON.parseObject(strResponse, ResponseEntity.class);
            } else {
                Log.d(TAG, "intercept: service response is null");
                resultEntity = new ResponseEntity();
                resultEntity.setSuccess(false);
                resultEntity.setMsg("服务器异常，请稍后重试");
                strResponse = JSONObject.toJSONString(resultEntity);
            }
            mediaType = response.body().contentType();
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "intercept: SocetTimeOutException");
            if (TextUtils.isEmpty(strResponse) && resultEntity == null) {
                resultEntity = new ResponseEntity();
                resultEntity.setSuccess(false);
                resultEntity.setMsg("服务器连接超时");
                strResponse = JSONObject.toJSONString(resultEntity);
            }
            e.printStackTrace();
        } catch (Exception e) {
            Log.d(TAG, "intercept: Exception");
            if (resultEntity == null) {
                resultEntity = new ResponseEntity();
                resultEntity.setSuccess(false);
                resultEntity.setMsg("服务器异常，请稍后重试");
                strResponse = JSONObject.toJSONString(resultEntity);
            }
            e.printStackTrace();
        }
//        request.
        //注意如果这里不继续传递回去，接口将收不到数据
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, strResponse))
                .build();
    }
}
