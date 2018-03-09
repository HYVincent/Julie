package com.vincent.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vincent.mylibrary.R;


/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary.dialog
 * @class describe
 * @date 2017/12/8 14:36
 */


public class LoadingDialog extends BaseDialog {

    private static final String TAG = LoadingDialog.class.getSimpleName();

    private ProgressBar progressBar;
    private TextView tvMsg;
    private String strMsg;

    public LoadingDialog(Context mContext) {
        super(mContext,R.style.MyDialogBgIsWhite);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_loading);
        //下面的参数设置也可以在别的地方设置
        setOutCancel(false);
        setMargin(0);
        setWidth(-1);
        setDimAmount(0);
        initWidget();
        setShowBottom(false);
    }


    public void initWidget() {
        progressBar = findViewById(R.id.library_loading_progressBar);
        tvMsg = findViewById(R.id.library_loading_text);
        tvMsg.setText(strMsg);
    }

    public LoadingDialog setMsg(String strMsg) {
        this.strMsg = strMsg;
        return this;
    }


}
