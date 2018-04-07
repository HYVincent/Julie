package com.vincent.julie.util;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.loader.ImageLoader;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.util
 * @class describe
 * @date 2018/4/6 17:22
 */
public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestOptions requestOptions = RequestOptions.centerCropTransform();
        requestOptions.override(width,height);
        Glide.with(activity).applyDefaultRequestOptions(requestOptions).load(path).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestOptions requestOptions = RequestOptions.centerCropTransform();
        requestOptions.override(width,height);
        Glide.with(activity).applyDefaultRequestOptions(requestOptions).load(path).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
