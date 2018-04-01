package com.vincent.julie.widget.environmental_parameter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.adapter.EvironmentalParameterAdapter;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.bean.EvironmentalParameterBean;
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
 * @page com.vincent.julie.widget.environmental_parameter
 * @class describe
 * @date 2018/3/31 13:00
 */
public class EnvironmentalParameterActivity extends BaseActivity implements IEnvironmentalParameterView {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.include_layout_title_cl)
    ConstraintLayout includeLayoutTitleCl;
    @BindView(R.id.environmental_parameter_rlv)
    RecyclerView recycleView;

    private List<EvironmentalParameterBean> data = new ArrayList<>();
    private EvironmentalParameterAdapter adapter;
    private IEnvironmentalParameterPresenter presenter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, EnvironmentalParameterActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envionmental_parameter);
        ButterKnife.bind(this);
        includeLayoutTitle.setText(getString(R.string.envionmental_parameter_text_title));
        initRecycleView();
        presenter = new EnvironmentalParameterPresenterImpl(this);
        presenter.init();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EnvironmentalParameterActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);
        adapter = new EvironmentalParameterAdapter(EnvironmentalParameterActivity.this);
        adapter.setData(data);
        recycleView.setAdapter(adapter);
    }

    @Override
    public void refreshData(List<EvironmentalParameterBean> data) {
        adapter.setData(data);
    }

    @Override
    public void loadFail() {

    }

    @OnClick(R.id.include_titie_ll_left)
    public void onViewClicked() {
        finish();
    }
}
