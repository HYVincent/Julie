package com.vincent.mylibrary.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * @name MyUtils
 * @class name：com.vincent.library.util
 * @class describe 封装图片加载库 方便替换或者加载各种类型的图片
 * @author Vincent QQ:1032006226
 * @time 2017/9/1 9:54
 * @change
 * @chang time
 * @class describe
 */

public class ImageLoadingUtils {

    /**
     * 图片加载
     * @param context
     * @param imageView
     */
    public static void loadingImg(Context context, Object o, ImageView imageView){
        Glide.with(context)
                .load(o)
                .into(imageView);
    }

    /**
     * 图片加载
     * @param context
     * @param imageView
     */
    public static void loadingImg(Context context, Object o, ImageView imageView,int width,int height){


        Glide.with(context)
                .load(o)
                .into(imageView);
    }

//    public static void loadingImg(Context context,Object o,int)


    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(Context context, final Object imageUrl, int errorImageId, final ImageView imageView) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        requestOptions.format(DecodeFormat.PREFER_ARGB_8888);
        if(errorImageId != 0){
            requestOptions.error(errorImageId);
            requestOptions.placeholder(errorImageId);
        }


        Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .into(imageView);
    }





    /**
     * 设置图片的闪烁效果
     * @param iv_chat_head
     */
    public static void setFlickerAnimation(ImageView iv_chat_head) {
        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);//闪烁时间间隔
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
//        animation.setRepeatCount(Animation.INFINITE);//无限模式
        animation.setRepeatCount(Animation.ZORDER_NORMAL);
        animation.setRepeatMode(Animation.REVERSE);
        iv_chat_head.setAnimation(animation);
        iv_chat_head.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.cancel();
            }
        },3000);
    }

}
