package com.vincent.mylibrary.util.apk_down;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name FoodMeterial
 * @page com.vincent.mylibrary.util.apk_down
 * @class describe
 * @date 2018/3/22 11:22
 */

public class DownApkUtil {

    private static DownApkUtil instance;

    public static DownApkUtil getInstance() {
        if(instance == null){
            instance = new DownApkUtil();
        }
        return instance;
    }

    public void init(Activity activity){
        //检查升级相关
        Intent intent = new Intent(activity, DownloadService.class);
        activity.startService(intent); // 启动服务
        activity.bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务
    }

    public void unRegister(Activity activity){
        activity.unbindService(connection);
    }

    //以下为APP下载更新所需
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

    };

    /**
     * 注意下载apk之前需要检查权限
     * @param url
     * @param notifyImg
     * @param notifyTitle
     */
    public void downApk(String url,int notifyImg,String notifyTitle){
        if(downloadBinder == null){
            throw new NullPointerException("please call the init() in your fragment or activity's onCreate()");
        }
        downloadBinder.startDownload(url,notifyImg,notifyTitle);
    }


}
