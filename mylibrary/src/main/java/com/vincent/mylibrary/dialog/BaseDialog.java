package com.vincent.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.vincent.mylibrary.R;
import com.vincent.mylibrary.util.DpUtil;
import com.vincent.mylibrary.util.ScreenUtils;


/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name StarKangMedical
 * @page com.vincent.mylibrary.dialog
 * @class describe
 * @date 2017/12/8 14:24
 */

public class BaseDialog extends Dialog {

    private static final String TAG = BaseDialog.class.getSimpleName();

    private int margin;//左右边距
    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private boolean showBottom = false;//是否底部显示
    private boolean outCancel = true;//是否点击外部取消

    @StyleRes
    private int animStyle;


    public BaseDialog(@NonNull Context context) {
        super(context, R.style.MyDialogBgIsTransparent);
    }


    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initParams();
    }

    private void initParams() {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if(width == 0){
//            width = ScreenUtils.getScreenWidth(getContext());
            width = ScreenUtils.getScreenWidth(getContext()) - 2 * DpUtil.dp2px(getContext(), margin);
        }
        if(height == 0){
            height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        if(showBottom){
            layoutParams.gravity = Gravity.BOTTOM;
        }else {
            layoutParams.gravity = Gravity.CENTER;
        }
        layoutParams.height = height;
        layoutParams.width = width;
        layoutParams.dimAmount = dimAmount;
        //设置dialog进入、退出的动画
        window.setWindowAnimations(animStyle);
        window.setAttributes(layoutParams);
        setCanceledOnTouchOutside(outCancel);
    }

    public BaseDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    @Override
    public void show() {
        super.show();
    }

    /**
     * 设置背景变暗程度 设置为0的时候背景不变  0.5 半透明
     * @param dimAmount
     * @return
     */
    public void setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
    }

    public void setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;

    }

    public void setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
    }

    public void setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
    }



}
