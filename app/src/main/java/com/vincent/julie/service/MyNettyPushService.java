package com.vincent.julie.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.vincent.julie.netty.Config;
import com.vincent.julie.netty.NettyClientBootstrap;
import com.vincent.julie.netty.msg.PushMsg;
import com.vincent.mylibrary.util.EventUtil;
import com.vincent.mylibrary.util.MainHandler;
import com.vincent.mylibrary.util.NotificationUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.service
 * @class describe
 * @date 2018/2/7 0:57
 */

public class MyNettyPushService extends Service {

    private static final String TAG = MyNettyPushService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(Config.openNetty){
            Log.d(TAG, "onCreate: start connect Netty Service ..");
            try {
                //开始连接服务器
                NettyClientBootstrap.getInstance().startNetty();
            }catch (Exception e){
                e.printStackTrace();
                Log.d(TAG, "onCreate: netty start error ..");
            }
            if(NettyClientBootstrap.getInstance().isOpen()){
                Log.d(TAG, "onCreate: netty connect is success!");
                //TODO  发送系统通知..
            }else {
                Log.d(TAG, "onCreate: Netty connect is fail");
            }
            EventUtil.register(this);
        }else {
            Log.d(TAG, "onCreate: push is close");
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void fromNettyServiceMsg(final PushMsg pushMsg){
        //处理消息 测试通过
        Log.d(TAG, "fromNettyServiceMsg: accept from service pushMsg-->"+pushMsg.getPhoneNum());
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
//                NotificationUtil.sendNotification(NettyPushService.this, NettyMsgDetailActivity.class,pushMsg.getContent());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(Config.openNetty&& NettyClientBootstrap.getInstance().isOpen()){
            NettyClientBootstrap.getInstance().closeChannel();
            EventUtil.unregister(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}

