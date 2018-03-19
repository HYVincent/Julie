package com.vincent.julie.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.jaeger.library.StatusBarUtil;
import com.vincent.julie.R;
import com.vincent.mylibrary.dialog.LoadingDialog;
import com.vincent.mylibrary.util.ActivityUtils;
import com.vincent.mylibrary.util.MainHandler;
import com.vincent.mylibrary.util.MyToastUtil;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @project_name StarKangMedical
 * @page_name：com.toncentsoft.starkangmedical.base
 * @class describe
 * @date 2017/12/8 10:27
 */

public class BaseActivity extends AppCompatActivity {

    private LoadingDialog loadingDialog = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>21){
            StatusBarUtil.setColor(BaseActivity.this,
                    ContextCompat.getColor(BaseActivity.this, R.color.color_blue_63d8ff),0);
        }
        ActivityUtils.addActivity(this);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoadingDialog();
        ActivityUtils.removeActivity(this);
    }

    /**
     * 显示加载效果
     * @param msg
     */
    @UiThread
    public void showLoadingDialog(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog == null){
                    loadingDialog = new LoadingDialog(BaseActivity.this);
                }
                loadingDialog.setMsg(msg);
                loadingDialog.show();
            }
        });
    }

    /**
     * 关闭加载效果
     */
    public void hideLoadingDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog != null && loadingDialog.isShowing()){
                    Log.d("loading dialog", "hideLoadingDialog: close loading dialog");
                    loadingDialog.dismiss();
                }else {
                    Log.d("loading dialog", "hideLoadingDialog: loading dialog is null");
                }
            }
        });
    }

    /**
     * 自定义Toast实现
     * @param msg
     */
    @UiThread
    public void toastMsg(final String msg){
        if(TextUtils.isEmpty(msg)){
            Log.d("toast", "toastMsg: msg is null");
            return;
        }
        Log.d("toast", "toastMsg: msg = "+msg);
        //写在这里比较好，不容易出错 如果在show()方法里面写某些时候会导致app异常
       MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                MyToastUtil.showMsg(msg);
            }
        });
    }

}
