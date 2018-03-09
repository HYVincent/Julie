package com.vincent.mylibrary.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vincent.mylibrary.R;
import com.vincent.mylibrary.util.EditTextUtils;


/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog.simple
 * @class describe
 * @date 2018/2/6 14:44
 */

public class InputContentCenterDialog extends BaseDialog {

    private TextView tvTitle,tvCancel,tvGo;
    private EditText etInput;
    private String strTitle,strCancel,strGo,strHintText;
    private InputContentDialogListener inputContentDialogListener;
    //是否判断内容为空
    private boolean isCheckNull = false;
    private Context mContext;
    private static final String TAG = InputContentCenterDialog.class.getSimpleName();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x11:
                    EditTextUtils.openKeyboard(mContext,etInput);
                    break;
                case 0x12:
                    EditTextUtils.closeKeyboard((Activity) mContext);
                    break;
                default:break;
            }
        }
    };

    public InputContentCenterDialog setCheckNull(boolean checkNull) {
        isCheckNull = checkNull;
        return this;
    }

    public InputContentCenterDialog setInputContentDialogListener(InputContentDialogListener inputContentDialogListener) {
        this.inputContentDialogListener = inputContentDialogListener;
        return this;
    }

    public InputContentCenterDialog setStrTitle(String strTitle) {
        this.strTitle = strTitle;
        return this;
    }

    public InputContentCenterDialog setStrCancel(String strCancel) {
        this.strCancel = strCancel;
        return this;
    }

    public InputContentCenterDialog setStrGo(String strGo) {
        this.strGo = strGo;
        return this;
    }

    public InputContentCenterDialog setStrHintText(String strHintText) {
        this.strHintText = strHintText;
        return this;
    }

    public InputContentCenterDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public void show() {
        super.show();
        etInput.setText(null);
        Message message = Message.obtain();
        message.what = 0x11;
        handler.sendMessageDelayed(message,100);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Message message = Message.obtain();
        message.what = 0x12;
        handler.sendMessageDelayed(message,100);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_input_content_center);
        setShowBottom(false);
        tvTitle = findViewById(R.id.library_input_content_center_title);
        tvCancel = findViewById(R.id.library_input_content_center_tv_cancel);
        tvGo = findViewById(R.id.library_input_content_center_tv_action);
        etInput = findViewById(R.id.library_input_content_center_et);
        tvTitle.setText(strTitle);
        tvCancel.setText(strCancel);
        tvGo.setText(strGo);
        etInput.setHint(strHintText);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputContentDialogListener != null){
                    String content = etInput.getText().toString().trim();
                    if(isCheckNull && TextUtils.isEmpty(content)){
                        inputContentDialogListener.onConentNull();
                    }else {
                        inputContentDialogListener.onClick(content);
                        dismiss();
                    }
                }else {
                    Log.e(TAG, "onClick: please setting InputContentCenterDialog's setInputContentDialogListener method" );
                }
            }
        });
    }


    public interface InputContentDialogListener{

        /**
         * 获取内容
         * @param content
         */
        void onClick(String content);

        /**
         * 内容为空
         */
        void onConentNull();
    }

}
