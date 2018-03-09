package com.vincent.mylibrary.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vincent.mylibrary.R;


/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name CustomDialog
 * @page com.vincent.dialog.simple
 * @class describe
 * @date 2018/2/1 11:15
 */


public class FileUploadProgressDialog extends BaseDialog {

    private String strTitle = "文件上传中";
    private String strCancel = "取消";
    private String strProgress = "0%";
    private int currentProgress = 0;
    private TextView tvTitle,tvCancel,tvProgress;
    private ProgressBar pbProgerss;
    private CancelUploadFileListener cancelUploadFileListener;

    public FileUploadProgressDialog setCancelUploadFileListener(CancelUploadFileListener cancelUploadFileListener) {
        this.cancelUploadFileListener = cancelUploadFileListener;
        return this;
    }

    public FileUploadProgressDialog setStrTitle(String strTitle) {
        this.strTitle = strTitle;
        return this;
    }

    public FileUploadProgressDialog setStrCancel(String strCancel) {
        this.strCancel = strCancel;
        return this;
    }

    @MainThread
    public FileUploadProgressDialog setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        strProgress = String.valueOf(currentProgress)+"%";
        if(pbProgerss!=null){
            pbProgerss.setProgress(currentProgress);
        }
        if(tvProgress != null){
            tvProgress.setText(strProgress);
        }
        return this;
    }

    public FileUploadProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_layout_file_upload);
        setMargin(15);
        tvTitle = findViewById(R.id.library_file_upload_title_tv);
        tvProgress = findViewById(R.id.library_file_upload_progress_tv);
        tvCancel = findViewById(R.id.library_file_upload_cancel_tv);
        pbProgerss = findViewById(R.id.library_file_upload_progress_pb);
        tvTitle.setText(strTitle);
        tvCancel.setText(strCancel);
        tvProgress.setText(strProgress);
        pbProgerss.setMax(100);
//        pbProgerss.setMin(0);
        pbProgerss.setProgress(currentProgress);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cancelUploadFileListener != null){
                    cancelUploadFileListener.onCancel();
                }
                dismiss();
            }
        });
    }

    public interface CancelUploadFileListener{
        void onCancel();
    }

}
