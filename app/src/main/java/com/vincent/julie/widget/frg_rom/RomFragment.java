package com.vincent.julie.widget.frg_rom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vincent.julie.R;
import com.vincent.julie.adapter.RoomAdapter;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.base.RoomBean;
import com.vincent.julie.view.EmptyRecyclerView;
import com.vincent.julie.view.GlideImageLoader;
import com.vincent.mylibrary.dialog.InputContentCenterDialog;
import com.youth.banner.Banner;

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

public class RomFragment extends BaseFragment {

    @BindView(R.id.frg_rom_banner)
    Banner banner;
    @BindView(R.id.frg_room_list)
    EmptyRecyclerView recyclerView;
    @BindView(R.id.frg_room_add)
    LinearLayout frgRoomAdd;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frg_layout_rom, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        initBanner();
        initRecycleView();
        return view;
    }

    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.rom_icon_default);
        banner.setDelayTime(10 * 1000);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    private List<RoomBean> data = new ArrayList<>();
    private RoomAdapter adapter;

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RoomAdapter(getContext());
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_recycleview_empty_view,null,false);
        recyclerView.setEmptyView(view);
        adapter.setItemOnClickListener(new RoomAdapter.RoomOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                toastMsg(data.get(position).getRoomName());
            }
        });
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

    @OnClick(R.id.frg_room_add)
    public void onViewClicked() {
        InputContentCenterDialog inputContentCenterDialog = new InputContentCenterDialog(getContext());
        inputContentCenterDialog.setStrTitle(getString(R.string.frg_room_text_tag))
                .setInputContentDialogListener(new InputContentCenterDialog.InputContentDialogListener() {
                    @Override
                    public void onClick(String content) {
                        RoomBean roomBean = new RoomBean();
                        roomBean.setRoomName(content);
                        data.add(roomBean);
                        adapter.setData(data);
                    }

                    @Override
                    public void onConentNull() {

                    }
                }).setCheckNull(true)
                .setStrHintText(getString(R.string.frg_room_text_tag_not_null))
                .show();
    }
}
