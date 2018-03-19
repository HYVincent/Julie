package com.vincent.julie.widget.frg_info_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincent.julie.R;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.base.MyApp;
import com.vincent.julie.netty.NettyPush;
import com.vincent.julie.netty.msg.MsgType;
import com.vincent.julie.netty.msg.PushMsg;
import com.vincent.julie.service.MyNettyPushService;
import com.vincent.mylibrary.util.ExitAppUtils;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_main
 * @class describe
 * @date 2018/3/10 23:45
 */

public class MainFragment extends BaseFragment {

    private View view;
    private static final String TAG = MainFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.frg_layout_main,container,false);
        }
        getActivity().startService(new Intent(getActivity(), MyNettyPushService.class));
        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PushMsg pushMsg = new PushMsg();
                pushMsg.setPhoneNum(MyApp.user.getUser_phone());
                pushMsg.setType(MsgType.PUSH);
                pushMsg.setData("我来了");
                NettyPush.getInstance().senMsg(pushMsg);
            }
        });
        return view;
    }

    @Override
    public boolean onBackPressed() {
        /*ExitAppUtils.isQuit(getContext(), getString(R.string.toast_msg_app_exit_hint_msg), new ExitAppUtils.ExitAppListener() {
            @Override
            public void removeAllActivity() {
                getActivity().stopService(new Intent(getContext(),MyNettyPushService.class));
                getActivity().finish();
            }
        });*/
        return false;
    }
}
