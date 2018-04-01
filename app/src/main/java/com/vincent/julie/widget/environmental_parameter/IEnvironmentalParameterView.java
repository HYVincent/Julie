package com.vincent.julie.widget.environmental_parameter;

import com.vincent.julie.bean.EvironmentalParameterBean;
import com.vincent.mylibrary.IBaseView;

import java.util.List;

/**
 * @author Administrator QQ:1032006226
 * @version v1.0
 * @name Julie
 * @page com.vincent.julie.widget.environmental_parameter
 * @class describe
 * @date 2018/3/31 13:33
 */
public interface IEnvironmentalParameterView extends IBaseView {

    /**
     * 树新数据
     * @param data
     */
    void refreshData(List<EvironmentalParameterBean> data);

}
