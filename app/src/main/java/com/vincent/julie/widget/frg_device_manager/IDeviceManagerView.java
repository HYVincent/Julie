package com.vincent.julie.widget.frg_device_manager;

import com.vincent.julie.adapter.DeviceListBean;
import com.vincent.mylibrary.IBaseView;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.frg_device_manager
 * @class describe
 * @date 2018/3/26 1:19
 */

public interface IDeviceManagerView extends IBaseView {

    void refresh(List<DeviceListBean> data);

}
