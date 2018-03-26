package com.vincent.julie.widget.frg_main;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.vincent.julie.BuildConfig;
import com.vincent.julie.R;
import com.vincent.julie.base.AppConfig;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.service.MyNettyPushService;
import com.vincent.julie.view.GlideImageLoader;
import com.vincent.julie.widget.frg_main.data.DataFragment;
import com.vincent.julie.widget.frg_main.info.InfoFragment;
import com.vincent.julie.widget.frg_main.scene.SceneFragment;
import com.vincent.mylibrary.adapter.MyFragmentAdapter;
import com.youth.banner.Banner;

import java.text.DateFormat;
import java.util.ArrayList;
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
 * @page com.vincent.julie.widget.frg_main
 * @class describe
 * @date 2018/3/10 23:45
 */

public class MainFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, IFrgInfoServiceView {

    @BindView(R.id.frg_info_service_banner)
    Banner banner;
    @BindView(R.id.frg_main_tv_current_city)
    TextView frgMainTvCurrentCity;
    @BindView(R.id.frg_main_tv_current_weather)
    TextView frgMainTvCurrentWeather;
    @BindView(R.id.frg_mine_iv_weather)
    ImageView frgMineIvWeather;
    @BindView(R.id.frg_main_tv_temperature)
    TextView frgMainTvTemperature;
    @BindView(R.id.frg_min_tv_scene)
    TextView frgMinTvScene;
    @BindView(R.id.frg_min_tv_date)
    TextView frgMinTvDate;
    @BindView(R.id.frg_min_tv_info)
    TextView frgMinTvInfo;
    @BindView(R.id.frg_main_vp_content)
    ViewPager viewPager;
    Unbinder unbinder;
    private View view;
    private static final String TAG = MainFragment.class.getSimpleName();

    private IFrgInfoServicePresenter presenter;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            frgMainTvCurrentCity.setText(aMapLocation.getCity());
//            frgMainTvMyLocation.setText("我在"+aMapLocation.getAddress()+"附近");
//            frgMainTvMyLocation.setVisibility(View.VISIBLE);
//            Log.d(TAG, "onLocationChanged: "+aMapLocation.getAddress());
//            Log.d(TAG, "onLocationChanged: "+ JSON.toJSONString(aMapLocation));
//            Log.d(TAG, "onLocationChanged: "+aMapLocation.getCity());
//            Log.d(TAG, "onLocationChanged: "+aMapLocation.getCountry());
//            Log.d(TAG, "onLocationChanged: "+aMapLocation.getPoiName());
//            Log.d(TAG, "onLocationChanged: "+aMapLocation.getDistrict());
//            Log.d(TAG, "onLocationChanged: "+aMapLocation.getAdCode());
            presenter.getCurrentCityWeather(getContext(), AppConfig.GAODE_WEATHER_WEB_API_KEY, "JSON", aMapLocation.getAdCode());
        }

    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getContext().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        //30 s定位一次
        mLocationOption.setInterval(30 * 1000);
        //获取一次定位结果：
        //该方法默认为false。
//        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
//        mLocationOption.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
    }

    /**
     * 开始定位
     */
    private void startLocation() {
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    //权限请求码
    private static final int REQUEST_LOCATION_CODE = 110;
    //权限数组
    private String[] PERMISSION_DESC = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION};

    /**
     * 请求权限任务
     */
    @AfterPermissionGranted(REQUEST_LOCATION_CODE)
    public void callLocationTask() {
        //检查用户是否已授权
        if (EasyPermissions.hasPermissions(getContext(), PERMISSION_DESC)) {
            //已经授权
            startLocation();
        } else {
            //请求权限
            EasyPermissions.requestPermissions(this,
                    getString(R.string.request_permission_location),//提示用户授权
                    REQUEST_LOCATION_CODE,
                    PERMISSION_DESC);
        }
    }
    //授权的结果回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //通过
        startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //没通过

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frg_layout_main, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        getActivity().startService(new Intent(getActivity(), MyNettyPushService.class));
        presenter = new FrgInfoServicePresenterImpl(this);
        presenter.checkNewVersion(getContext(), BuildConfig.VERSION_CODE);
        initLocation();
        callLocationTask();
        initBanner();
        initViewPage();
        onTabSelected(0);
        return view;
    }

    private List<Fragment> list = new ArrayList<>();
    private MyFragmentAdapter adapter;

    private void initViewPage() {
        SceneFragment localFragment = new SceneFragment();
        DataFragment cloudFragment = new DataFragment();
        InfoFragment diagnoseFragment = new InfoFragment();
        list.add(localFragment);
        list.add(cloudFragment);
        list.add(diagnoseFragment);

        adapter = new MyFragmentAdapter(getChildFragmentManager(), list);
//        mTabWidth = ScreenUtils.getScreenWidth(getContext()) / list.size();
//        mTabWidth = frgHistoryTvCloud.getWidth();

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
//                frgHistoryIvCloud.getLayoutParams();
//        params.width = mTabWidth; // 设置指示线的宽度
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                scrollTabIndicator(position, positionOffset);
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int position) {
                onTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 切换
     *
     * @param position
     */
    private void onTabSelected(int position) {
        switch (position) {
            case 0:
                frgMinTvScene.setTextColor(ContextCompat.getColor(getContext(), R.color.color_white_ffffff));
                frgMinTvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvInfo.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvScene.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_blue));
                frgMinTvDate.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                frgMinTvInfo.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                break;
            case 1:
                frgMinTvScene.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.color_white_ffffff));
                frgMinTvInfo.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvScene.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                frgMinTvDate.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_blue));
                frgMinTvInfo.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                break;
            case 2:
                frgMinTvScene.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvInfo.setTextColor(ContextCompat.getColor(getContext(), R.color.color_white_ffffff));
                frgMinTvScene.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                frgMinTvDate.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                frgMinTvInfo.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_blue));
                break;
            default:
                frgMinTvScene.setTextColor(ContextCompat.getColor(getContext(), R.color.color_white_ffffff));
                frgMinTvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvInfo.setTextColor(ContextCompat.getColor(getContext(), R.color.color_gray_8a8a8a));
                frgMinTvScene.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_blue));
                frgMinTvDate.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                frgMinTvInfo.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.shape_background_frg_mian_menu_gray));
                break;
        }
    }


    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.common_guide_one);
        images.add(R.drawable.common_guide_two);
        images.add(R.drawable.common_guide_three);
        banner.setDelayTime(5 * 1000);
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



    @Override
    public void loadFail() {

    }

    @Override
    public void refreshWeather() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.frg_min_tv_scene, R.id.frg_min_tv_date, R.id.frg_min_tv_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.frg_min_tv_scene:
                viewPager.setCurrentItem(0);
                break;
            case R.id.frg_min_tv_date:
                viewPager.setCurrentItem(1);
                break;
            case R.id.frg_min_tv_info:
                viewPager.setCurrentItem(2);
                break;
            default:
                viewPager.setCurrentItem(0);
                break;
        }
    }
}
