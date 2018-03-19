package com.vincent.julie.widget.frg_device_manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseFragment;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main
 * @class describe 安全
 * @date 2018/3/10 23:45
 */

public class DeviceManagerFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.frg_layout_device_manager,container,false);
        }
        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
