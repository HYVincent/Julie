package com.vincent.julie.widget.weather_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.view.WeatherView;
import com.vincent.mylibrary.more_language.AppTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.weather_info
 * @class describe
 * @date 2018/4/1 10:26
 */
public class WeatherInfoActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.weather_info_view)
    WeatherView weatherInfoView;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, WeatherInfoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.weather_info_text_title));
        weatherInfoView.setData(initData());
    }

    private List<Float> initData() {
        List<Float> data = new ArrayList<>();
        data.add(-12f);
        data.add(13.4f);
        data.add(16.4f);
        data.add(18.4f);
        data.add(23.4f);
        data.add(19.4f);
        data.add(11.4f);
        data.add(18.4f);
        data.add(12.4f);
        data.add(15.4f);
        data.add(10.4f);
        data.add(11.4f);
        data.add(18.4f);
        data.add(10.4f);
        data.add(12.4f);
        data.add(19.4f);
        data.add(15.4f);
        data.add(18.4f);
        data.add(12.4f);
        data.add(-20.4f);
        data.add(-12.4f);
        data.add(13.4f);
        data.add(18.4f);
        data.add(13.4f);
        return data;
    }

    @OnClick(R.id.include_titie_ll_left)
    public void onViewClicked() {
        finish();
    }
}
