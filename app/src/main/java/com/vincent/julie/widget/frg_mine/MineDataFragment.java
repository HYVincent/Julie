package com.vincent.julie.widget.frg_mine;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vincent.julie.BuildConfig;
import com.vincent.julie.R;
import com.vincent.julie.base.AppConfig;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.base.MyApp;
import com.vincent.julie.bean.VersionBean;
import com.vincent.julie.view.VersionUpgradeDialog;
import com.vincent.julie.widget.help.HelpFeedBackActivity;
import com.vincent.julie.widget.login.LoginActivity;
import com.vincent.julie.widget.setting.SettingActivity;
import com.vincent.julie.widget.system_msg.SystemMsgActivity;
import com.vincent.mylibrary.MyLibrary;
import com.vincent.mylibrary.dialog.OrdinaryMsgDialog;
import com.vincent.mylibrary.util.apk_down.DownApkUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.query_data
 * @class describe
 * @date 2018/3/15 21:36
 */

public class MineDataFragment extends BaseFragment implements IFrgMineView, EasyPermissions.PermissionCallbacks {
    private static final String TAG = MineDataFragment.class.getSimpleName();
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
    @BindView(R.id.frg_mine_tv_new_version)
    TextView frgMineTvNewVersion;
    @BindView(R.id.frg_mine_rl_system_msg)
    RelativeLayout frgMineRlSystemMsg;
    @BindView(R.id.frg_mine_tv_account)
    TextView tvAccount;
    @BindView(R.id.frg_mine_tv_current_version)
    TextView frgMineTvCurrentVersion;
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
        tvAccount.setText(getString(R.string.frg_mine_text_account)+ MyApp.user.getUser_phone());
        presenter = new FrgMinePresenterImpl(this);
        DownApkUtil.getInstance().init(getActivity());
        if (MyLibrary.getSpUtil().getBoolean(AppConfig.EVENTMSG_NEW_VERSION, false)) {
            frgMineTvNewVersion.setVisibility(View.VISIBLE);
        }
        frgMineTvCurrentVersion.setText("V"+BuildConfig.VERSION_NAME);
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
        DownApkUtil.getInstance().unRegister(getActivity());
    }

    @OnClick({R.id.frg_mine_rl_setting, R.id.frg_mine_rl_common,R.id.frg_mine_rl_system_msg,
            R.id.frg_mine_rl_app_upgrade, R.id.frg_mine_rl_help, R.id.frg_mine_rl_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frg_mine_rl_setting:
                SettingActivity.actionStart(getActivity());
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
                                MyLibrary.getSpUtil().putString(AppConfig.SHARED_ACCOUNT,"");
                                MyLibrary.getSpUtil().putString(AppConfig.SHARED_PASSWORD,"");
                                getActivity().finish();
                                LoginActivity.actionStart(getActivity());
                            }
                        })
                        .show();
                break;
            case R.id.frg_mine_rl_system_msg:
                SystemMsgActivity.actionStart(getActivity());
                break;
            case R.id.frg_mine_rl_app_upgrade:
                presenter.checkAppUpgrade(getContext(), BuildConfig.VERSION_CODE);
                break;
            default:
                break;
        }
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!MyLibrary.getSpUtil().getBoolean(AppConfig.EVENTMSG_NEW_VERSION, false)) {
            frgMineTvNewVersion.setVisibility(View.GONE);
        }
    }

    /**
     * apk下载的完整地址
     */
    private String fullUpgradeUrl;

    @Override
    public void newVersion(final VersionBean versionBean) {
        String[] strings = versionBean.getVersion_desc().split(",");
        List<String> data = Arrays.asList(strings);
        VersionUpgradeDialog upgradeDialog = new VersionUpgradeDialog(getActivity(), versionBean.isIs_must_upgrade(), versionBean.getVersion_dialog_title(), data);
        upgradeDialog.setOutCancel(!versionBean.isIs_must_upgrade());
        upgradeDialog.setCanceledOnTouchOutside(!versionBean.isIs_must_upgrade());
        upgradeDialog.setUpgradeClickListener(new VersionUpgradeDialog.UpgradeClickListener() {
            @Override
            public void onUpgrade() {
                fullUpgradeUrl = AppConfig.SERVICE_FILE_ADDRESS + versionBean.getVersion_file_path();
                callDownTask();
            }
        }).show();
    }

    private void downApk() {
        Log.d(TAG, "downApk: " + fullUpgradeUrl);
        DownApkUtil.getInstance().downApk(fullUpgradeUrl, R.mipmap.icon_app_logo, "正在下载我的智家升级包..");
    }

    private static final int PERMISSION_CAODE_DOWN = 101;
    private static final String[] PERMISSIONS = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @AfterPermissionGranted(PERMISSION_CAODE_DOWN)
    public void callDownTask() {
        if (EasyPermissions.hasPermissions(getContext(), PERMISSIONS)) {
            downApk();
        } else {
            EasyPermissions.requestPermissions(this,//上下文对象，一般为Activity或者Fragment
                    getString(R.string.toast_msg_permission_fail), //用户拒绝权限的提示信息
                    PERMISSION_CAODE_DOWN,//权限申请代码
                    PERMISSIONS);//需要申请的权限列表
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        downApk();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (requestCode == PERMISSION_CAODE_DOWN) {
            toastMsg(getString(R.string.toast_msg_permission_fail));
        }
    }

}
