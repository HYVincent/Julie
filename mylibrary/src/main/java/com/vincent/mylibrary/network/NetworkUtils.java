package com.vincent.mylibrary.network;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vincent.mylibrary.entity.NetworkResponseEntity;
import com.vincent.mylibrary.network.callback.NetworkResponseListener;
import com.vincent.mylibrary.util.ThreadPoolManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.network
 * @class describe
 * @date 2018/3/1 22:40
 */

public class NetworkUtils {
    private static NetworkUtils instance;
    private static final String TAG = "网络";
    /**
     * 我家的局域网笔记本电脑ip
     */
    public static final String BASE_SERVICE_ADDRESS = "http://127.0.0.1:8080/MyWeb/";
    /**
     * 我的服务器ip
     */
//    public static final String BASE_SERVICE_ADDRESS="http://123.207.47.61:8080/MyWeb/";
    /**
     * 网络链接超时
     */
    private static final int NETWORK_CONNECT_TIME_OUR = 5 * 1000;
    /**
     * 网络读取超时时间
     */
    private static final int NETWORK_READ_TIME_OUR = 5 * 1000;
    //添加请求头
    private static String REQUEST_HEAD = "";

    private NetworkUtils(){
    };

    /**
     * 获取NetworkUtils的实例对象
     * @return
     */
    public static synchronized NetworkUtils getInstance() {
        if(instance == null){
            instance = new NetworkUtils();
        }
        return instance;
    }


    /**
     * post请求
     * @param addressPath 请求的路径
     * @param params 请求参数
     * @param networkResponseListener 响应结果
     */
    public void postRequest(final String addressPath,final Map<String,String> params,final NetworkResponseListener networkResponseListener){
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //响应结果
                    StringBuffer response = new StringBuffer();
                    //请求的完整地址拼接
                    StringBuffer urlStr = new StringBuffer();
                    urlStr.append(BASE_SERVICE_ADDRESS);
                    urlStr.append(addressPath);
                    urlStr.append("?");
                    urlStr.append(assemblyParams(params));
                    Log.d(TAG, "run: url->"+urlStr.toString());
                    HttpURLConnection mHttpURLConnection = getmHttpURLConnection(RequestMethod.POST,urlStr.toString());
                    InputStream in = mHttpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    if(mHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        String line;
                        if((line = reader.readLine())!=null){
                            response.append(line);
                        }
                        networkResponseListener.onSuccess(JSON.parseObject(response.toString(),NetworkResponseEntity.class));
                    }else {
                        networkResponseListener.onFail("errorCode = "+ mHttpURLConnection.getResponseCode());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    //回调错误方法
                    networkResponseListener.onFail("请求错误");
                }
            }
        });
    }

    /**
     * 字符串参数拼接
     * @param params
     * @return
     */
    private String assemblyParams(Map<String,String> params) throws IOException{
        StringBuffer stringBuffer = new StringBuffer();
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            stringBuffer.append(URLEncoder.encode((String) entry.getKey(),"UTF-8"));
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode((String) entry.getValue(),"UTF-8"));
            if(iter.hasNext()){
                stringBuffer.append("&");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 根据url获取HttpURLConnection
     * @param requestMethod
     * @param url
     * @return
     */
    private HttpURLConnection getmHttpURLConnection(String requestMethod,String url){
        HttpURLConnection mHttpURLConnect = null;
        try {
            URL mURL = new URL(url);
            mHttpURLConnect = (HttpURLConnection) mURL.openConnection();
            //设置网络连接超时时间
            mHttpURLConnect.setConnectTimeout(NETWORK_CONNECT_TIME_OUR);
            //设置网络读取时间
            mHttpURLConnect.setReadTimeout(NETWORK_READ_TIME_OUR);
            //设置请求方法
            mHttpURLConnect.setRequestMethod(requestMethod);
            //忽略缓存
            mHttpURLConnect.setUseCaches(false);
            //添加Header
            mHttpURLConnect.setRequestProperty("Connection","Keep-Alive");
//            mHttpURLConnect.setRequestProperty("Content-Length",String.valueOf(params.length()));
            //接受输入流
            mHttpURLConnect.setDoInput(true);
            //传入参数时需要开启
            mHttpURLConnect.setDoOutput(true);
        }catch (IOException e){
            e.printStackTrace();
            Log.d(TAG, "getmHttpURLConnection: ffffffffffffffff");
        }
        return mHttpURLConnect;
    }

}
