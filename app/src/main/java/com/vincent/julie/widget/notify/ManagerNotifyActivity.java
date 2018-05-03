package com.vincent.julie.widget.notify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.adapter.ManagerNotifyAdapter;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.bean.ManagerNotifyBean;
import com.vincent.mylibrary.more_language.AppTextView;
import com.vincent.mylibrary.util.ActivityUtils;
import com.vincent.mylibrary.util.DateUtils;
import com.vincent.mylibrary.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.notify
 * @class describe
 * @date 2018/5/3 23:10
 */

public class ManagerNotifyActivity extends BaseActivity {

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    AppTextView includeLayoutTitle;
    @BindView(R.id.manager_notify_rlv)
    RecyclerView recycleView;

    public static void actionStart(Activity activity){
        activity.startActivity(new Intent(activity,ManagerNotifyActivity.class));
    }

    private List<ManagerNotifyBean> data = new ArrayList<>();
    private ManagerNotifyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_notify);
        ButterKnife.bind(this);
        includeLayoutTitle.setText("物业通知");
        initRecycleView();
        initData();
    }

    private void initData() {
        ManagerNotifyBean bean1 = new ManagerNotifyBean();
        bean1.setDesc("2018年5月份物业管理费20元");
        bean1.setNotifyTime((System.currentTimeMillis()- DateUtils.day*2));
        bean1.setNotifyType(1);
        bean1.setTitle("物业费");
        data.add(bean1);
        adapter.setData(data);
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);
        adapter = new ManagerNotifyAdapter(this);
        adapter.setData(data);
        recycleView.addItemDecoration(new SpaceItemDecoration(20));
        recycleView.setAdapter(adapter);
    }

    @OnClick(R.id.include_titie_ll_left)
    public void onViewClicked() {
        finish();
    }
}
