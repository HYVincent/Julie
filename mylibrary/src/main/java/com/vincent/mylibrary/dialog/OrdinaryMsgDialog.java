package com.vincent.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.vincent.mylibrary.R;


/**
 * @author Vincent QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog
 * @class describe
 * @date 2017/12/26 10:12
 */

public class OrdinaryMsgDialog extends BaseDialog{

    private TextView tvTitle,tvContent,tvCancel,tvOk;
    private String strTitle,strContent,strCancel = "取消",strOk = "确定 ";
    private OnActionClickListener onActionClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_ordinary_msg);
        tvTitle = findViewById(R.id.library_ordinary_msg_tv_title);
        tvTitle.setText(strTitle);
        tvContent = findViewById(R.id.library_ordinary_msg_tv_content);
        tvContent.setText(strContent);
        tvCancel = findViewById(R.id.library_ordinary_msg_tv_cancel);
        tvCancel.setText(strCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvOk = findViewById(R.id.library_ordinary_msg_tv_ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onActionClickListener.doAction();
                dismiss();
            }
        });
        tvOk.setText(strOk);
        setMargin(12);
        setOutCancel(false);
    }

    public OrdinaryMsgDialog(@NonNull Context context) {
        super(context);
    }

    public OrdinaryMsgDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public OrdinaryMsgDialog setStrTitle(String strTitle) {
        this.strTitle = strTitle;
        return this;
    }

    public OrdinaryMsgDialog setStrContent(String strContent) {
        this.strContent = strContent;
        return this;
    }

    public OrdinaryMsgDialog setStrCancel(String strCancel) {
        this.strCancel = strCancel;
        return this;
    }

    public OrdinaryMsgDialog setStrOk(String strOk) {
        this.strOk = strOk;
        return this;
    }

    public OrdinaryMsgDialog setOnActionClickListener(OnActionClickListener onActionClickListener) {
        this.onActionClickListener = onActionClickListener;
        return this;
    }

    public interface OnActionClickListener{
        void doAction();
    }

}
