package com.vincent.julie.widget.frg_device_manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.vincent.julie.R;
import com.vincent.julie.adapter.DeviceListAdapter;
import com.vincent.julie.adapter.DeviceListBean;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.bean.DeviceBean;
import com.vincent.julie.widget.add_device.AddDeviceActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main
 * @class describe 安全
 * @date 2018/3/10 23:45
 */

public class DeviceManagerFragment extends BaseFragment implements IDeviceManagerView{

    @BindView(R.id.include_titie_ll_left)
    LinearLayout includeTitieLlLeft;
    @BindView(R.id.include_layout_title)
    TextView includeLayoutTitle;
    @BindView(R.id.include_title_right_iv)
    ImageView includeTitleRightIv;
    @BindView(R.id.include_title_right_ll)
    LinearLayout includeTitleRightLl;
    @BindView(R.id.frg_device_manager_rlv)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private View view;

    private IDeviceManagerPresenter presenter;
    private static final String TAG = DeviceManagerFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frg_layout_device_manager, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        includeLayoutTitle.setText(getString(R.string.frg_device_manager_title));
        includeTitieLlLeft.setVisibility(View.GONE);
        includeTitleRightIv.setImageDrawable(ContextCompat.getDrawable(getContext(),R.mipmap.commin_icon_add_white));
        initRecycleView();
        presenter = new DeviceManagerPresenterImpl(this);
        presenter.getDeviceList();
        return view;
    }

    private List<DeviceListBean> data = new ArrayList<>();
    private DeviceListAdapter adapter;

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DeviceListAdapter(getContext());
        adapter.setData(data);
        adapter.setOnItemClickListener(new DeviceListAdapter.DeviceItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                
            }
        });
        recyclerView.setAdapter(adapter);
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

    @OnClick(R.id.include_title_right_ll)
    public void onViewClicked() {
        //TODO 添加家电控制
        AddDeviceActivity.actionStart(getActivity());
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void refresh(List<DeviceListBean> data) {
        Log.d(TAG, "refresh: 刷新数据.....");
        adapter.setData(data);
    }
}
