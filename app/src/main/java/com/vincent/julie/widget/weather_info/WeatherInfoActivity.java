package com.vincent.julie.widget.weather_info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.base.AppConfig;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.view.WeatherView;
import com.vincent.mylibrary.MyLibrary;
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
    @BindView(R.id.weather_info_tv_setting)
    TextView weatherInfoTvSetting;

    private float settingValue = 0f;

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
        settingValue = MyLibrary.getSpUtil().getFloat(AppConfig.SHARED_WEATHER_SETTING,25.0f);
        weatherInfoTvSetting.setText(String.valueOf(settingValue)+"℃");
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

    @OnClick({R.id.include_titie_ll_left,R.id.weather_info_iv_minus,R.id.weather_info_iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.include_titie_ll_left:
                finish();
                break;
            case R.id.weather_info_iv_add:
                settingValue++;
                if(settingValue >60){
                    settingValue = 60;
                    toastMsg("温度最大值为60℃");
                }
                weatherInfoTvSetting.setText(String.valueOf(settingValue)+"℃");
                MyLibrary.getSpUtil().putFloat(AppConfig.SHARED_WEATHER_SETTING,settingValue);
                break;
            case R.id.weather_info_iv_minus:
                settingValue--;
                if(settingValue < - 20){
                    settingValue = -20;
                    toastMsg("温度最小值为-20℃");
                }
                weatherInfoTvSetting.setText(String.valueOf(settingValue)+"℃");
                MyLibrary.getSpUtil().putFloat(AppConfig.SHARED_WEATHER_SETTING,settingValue);
                break;
        }
    }
}
