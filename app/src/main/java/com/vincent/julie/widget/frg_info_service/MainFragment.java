package com.vincent.julie.widget.frg_info_service;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.vincent.julie.R;
import com.vincent.julie.base.AppConfig;
import com.vincent.julie.base.BaseFragment;
import com.vincent.julie.base.MyApp;
import com.vincent.julie.netty.NettyPush;
import com.vincent.julie.netty.msg.MsgType;
import com.vincent.julie.netty.msg.PushMsg;
import com.vincent.julie.service.MyNettyPushService;
import com.vincent.julie.widget.frg_mine.IFrgMinePresenter;
import com.vincent.julie.widget.frg_mine.MineDataFragment;
import com.vincent.mylibrary.util.ExitAppUtils;

import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;
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

public class MainFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks,IFrgInfoServiceView{

    private View view;
    private static final String TAG = MainFragment.class.getSimpleName();

    private IFrgInfoServicePresenter presenter;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            Log.d(TAG, "onLocationChanged: "+aMapLocation.getAddress());
            Log.d(TAG, "onLocationChanged: "+ JSON.toJSONString(aMapLocation));
            Log.d(TAG, "onLocationChanged: "+aMapLocation.getCity());
            Log.d(TAG, "onLocationChanged: "+aMapLocation.getCountry());
            Log.d(TAG, "onLocationChanged: "+aMapLocation.getPoiName());
            Log.d(TAG, "onLocationChanged: "+aMapLocation.getDistrict());
            Log.d(TAG, "onLocationChanged: "+aMapLocation.getAdCode());
            presenter.getCurrentCityWeather(getContext(), AppConfig.GAODE_WEATHER_WEB_API_KEY,"JSON",aMapLocation.getAdCode());
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
        if(null != mLocationClient){
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
        mLocationOption.setInterval(30*1000);
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
    private void startLocation(){
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    private static final int REQUEST_LOCATION_CODE = 110;

    private String[] PERMISSION_DESC = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_COARSE_LOCATION};

    @AfterPermissionGranted(REQUEST_LOCATION_CODE)
    public void callLocationTask(){
        if(EasyPermissions.hasPermissions(getContext(), PERMISSION_DESC)){
            startLocation();
        }else {
            EasyPermissions.requestPermissions(this,
                    getString(R.string.request_permission_location),
                    REQUEST_LOCATION_CODE,
                    PERMISSION_DESC);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.frg_layout_main,container,false);
        }
        getActivity().startService(new Intent(getActivity(), MyNettyPushService.class));
        presenter = new FrgInfoServicePresenterImpl(this);
        initLocation();
        callLocationTask();
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

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //通过
        startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //没通过

    }

    @Override
    public void loadFail() {

    }

    @Override
    public void refreshWeather() {

    }
}
