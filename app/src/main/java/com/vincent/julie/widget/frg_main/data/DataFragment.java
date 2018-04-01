package com.vincent.julie.widget.frg_main.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.widget.environmental_parameter.EnvironmentalParameterActivity;
import com.vincent.julie.widget.household_electricity.HouseholdElectricityActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main.data
 * @class describe
 * @date 2018/3/25 20:36
 */

public class DataFragment extends BaseFragment {

    @BindView(R.id.frg_data_electro_rl)
    RelativeLayout frgDataElectroRl;
    @BindView(R.id.frg_data_prarams_rl)
    RelativeLayout frgDataPraramsRl;
    @BindView(R.id.frg_data_alarm_rl)
    RelativeLayout frgDataAlarmRl;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frg_layout_data, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.frg_data_electro_rl, R.id.frg_data_prarams_rl, R.id.frg_data_alarm_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frg_data_electro_rl:
                HouseholdElectricityActivity.actionStart(getActivity());
                break;
            case R.id.frg_data_prarams_rl:
                EnvironmentalParameterActivity.actionStart(getActivity());
                break;
            case R.id.frg_data_alarm_rl:
                break;
            default:break;
        }
    }
}
