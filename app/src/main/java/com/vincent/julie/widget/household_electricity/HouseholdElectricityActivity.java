package com.vincent.julie.widget.household_electricity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.view.StatisticsView;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.CalendarUtil;
import com.vincent.mylibrary.util.FloatUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget
 * @class describe 家庭用电
 * @date 2018/3/31 0:23
 */
public class HouseholdElectricityActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.household_electricity_kwh)
    TextView householdElectricityKwh;
    @BindView(R.id.household_electricity_tv_money)
    TextView householdElectricityTvMoney;
    @BindView(R.id.household_electricity_tv_total_money)
    TextView householdElectricityTvTotalMoney;
    @BindView(R.id.household_electricity_sv)
    StatisticsView statisticsView;
    //电价 0.54/°
    private float unit = 0.54f;
    private List<Float> data = new ArrayList<>();
    private float todayValue = 0f;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, HouseholdElectricityActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_electricity);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.household_electricity_text_title));
        data.addAll(FloatUtil.getRandom(CalendarUtil.getDayOfMonth(),20));
//        data.addAll(FloatUtil.getRandom(14,20));
        statisticsView.addListData(data);
        float total = 0f;
        for (int i = 0;i<data.size();i++){
            total += data.get(i);
        }
        householdElectricityKwh.setText(FloatUtil.fomatFloatStr(data.get(data.size()-1)));
        householdElectricityTvTotalMoney.setText(FloatUtil.fomatFloatStr(total * unit));
    }
    @OnClick(R.id.include_titie_ll_left)
    public void onViewClicked() {
        finish();
    }
}
