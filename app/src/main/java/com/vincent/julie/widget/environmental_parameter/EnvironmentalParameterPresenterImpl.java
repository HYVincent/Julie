package com.vincent.julie.widget.environmental_parameter;

import com.vincent.julie.bean.EvironmentalParameterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.environmental_parameter
 * @class describe
 * @date 2018/3/31 13:32
 */
public class EnvironmentalParameterPresenterImpl implements IEnvironmentalParameterPresenter {

    private IEnvironmentalParameterView view;

    public EnvironmentalParameterPresenterImpl(IEnvironmentalParameterView view) {
        this.view = view;
    }

    @Override
    public void init() {
        List<EvironmentalParameterBean> data = new ArrayList<>();
        // 温度 0  湿度 2 光照 3 pm25 4 噪音 5
        EvironmentalParameterBean bean1 = new EvironmentalParameterBean();
        bean1.setType(0);
        bean1.setRoomName("客厅");
        bean1.setTemperature(24);

        EvironmentalParameterBean bean2 = new EvironmentalParameterBean();
        bean2.setType(1);
        bean2.setRoomName("客厅");
        bean2.setHumidity(57);

        EvironmentalParameterBean bean3 = new EvironmentalParameterBean();
        bean3.setType(2);
        bean3.setRoomName("客厅");
        bean3.setIllumination(74);

        EvironmentalParameterBean bean4 = new EvironmentalParameterBean();
        bean4.setType(3);
        bean4.setRoomName("客厅");
        bean4.setPm25(24);

        EvironmentalParameterBean bean5 = new EvironmentalParameterBean();
        bean5.setType(4);
        bean5.setRoomName("客厅");
        bean5.setNoise(24);

        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        data.add(bean5);
        view.refreshData(data);

    }



}
