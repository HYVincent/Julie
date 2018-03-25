package com.vincent.julie.widget.frg_device_manager;

import com.vincent.julie.adapter.DeviceListBean;
import com.vincent.julie.bean.DeviceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_device_manager
 * @class describe
 * @date 2018/3/26 1:19
 */

public class DeviceManagerPresenterImpl implements IDeviceManagerPresenter {

    private IDeviceManagerView view;
    private IDeviceManagerModel model;

    public DeviceManagerPresenterImpl(IDeviceManagerView view) {
        this.view = view;
        model = new DeviceManagerModelImpl();
    }


    @Override
    public void getDeviceList() {
//类型  1 灯  2 电源  3 窗帘  4 影音  5 空调  6 遥控器  7 安防
        List<DeviceListBean> data = new ArrayList<>();
        DeviceListBean deviceBean = new DeviceListBean();
        deviceBean.setName("灯光");
        deviceBean.setRunNum(20);
        deviceBean.setRunNum(11);
        deviceBean.setType(1);
        data.add(deviceBean);

        DeviceListBean powDeviceBean = new DeviceListBean();
        powDeviceBean.setName("电源");
        powDeviceBean.setRunNum(4);
        powDeviceBean.setRunNum(1);
        powDeviceBean.setType(2);
        data.add(powDeviceBean);

        DeviceListBean curtainDeviceBean = new DeviceListBean();
        curtainDeviceBean.setName("窗帘");
        curtainDeviceBean.setRunNum(3);
        curtainDeviceBean.setRunNum(2);
        curtainDeviceBean.setType(3);
        data.add(curtainDeviceBean);

        DeviceListBean playDeviceBean = new DeviceListBean();
        playDeviceBean.setName("影音");
        playDeviceBean.setRunNum(1);
        playDeviceBean.setRunNum(0);
        playDeviceBean.setType(4);
        data.add(playDeviceBean);

        DeviceListBean airConditionerdeviceBean = new DeviceListBean();
        airConditionerdeviceBean.setName("空调");
        airConditionerdeviceBean.setRunNum(2);
        airConditionerdeviceBean.setRunNum(1);
        airConditionerdeviceBean.setType(5);
        data.add(airConditionerdeviceBean);

        DeviceListBean controlDeviceBean = new DeviceListBean();
        controlDeviceBean.setName("遥控器");
        controlDeviceBean.setTotalNum(1);
        controlDeviceBean.setRunNum(0);
        controlDeviceBean.setType(6);
        data.add(controlDeviceBean);

        DeviceListBean security = new DeviceListBean();
        security.setName("安防");
        security.setRunNum(3);
        security.setRunNum(3);
        security.setType(7);
        data.add(security);

        view.refresh(data);

    }
}
