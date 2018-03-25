package com.vincent.julie.widget.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.vincent.julie.R;
import com.vincent.julie.base.BackHandledInterface;
import com.vincent.julie.base.BaseActivity;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.service.MyNettyPushService;
import com.vincent.julie.widget.frg_device_manager.DeviceManagerFragment;
import com.vincent.julie.widget.frg_rom.RomFragment;
import com.vincent.julie.widget.frg_main.MainFragment;
import com.vincent.julie.widget.frg_mine.MineDataFragment;
import com.vincent.mylibrary.util.ActivityUtils;
import com.vincent.mylibrary.util.ExitAppUtils;
import com.vincent.mylibrary.view.CustomTabView;

public class MainActivity extends BaseActivity implements BackHandledInterface,CustomTabView.OnTabCheckListener{

    private Fragment currentFragment, fragment1,fragment2,fragment3,fragment4;
    private  CustomTabView.Tab tab1, tab2, tab3,tab4,tabCenter;
    private CustomTabView mainCtButtomMenu;
    private ImageView ivVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivVoice = findViewById(R.id.main_iv_voice);
        ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice_default));
        initButtomTab();
        initButtomView();
    }

    public static void actionStart(Activity activity){
        Intent intent = new Intent( activity,MainActivity.class);
        activity.startActivity(intent);
    }

    private void initButtomView() {
        mainCtButtomMenu = findViewById(R.id.main_ct_buttom_menu);
        tab1 = new CustomTabView.Tab().setText(getString(R.string.man_text_tab_1))
                .setColor(ContextCompat.getColor(MainActivity.this,R.color.color_gray_999999))
                .setCheckedColor(ContextCompat.getColor(MainActivity.this,R.color.color_blue_07aef5))
                .setNormalIcon(R.mipmap.main_icon_home_2)
                .setPressedIcon(R.mipmap.main_icon_home_1);
        mainCtButtomMenu.addTab(tab1);

        tab2 = new CustomTabView.Tab().setText(getString(R.string.man_text_tab_2))
                .setColor(ContextCompat.getColor(MainActivity.this,R.color.color_gray_999999))
                .setCheckedColor(ContextCompat.getColor(MainActivity.this,R.color.color_blue_07aef5))
                .setNormalIcon(R.mipmap.main_icon_data_query_default)
                .setPressedIcon(R.mipmap.main_icon_data_query_select);
        mainCtButtomMenu.addTab(tab2);

        tabCenter = new CustomTabView.Tab().setText(null)
//                .setColor(ContextCompat.getColor(MainActivity.this,R.color.color_gray_999999))
//                .setCheckedColor(ContextCompat.getColor(MainActivity.this,R.color.color_blue_07aef5))
//                .setNormalIcon(R.mipmap.main_icon_voice_default)
//                .setPressedIcon(R.mipmap.main_icon_voice);
        ;
        mainCtButtomMenu.addTab(tabCenter);

        tab3 = new CustomTabView.Tab().setText(getString(R.string.man_text_tab_3))
                .setColor(ContextCompat.getColor(MainActivity.this,R.color.color_gray_999999))
                .setCheckedColor(ContextCompat.getColor(MainActivity.this,R.color.color_blue_07aef5))
                .setNormalIcon(R.mipmap.main_icon_device_manager_default)
                .setPressedIcon(R.mipmap.main_icon_device_manager_select);

        mainCtButtomMenu.addTab(tab3);

        tab4= new CustomTabView.Tab().setText(getString(R.string.man_text_tab_4))
                .setColor(ContextCompat.getColor(MainActivity.this,R.color.color_gray_999999))
                .setCheckedColor(ContextCompat.getColor(MainActivity.this,R.color.color_blue_07aef5))
                .setNormalIcon(R.mipmap.main_icon_mine_defalut)
                .setPressedIcon(R.mipmap.main_icon_mine_select);
        mainCtButtomMenu.addTab(tab4);


        mainCtButtomMenu.setCurrentItem(0);
        mainCtButtomMenu.setOnTabCheckListener(this);
    }

    /**
     * 初始化底部Buttom
     */
    private void initButtomTab() {
        if (fragment1 == null) {
            fragment1 = new MainFragment();
        }
        if (!fragment1.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_rl_content, fragment1).commit();

            // 记录当前Fragment
            currentFragment = fragment1;
        }
    }

    //以下部分为监听Fragment的点击返回事件
    private BaseFragment baseFragment;

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.baseFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
       /* if(baseFragment == null || !baseFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed();
            }else{
                getSupportFragmentManager().popBackStack();
            }
        }*/
        ExitAppUtils.isQuit(MainActivity.this, getString(R.string.toast_msg_app_exit_hint_msg), new ExitAppUtils.ExitAppListener() {
            @Override
            public void removeAllActivity() {
                stopService(new Intent(MainActivity.this,MyNettyPushService.class));
                MainActivity.super.onBackPressed();
                ActivityUtils.removeAllActivity();
                finish();
            }
        });
    }

    @Override
    public void onTabSelected(View v, int position) {
        switch (position){
            case 0:
                ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice_default));
                if(fragment1 == null){
                    fragment1 = new MainFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragment1,MainFragment.class.getSimpleName());
                break;
            case 1:
                ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice_default));
                if(fragment2 == null){
                    fragment2 = new RomFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),fragment2,RomFragment.class.getSimpleName());
                break;
            case 2:
                ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice));
                toastMsg("语音");
                break;
            case 3:
                ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice_default));
                if(fragment3 == null){
                    fragment3 = new DeviceManagerFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),fragment3,MineDataFragment.class.getSimpleName());
                break;
            case 4:
                ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice_default));
                if(fragment4 == null){
                    fragment4 = new MineDataFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(),fragment4,MineDataFragment.class.getSimpleName());
                break;
            default:
                ivVoice.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.mipmap.main_icon_voice_default));
                if(fragment1 == null){
                    fragment1 = new MainFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), fragment1,MainFragment.class.getSimpleName());
                break;
        }
    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment,String tag) {
        if (currentFragment == fragment){ return;}
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.main_rl_content, fragment,tag).commit();
        } else {
//            transaction.hide(currentFragment).show(fragment).commit();
            transaction.hide(currentFragment).show(fragment).commitAllowingStateLoss ();
        }
        currentFragment = fragment;
    }
}
