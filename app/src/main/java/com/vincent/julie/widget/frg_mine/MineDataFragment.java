package com.vincent.julie.widget.frg_mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vincent.julie.BuildConfig;
import com.vincent.julie.R;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.bean.VersionBean;
import com.vincent.julie.widget.help.HelpFeedBackActivity;
import com.vincent.julie.widget.login.LoginActivity;
import com.vincent.mylibrary.dialog.OrdinaryMsgDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.query_data
 * @class describe
 * @date 2018/3/15 21:36
 */

public class MineDataFragment extends BaseFragment implements IFrgMineView{

    @BindView(R.id.frg_mine_rl_setting)
    RelativeLayout frgMineRlSetting;
    @BindView(R.id.frg_mine_rl_common)
    RelativeLayout frgMineRlCommon;
    @BindView(R.id.frg_mine_rl_help)
    RelativeLayout frgMineRlHelp;
    @BindView(R.id.frg_mine_rl_exit)
    RelativeLayout frgMineRlExit;
    @BindView(R.id.frg_mine_rl_app_upgrade)
    RelativeLayout frgMineRlUpdate;
    Unbinder unbinder;
    private View view;

    private IFrgMinePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frg_layout_mine, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        presenter = new FrgMinePresenterImpl(this);
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

    @OnClick({R.id.frg_mine_rl_setting, R.id.frg_mine_rl_common,
            R.id.frg_mine_rl_app_upgrade,R.id.frg_mine_rl_help, R.id.frg_mine_rl_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frg_mine_rl_setting:
                break;
            case R.id.frg_mine_rl_common:
                break;
            case R.id.frg_mine_rl_help:
                HelpFeedBackActivity.actionStart(getActivity());
                break;
            case R.id.frg_mine_rl_exit:
                OrdinaryMsgDialog ordinaryMsgDialog = new OrdinaryMsgDialog(getContext());
                ordinaryMsgDialog.setMargin(30);
                ordinaryMsgDialog.setStrCancel("取消")
                        .setStrContent("是否退出登陆?")
                        .setStrOk("去意已决")
                        .setStrTitle("提示")
                        .setOnActionClickListener(new OrdinaryMsgDialog.OnActionClickListener() {
                            @Override
                            public void doAction() {
                                getActivity().finish();
                                LoginActivity.actionStart(getActivity());
                            }
                        })
                        .show();
                break;
            case R.id.frg_mine_rl_app_upgrade:
                presenter.checkAppUpgrade(getContext(), BuildConfig.VERSION_CODE);
                Log.d("版本号", "onViewClicked: "+BuildConfig.VERSION_CODE);
                break;
            default:break;
        }
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void newVersion(VersionBean versionBean) {

    }
}
