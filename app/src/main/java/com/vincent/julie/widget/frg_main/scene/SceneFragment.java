package com.vincent.julie.widget.frg_main.scene;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincent.julie.R;
import com.vincent.julie.adapter.SceneAdapter;
import com.vincent.julie.base.AppConfig;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.bean.SceneBean;
import com.vincent.mylibrary.entity.EventMsg;
import com.vincent.mylibrary.util.EventUtil;
import com.vincent.mylibrary.view.SpaceItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main.data
 * @class describe
 * @date 2018/3/25 20:36
 */

public class SceneFragment extends BaseFragment implements ISceneView {

    @BindView(R.id.rlv_secne_list)
    RecyclerView recycleView;
    Unbinder unbinder;
    private View view;

    private List<SceneBean> data = new ArrayList<>();
    private SceneAdapter adapter;
    private IScenePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frg_layout_secne, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        initRecycleView();
        presenter = new ScenePresenterImpl(this);
        presenter.initSceneData();
        EventUtil.register(this);
        return view;
    }



    private void initRecycleView() {
        //初始化gradLayoutManager对象，并设置横向item数量为2
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recycleView.setLayoutManager(gridLayoutManager);
        adapter = new SceneAdapter(getContext());
        //给adapter绑定数据
        adapter.setData(data);
        //给RecycleView设置适配器
        recycleView.setAdapter(adapter);
        //添加Item之间的间距
        recycleView.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshView(EventMsg eventMsg){
        switch (eventMsg.msgType){
            case AppConfig.EVENTMSG_ADD_SCENE:
                toastMsg("开发中..");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventUtil.unregister(this);
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void refreshScene(List<SceneBean> data) {
        adapter.setData(data);
    }
}
