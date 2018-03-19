package com.vincent.julie.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.util.NetworkUtil;
import com.vincent.mylibrary.util.ThreadPoolManager;

/**
 * Created by LIUYONGKUI726 on 2017-06-01.
 */

public abstract class MySubscriber<T>  extends BaseSubscriber<T> {

    private static final String TAG = MySubscriber.class.getSimpleName();
    private Context context;

    public MySubscriber(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtil.isNetworkAvailable(context)) {
            Toast.makeText( context, "网络连接失败",Toast.LENGTH_SHORT).show();
            onCompleted();
            return;
        }
    }

    @Override
    public void onCompleted() {
        super.onCompleted();

    }
}
