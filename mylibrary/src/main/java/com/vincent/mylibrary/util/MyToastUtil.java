package com.vincent.mylibrary.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vincent.mylibrary.MyLibrary;
import com.vincent.mylibrary.R;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @project_name StarKangMedical
 * @page_name com.vincent.mylibrary.util
 * @class describe 自定义Toast封装类
 * @date 2017/12/8 12:51
 */


public class MyToastUtil {

    private static MyToast toast;
    private static MyToastUtil myToastUtil;

    private MyToastUtil(String msg,int imgId){
        if(toast == null){
            toast = new MyToast(msg,imgId);
        }
    }


    /**
     * 显示文字
     * @param msg
     */
    public static void showMsg(String msg){
        if(myToastUtil != null){
            toast.hideTvMsg(false);
            toast.hideImg(true);
            toast.resetMsg(msg);
        }else {
            myToastUtil = new MyToastUtil(msg,0);
        }
    }

    /**
     * 只显示图片
     * @param imgId
     */
    public static void showMsg(int imgId){
        if(myToastUtil != null){
            toast.hideTvMsg(true);
            toast.resetImg(imgId);
        }else {
            myToastUtil = new MyToastUtil("",imgId);
        }
    }

    /**
     * 图文都有
     * @param msg
     * @param imgId
     */
    public static void showMsg(String msg,int imgId){
        if(myToastUtil != null){
            toast.hideImg(false);
            toast.hideTvMsg(false);
            toast.resetMsg(msg);
            toast.resetImg(imgId);
        }else {
            myToastUtil = new MyToastUtil(msg,imgId);
        }
    }


    class MyToast extends Toast{

        private  ImageView ivImg;
        private  TextView tvMsg;
        private View view;
        private Context mContext;

        /**
         * Construct an empty Toast object.  You must call {@link #setView} before you
         * can call {@link #show}.
         *
         * //@param context The context to use.  Usually your {@link Application}
         *                or {@link Activity} object.
         */
        public MyToast(String msg,int imgId) {
            super(MyLibrary.getmContext());
            mContext = MyLibrary.getmContext();
            view = LayoutInflater.from(MyLibrary.getmContext()).inflate(R.layout.library_layout_toast, null);
            ivImg = view.findViewById(R.id.toast_iv_icon);
            tvMsg = view.findViewById(R.id.toast_tv_msg);
            tvMsg.setText(msg);
            if(imgId != 0){
                ivImg.setImageDrawable(ContextCompat.getDrawable(MyLibrary.getmContext(),imgId));
                ivImg.setVisibility(View.VISIBLE);
            }else {
                ivImg.setVisibility(View.GONE);
            }
            setView(view);
            show();
        }

        private void resetMsg(String msg){
            if(tvMsg != null){
                tvMsg.setText(msg);
            }
            hideImg(true);
            show();
        }

        private void resetImg(int imgId){
            if(ivImg != null&&imgId != 0){
                ivImg.setImageDrawable(ContextCompat.getDrawable(mContext,imgId));
                hideImg(false);
            }
            show();
        }

        /**
         * 隐藏或者显示图片
         * @param isHide
         */
        private void hideImg(boolean isHide) {
            if(ivImg != null){
                if(isHide){
                    ivImg.setVisibility(View.GONE);
                }else {
                    ivImg.setVisibility(View.VISIBLE);
                }
            }
        }

        /**
         * 隐藏或者显示图片
         * @param isHide
         */
        private void hideTvMsg(boolean isHide) {
            if(tvMsg != null){
                if(isHide){
                    tvMsg.setVisibility(View.GONE);
                }else {
                    tvMsg.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
