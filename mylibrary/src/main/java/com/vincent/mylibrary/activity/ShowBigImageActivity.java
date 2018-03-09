package com.vincent.mylibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.elvishew.xlog.XLog;
import com.jaeger.library.StatusBarUtil;
import com.vincent.mylibrary.R;
import com.vincent.mylibrary.adapter.CommonItemOnClickListener;
import com.vincent.mylibrary.adapter.ImgLoadingAdapter;
import com.vincent.mylibrary.view.WrapContentLinearLayoutManager;

import java.util.List;


/**
 * @author Vincent QQ:1032006226
 * @name KangDaEr
 * @class name：com.toncentsoft.kangdaer.ui.activity
 * @class describe 显示一张大图
 * @time 2017/10/26 9:53
 * @change
 * @chang time
 * @class describe
 */

public class ShowBigImageActivity extends AppCompatActivity {

    RecyclerView rlv;
    private List<String> data;
    private ImgLoadingAdapter adapter;


    public static void actionStart(Activity activity, String imgPath) {
        Intent intent = new Intent(activity, ShowBigImageActivity.class);
        intent.putExtra("imgPath", imgPath);
        activity.startActivity(intent);
    }

    public static void actionStart(Activity activity, List<String> data) {
        Intent intent = new Intent(activity, ShowBigImageActivity.class);
        intent.putExtra("data", JSONArray.toJSONString(data));
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big_img);
        if(Build.VERSION.SDK_INT>21){
            StatusBarUtil.setColor(this,
                    ContextCompat.getColor(this, R.color.color_black_semitransparent),0);
        }
        rlv = (RecyclerView) findViewById(R.id.textView28);
        data = JSONArray.parseArray(getIntent().getStringExtra("data"), String.class);
        XLog.d("data:" + JSONArray.toJSONString(data));
        adapter = new ImgLoadingAdapter(this);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlv.setLayoutManager(linearLayoutManager);
        adapter.setData(data);
        rlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new CommonItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                finish();
            }
        });
//        ImageLoadingUtils.loadingImg(this, getIntent().getStringExtra("imgPath"), imageView4);
//        ImageLoadingUtils.loadIntoUseFitWidth(this,getIntent().getStringExtra("imgPath"),0,imageView4);
    }
}
